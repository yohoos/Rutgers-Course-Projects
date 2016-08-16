<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="alternate" type="application/json+oembed" href="https://www.jotform.com/oembed/?format=json&amp;url=http%3A%2F%2Fwww.jotform.com%2Fform%2F61145623511143" title="oEmbed Form"><link rel="alternate" type="text/xml+oembed" href="https://www.jotform.com/oembed/?format=xml&amp;url=http%3A%2F%2Fwww.jotform.com%2Fform%2F61145623511143" title="oEmbed Form">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="HandheldFriendly" content="true" />
<title>Message Contact Form</title>
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
        background: white;
    }
    .form-header{
      color: gray;
    }
    .form-all{
        margin:0px auto;
        padding-top:0px;
        width:590px;
        color:gray !important;
        font-family:"Lucida Grande", "Lucida Sans Unicode", "Lucida Sans", Verdana, sans-serif;
        font-size:14px;
    }
</style>

<script src="https://cdn.jotfor.ms/static/prototype.forms.js" type="text/javascript"></script>
<script src="https://cdn.jotfor.ms/static/jotform.forms.js?3.3.12763" type="text/javascript"></script>
<script type="text/javascript">
   JotForm.init(function(){
      setTimeout(function() {
          $('input_16').hint('ex: myname@example.com');
       }, 20);
	JotForm.clearFieldOnHide="disable";
	JotForm.onSubmissionError="jumpToSubmit";
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
<form class="jotform-form" action="contactServlet" method="post" name="form_61145623511143" id="61145623511143" accept-charset="utf-8">
  <input type="hidden" name="formID" value="61145623511143" />
  <div class="form-all">
    <ul class="form-section page-section">
      <li id="cid_18" class="form-input-wide" data-type="control_head">
        <div class="form-header-group">
          <div class="header-text httal htvam">
            <h1 id="header_18" class="form-header">
              Contact Us
            </h1>
            <div id="subHeader_18" class="form-subHeader">
              Please fill out this form and we will get in touch with you shortly.
            </div>
          </div>
        </div>
      </li>
      <li><p style="color:red">${ requestScope.error }</p></li>
      <li class="form-line jf-required" data-type="control_textarea" id="id_11">
	        <label class="form-label form-label-left form-label-auto" id="label_11" for="input_11">
	          Question
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        <div id="cid_11" class="form-input jf-required">
	          <div class="form-textarea-limit">
	            <span>
	              <textarea id="input_11" class="form-textarea validate[required]" name="message" cols="60" rows="10" maxlength="200"></textarea>
	              <div class="form-textarea-limit-indicator">
	                <span limit="200" type="Letters" minimum="-1" typeMinimum="None" id="input_11-limit">
	                  0/200
	                </span>
	              </div>
	            </span>
	          </div>
	        </div>
	      </li>
      <li class="form-line" data-type="control_button" id="id_14">
        <div id="cid_14" class="form-input-wide">
          <div style="text-align:center" class="form-buttons-wrapper">
            <button id="input_14" type="submit" class="form-submit-button">
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
  <input type="hidden" id="simple_spc" name="simple_spc" value="61145623511143" />
  <script type="text/javascript">
  document.getElementById("si" + "mple" + "_spc").value = "61145623511143-61145623511143";
  </script>
</form></body>
</html>
