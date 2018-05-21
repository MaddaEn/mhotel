package hotel.business;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

//import hotel.inj.Logged;
import mhotel.DatasourceUtils;
import mhotel.H2DB;
import mhotel.dao.CustomerDAO;
import mhotel.model.Address;
import mhotel.model.Customer;

@ApplicationScoped
public class CustomerService {
	private static final Logger __logger = Logger.getLogger("hotel.business.CustomerService");

	@Inject
	@H2DB
	private DataSource mDataSource;

	public List<Customer> getAllCustomers() throws Exception {
		if (__logger.isLoggable(Level.INFO))
			__logger.log(Level.INFO, "getAllCustomers entered");
		try {
			if (__logger.isLoggable(Level.FINE))
				__logger.log(Level.FINE, "got datasource" + mDataSource.toString());
			Connection connection = null;
			List<Customer> customerList = null;
			try {
				connection = mDataSource.getConnection();
				CustomerDAO customerDAO = new CustomerDAO(connection);
				customerList = customerDAO.listAll();
				return customerList;
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			__logger.log(Level.WARNING, "getAllCustomers", e);
			throw e;
		}
	}

	public Customer getCustomerById(long pId) throws Exception {
		if (__logger.isLoggable(Level.INFO))
			__logger.log(Level.INFO, "getAllCustomers entered");
		try {
			Connection connection = null;
			try {
				connection = mDataSource.getConnection();
				CustomerDAO customerDAO = new CustomerDAO(connection);
				return customerDAO.loadById(pId);
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			__logger.log(Level.WARNING, "getCustomerById", e);
			throw e;
		}
	}

	public Customer addCustomer(Customer pCustomer) throws Exception {
		try {
			Connection connection = null;
			try {
				connection = mDataSource.getConnection();
				CustomerDAO customerDAO = new CustomerDAO(connection);
				return customerDAO.insert(pCustomer);
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			__logger.log(Level.WARNING, "addCustomer", e);
			throw e;
		}
	}
	
	public Customer addCustomerService(String nume, String sex, String id, String idType, String addCity,
			String addCountry, String addNumber, String addCP, String addStreet) throws Exception {
		try {
			DataSource ds = DatasourceUtils.getDataSource();
			Connection connection = null;
			try {
				connection = ds.getConnection();
				connection.setAutoCommit(false);
				Customer customer = new Customer();
				customer.setName(nume);
				customer.setSex(sex);
				customer.setLegalId(id);
				customer.setLegalIdType(idType);

				Address addr = new Address();
				addr.setCity(addCity);
				addr.setCountry(addCountry);
				addr.setNumber(addNumber);
				addr.setPostalCode(addCP);
				addr.setStreet(addStreet);
				customer.setAddress(addr);
				CustomerDAO customerDAO = new CustomerDAO(connection);
				Customer cust = customerDAO.insert(customer);
				connection.commit();
				return cust;
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (Exception e) {
			__logger.log(Level.WARNING, "addCustomer", e);
			throw e;
		}
	}
}
