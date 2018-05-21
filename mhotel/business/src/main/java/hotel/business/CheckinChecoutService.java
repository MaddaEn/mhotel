package hotel.business;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import mhotel.DatasourceUtils;

import mhotel.dao.CustomerRecordDAO;
import mhotel.dao.RoomDAO;
import mhotel.model.CustomerRecord;
import mhotel.model.Hotel;
import mhotel.model.Room;

public class CheckinChecoutService {
	private static final Logger __logger = Logger.getLogger("hotel.business.CheckinChecoutService");

	public void chekIn(Long custId, Long roomId) throws Exception {
		if (__logger.isLoggable(Level.INFO))
			__logger.log(Level.INFO, "chekIn entered");
		try {
			DataSource ds = DatasourceUtils.getDataSource();
			if (__logger.isLoggable(Level.FINE))
				__logger.log(Level.FINE, "got datasource" + ds.toString());
			Connection connection = null;
			List<Hotel> hotelList = null;
			try {
				connection = ds.getConnection();
				connection.setAutoCommit(false);
				CustomerRecordDAO crDAO = new CustomerRecordDAO(connection);
				crDAO.insert(custId, roomId, new Date());
				connection.commit();

			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			__logger.log(Level.WARNING, "chekIn", e);
			throw e;
		}
	}

	public void chekOut(Long custRecordId) throws Exception {
		if (__logger.isLoggable(Level.INFO))
			__logger.log(Level.INFO, "chekOut entered");
		try {
			DataSource ds = DatasourceUtils.getDataSource();
			if (__logger.isLoggable(Level.FINE))
				__logger.log(Level.FINE, "got datasource" + ds.toString());
			Connection connection = null;
			List<Hotel> hotelList = null;
			try {
				connection = ds.getConnection();
				connection.setAutoCommit(false);
				CustomerRecordDAO crDAO = new CustomerRecordDAO(connection);
				crDAO.checkout(custRecordId, new Date());
				connection.commit();

			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			__logger.log(Level.WARNING, "chekOut", e);
			throw e;
		}
	}

}
