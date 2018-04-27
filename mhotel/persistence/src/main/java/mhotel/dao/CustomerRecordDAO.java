package mhotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import mhotel.model.Address;
import mhotel.model.Customer;
import mhotel.model.CustomerRecord;
import mhotel.model.Room;

public class CustomerRecordDAO implements BaseDAOInterface<CustomerRecord> {

	// ME

	private final Connection mConnection;
	private final CustomerDAO mCustDAO;
	private final RoomDAO mRoomDAO;

	public CustomerRecordDAO(Connection mConnection, CustomerDAO custDAO, RoomDAO roomDAO) {
		super();
		this.mConnection = mConnection;
		this.mCustDAO = custDAO;
		this.mRoomDAO = roomDAO;
	}

	@Override
	public CustomerRecord insert(CustomerRecord pValue) throws SQLException {
		PreparedStatement stmt = null;
		if (pValue.getId() != null) {
			throw new RuntimeException("Object must have null ID for insert");
		}
		try {
			long cust_id;
			if (pValue.getCustomer().getId() == null) {
				Customer cust = mCustDAO.insert(pValue.getCustomer());
				cust_id = cust.getId();
			} else {
				mCustDAO.update(pValue.getCustomer());
				cust_id = pValue.getId();
			}

			long room_id;
			if (pValue.getRoom().getId() == null) {
				Room ro = mRoomDAO.insert(pValue.getRoom());
				room_id = ro.getId();
			} else {
				mRoomDAO.update(pValue.getRoom());
				room_id = pValue.getId();
			}

			stmt = mConnection.prepareStatement(
					"INSERT INTO HOTEL.CUSTOMER_RECORD(CUSTOMER_ID,ROOM_ID,CHECKED_IN,CHECKED_OUT) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			stmt.setLong(1, cust_id);
			stmt.setLong(2, room_id);
			stmt.setDate(3, pValue.getCheckInDate());

			if (pValue.getCheckOutDate() != null) {
				stmt.setDate(4, pValue.getCheckOutDate());
			} else {
				stmt.setNull(4, Types.TIMESTAMP);
			}

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

	@Override
	public CustomerRecord update(CustomerRecord pValue) throws SQLException {
		PreparedStatement stmt = null;
		if (pValue.getId() == null) {
			throw new RuntimeException("Object must NOT have null ID for update");
		}
		try {
			stmt = mConnection.prepareStatement(
					"UPDATE HOTEL.CUSTOMER_RECORD SET CUSTOMER_ID=?,ROOM_ID=?,CHECKED_IN=?,CHECKED_OUT=? WHERE ID=?");
			if (pValue.getCustomer().getId() != null) {
				// if(pValue.getSTreet().length() > 64 ) //
				stmt.setLong(1, pValue.getCustomer().getId());
			} else {
				stmt.setNull(1, Types.INTEGER);
			}
			if (pValue.getRoom().getId() != null) {
				stmt.setLong(2, pValue.getRoom().getId());
			} else {
				stmt.setNull(2, Types.INTEGER);
			}
			if (pValue.getCheckInDate() != null) {
				stmt.setDate(3, pValue.getCheckInDate());
			} else {
				stmt.setNull(3, Types.TIMESTAMP);
			}
			if (pValue.getCheckOutDate() != null) {
				stmt.setDate(4, pValue.getCheckOutDate());
			} else {
				stmt.setNull(4, Types.TIMESTAMP);
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

	@Override
	public List<CustomerRecord> listAll() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		try {
			stmt = mConnection.prepareStatement(
					"SELECT ID,CUSTOMER_ID,ROOM_ID,CHECKED_IN,CHECKED_OUT FROM  HOTEL.CUSTOMER_RECORD ");
			rset = stmt.executeQuery();
			List<CustomerRecord> result = new ArrayList<>();
			while (rset.next()) {
				CustomerRecord custRec = new CustomerRecord();
				custRec.setId(rset.getLong(1));
				custRec.setCustomer(mCustDAO.loadById(rset.getLong(2)));
				custRec.setRoom(mRoomDAO.loadById(rset.getLong(3)));
				custRec.setCheckInDate(rset.getDate(4));
				custRec.setCheckOutDate(rset.getDate(5));

				result.add(custRec);

			}
			return result;
		} finally {
			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
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
				CustomerRecord custRec = new CustomerRecord();
				custRec.setId(rset.getLong(1));
				custRec.setCustomer(mCustDAO.loadById(rset.getLong(2)));
				custRec.setRoom(mRoomDAO.loadById(rset.getLong(3)));
				custRec.setCheckInDate(rset.getDate(4));
				custRec.setCheckOutDate(rset.getDate(5));
				return custRec;
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
}
