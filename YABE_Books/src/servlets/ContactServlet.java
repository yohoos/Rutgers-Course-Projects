package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MessageDao;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		String name = request.getSession().getAttribute("name").toString();
		String message = request.getParameter("message");
		
        if(!message.matches(".+")){    
            request.setAttribute("error", "Message Required");
        	RequestDispatcher rd=request.getRequestDispatcher("contact.jsp");
            rd.forward(request,response);
        }    
        else{    
        	MessageDao.store(name, message);
        	request.setAttribute("link", "lobby.jsp");
            RequestDispatcher rd=request.getRequestDispatcher("redirect.jsp");
            rd.forward(request,response);
        }   
		
	}

}
