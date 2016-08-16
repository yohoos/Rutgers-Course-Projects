<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html class="supernova"><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="alternate" type="application/json+oembed" href="https://www.jotform.com/oembed/?format=json&amp;url=http%3A%2F%2Fwww.jotform.com%2Fform%2F61226401662144" title="oEmbed Form"><link rel="alternate" type="text/xml+oembed" href="https://www.jotform.com/oembed/?format=xml&amp;url=http%3A%2F%2Fwww.jotform.com%2Fform%2F61226401662144" title="oEmbed Form">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="HandheldFriendly" content="true" />
<title>List Book to Sell</title>
<link href="https://cdn.jotfor.ms/static/formCss.css?3.3.12930" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="https://cdn.jotfor.ms/css/styles/nova.css?3.3.12930" />
<link type="text/css" media="print" rel="stylesheet" href="https://cdn.jotfor.ms/css/printForm.css?3.3.12930" />
<link type="text/css" rel="stylesheet" href="https://cdn.jotfor.ms/themes/CSS/566a91c2977cdfcd478b4567.css?"/>
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
</style>

<script src="https://cdn.jotfor.ms/static/prototype.forms.js" type="text/javascript"></script>
<script src="https://cdn.jotfor.ms/static/jotform.forms.js?3.3.12930" type="text/javascript"></script>
<script type="text/javascript">
   JotForm.init(function(){
      setTimeout(function() {
          $('input_10').hint('e.g. 1999');
       }, 20);
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
	<form class="jotform-form" action="sellItem" method="post" name="form_61226401662144" id="61226401662144" accept-charset="utf-8">
	  <input type="hidden" name="formID" value="61226401662144" />
	  <div class="form-all">
	    <ul class="form-section page-section">
	      <li id="cid_1" class="form-input-wide" data-type="control_head">
	        <div class="form-header-group">
	          <div class="header-text httal htvam">
	          	<h2 style="color:red">${ error }</h2>
	            <h2 id="header_1" class="form-header">
	              List Book to Sell
	            </h2>
	          </div>
	        </div>
	      </li>
	      <li class="form-line jf-required" data-type="control_textbox" id="id_3">
	        <label class="form-label form-label-left form-label-auto" id="label_3" for="input_3">
	          Auction Name
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        <div id="cid_3" class="form-input jf-required">
	          <input type="text" class=" form-textbox validate[required, AlphaNumeric]" data-type="input-textbox" id="input_3" name="auctionName" size="40" value="" maxlength="100" />
	        </div>
	      </li>
	      <li class="form-line jf-required" data-type="control_textbox" id="id_4">
	        <label class="form-label form-label-left form-label-auto" id="label_4" for="input_4">
	          Starting Bid
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        <div id="cid_4" class="form-input jf-required">
	          <input type="text" class=" form-textbox validate[required, Currency]" data-type="input-textbox" id="input_4" name="startingBid" size="20" value="0.01" maxlength="10" />
	        </div>
	      </li>
	      <li class="form-line jf-required" data-type="control_textbox" id="id_5">
	        <label class="form-label form-label-left form-label-auto" id="label_5" for="input_5">
	          Reserve Price
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        <div id="cid_5" class="form-input jf-required">
	          <input type="text" class=" form-textbox validate[required, Currency]" data-type="input-textbox" id="input_5" name="reservePrice" size="20" value="0.01" maxlength="10" />
	        </div>
	      </li>
	      <li class="form-line jf-required" data-type="control_textbox" id="id_6">
	        <label class="form-label form-label-left form-label-auto" id="label_6" for="input_6">
	          Author
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        <div id="cid_6" class="form-input jf-required">
	          <input type="text" class=" form-textbox validate[required, Alphabetic]" data-type="input-textbox" id="input_6" name="author" size="20" value="" maxlength="30" />
	        </div>
	      </li>
	      <li class="form-line jf-required" data-type="control_textbox" id="id_7">
	        <label class="form-label form-label-left form-label-auto" id="label_7" for="input_7">
	          Publisher
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        <div id="cid_7" class="form-input jf-required">
	          <input type="text" class=" form-textbox validate[required, Alphabetic]" data-type="input-textbox" id="input_7" name="publisher" size="20" value="" maxlength="30" />
	        </div>
	      </li>
	      <li class="form-line jf-required" data-type="control_textbox" id="id_8">
	        <label class="form-label form-label-left form-label-auto" id="label_8" for="input_8">
	          Book Title
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        <div id="cid_8" class="form-input jf-required">
	          <input type="text" class=" form-textbox validate[required, Alphabetic]" data-type="input-textbox" id="input_8" name="bookTitle" size="20" value="" maxlength="50" />
	        </div>
	      </li>
	      
	      <li class="form-line jf-required"  id="id_9"> 
	        <label class="form-label form-label-left form-label-auto" id="label_9" for="input_9">
	          Genre
	          <span class="form-required">
	            *
	          </span>	          	 
	        </label>
	        <select name="genre" id="genre">
    				<option value="fiction">Fiction</option>
    				<option value="nonfiction">Non-Fiction</option>
 			</select>
 			 
 				 
	      <div id="cid_9" class="form-input jf-required"> 
	        <input type = "hidden" name="genre"/>   
	        </div>
	     
	      </li> 
	      <li class="form-line jf-required" data-type="control_textbox" id="id_9.5">
	        <label class="form-label form-label-left form-label-auto" id="label_9.5" for="input_9.5">
	          Sub-Genre
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        	<select name="subgenre">
    				<option value="comedy">Comedy</option>
    				<option value="drama">Drama</option>
    				<option value="fantasy">Fantasy</option>
    				<option value="horror">Horror</option>
    				<option value="romance">Romance</option>
    				<option value="sci-fi">Sci-Fi</option>
    				<option value="biography">Biography</option>
    				<option value="documentary">Documentary</option>
    				<option value="textbook">Textbook</option>
 				</select>
	        <div id="cid_9.5" class="form-input jf-required">
	          <input type="hidden"  name="subgenre"/>
	        </div>
	      </li>
	     
	   <!--     <li class="form-line jf-required" data-type="control_textbox" id="id_9">  
	   		<li class="form-line jf-required"  id="id_9"> 
	        <label class="form-label form-label-left form-label-auto" id="label_9" for="input_9">
	          Genre
	          <span class="form-required">
	            *
	          </span>
	          	 <select name="genre">
    				<option value="fiction">fiction</option>
    				<option value="nonfiction">nonfiction</option>
 				 </select>
	        </label>
	        <div id="cid_9" class="form-input jf-required">
	        <input type="text" name="genre"/>
	         <input type="text" class=" form-textbox validate[required, Alphabetic]" data-type="input-textbox" id="input_9" name="genre" size="20" value="" maxlength="30"/>   
	        </div>
	      </li> 
	      <li class="form-line jf-required" data-type="control_textbox" id="id_9.5">
	        <label class="form-label form-label-left form-label-auto" id="label_9.5" for="input_9.5">
	          Sub-Genre
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        <div id="cid_9.5" class="form-input jf-required">
	          <input type="text" class=" form-textbox validate[required, Alphabetic]" data-type="input-textbox" id="input_9.5" name="subgenre" size="20" value="" maxlength="30"/>
	        </div>
	      </li>
	       -->

	      
	      <li class="form-line jf-required" data-type="control_textbox" id="id_10">
	        <label class="form-label form-label-left form-label-auto" id="label_10" for="input_10">
	          Print Year
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        <div id="cid_10" class="form-input jf-required">
	          <input type="text" class=" form-textbox validate[required, Numeric]" data-type="input-textbox" id="input_10" name="printYear" size="20" value="" maxlength="4" />
	        </div>
	      </li>
	      <li class="form-line jf-required" data-type="control_textbox" id="id_10.5">
	        <label class="form-label form-label-left form-label-auto" id="label_10.5" for="input_10.5">
	          Duration
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        <div id="cid_10.5" class="form-input jf-required">
	          <input type="text" class=" form-textbox validate[required, Numeric]" data-type="input-textbox" id="input_10.5" name="duration" size="10" value="" maxlength="1" />
	        </div>
	      </li>
	      <li class="form-line jf-required" data-type="control_textarea" id="id_11">
	        <label class="form-label form-label-left form-label-auto" id="label_11" for="input_11">
	          Description
	          <span class="form-required">
	            *
	          </span>
	        </label>
	        <div id="cid_11" class="form-input jf-required">
	          <div class="form-textarea-limit">
	            <span>
	              <textarea id="input_11" class="form-textarea validate[required]" name="description" cols="40" rows="6" maxlength="200"></textarea>
	              <div class="form-textarea-limit-indicator">
	                <span limit="200" type="Letters" minimum="-1" typeMinimum="None" id="input_11-limit">
	                  0/200
	                </span>
	              </div>
	            </span>
	          </div>
	        </div>
	      </li>
	      <li class="form-line" data-type="control_button" id="id_2">
	        <div id="cid_2" class="form-input-wide">
	          <div style="margin-left:156px" class="form-buttons-wrapper">
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
	  <input type="hidden" id="simple_spc" name="simple_spc" value="61226401662144" />
	  <script type="text/javascript">
	  document.getElementById("si" + "mple" + "_spc").value = "61226401662144-61226401662144";
	  </script>
	</form>
</body>
</html>
