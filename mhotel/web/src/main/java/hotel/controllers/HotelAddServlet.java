package hotel.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import hotel.business.HotelBusiness;
import mhotel.DatasourceUtils;
import mhotel.dao.CustomerDAO;
import mhotel.dao.HotelDAO;
import mhotel.model.Address;
import mhotel.model.Customer;
import mhotel.model.Hotel;
import mhotel.model.Room;

/**
 * Servlet implementation class HotelAddServlet
 */
public class HotelAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HotelAddServlet() {
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
		String rating = request.getParameter("rating");

		String addCountry = request.getParameter("addr_country");
		String addCity = request.getParameter("addr_city");
		String addStreet = request.getParameter("addr_street");
		String addNumber = request.getParameter("addr_nbr");
		String addCP = request.getParameter("addr_cp");
		try {
			Hotel hotel = new HotelBusiness().addHotelService(nume, rating, addCity, addCountry, addNumber, addCP,
					addStreet);
			response.sendRedirect(request.getContextPath() + "/hotel/list");

		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}