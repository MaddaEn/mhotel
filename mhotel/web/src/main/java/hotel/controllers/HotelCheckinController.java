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
import hotel.business.RoomService;
import mhotel.DatasourceUtils;
import mhotel.dao.CustomerRecordDAO;
import mhotel.dao.HotelDAO;
import mhotel.dao.RoomDAO;
import mhotel.model.CustomerRecord;
import mhotel.model.Hotel;
import mhotel.model.Room;

/**
 * Servlet implementation class HotelCheckinController
 */
public class HotelCheckinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HotelCheckinController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long customerId = Long.parseLong(request.getParameter("customer_id"));
		try {
		
			List<CustomerRecord> customerRecords= new HotelBusiness().getActiveCheckInsById(customerId);
			List<Room> roomList = new RoomService().listAllFreeRooms();
			RequestDispatcher rd = request.getRequestDispatcher("/roomCheckIn.jsp");
			request.setAttribute("rooms", roomList);
			request.setAttribute("activeCheckins", customerRecords);

			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}