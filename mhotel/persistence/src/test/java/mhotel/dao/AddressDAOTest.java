package mhotel.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mhotel.DatasourceUtils;
import mhotel.model.Address;

public class AddressDAOTest {
	private AddressDAO addrDAO;
	private Connection conn;

	@Before
	public void setUp() throws Exception {
		//conn = DatasourceUtils.getDataSource().getConnection();
		//addrDAO = new AddressDAO(conn);
	}

	@After
	public void tearDown() throws Exception {
		//conn.close();
	}

	//@Test
	public void testInsert() throws SQLException {
		Address addr = new Address();
		addr.setCity("CITY");
		addr.setCountry("COUNTRY");
		addr.setNumber("NUMBER");
		addr.setPostalCode("PC");
		addr.setStreet("STR");
		Address addr1 = addrDAO.insert(addr);
		assertTrue("ID DUPA INSERT E NULL", addr1.getId() != null);
		assertEquals(addr.getCity(), addr1.getCity());
		assertEquals(addr.getCountry(), addr1.getCountry());
		
	}

	//@Test
	public void testUpdate() {
		//fail("Not yet implemented");
	}

	@Test
	public void testListAll() {
		//fail("Not yet implemented");
	}

	@Test
	public void testLoadById() {
		//fail("Not yet implemented");
	}

}
