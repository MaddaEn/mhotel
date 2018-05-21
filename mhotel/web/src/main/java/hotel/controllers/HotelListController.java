package hotel.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import hotel.business.HotelBusiness;
import mhotel.DatasourceUtils;
import mhotel.dao.CustomerDAO;
import mhotel.dao.HotelDAO;
import mhotel.model.Customer;
import mhotel.model.Hotel;

/**
 * Servlet implementation class HotelListController
 */
public class HotelListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HotelListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<Hotel> hotelList = new HotelBusiness().getAllHotels();
			RequestDispatcher rd = request.getRequestDispatcher("/hotelList.jsp");
			request.setAttribute("hotels", hotelList);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}