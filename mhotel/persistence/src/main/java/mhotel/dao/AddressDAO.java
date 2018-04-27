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

public class AddressDAO implements BaseDAOInterface<Address> {
	private final Connection mConnection;

	public AddressDAO(Connection pConn) {
		mConnection = pConn;
	}

	@Override
	public Address insert(Address pValue) throws SQLException {
		PreparedStatement stmt = null;
		if(pValue.getId() != null) {
			throw new RuntimeException("Object must have null ID for insert");
		}
		try {
			stmt = mConnection.prepareStatement(
					"INSERT INTO HOTEL.ADDRESS(STREET,ADDRESS_NUMBER,CITY,COUNTRY,ZIP_CODE) VALUES(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			if (pValue.getStreet() != null) {
				// if(pValue.getSTreet().length() > 64 ) //
				stmt.setString(1, pValue.getStreet());
			} else {
				stmt.setNull(1, Types.VARCHAR);
			}
			if (pValue.getNumber() != null) {
				stmt.setString(2, pValue.getNumber());
			} else {
				stmt.setNull(2, Types.VARCHAR);
			}
			if (pValue.getCity() != null) {
				stmt.setString(3, pValue.getCity());
			} else {
				stmt.setNull(3, Types.VARCHAR);
			}
			if (pValue.getCountry() != null) {
				stmt.setString(4, pValue.getCountry());
			} else {
				stmt.setNull(4, Types.VARCHAR);
			}
			if (pValue.getPostalCode() != null) {
				stmt.setString(5, pValue.getPostalCode());
			} else {
				stmt.setNull(5, Types.VARCHAR);
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
	public Address update(Address pValue) throws SQLException {
		PreparedStatement stmt = null;
		if(pValue.getId() == null) {
			throw new RuntimeException("Object must NOT have null ID for update");
		}
		try {
			stmt = mConnection.prepareStatement(
					"UPDATE HOTEL.ADDRESS SET STREET=?,ADDRESS_NUMBER=?,CITY=?,COUNTRY=?,ZIP_CODE=? WHERE ID=?");
			if (pValue.getStreet() != null) {
				// if(pValue.getSTreet().length() > 64 ) //
				stmt.setString(1, pValue.getStreet());
			} else {
				stmt.setNull(1, Types.VARCHAR);
			}
			if (pValue.getNumber() != null) {
				stmt.setString(2, pValue.getNumber());
			} else {
				stmt.setNull(2, Types.VARCHAR);
			}
			if (pValue.getCity() != null) {
				stmt.setString(3, pValue.getCity());
			} else {
				stmt.setNull(3, Types.VARCHAR);
			}
			if (pValue.getCountry() != null) {
				stmt.setString(4, pValue.getCountry());
			} else {
				stmt.setNull(4, Types.VARCHAR);
			}
			if (pValue.getPostalCode() != null) {
				stmt.setString(5, pValue.getPostalCode());
			} else {
				stmt.setNull(5, Types.VARCHAR);
			}
			stmt.setLong(6, pValue.getId());
			int rc = stmt.executeUpdate();
			return loadById(pValue.getId());
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	@Override
	public List<Address> listAll() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			stmt = mConnection.prepareStatement(
					"SELECT ID,STREET,ADDRESS_NUMBER,CITY,COUNTRY, ZIP_CODE FROM  HOTEL.ADDRESS ");
			rset = stmt.executeQuery();
			List<Address> adresses = new ArrayList<>();
			while(rset.next()) {
				Address addr = new Address();
				addr.setId(rset.getLong(1));
				addr.setStreet(rset.getString(2));
				addr.setNumber(rset.getString(3));
				addr.setCity(rset.getString(4));
				addr.setCountry(rset.getString(5));
				addr.setPostalCode(rset.getString(6));
				adresses.add(addr);
			}
			return adresses;
		} finally {
			if(rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	@Override
	public Address loadById(Long pId) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		if(pId == null) {
			throw new RuntimeException("loadById - NULL pID");
		}
		try {
			stmt = mConnection.prepareStatement(
					"SELECT ID,STREET,ADDRESS_NUMBER,CITY,COUNTRY, ZIP_CODE FROM  HOTEL.ADDRESS WHERE ID=?");
			stmt.setLong(1, pId);
			rset = stmt.executeQuery();
			if(rset.next()) {
				Address addr = new Address();
				addr.setId(rset.getLong(1));
				addr.setStreet(rset.getString(2));
				addr.setNumber(rset.getString(3));
				addr.setCity(rset.getString(4));
				addr.setCountry(rset.getString(5));
				addr.setPostalCode(rset.getString(6));
				return addr;
			} else {
				return null;
			}
			
		} finally {
			if(rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
	}

}
