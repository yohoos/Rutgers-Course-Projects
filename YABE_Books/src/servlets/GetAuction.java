package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GetListing;
import dao.PastBids;

/**
 * Servlet implementation class GetAuction
 */
@WebServlet("/GetAuction")
public class GetAuction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAuction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String auction = request.getParameter("param").toString();
		ArrayList<String> arr = GetListing.insertListing(auction);
		ArrayList<String[]> pastBids = PastBids.getBids(arr.get(2));
		request.setAttribute("info", arr);
		request.setAttribute("bids", pastBids);
		RequestDispatcher rd = request.getRequestDispatcher("book.jsp");
		rd.forward(request, response);
	}

}
