package hotel.business;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import mhotel.DatasourceUtils;
import mhotel.dao.CustomerDAO;
import mhotel.dao.HotelDAO;
import mhotel.dao.RoomDAO;
import mhotel.model.Customer;
import mhotel.model.Hotel;
import mhotel.model.Room;

public class RoomService {

	private static final Logger __logger = Logger.getLogger("hotel.business.RoomService");

	public List<Room> listAllFreeRooms() throws Exception {
		if (__logger.isLoggable(Level.INFO))
			__logger.log(Level.INFO, "listAllFreeRooms listed");
		try {
			DataSource ds = DatasourceUtils.getDataSource();
			Connection connection = null;
			List<Room> freeRoomsList = null;
			try {
				connection = ds.getConnection();

				RoomDAO RoomDAO = new RoomDAO(connection);
				return freeRoomsList = RoomDAO.listAllFree();

			} finally {
				if (connection != null) {
					connection.close();
				}
			}

		} catch (Exception e) {
			__logger.log(Level.WARNING, "listAllFreeRooms", e);
			throw e;
		}
	}

	public Room addRoom(String hotelId, String numar, String etaj, String paturi) throws Exception {
		try {
			DataSource ds = DatasourceUtils.getDataSource();
			Connection connection = null;
			try {
				connection = ds.getConnection();
				connection.setAutoCommit(false);
				HotelDAO hotelDAO = new HotelDAO(connection);
				Hotel hotel = hotelDAO.loadById(Long.parseLong(hotelId));
				Room room = new Room();
				room.setNumber(numar);
				room.setFloor(Integer.parseInt(etaj));
				room.setNumberOfBeds(Integer.parseInt(paturi));
				room.setAvailableForRent(true);
				room.setHotel(hotel);

				RoomDAO roomDAO = new RoomDAO(connection);

				Room rm = roomDAO.insert(room);
				connection.commit();
				return rm;

			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			__logger.log(Level.WARNING, "addRoom", e);
			throw e;
		}
	}

	public List<Room> listAllOccupiedRooms() throws Exception {
		if (__logger.isLoggable(Level.INFO))
			__logger.log(Level.INFO, "listAllOccupiedRooms listed");
		try {
			DataSource ds = DatasourceUtils.getDataSource();
			Connection connection = null;
			List<Room> occupiedRoomsList = null;
			try {
				connection = ds.getConnection();

				RoomDAO RoomDAO = new RoomDAO(connection);
				return occupiedRoomsList = RoomDAO.listAllOccupied();

			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			__logger.log(Level.WARNING, "listAllOccupiedRooms", e);
			throw e;
		}
	}
}
