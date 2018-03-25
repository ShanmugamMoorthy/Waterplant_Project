<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%-- 	<jsp:include page="MasterPage.jsp" /> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"	href="css/bootstrap.min.css">
<link rel="stylesheet"	href="css/HomePageStyle.css">
<script	src="external-js/jquery.min.js"></script>
<script	src="external-js/bootstrap.min.js"></script>
<script>
		$(document).ready(function() {
			$('#Login').click(function() {
				var logindata = {
					username : $('#username').val(),
					password : $('#password').val(),
					keyword : "logincheck"
				};
				$.post('Login', $.param(logindata), function(response) {
					if(response != "Invalid"){
						window.location.href="Menu.jsp";
					}
					else{
						alert("Invalid Credentials");
					}
				});
			});
		});
</script>
<title>Home Page - Water Plant</title>
</head>
<body>
	<div class = "container col-md-12">
  		<div class="col-md-6">
    		<div id="myCarousel" class="carousel slide" data-ride="carousel">
      			<div class="carousel-inner" role="listbox">
        			<div class="item active">
          				<img class="first-slide" src="images/1.jpg" alt="First slide">
          				<!-- In case you want some caption on your slide, uncomment this section here
          				<div class="container">
            				<div class="carousel-caption">
              					<h1>Example headline.</h1>
              					<p>Note: If you're viewing this page via a <code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.</p>
            				</div>
          				</div>-->
        			</div>
        			<div class="item">
          				<img class="second-slide" src="http://xtribune.com/wp-content/uploads/2015/06/o-BOTTLED-WATER-facebook.jpg" alt="Second slide">
          				<!-- In case you want some caption with button on your slide, uncomment this section here          
          				<div class="container">
            				<div class="carousel-caption">
            		 			<h1>Another example headline.</h1>
              					<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
              					<p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p>
            				</div>
          				</div> -->
        			</div>
        			<div class="item">
          				<img class="third-slide" src="images/21_2_1.jpg" alt="Third slide">
          				<!-- In case you want some caption with button on your slide, uncomment this section here              
          				<div class="container">
          					<div class="carousel-caption">
              					<h1>One more for good measure.</h1>
              					<p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
              					<p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p>
            				</div>
          				</div> -->
        			</div>
      			</div>
      			<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        			<span class="sr-only">Previous</span>
      			</a>
      			<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        			<span class="sr-only">Next</span>
      			</a>
    		</div><!-- /.carousel -->
  		</div>
  		<div class="col-md-6">
              <h2 class="featurette-heading">Water Plant Automation <span class="text-muted">It'll blow your mind.</span></h2>
          	  <p class="lead">Welcome to the interactive site of Water Plant. A place where you can
			visualize how process works.It is the one which makes your work more easier and faster forever.<br>To further explore, kindly <a
				data-toggle="modal" data-target="#myModal">LOGIN HERE</a></p>
        </div>
		<br>
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content ">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Login</h4>
					</div>
					<div class="modal-body  ">
						<center>
							<div class="input-group col-sm-8">

								<span class="input-group-addon "><i
									class="glyphicon glyphicon-user"></i> </span> <input
									class="form-control " name="username" id="username" size="14"
									placeholder="Enter Username" required autofocus />
							</div>
							<br>
							<div class="input-group col-sm-8">
								<span class="input-group-addon "><i
									class="glyphicon glyphicon-briefcase"></i> </span> <input
									name="password" class="form-control " id="password"
									type="password" size="14" placeholder="Enter Password" required />
							</div>
						</center>
					</div>
					<div class="modal-footer">
						<button type="button" name="action" value="Login" id="Login"
							class="btn btn-info">Login</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>