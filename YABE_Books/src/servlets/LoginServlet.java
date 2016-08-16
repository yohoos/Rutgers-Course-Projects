package servlets;

import java.io.IOException;  
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import dao.LoginDao;  
  
public class LoginServlet extends HttpServlet{  
  
    private static final long serialVersionUID = 1L;  
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)    
            throws ServletException, IOException {    
  
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();    
          
        String n=request.getParameter("username");   
        String p=request.getParameter("userpass");   
          
        HttpSession session = request.getSession(false);  
        if(session!=null)  
        session.setAttribute("name", n);
        
        ArrayList<String> arr = LoginDao.validate(n, p);
  
        if(arr.size() > 0){
        	if (n.equals(arr.get(0)) && p.equals(arr.get(2))) {
        		session.setAttribute("privilege", arr.get(3));
                RequestDispatcher rd=request.getRequestDispatcher("lobby.jsp");
                rd.forward(request,response);    
        	} else {
                request.setAttribute("error", "Invalid");
            	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
                rd.forward(request,response);
        	}
        }    
        else{    
            request.setAttribute("error", "Invalid");
        	RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
            rd.forward(request,response);
        }    
  
        out.close();    
    }    
}   