package hotel.rest;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import hotel.business.CustomerService;
import hotel.inj.Logged;
import mhotel.model.Customer;

@Path("/customers")
@ApplicationScoped
public class CustomerResource {
	private static final Logger __logger = Logger.getLogger("hotel.rest.CustomerResource");
	
	@Inject
	private CustomerService mCustomerService;


	@GET
	@Path("/all")
	@Produces("application/json")
	@Logged

	public List<Customer> getAllCustomers() throws Exception {
		if (__logger.isLoggable(Level.INFO))
			__logger.log(Level.INFO, "getAllCustomers entered");
		return mCustomerService.getAllCustomers();
	}

	@GET
	@Path("/all/{cid}")
	public Customer getCustomerById(@PathParam("cid") long pId) throws Exception {
		return mCustomerService.getCustomerById(pId);
	}

	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	public Customer addCustomer(Customer pCustomer) throws Exception {
		return mCustomerService.addCustomer(pCustomer);
	}
	
	private void bau() {
		System.out.println("Bau");
	}
}
