package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GetEarningsDao;

/**
 * Servlet implementation class GetEarningsServlet
 */
@WebServlet("/GetEarningsServlet")
public class GetEarningsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetEarningsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String total = GetEarningsDao.getEarnings(0).get(0)[0];
		request.setAttribute("total", total);
		
		ArrayList<String[]> itemEarnings = GetEarningsDao.getEarnings(1);
		request.setAttribute("itemEarnings", itemEarnings);
		
		ArrayList<String[]> typeEarnings = GetEarningsDao.getEarnings(2);
		request.setAttribute("typeEarnings", typeEarnings);
		
		ArrayList<String[]> userEarnings = GetEarningsDao.getEarnings(3);
		request.setAttribute("userEarnings", userEarnings);
		
		ArrayList<String[]> bestItems = GetEarningsDao.getEarnings(4);
		request.setAttribute("bestItems", bestItems);
		
		ArrayList<String[]> bestBuyer = GetEarningsDao.getEarnings(5);
		request.setAttribute("bestBuyer", bestBuyer);
		
		RequestDispatcher rd = request.getRequestDispatcher("reports.jsp");
		rd.forward(request, response);
	}

}
