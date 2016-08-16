<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="styles/layout.css" type="text/css">
<style type="text/css">
	body, html{
        margin:0;
        padding:0;
        background: white;
    }
</style>
<title>Reports</title>
</head>
<body>
  <div class="wrapper row1">
    <header id="header" class="clear">
      <div id="hgroup">
        <h1><a href="lobby.jsp">YABE-Books</a></h1>
        <h2>Sell and Buy Books Here!</h2>
      </div>
      <nav>
        <ul>
          <li><a href="profileServlet">Profile (${ sessionScope.name })</a></li>
          <li><a href="search.jsp">Search</a></li>
          <li><a href="sell.jsp">Sell</a></li>
          <li><a href="logoutServlet">Logout</a></li>
          <li><a href="AnswersServlet">Answers</a></li>
          <li class="last"><a href="contact.jsp">Contact Us</a></li>
        </ul>
      </nav>
      <% 
      String priv = session.getAttribute("privilege").toString(); 
      if (priv.equals("Admin") || priv.equals("CRep")) { %>
    	<nav>
        	<ul>
        	<% if (priv.equals("Admin")) { %>
        		<li><a href="createRep.jsp">Create Rep.</a></li>
        	<% } %>
        		<li><a href="getEarningsServlet">Reports</a></li>
        	</ul>
      	</nav>
      <% } %>
    </header>
  </div>
  <div>
  	<br></br>
  	<br></br>
  	<table align= "center" style="border-spacing: 20px">
  		<tr style="color:red"><td>Total Earnings: </td><td>${ total }</tr>
  		<tr style="color:green"><td>Earnings Per Item: </td></tr>
  		<%@ page import = "java.util.ArrayList" %>
  		<% 
  		ArrayList<String[]> itemEarnings = (ArrayList<String[]>) request.getAttribute("itemEarnings");
  		for (int i=0; i<itemEarnings.size(); i++) { %>
  			<tr style="color:green"><td><%= itemEarnings.get(i)[0] %></td><td><%= itemEarnings.get(i)[1] %></td></tr>
  		<% }
  		%>
  		<tr style="color:black"><td>Earnings Per Item Type: </td></tr>
  		<% 
  		ArrayList<String[]> typeEarnings = (ArrayList<String[]>) request.getAttribute("typeEarnings");
  		for (int i=0; i<typeEarnings.size(); i++) { %>
  			<tr style="color:black"><td><%= typeEarnings.get(i)[0] %></td><td><%= typeEarnings.get(i)[1] %></td><td><%= typeEarnings.get(i)[2] %></td></tr>
  		<% }
  		%>
  		<tr style="color:purple"><td>Earnings Per End-User: </td></tr>
		<% 
  		ArrayList<String[]> userEarnings = (ArrayList<String[]>) request.getAttribute("userEarnings");
  		for (int i=0; i<userEarnings.size(); i++) { %>
  			<tr style="color:purple"><td><%= userEarnings.get(i)[0] %></td><td><%= userEarnings.get(i)[1] %></td></tr>
  		<% }
  		%>
  		<tr style="color:black"><td>Best Selling Items: (Shows Top 5)</td></tr>
  		<% 
  		ArrayList<String[]> bestItems = (ArrayList<String[]>) request.getAttribute("bestItems");
  		for (int i=0; i<bestItems.size(); i++) { %>
  			<tr style="color:black"><td><%= bestItems.get(i)[0] %></td><td><%= bestItems.get(i)[1] %></td></tr>
  		<% }
  		%>
  		<tr style="color:crimson"><td>Best Buyer: (Shows Top 5)</td></tr>
  		<% 
  		ArrayList<String[]> bestBuyer = (ArrayList<String[]>) request.getAttribute("bestBuyer");
  		for (int i=0; i<bestBuyer.size(); i++) { %>
  			<tr style="color:crimson"><td><%= bestBuyer.get(i)[0] %></td><td><%= bestBuyer.get(i)[1] %></td></tr>
  		<% }
  		%>
  	</table>
  </div>
</body>
</html>