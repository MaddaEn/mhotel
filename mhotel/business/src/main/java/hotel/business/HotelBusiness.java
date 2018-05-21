package hotel.business;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import mhotel.DatasourceUtils;
import mhotel.dao.CustomerRecordDAO;
import mhotel.dao.HotelDAO;
import mhotel.dao.RoomDAO;
import mhotel.model.Address;
import mhotel.model.CustomerRecord;
import mhotel.model.Hotel;
import mhotel.model.Room;

public class HotelBusiness {
	private static final Logger __logger = Logger.getLogger("hotel.business.HotelBusiness");

	public List<Hotel> getAllHotels() throws Exception {
		if (__logger.isLoggable(Level.INFO))
			__logger.log(Level.INFO, "getAllHotels entered");
		try {
			DataSource ds = DatasourceUtils.getDataSource();
			if (__logger.isLoggable(Level.FINE))
				__logger.log(Level.FINE, "got datasource" + ds.toString());
			Connection connection = null;
			List<Hotel> hotelList = null;
			try {
				connection = ds.getConnection();
				HotelDAO hotelDAO = new HotelDAO(connection);
				hotelList = hotelDAO.listAll();
				return hotelList;
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			__logger.log(Level.WARNING, "getAllHotels", e);
			throw e;
		}
	}

	public Hotel addHotelService(String nume, String rating, String addCity, String addCountry, String addNumber,
			String addCP, String addStreet) throws Exception {
		try {
			DataSource ds = DatasourceUtils.getDataSource();
			Connection connection = null;
			try {
				connection = ds.getConnection();
				connection.setAutoCommit(false);
				Hotel hotel = new Hotel();
				hotel.setName(nume);
				hotel.setRating(Integer.parseInt(rating));

				Address addr = new Address();
				addr.setCity(addCity);
				addr.setCountry(addCountry);
				addr.setNumber(addNumber);
				addr.setPostalCode(addCP);
				addr.setStreet(addStreet);
				hotel.setAddress(addr);
				hotel.setRooms(new ArrayList<Room>());
				HotelDAO hotelDAO = new HotelDAO(connection);
				Hotel h = hotelDAO.insert(hotel);
				connection.commit();
				return h;
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			__logger.log(Level.WARNING, "addHotel", e);
			throw e;
		}
	}

	public List<CustomerRecord> getAllActiveCheckInsService() throws Exception {
		if (__logger.isLoggable(Level.INFO))
			__logger.log(Level.INFO, "getAllHotels entered");
		try {
			DataSource ds = DatasourceUtils.getDataSource();
			Connection connection = null;
			List<CustomerRecord> customerRecords = null;
			try {
				connection = ds.getConnection();

				CustomerRecordDAO customerRecordDAO = new CustomerRecordDAO(connection);
				return customerRecords = customerRecordDAO.getAllActiveCheckIns();
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			__logger.log(Level.WARNING, "getAllHotels", e);
			throw e;
		}
	}

	public List<CustomerRecord> getActiveCheckInsById(Long customerId) throws Exception {
		if (__logger.isLoggable(Level.INFO))
			__logger.log(Level.INFO, "getAllHotels entered");
		try {
			DataSource ds = DatasourceUtils.getDataSource();
			Connection connection = null;
		
			List<CustomerRecord> customerRecords = null;
			try {
				connection = ds.getConnection();
			
				CustomerRecordDAO customerRecordDAO = new CustomerRecordDAO(connection);
				return customerRecords = customerRecordDAO.getActiveCheckIns(customerId);
			} finally {
				if (connection != null) {
					connection.close();
				}
			}

		} catch (Exception e) {
			__logger.log(Level.WARNING, "getAllHotels", e);
			throw e;
		}
	}
}
