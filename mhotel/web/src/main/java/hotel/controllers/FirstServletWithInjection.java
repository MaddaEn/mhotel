package hotel.controllers;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotel.inj.ServiceIntf;
import mhotel.model.Address;

/**
 * Servlet implementation class FirstServletWithInjection
 */
@WebServlet("/fsi")
public class FirstServletWithInjection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject @Named("pufi")
	private ServiceIntf mService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServletWithInjection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("addr: ").append(mService.doCeva());
	}

	
}
