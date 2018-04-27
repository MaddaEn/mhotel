package mhotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


import mhotel.model.Hotel;
import mhotel.model.Room;

public class RoomDAO implements BaseDAOInterface<Room> {
	private final Connection mConnection;

	public RoomDAO(Connection pConn) {
		mConnection = pConn;
	}

	@Override
	public Room insert(Room pValue) throws SQLException {
		PreparedStatement stmt = null;
		if (pValue.getId() != null) {
			throw new RuntimeException("Object must have null ID for insert");
		}
		try {
			stmt = mConnection.prepareStatement(
					"INSERT INTO HOTEL.ROOM(FLOOR_NBR,ROOM_NBR,BED_NBR,AVAILABLE_FOR_RENT,HOTEL_ID) VALUES(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, pValue.getFloor());

			if (pValue.getNumber() != null) {
				stmt.setString(2, pValue.getNumber());
			} else {
				stmt.setNull(2, Types.VARCHAR);
			}
			stmt.setInt(3, pValue.getNumberOfBeds());
			stmt.setBoolean(4, pValue.isAvailableForRent());
			stmt.setLong(5, pValue.getHotel().getId());
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

	/**
	 * Nu actualizam HOTEL_ID (hotelul NU se schimba)
	 */
	@Override
	public Room update(Room pValue) throws SQLException {
		PreparedStatement stmt = null;
		if (pValue.getId() == null) {
			throw new RuntimeException("Object must NOT have null ID for update");
		}
		try {
			stmt = mConnection.prepareStatement(
					"UPDATE HOTEL.ROOM SET FLOOR_NBR=?,ROOM_NBR=?,BED_NBR=?,AVAILABLE_FOR_RENT=? WHERE ID=?");

			stmt.setInt(1, pValue.getFloor());

			if (pValue.getNumber() != null) {
				stmt.setString(2, pValue.getNumber());
			} else {
				stmt.setNull(2, Types.VARCHAR);
			}
			stmt.setInt(3, pValue.getNumberOfBeds());

			stmt.setBoolean(4, pValue.isAvailableForRent());

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
	public List<Room> listAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Room> findRoomsForHotel(Hotel pHotel) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		
		try {
			stmt = mConnection.prepareStatement(
					"SELECT ID,FLOOR_NBR,ROOM_NBR,BED_NBR,AVAILABLE_FOR_RENT, HOTEL_ID FROM  HOTEL.ROOM WHERE HOTEL_ID=?");
			stmt.setLong(1, pHotel.getId());
			rset = stmt.executeQuery();
			List<Room> roomList = new ArrayList<>();
			while (rset.next()) {
				Room room = new Room();
				room.setId(rset.getLong(1));
				room.setFloor(rset.getInt(2));
				room.setNumber(rset.getString(3));
				room.setNumberOfBeds(rset.getInt(4));
				room.setAvailableForRent(rset.getBoolean(5));
				roomList.add(room);
		
			}
			for(Room r : roomList) {
				r.setHotel(pHotel);
			}
			return roomList;

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
	 * ATENTIE - HOTEL reference is NULL after loadById
	 */
	@Override
	public Room loadById(Long pId) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rset = null;
		if (pId == null) {
			throw new RuntimeException("loadById - NULL pID");
		}
		try {
			stmt = mConnection.prepareStatement(
					"SELECT ID,FLOOR_NBR,ROOM_NBR,BED_NBR,AVAILABLE_FOR_RENT, HOTEL_ID FROM  HOTEL.ROOM WHERE ID=?");
			stmt.setLong(1, pId);
			rset = stmt.executeQuery();
			if (rset.next()) {
				Room room = new Room();
				room.setId(rset.getLong(1));
				room.setFloor(rset.getInt(2));
				room.setNumber(rset.getString(3));
				room.setNumberOfBeds(rset.getInt(4));
				room.setAvailableForRent(rset.getBoolean(5));
				// cust.setAddress(mAddrDAO.loadById(rset.getLong(6)));
				return room;
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
