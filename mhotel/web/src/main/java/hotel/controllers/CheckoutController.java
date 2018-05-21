package hotel.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import hotel.business.CheckinChecoutService;
import mhotel.DatasourceUtils;
import mhotel.dao.CustomerRecordDAO;
import mhotel.model.Hotel;

/**
 * Servlet implementation class CheckoutController
 */
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckoutController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long custRecordId = Long.parseLong(request.getParameter("cr_id"));
		try {
			CheckinChecoutService CheckinChecoutService = new CheckinChecoutService();
			CheckinChecoutService.chekOut(custRecordId);
			response.sendRedirect(request.getContextPath() + "/hotel/checkout");
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
