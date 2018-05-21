package mhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

import mhotel.dao.AddressDAO;
import mhotel.dao.CustomerDAO;
import mhotel.dao.HotelDAO;
import mhotel.dao.RoomDAO;
import mhotel.model.Address;
import mhotel.model.Customer;
import mhotel.model.Hotel;
import mhotel.model.Room;

public class Main {
	public static void main(String[] args) throws SQLException {
		String jdbcURL = "jdbc:h2:C:\\Users\\Mada\\hotel.mv.db;AUTO_SERVER=TRUE;";
		// String jdbcURLPostgresql = jdbc:postgresql://host:port/database
		String userName = "xx";
		String userPasswd = "xx";
		
		// DE ACI NU MAI STIU CE BAZA E
		Connection conn = DriverManager.getConnection(jdbcURL, userName, userPasswd); 
		conn.setAutoCommit(false);
		AddressDAO addrDAO = new AddressDAO(conn);
		Address addr = new Address();
		addr.setCity("Bucharest");
		addr.setCountry("RO");
		addr.setNumber("14");
		addr.setStreet("Banu andronache");
		System.out.println("Inainte de insert:" + addr);
		addr = addrDAO.insert(addr);
		System.out.println("Dupa insert:" + addr);
		
		addr = new Address();
		addr.setCity("Bucharest");
		addr.setCountry("RO");
		addr.setNumber("19");
		addr.setStreet("Banu andronache");
		System.out.println("Inainte de insert:" + addr);
		addr = addrDAO.insert(addr);
		System.out.println("Dupa insert:" + addr);
		conn.commit();
		System.out.println(addrDAO.loadById(1L));
		System.out.println(addrDAO.listAll());
		
		conn.setAutoCommit(false);
		addr = new Address();
		addr.setCity("BUCURESTI");
		addr.setCountry("RO");
		addr.setNumber("99");
		addr.setStreet("Banu andronache");
		Customer cust = new Customer();
		cust.setAddress(addr);
		cust.setLegalId("112233");
		cust.setLegalIdType("BI");
		cust.setName("Vasile");
		cust.setSex("M");
		CustomerDAO cdao = new CustomerDAO(conn);
		cust = cdao.insert(cust);
		System.out.println(cust);
		conn.commit();
		
		
		Address hotelAddress = new Address();
		hotelAddress.setCity("Brasov");
		hotelAddress.setCountry("RO");
		hotelAddress.setNumber("33BIS");
		hotelAddress.setPostalCode("1234567");
		hotelAddress.setStreet("Strada Ingusta");
		Room room1 = new Room();
		room1.setAvailableForRent(true);
		room1.setFloor(1);
		room1.setNumber("10");
		room1.setNumberOfBeds(2);
		Room room2 = new Room();
		room2.setAvailableForRent(true);
		room2.setFloor(2);
		room2.setNumber("21");
		room2.setNumberOfBeds(1);
		Room room3 = new Room();
		room3.setAvailableForRent(true);
		room3.setFloor(2);
		room3.setNumber("22");
		room3.setNumberOfBeds(2);
		Hotel hotel = new Hotel();
		hotel.setAddress(hotelAddress);
		hotel.setName("Capra neagra");
		hotel.setRating(3);
		hotel.setRooms(Arrays.asList(room1,room2,room3));
		System.out.println("BEFORE SAVE Hotel:" + hotel);
		conn.setAutoCommit(false);
		HotelDAO hotelDAO = new HotelDAO(conn);
		hotel = hotelDAO.insert(hotel);
		System.out.println("AFTER SAVE Hotel:" + hotel);

		conn.commit();
		
		conn.setAutoCommit(false);
		Room room4 = new Room();
		room4.setAvailableForRent(true);
		room4.setFloor(3);
		room4.setHotel(hotel);
		room4.setNumber("33");
		room4.setNumberOfBeds(2);
		RoomDAO roomDAO = new RoomDAO(conn);
		roomDAO.insert(room4);
		hotel = hotelDAO.loadById(1L);
		System.out.println("AFTER SAVE ROOM4 Hotel:" + hotel);

		conn.commit();

		conn.close();
	}
}
