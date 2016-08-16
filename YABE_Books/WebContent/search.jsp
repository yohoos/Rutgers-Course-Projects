<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
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
    <br/><br/>
    
    <form method="post" name="frm" action="BrowseServlet">
      <table border="1" width="300" align="center" >
        <tr><td colspan=2 style="font-size:12pt;" align="center">
        <h3>Browse Auctions</h3></td></tr>
        <tr><td colspan=2 align="center">
        <label>Open</label>
        <!-- <input  type="submit" name="submit" value="Search"> -->
        <input  type="submit" name="submit" value="open">
        <label>Closed</label>
        <input  type="submit" name="submit" value="closed"></td></tr>
      </table>
      
    </form>
    <form method="post" name="frm" action="SearchServlet">
      <table border="1" width="300" align="center" >
        <tr><td colspan=2 style="font-size:12pt;" align="center">
        <h3>Search For Book</h3></td></tr>
        <tr><td ><b>Book Name</b></td>
          <td>: <input  type="text" name="searchbook" id="searchbook">
        </td></tr>        
        <tr><td colspan=2 align="center">
        <input  type="submit" name="submit" value="Search"></td></tr>
      </table>
    </form>
    <form method="post" name="frm" action="NameServlet">
      <table border="1" width="300" align="center" >
        <tr><td colspan=2 style="font-size:12pt;" align="center">
        <h3>Search by Seller</h3></td></tr>
        <tr><td ><b>Seller Name</b></td>
          <td>: <input  type="text" name="searchbook" id="searchbook">
        </td></tr>        
        <tr><td colspan=2 align="center">
        <input  type="submit" name="submit" value="Search"></td></tr>
      </table>
    </form>
    <form method="post" name="frm" action="GenreServlet">
      <table border="1" width="300" align="center" >
        <tr><td colspan=2 style="font-size:12pt;" align="center">
        <h3>Search by Genre</h3></td></tr>
        <tr><td ><b>Genre</b></td>
          <td>: <input  type="text" name="searchbook" id="searchbook">
        </td></tr>        
        <tr><td colspan=2 align="center">
        <input  type="submit" name="submit" value="Search"></td></tr>
      </table>
    </form>
  </body>
</html>