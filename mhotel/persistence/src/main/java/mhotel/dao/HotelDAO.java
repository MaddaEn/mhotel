package mhotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import mhotel.model.Customer;
import mhotel.model.Hotel;
import mhotel.model.Room;

public class HotelDAO implements BaseDAOInterface<Hotel> {
	private final Connection mConnection;
	private final RoomDAO mRoomDAO;
	private final AddressDAO mAddressDAO;

	public HotelDAO(Connection pConn) {
		mConnection = pConn;
		mRoomDAO = new RoomDAO(mConnection);
		mAddressDAO = new AddressDAO(mConnection);
	}
	
	protected HotelDAO(Connection pConn, RoomDAO pRoomDAO) {
		mConnection = pConn;
		mRoomDAO = pRoomDAO;
		mAddressDAO = new AddressDAO(mConnection);
	}

	@Override
	public Hotel insert(Hotel pValue) throws SQLException {
		PreparedStatement stmt = null;
		if (pValue.getId() != null) {
			throw new RuntimeException("Object must have null ID for insert");
		}
		try {
			if (pValue.getAddress().getId() == null) {
				pValue.setAddress(mAddressDAO.insert(pValue.getAddress()));
			} else {
				pValue.setAddress(mAddressDAO.update(pValue.getAddress()));
			}

			stmt = mConnection.prepareStatement("INSERT INTO HOTEL.HOTEL(NAME,RATING,ADDRESS_ID) VALUES(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			if (pValue.getName() != null) {
				stmt.setString(1, pValue.getName());
			} else {
				stmt.setNull(1, Types.VARCHAR);
			}
			stmt.setInt(2, pValue.getRating());
			stmt.setLong(3, pValue.getAddress().getId());

			int rc = stmt.executeUpdate();
			if (rc == 1) {
				ResultSet rset = stmt.getGeneratedKeys();
				if (rset.next()) {
					long id = rset.getLong(1);
					rset.close();
					for (Room r : pValue.getRooms()) {
						// DE CE ?????????
						pValue.setId(id);
						r.setHotel(pValue);
						if (r.getId() == null) {
							mRoomDAO.insert(r);
						} else {
							mRoomDAO.update(r);
						}
					}

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
	public Hotel update(Hotel pValue) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Hotel> listAll() throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		List<Hotel> resultList = new ArrayList<>();
		try {
			stmt = mConnection.prepareStatement("SELECT ID,NAME,RATING, ADDRESS_ID FROM  HOTEL.HOTEL");
			rset = stmt.executeQuery();
			while (rset.next()) {
				Hotel hotel = new Hotel();
				hotel.setId(rset.getLong(1));
				hotel.setName(rset.getString(2));
				hotel.setRating(rset.getInt(3));
				hotel.setAddress(mAddressDAO.loadById(rset.getLong(4)));
				hotel.setRooms(mRoomDAO.findRoomsForHotel(hotel));
				resultList.add(hotel);
			
			}
			return resultList;
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
	public Hotel loadById(Long pId) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		if (pId == null) {
			throw new RuntimeException("loadById - NULL pID");
		}
		try {
			stmt = mConnection.prepareStatement("SELECT ID,NAME,RATING, ADDRESS_ID FROM  HOTEL.HOTEL WHERE ID=?");
			stmt.setLong(1, pId);
			rset = stmt.executeQuery();
			if (rset.next()) {
				Hotel hotel = new Hotel();
				hotel.setId(rset.getLong(1));
				hotel.setName(rset.getString(2));
				hotel.setRating(rset.getInt(3));
				hotel.setAddress(mAddressDAO.loadById(rset.getLong(4)));
				hotel.setRooms(mRoomDAO.findRoomsForHotel(hotel));
				return hotel;
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

	/**
	 * Load the hotel without loading rooms
	 * @param pId
	 * @return
	 * @throws SQLException
	 */
	public Hotel loadByIdNoRooms(Long pId) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		if (pId == null) {
			throw new RuntimeException("loadById - NULL pID");
		}
		try {
			stmt = mConnection.prepareStatement("SELECT ID,NAME,RATING, ADDRESS_ID FROM  HOTEL.HOTEL WHERE ID=?");
			stmt.setLong(1, pId);
			rset = stmt.executeQuery();
			if (rset.next()) {
				Hotel hotel = new Hotel();
				hotel.setId(rset.getLong(1));
				hotel.setName(rset.getString(2));
				hotel.setRating(rset.getInt(3));
				hotel.setAddress(mAddressDAO.loadById(rset.getLong(4)));
				//hotel.setRooms(mRoomDAO.findRoomsForHotel(hotel));
				return hotel;
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
