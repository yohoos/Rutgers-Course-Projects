<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="styles/layout.css" type="text/css">
<style type="text/css">
	body, html{
        margin:0;
        padding:0;
        background: white;
    }
</style>
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
  <div>
  	<p style="text-align:center">${ error }</p>
	<table style="width:100%; border-spacing:20px">
		<tr><td>Original Poster</td><td>Question</td><td>Answer</td></tr>
		<%@ page import = "java.util.ArrayList" %>
		<% 
		ArrayList<String[]> answers = (ArrayList<String[]>) request.getAttribute("answers"); 
		for (int i=0; i<answers.size(); i++) {
			String[] tuple = answers.get(i);
		%>
		<tr><td><%= tuple[1] %></td><td style="color:red"><%= tuple[2] %></td>
		<% if (tuple[3] == "No Answer" && (request.getSession().getAttribute("privilege").equals("Admin") 
		|| request.getSession().getAttribute("privilege").equals("CRep"))) { %>
			<td><form action="newAnswer" method="post"><textarea name="answer" rows="10" cols="10"></textarea>
			<input type="hidden" name="qid" value="<%= tuple[0] %>"/>
			<input type="submit" value="Answer"/></form></td></tr>
		<% } else { %>
		<td style="color:blue"><%= tuple[3] %></td></tr>
		<% }
		} %>
	</table>
  </div>
</body>
</html>