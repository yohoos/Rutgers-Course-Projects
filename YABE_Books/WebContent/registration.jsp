<%@ page session ="true" language ="java"  contentType = "text/html; charset=UTF-8" %>
<%@ page import ="java.sql.Connection" %>
<%@ page import ="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import ="java.lang.String" %>
<%@ page import = "java.sql.PreparedStatement" %>
<%@ page import = "javax.servlet.RequestDispatcher" %>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String verify = request.getParameter("verify");
    String email = request.getParameter("email");
    String priv = request.getParameter("priv");

    if(!password.equals(verify)) {
        username = null;
        password = null;
        verify = null;
        
    }

    if(username != null && password != null)
    {   
       	try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yabe22", "root", "Becaused7Ka");
            PreparedStatement st = con.prepareStatement("INSERT INTO Account (Name, Email, Password, Privilege) Values (?,?,?,?)");
            st.setString(1, username);
            st.setString(2, email);
            st.setString(3, password);
            st.setString(4, priv);
            
            int i = st.executeUpdate();

            request.setAttribute("link", "index.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("redirect.jsp");
            rd.forward(request, response);            	                    	
            
       	} catch (Exception e) {
       		if (priv.equals("EndUser")) {
	        	request.setAttribute("insert_error", "Username or Email already taken!");
	        	RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
	        	rd.forward(request, response);       			
       		} else {
	        	request.setAttribute("insert_error", "Username or Email already taken!");
	        	RequestDispatcher rd = request.getRequestDispatcher("createReps.jsp");
	        	rd.forward(request, response);  
       		}
       	}

    } else {
    	request.setAttribute("p_error", "Password does not match!");
    	RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
    	rd.forward(request, response);
    }
%>
