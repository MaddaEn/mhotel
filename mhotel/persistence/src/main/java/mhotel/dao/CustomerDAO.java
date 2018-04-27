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

public class CustomerDAO implements BaseDAOInterface<Customer> {
	private final Connection mConnection;
	private final AddressDAO mAddrDAO;

	public CustomerDAO(Connection pConn) {
		mConnection = pConn;
		mAddrDAO = new AddressDAO(pConn);
	}

	/**
	 * Cand se salabeaz se verifi /sd;sfdf'f; s;'df;'fls s';df;'ls;'fd s;'dlf';s
	 */
	@Override
	public Customer insert(Customer pValue) throws SQLException {
		PreparedStatement stmt = null;
		if (pValue.getId() != null) {
			throw new RuntimeException("Object must have null ID for insert");
		}
		try {
			long address_id;
			if (pValue.getAddress().getId() == null) {
				Address addr = mAddrDAO.insert(pValue.getAddress());
				address_id = addr.getId();
			} else {
				mAddrDAO.update(pValue.getAddress());
				address_id = pValue.getId();
			}

			stmt = mConnection.prepareStatement(
					"INSERT INTO HOTEL.CUSTOMER(NAME,SEX,LEGAL_ID,LEGAL_ID_TYPE,ADDRESS_ID) VALUES(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			if (pValue.getName() != null) {
				// if(pValue.getSTreet().length() > 64 ) //
				stmt.setString(1, pValue.getName());
			} else {
				stmt.setNull(1, Types.VARCHAR);
			}
			if (pValue.getSex() != null) {
				stmt.setString(2, pValue.getSex());
			} else {
				stmt.setNull(2, Types.VARCHAR);
			}
			if (pValue.getLegalId() != null) {
				stmt.setString(3, pValue.getLegalId());
			} else {
				stmt.setNull(3, Types.VARCHAR);
			}
			if (pValue.getLegalIdType() != null) {
				stmt.setString(4, pValue.getLegalIdType());
			} else {
				stmt.setNull(4, Types.VARCHAR);
			}
			stmt.setLong(5, address_id);
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
	public Customer update(Customer pValue) throws SQLException {
		PreparedStatement stmt = null;
		if (pValue.getId() == null) {
			throw new RuntimeException("Object must NOT have null ID for update");
		}
		try {
			mAddrDAO.update(pValue.getAddress());
			stmt = mConnection
					.prepareStatement("UPDATE HOTEL.CUSTOMER SET NAME=?,SEX=?,LEGAL_ID=?,LEGAL_ID_TYPE=? WHERE ID=?");
			if (pValue.getName() != null) {
				// if(pValue.getSTreet().length() > 64 ) //
				stmt.setString(1, pValue.getName());
			} else {
				stmt.setNull(1, Types.VARCHAR);
			}
			if (pValue.getSex() != null) {
				stmt.setString(2, pValue.getSex());
			} else {
				stmt.setNull(2, Types.VARCHAR);
			}
			if (pValue.getLegalId() != null) {
				stmt.setString(3, pValue.getLegalId());
			} else {
				stmt.setNull(3, Types.VARCHAR);
			}
			if (pValue.getLegalIdType() != null) {
				stmt.setString(4, pValue.getLegalIdType());
			} else {
				stmt.setNull(4, Types.VARCHAR);
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
	public List<Customer> listAll() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
			try {
			stmt = mConnection.prepareStatement(
					"SELECT ID,NAME,SEX,LEGAL_ID,LEGAL_ID_TYPE, ADDRESS_ID FROM  HOTEL.CUSTOMER ");
			rset = stmt.executeQuery();
			List<Customer> result = new ArrayList<>();
			while (rset.next()) {
				Customer cust = new Customer();
				cust.setId(rset.getLong(1));
				cust.setName(rset.getString(2));
				cust.setSex(rset.getString(3));
				cust.setLegalId(rset.getString(4));
				cust.setLegalIdType(rset.getString(5));
				cust.setAddress(mAddrDAO.loadById(rset.getLong(6)));
				result.add(cust);
			
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
	public Customer loadById(Long pId) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		if (pId == null) {
			throw new RuntimeException("loadById - NULL pID");
		}
		try {
			stmt = mConnection.prepareStatement(
					"SELECT ID,NAME,SEX,LEGAL_ID,LEGAL_ID_TYPE, ADDRESS_ID FROM  HOTEL.CUSTOMER WHERE ID=?");
			stmt.setLong(1, pId);
			rset = stmt.executeQuery();
			if (rset.next()) {
				Customer cust = new Customer();
				cust.setId(rset.getLong(1));
				cust.setName(rset.getString(2));
				cust.setSex(rset.getString(3));
				cust.setLegalId(rset.getString(4));
				cust.setLegalIdType(rset.getString(5));
				cust.setAddress(mAddrDAO.loadById(rset.getLong(6)));
				return cust;
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
