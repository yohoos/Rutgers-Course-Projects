package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.InsertBid;
//import dao.InsertBookDao;
import dao.InsertListingDao;
import dao.UpdateGenres;

/**
 * Servlet implementation class sellServlet
 */
@WebServlet("/sellServlet")
public class sellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sellServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String username = session.getAttribute("name").toString();
		String auctionName = request.getParameter("auctionName");
		String startingBid = request.getParameter("startingBid");
		String reservePrice = request.getParameter("reservePrice");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		String bookTitle = request.getParameter("bookTitle");
		String genre = request.getParameter("genre");
		String subgenre = request.getParameter("subgenre");
		String printYear = request.getParameter("printYear");
		String description = request.getParameter("description");
		String duration = request.getParameter("duration");
		
//		if (!InsertBookDao.insertBook(isbn, author, publisher, bookTitle, genre, printYear, description)) {
//			request.setAttribute("error", "Error in Database Update for Books!");
//			RequestDispatcher rd = request.getRequestDispatcher("sell.jsp");
//			rd.forward(request, response);
//			return;
//		}
		
		Calendar time = Calendar.getInstance();
		time.add(Calendar.DAY_OF_MONTH, Integer.parseInt(duration));
		Timestamp ts = new Timestamp(time.getTimeInMillis());
		String endtime = ts.toString();
		
		if (!UpdateGenres.update(genre, subgenre)) {
			request.setAttribute("error", "Genre or Subgenre is not real!");
			RequestDispatcher rd = request.getRequestDispatcher("sell.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (!InsertListingDao.insertListing(author, publisher, bookTitle, genre, subgenre, printYear, description, reservePrice, auctionName, username, endtime)){
			request.setAttribute("error", "Listing Name Already Exists! Choose another!");
			RequestDispatcher rd = request.getRequestDispatcher("sell.jsp");
			rd.forward(request, response);
			return;
		}
		if (!InsertBid.insertBid(username, auctionName, startingBid)) {
			request.setAttribute("error", "Error in Inserting Bid!");
			RequestDispatcher rd = request.getRequestDispatcher("sell.jsp");
			rd.forward(request, response);
			return;
			
		} else {
			request.setAttribute("link", "lobby.jsp");
			RequestDispatcher rd=request.getRequestDispatcher("redirect.jsp");
            rd.forward(request,response);
		}
	}

}
