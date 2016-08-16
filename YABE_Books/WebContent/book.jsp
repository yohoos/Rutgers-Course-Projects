<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="styles/layout.css" type="text/css">
<style type="text/css">
	body, html{
        margin:0;
        padding:0;
        background: white;
    }
</style>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
        		<li><a href="GetEarningsServlet">Reports</a></li>
        	</ul>
      	</nav>
      <% } %>
    </header>
  </div>
  <br></br>
  <div id=LoginForm>
 	<%@ page import="java.util.ArrayList" %>
  	<% 
  	ArrayList<String> arr = (ArrayList<String>) request.getAttribute("info");
  	%>
  	<h3 style="text-align:center"><%= arr.get(2) %></h3>
  	<br></br>
  	<p style="text-align:center">${ error }</p>
  	<form action="newBid" method="post">
	  	<fieldset>
		  	<input type="hidden" name="auction" value="<%= arr.get(2) %>"/>
		  	<input type="hidden" name="cBid" value="<%= arr.get(13) %>"/>
		  	<table align = "center">
		  	  <tr><td>Current Bid: </td><td style="color:blue"><%= arr.get(13) %></td></tr>
		  	  <tr><td>Top Bidder: </td><td style="color:blue"><%= arr.get(12) %></td></tr>
		  	  <tr><td>Author: </td><td style="color:blue"><%= arr.get(2) %></td></tr>
		  	  <tr><td>Publisher: </td><td style="color:blue"><%= arr.get(8) %></td></tr>
		  	  <tr><td>Book Title: </td><td style="color:blue"><%= arr.get(6) %></td></tr>
		  	  <tr><td>Genre: </td><td style="color:blue"><%= arr.get(4) %></td></tr>
		  	  <tr><td>Sub-Genre: </td><td style="color:blue"><%= arr.get(5) %></td></tr>
		  	  <tr><td>Print Year: </td><td style="color:blue"><%= arr.get(9) %></td></tr>
		  	  <tr><td>Description: </td><td style="color:blue"><%= arr.get(10) %></td></tr>
		  	  <tr><td>Ends On: </td><td style="color:blue"><%= arr.get(11) %></td></tr>
		  	  <tr><td><input required="required" type="number" min="0.01" step="0.01" name="bid" maxlength=30/></td><td><input type="submit" name="bid" value="Bid Now!"/></td></tr>	  	
		  	</table>
	  	</fieldset>
  	</form>
  </div>
  <div style="text-align:center">
  	<br></br>
  	<br></br>
  	<h2>Past Bids</h2>
  	<% ArrayList<String[]> bids = (ArrayList<String[]>) request.getAttribute("bids"); %>
  	<div style="color:blue">
  		<% for (int i=0; i<bids.size(); i++) { %>
  			<p><%= bids.get(i)[0]+" : "+ bids.get(i)[1] %>
  		<% } %>
  	</div>
  </div>
</body>
</html>