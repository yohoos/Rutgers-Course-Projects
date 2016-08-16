package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AutoBidThread;
import dao.InsertBid;

/**
 * Servlet implementation class PlaceBid
 */
@WebServlet("/PlaceBid")
public class PlaceBid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceBid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bid = request.getParameter("bid");
		String cBid = request.getParameter("cBid");
		
		if (bid.length() <= 0) {
			request.setAttribute("error", "New bid needs to be greater than current bid!");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		} else if (Double.parseDouble(cBid) >= Double.parseDouble(bid)) {
			request.setAttribute("error", "New bid needs to be greater than current bid!");
			RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
			rd.forward(request, response);
		} else {
			String auction = request.getParameter("auction");
			String username = request.getSession().getAttribute("name").toString();
			boolean check = InsertBid.insertBid(username, auction, bid);
//			boolean check = AutoBidThread.addAutoBid(username, auction, bid);
			if (!check) {
				request.setAttribute("error", "Error Inserting Bid!");
				RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("link", "lobby.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("redirect.jsp");
				rd.forward(request, response);
			}
		}
	}

}
