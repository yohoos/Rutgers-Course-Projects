<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html class="supernova"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="alternate" type="application/json+oembed" href="https://www.jotform.com/oembed/?format=json&amp;url=http%3A%2F%2Fwww.jotform.com%2Fform%2F61236183561150" title="oEmbed Form"><link rel="alternate" type="text/xml+oembed" href="https://www.jotform.com/oembed/?format=xml&amp;url=http%3A%2F%2Fwww.jotform.com%2Fform%2F61236183561150" title="oEmbed Form">
<meta property="og:title" content="Profile" >
<meta property="og:url" content="http://www.jotform.us/form/61236183561150" >
<meta property="og:description" content="Please click the link to complete this form.">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="HandheldFriendly" content="true" />
<title>Profile</title>
<link href="//cdn.jotfor.ms/static/formCss.css?3.3.12953" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="//cdn.jotfor.ms/css/styles/nova.css?3.3.12953" />
<link type="text/css" media="print" rel="stylesheet" href="//cdn.jotfor.ms/css/printForm.css?3.3.12953" />
<link type="text/css" rel="stylesheet" href="styles/layout.css"/>
<style type="text/css">
    .form-label-left{
        width:150px !important;
    }
    .form-line{
        padding-top:12px;
        padding-bottom:12px;
    }
    .form-label-right{
        width:150px !important;
    }
    body, html{
        margin:0;
        padding:0;
        background:false;
    }

    .form-header{
      color: gray;
    }
    .form-html, .form-collapse-table{
      color: white;
    }

    .form-all{
        margin:0px auto;
        padding-top:0px;
        width:690px;
        color:#555 !important;
        font-family:"Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Verdana, sans-serif;
        font-size:14px;
    }
</style>

<script src="//cdn.jotfor.ms/static/prototype.forms.js" type="text/javascript"></script>
<script src="//cdn.jotfor.ms/static/jotform.forms.js?3.3.12953" type="text/javascript"></script>
<script type="text/javascript">
   JotForm.init(function(){
	JotForm.clearFieldOnHide="disable";
	JotForm.onSubmissionError="jumpToFirstError";
   });
</script>
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
<form class="jotform-form" action="" method="post" name="form_61236183561150" id="61236183561150" accept-charset="utf-8">
  <input type="hidden" name="formID" value="61236183561150" />
  <div class="form-all">
    <ul class="form-section page-section">
      <li id="cid_1" class="form-input-wide" data-type="control_head">
        <div class="form-header-group">
          <div class="header-text httal htvam">
            <h2 id="header_1" class="form-header">
              ${ sessionScope.name }'s Profile
            </h2>
          </div>
        </div>
      </li>
      <li class="form-line" data-type="control_text" id="id_2">
        <div id="cid_2" class="form-input-wide">
          <div id="text_2" class="form-html">
            <p name="username">Username: ${ sessionScope.name } </p>
          </div>
        </div>
      </li>
      <li class="form-line" data-type="control_text" id="id_3">
        <div id="cid_3" class="form-input-wide">
          <div id="text_3" class="form-html">
            <p>Email: ${ email } </p>
          </div>
        </div>
      </li>
      <li class="form-line" data-type="control_text" id="id_3">
        <div id="cid_3" class="form-input-wide">
          <div id="text_3" class="form-html">
            <p>Interested In (Sub-Genres): </p>
		    <%@ page import = "java.util.ArrayList" %>
		    <% 
		    ArrayList<String> interests = (ArrayList<String>) request.getAttribute("interests");
		    for (int i=0; i<interests.size(); i++) { %>
		    	<p style="color:cyan"><%= interests.get(i) %></p>
		    <% } %>
          </div>
        </div>
      </li>
      <li class="form-line" data-type="control_text" id="id_4">
        <div id="cid_4" class="form-input-wide">
          <div id="text_4" class="form-html">
            <p>Auctions:</p>
            <%@ page import = "java.util.HashSet" %>
            <% 
            ArrayList<String> listings = (ArrayList<String>) request.getAttribute("listings");
		    for (int i=0; i<listings.size(); i++) { %>
		    	<p style="color:cyan"><%= listings.get(i) %></p>
		    <% } %>
          </div>
        </div>
      </li>
      <li class="form-line" data-type="control_text" id="id_7">
          <div id="cid_7" class="form-input-wide">
            <div id="text_7" class="form-html">
              <p>Questions:</p>
            </div>
          </div>
        </li>
  </div>
  <input type="hidden" id="simple_spc" name="simple_spc" value="61236183561150" />
  <script type="text/javascript">
  document.getElementById("si" + "mple" + "_spc").value = "61236183561150-61236183561150";
  </script>
</form></body>
</html>
<script type="text/javascript">JotForm.ownerView=true;</script>