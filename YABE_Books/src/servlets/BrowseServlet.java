package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
	
@WebServlet("/BrowseServlet")

public class BrowseServlet extends HttpServlet {
		
		private static final long serialVersionUID = 1L;  
	 
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        Connection conn = null;
	        String url = "jdbc:mysql://localhost:3306/";
	        String dbName = "yabe22";
	        String driver = "com.mysql.jdbc.Driver";
	        String userName = "root";
	        String password = "Becaused7Ka";
//	        String password = "magicdust50";
//	        String password = "codeman1";
	        
	        String type = request.getParameter("submit");
//	        System.out.println("*******"+type);
	        
	        Statement st;

	        try {
	        	
	            Class.forName(driver).newInstance();
	            conn = DriverManager.getConnection(url + dbName, userName, password);
	            //String search = request.getParameter("searchbook");
	 
	            ArrayList al = null;
	            ArrayList pid_list = new ArrayList();
	            String query = "";
	            if(type.equals("closed"))
	            {
	            	
	            query = " SELECT max(IFNULL(bi.Amount, 0)) as CurrentBid, bL.AuctionName as Title,  bL.GenreName as Genre, bL.Name as Seller " +
            				" FROM yabe22.BookListing as bL " +
            				" LEFT OUTER JOIN yabe22.Bid as bi " +
            				" ON bL.AuctionName = bi.AuctionName AND bL.status = 'closed' " +
            				" WHERE bL.status = 'closed' " + 
            				" GROUP BY bL.name, bL.AuctionName " +
            				" LIMIT 30; ";
	            
	            } else { //default query is "open listings"
	            	
	            query = " SELECT max(IFNULL(bi.Amount, 0)) as CurrentBid, bL.AuctionName as Title,  bL.GenreName as Genre, bL.Name as Seller " +
	            				" FROM yabe22.BookListing as bL " +
	            				" LEFT OUTER JOIN yabe22.Bid as bi " +
	            				" ON bL.AuctionName = bi.AuctionName " +
	            				" WHERE bL.status = 'open' " +
	            				" GROUP BY bL.name, bL.AuctionName " +
	            				" LIMIT 30; ";
	            }

	            
	            st = conn.createStatement();
	            ResultSet rs = st.executeQuery(query);
	 
	            while (rs.next()) {
	                al = new ArrayList();
	
	                al.add(rs.getString(1));
	                al.add(rs.getString(2));
	                al.add(rs.getString(3));
	                al.add(rs.getString(4));
	 
	                pid_list.add(al);
	            }
	 
	            request.setAttribute("piList", pid_list);
	            RequestDispatcher view = request.getRequestDispatcher("/searchview.jsp");
	            view.forward(request, response);
	            conn.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	    /** 
	     * Returns a short description of the servlet.
	     * @return a String containing servlet description
	     */
	    @Override
	    public String getServletInfo() {
	        return "Short description";
	    }// </editor-fold>

}