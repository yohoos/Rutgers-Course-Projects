<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="styles/layout.css" type="text/css">
<style type="text/css">
 table {
   width:300px;
   margin:10px auto;
   text-align: center;
 }
</style>
</head>
<body>
<div class="wrapper row1">
   <header id="header" class="clear">
     <div id="hgroup">
       <h1><a href="index.jsp">YABE-Books</a></h1>
       <h2>Sell and Buy Books Here!</h2>
     </div>
   </header>
 </div>
  <div id="LoginForm">
    <form action="loginServlet" method="post">
        <fieldset>
            <legend> Login to App </legend>
            <table align = "center">
            	<tr><td style="color:red">${ error }</td></tr>
                <tr>
                    <td>Username</td>
                    <td><input type="text" name="username" required="required" maxlength="30"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="userpass" required="required" maxlength="30"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="login" value="Login" /></td>
					<td colspan = "4"> Not Registered? <a style="color:blue" href = "register.jsp"> Click here </a></td>
                </tr>
            </table>
        </fieldset>
    </form>

  </div>
</body>
</html>
