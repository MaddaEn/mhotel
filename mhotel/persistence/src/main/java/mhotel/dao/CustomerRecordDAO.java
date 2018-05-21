package mhotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mhotel.model.Address;
import mhotel.model.CustomerRecord;

public class CustomerRecordDAO implements BaseDAOInterface<CustomerRecord> {
	private final Connection mConnection;
	private final CustomerDAO mCustomerDAO;
	private final RoomDAO mRoomDAO;

	public CustomerRecordDAO(Connection pConn) {
		mConnection = pConn;
		mCustomerDAO = new CustomerDAO(mConnection);
		mRoomDAO = new RoomDAO(mConnection);
	}

	@Override
	public CustomerRecord insert(CustomerRecord pValue) throws SQLException {
		PreparedStatement stmt = null;
		if (pValue.getId() != null) {
			throw new RuntimeException("Object must have null ID for insert");
		}
		try {
			stmt = mConnection.prepareStatement(
					"INSERT INTO HOTEL.CUSTOMER_RECORD(CUSTOMER_ID,ROOM_ID,CHECKED_IN) VALUES(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, pValue.getCustomer().getId());
			stmt.setLong(2, pValue.getRoom().getId());
			stmt.setDate(3, new java.sql.Date(pValue.getCheckInDate().getTime()));

			int rc = stmt.executeUpdate();
			if (rc == 1) {
				ResultSet rset = stmt.getGeneratedKeys();
				if (rset.next()) {
					long id = rset.getLong(1);
					rset.close();
					return loadById(id); // PE ACI AR TREBUI IESIT NORMAL
				} else {
					rset.close();
				}
			}
			return pValue;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public CustomerRecord insert(long pCustomerId, long pRoomId, java.util.Date pCheckInDate) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = mConnection.prepareStatement(
					"INSERT INTO HOTEL.CUSTOMER_RECORD(CUSTOMER_ID,ROOM_ID,CHECKED_IN) VALUES(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, pCustomerId);
			stmt.setLong(2, pRoomId);
			stmt.setDate(3, new java.sql.Date(pCheckInDate.getTime()));

			int rc = stmt.executeUpdate();
			if (rc == 1) {
				ResultSet rset = stmt.getGeneratedKeys();
				if (rset.next()) {
					long id = rset.getLong(1);
					rset.close();
					return loadById(id); // PE ACI AR TREBUI IESIT NORMAL
				} else {
					rset.close();
				}
			}
			return null;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	@Override
	public CustomerRecord update(CustomerRecord pValue) throws SQLException {
		PreparedStatement stmt = null;
		if (pValue.getId() == null) {
			throw new RuntimeException("Object must NOT have null ID for update");
		}
		try {
			stmt = mConnection.prepareStatement(
					"UPDATE HOTEL.CUSTOMER_RECORD SET ROOM_ID=?,CUSTOMER_ID=?,CHECKED_IN=?,CHECKED_OUT=? WHERE ID=?");
			stmt.setLong(2, pValue.getCustomer().getId());
			stmt.setLong(1, pValue.getRoom().getId());
			stmt.setDate(3, new java.sql.Date(pValue.getCheckInDate().getTime()));
			if (pValue.getCheckOutDate() != null) {
				stmt.setDate(4, new java.sql.Date(pValue.getCheckOutDate().getTime()));
			} else {
				stmt.setNull(2, Types.DATE);
			}

			stmt.setLong(5, pValue.getId());
			int rc = stmt.executeUpdate();
			return loadById(pValue.getId());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public void checkout(long pId, Date pCheckoutDate) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = mConnection.prepareStatement("UPDATE HOTEL.CUSTOMER_RECORD SET CHECKED_OUT=? WHERE ID=?");
			stmt.setDate(1, new java.sql.Date(pCheckoutDate.getTime()));
			stmt.setLong(2, pId);
			int rc = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	@Override
	public List<CustomerRecord> listAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerRecord loadById(Long pId) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		if (pId == null) {
			throw new RuntimeException("loadById - NULL pID");
		}
		try {
			stmt = mConnection.prepareStatement(
					"SELECT ID,CUSTOMER_ID,ROOM_ID,CHECKED_IN,CHECKED_OUT FROM  HOTEL.CUSTOMER_RECORD WHERE ID=?");
			stmt.setLong(1, pId);
			rset = stmt.executeQuery();
			if (rset.next()) {
				CustomerRecord cr = new CustomerRecord();
				cr.setId(rset.getLong(1));
				cr.setCustomer(mCustomerDAO.loadById(rset.getLong(2)));
				cr.setRoom(mRoomDAO.loadById(rset.getLong(3)));
				cr.setCheckInDate(rset.getDate(4));
				cr.setCheckOutDate(rset.getDate(5));

				return cr;
			} else {
				return null;
			}

		} finally {
			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public List<CustomerRecord> getActiveCheckIns(long pCustomerId) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			stmt = mConnection.prepareStatement(
					"SELECT ID,CUSTOMER_ID,ROOM_ID,CHECKED_IN,CHECKED_OUT FROM  HOTEL.CUSTOMER_RECORD WHERE CUSTOMER_ID=? AND CHECKED_OUT IS NULL");
			stmt.setLong(1, pCustomerId);
			List<CustomerRecord> records = new ArrayList<>();
			rset = stmt.executeQuery();
			while (rset.next()) {
				CustomerRecord cr = new CustomerRecord();
				cr.setId(rset.getLong(1));
				cr.setCustomer(mCustomerDAO.loadById(rset.getLong(2)));
				cr.setRoom(mRoomDAO.loadById(rset.getLong(3)));
				cr.setCheckInDate(rset.getDate(4));
				cr.setCheckOutDate(rset.getDate(5));

				records.add(cr);

			}
			return records;
		} finally {
			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public List<CustomerRecord> getAllActiveCheckIns() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;

		try {
			stmt = mConnection.prepareStatement(
					"SELECT ID,CUSTOMER_ID,ROOM_ID,CHECKED_IN,CHECKED_OUT FROM  HOTEL.CUSTOMER_RECORD WHERE CHECKED_OUT IS NULL");
			List<CustomerRecord> records = new ArrayList<>();
			rset = stmt.executeQuery();
			while (rset.next()) {
				CustomerRecord cr = new CustomerRecord();
				cr.setId(rset.getLong(1));
				cr.setCustomer(mCustomerDAO.loadById(rset.getLong(2)));
				cr.setRoom(mRoomDAO.loadFullById(rset.getLong(3)));
				cr.setCheckInDate(rset.getDate(4));
				cr.setCheckOutDate(rset.getDate(5));
				records.add(cr);

			}
			return records;
		} finally {
			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
	}

}
