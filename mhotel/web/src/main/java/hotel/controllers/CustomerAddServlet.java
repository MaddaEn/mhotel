package hotel.controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import hotel.business.CustomerService;
import mhotel.DatasourceUtils;
import mhotel.dao.CustomerDAO;
import mhotel.model.Address;
import mhotel.model.Customer;

/**
 * Servlet implementation class CustomerAddServlet
 */
public class CustomerAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nume = request.getParameter("nume");
		String sex = request.getParameter("sex");
		String id = request.getParameter("id");
		String idType = request.getParameter("idType");

		String addCountry = request.getParameter("addr_country");
		String addCity = request.getParameter("addr_city");
		String addStreet = request.getParameter("addr_street");
		String addNumber = request.getParameter("addr_nbr");
		String addCP = request.getParameter("addr_cp");
		try {
			Customer cust = new CustomerService().addCustomerService(nume, sex, id, idType, addCity, addCountry,
					addNumber, addCP, addStreet);
			response.sendRedirect(request.getContextPath() + "/customer/list");

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

	}

}