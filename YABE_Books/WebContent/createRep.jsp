<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html class="supernova"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="alternate" type="application/json+oembed" href="https://www.jotform.com/oembed/?format=json&amp;url=http%3A%2F%2Fwww.jotform.com%2Fform%2F61145750140142" title="oEmbed Form"><link rel="alternate" type="text/xml+oembed" href="https://www.jotform.com/oembed/?format=xml&amp;url=http%3A%2F%2Fwww.jotform.com%2Fform%2F61145750140142" title="oEmbed Form">
<meta property="og:title" content="Register" >
<meta property="og:url" content="http://www.jotform.us/form/61145750140142" >
<meta property="og:description" content="Please click the link to complete this form.">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="HandheldFriendly" content="true" />
<title>Create Customer Representative</title>
<link href="https://cdn.jotfor.ms/static/formCss.css?3.3.12763" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="https://cdn.jotfor.ms/css/styles/nova.css?3.3.12763" />
<link type="text/css" media="print" rel="stylesheet" href="https://cdn.jotfor.ms/css/printForm.css?3.3.12763" />
<link rel="stylesheet" href="styles/layout.css" type="text/css">
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

    .form-all{
        margin:0px auto;
        padding-top:0px;
        width:690px;
        color:#555 !important;
        font-family:"Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Verdana, sans-serif;
        font-size:14px;
    }
    .form-radio-item label, .form-checkbox-item label, .form-grading-label, .form-header{
        color: #555;
    }

</style>

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
<form action="registration.jsp" method="post" accept-charset="utf-8">
  <div class="form-all">
    <ul class="form-section page-section">
      <li id="cid_1" class="form-input-wide" data-type="control_head">
        <div class="form-header-group">
          <div class="header-text httal htvam">
            <h2 id="header_1" class="form-header">
              Create Customer Representative
            </h2>
          </div>
        </div>
      </li>
      <li class="form-line jf-required" data-type="control_fullname" id="id_3">
      	<div style="color:red">${ insert_error }</div>
        <label class="form-label form-label-left form-label-auto" id="label_3" for="input_3">
          Username
          <span class="form-required">
            *
          </span>
        </label>
        <div id="cid_3" class="form-input jf-required">
          <span class="form-sub-label-container" style="vertical-align: top">
            <input class="form-textbox validate[required]" type="text" size="10" name="username" id="first_3" maxlength="30"/>
          </span>
        </div>
      </li>
      <li class="form-line jf-required" data-type="control_email" id="id_6">
      	<div style="color:red">${ insert_error }</div>
        <label class="form-label form-label-left form-label-auto" id="label_6" for="input_6">
            E-mail
          <span class="form-required">
            *
          </span>
        </label>
        <div id="cid_6" class="form-input jf-required">
          <input type="email" class=" form-textbox validate[required, Email]" id="input_6" name="email" size="30" value="" maxlength="30"/>
        </div>
      </li>
      <li class="form-line jf-required" data-type="control_textbox" id="id_4">
      	<div style="color:red">${ p_error }</div>
        <label class="form-label form-label-left form-label-auto" id="label_4" for="input_4">
            Password
          <span class="form-required">
            *
          </span>
        </label>
        <div id="cid_4" class="form-input jf-required">
          <input type="password" class=" form-textbox validate[required]" data-type="input-textbox" id="input_4" name="password" size="20" value="" maxlength="30"/>
        </div>
      </li>
      <li class="form-line jf-required" data-type="control_textbox" id="id_5">
      	<div style="color:red">${ p_error }</div>
        <label class="form-label form-label-left form-label-auto" id="label_5" for="input_5">
            Verify Password
          <span class="form-required">
            *
          </span>
        </label>
        <div id="cid_5" class="form-input jf-required">
          <input type="password" class=" form-textbox validate[required]" data-type="input-textbox" id="input_5" name="verify" size="20" value="" maxlength="30"/>
        </div>
      </li>
      <li class="form-line" data-type="control_button" id="id_2">
        <div id="cid_2" class="form-input-wide">
          <div style="margin-left:156px" class="form-buttons-wrapper">
          	<input type="hidden" name="priv" value="CRep"/>
            <button id="input_2" type="submit" class="form-submit-button">
              Submit
            </button>
          </div>
        </div>
      </li>
      <li style="display:none">
        Should be Empty:
        <input type="text" name="website" value="" />
      </li>
    </ul>
  </div>
</form></body>
</html>
