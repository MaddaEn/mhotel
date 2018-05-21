package hotel.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import hotel.business.RoomService;
import mhotel.DatasourceUtils;
import mhotel.dao.CustomerRecordDAO;
import mhotel.dao.RoomDAO;
import mhotel.model.CustomerRecord;
import mhotel.model.Room;

/**
 * Servlet implementation class HotelCheckoutController
 */

public class UnavailableRoomsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UnavailableRoomsController() {
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
			List<Room> occupiedRoomsList = new RoomService().listAllOccupiedRooms();

			RequestDispatcher rd = request.getRequestDispatcher("/unavailableRooms.jsp");
			request.setAttribute("occupiedRooms", occupiedRoomsList);

			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}