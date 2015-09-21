<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>



<!DOCTYPE html>
<html lang="en">
  
<head>
    <meta charset="utf-8">
    <title>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Le styles -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <style>
      body { padding-top: 60px; /* 60px to make the container go all the way
      to the bottom of the topbar */ }
    </style>
    <link href="assets/css/bootstrap-responsive.css" rel="stylesheet">
    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js">
      </script>
    <![endif]-->
    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
    <style>
    </style>
    
    <script src="assets/js/gl.js"></script>
    
  </head>
  <body  onload="glrun('triangles',true);updateBombs()">
    <div class="navbar navbar-fixed-top navbar-inverse">
      <div class="navbar-inner">
        <div class="container">
          <a class="brand" href="addGoal.html">
            Get started
          </a>
          <ul class="nav">
          </ul>
        </div>
      </div>
    </div>
    <div class="container">
      <div id="left_panel" class="hero-unit"  style="float: left; display: inline;">
   
          
<h1>${boom}</h1>
<div>
        
        <a class="btn btn-primary" href="addBomb.html"  onclick="updateBombs()">
          Add Bomb
        </a>
        
        <a class="btn btn-primary" href="defuse.html">
          defuse!
        </a>
        
         <a class="btn btn-primary" href="cleanUp.html">
          Collect Garbage
        </a>
        
        <!--  - <a class="btn btn-primary" href="#" onclick="updateBombs()">
          Update
        </a> -->
       <p id="error_log">${errors}</p>
        <p id="update_test">${bombs}</p>
         
      </div>
   </div>
   
      <div style="float: left; display: inline;">
       <canvas id="gl">
            
        </canvas>
        <br/>
<form id="myForm">
<button type="button" onclick="glrun('triangles',false)">GL_TRIANGLES</button>
<button type="button" onclick="glrun('wireframe',false)">GL_LINE_STRIP</button>
<button type="button" onclick="glrun('points',false)">GL_POINTS</button>
<button type="button" onclick="toggleBackground()">Background</button>
<button type="button" onclick="showLog()">See Log</button>
</form>
   
      </div>
     

</div>
    
    <script>
    function updateBombs() {
    	//alert("Update");//.setAttribute("update_test", "updated");
  
  
		$(document).ready(
			function() {
				$.getJSON('<spring:url value="updateBombs.json"/>', {
					ajax : 'true'
				}, function(data){
					var html = '';
					var len = data.length;
					for (var i = 0; i < len; i++) {
						html += '<br/>' + data[i].description;//.toString
					}
					
					
					$('p#update_test').html(html);
				});
				
			});
		 window.requestAnimationFrame(updateBombs);
    }
    
    
		
	</script>
    	
    	
   
  
    <script src="jquery.js">
    </script>
     <script src="http://malsup.github.com/jquery.form.js"></script> 
    
    
    <script src="assets/js/bootstrap.js">
    </script>
</body>
</html>