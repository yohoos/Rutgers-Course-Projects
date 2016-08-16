<%@ page import="java.util.*" %>
 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	          <li class="last"><a href="contact.jsp">Contact Us</a></li>
	        </ul>
	      </nav>
	    </header>
    </div>
    <br></br>
        <table width="700px" align="center"
               style="border:1px solid #000000;">
            <tr>
                <td colspan=4 align="center">
                    <b>Search Results</b></td>
            </tr>
            <tr>
                <td><b>Current Bid</b></td>
                <td><b>Auction Name</b></td>
                <td><b>Genre</b></td>
                <td><b>Seller's Name</b></td>
            </tr>
            <%
                int count = 0;
                String color = "#F9EBB3";
                if (request.getAttribute("piList") != null) {
                    ArrayList<String> al = (ArrayList<String>) request.getAttribute("piList");
                    Iterator itr = al.iterator();
                    while (itr.hasNext()) {
                        count++;
                        ArrayList<String> pList = (ArrayList<String>) itr.next();
            %>
            <tr">
                <td><%=pList.get(0)%></td>
                <% 
                String auction = pList.get(1);
                %>
                <td><a href="auctionPage?param=<%= auction %>"><%= auction %></a></td>
                <td><%=pList.get(2)%></td>
                <td><%=pList.get(3)%></td>
            </tr>
            <%
                    }
                }
                if (count == 0) {
            %>
            <tr>
                <td colspan=4 align="center"
                    style="background-color:#ddeecc"><b>No Records Found</b></td>
            </tr>
            <%            }
            %>
        </table>
    </body>
</html>