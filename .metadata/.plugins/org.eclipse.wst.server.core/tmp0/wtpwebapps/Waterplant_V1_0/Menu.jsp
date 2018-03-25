<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Water Plant - Home</title>
<link rel="stylesheet"	href="css/bootstrap.min.css">
<link rel="stylesheet"	href="css/metisMenu.min.css">
<link rel="stylesheet"	href="css/font-awesome.min.css">
<link rel="stylesheet"	href="css/CustomStyle.css">
<link rel="stylesheet"	href="css/CarouselStyle.css">
<script	src="external-js/jquery.min.js"></script>	
<script	src="external-js/bootstrap.min.js"></script>
<script	src="external-js/metisMenu.min.js"></script>
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
		<div class="navbar-header ">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="Menu.jsp">Water Plant</a>
			
		</div>

		<div class="navbar-default sidebar" role="navigation" >
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu" style="color:white">
					<li class="sidebar-search">
					
					
						<div class="input-group custom-search-form form-group">
							<span class="input-group-addon"><i class="fa fa-search"></i></span> <input type="text" class="form-control" placeholder="Search...">
						</div>
					</li>
					
					<li><a href="Dashboard.jsp"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
						
							
					<li><a href="Rawmaterial.jsp"><i class="fa fa-briefcase"></i> Raw Material</a>
			 
					</li>
					
					<li><a href="Stock.jsp"><i class="fa fa-bar-chart-o fa-fw"></i> Stock</a>
					</li>
					
					<li><a href="Billing.jsp"><i class="fa fa-credit-card fa-fw"></i> Billing</a></li>
					
					<li><a href="Expense.jsp"><i class="fa fa-inr fa-fw"></i> Expense</a></li>
					
					<li><a href="Customer.jsp"><i class="fa fa-address-book fa-fw"></i> Customer</a></li>
				</ul>
			</div>
		</div>
		
		<div id="session">
		<p class="navbar-brand" style="font-size:13px">Welcome, <b><%=session.getAttribute("username") %></b></p>
		</div>
		
	<div id="button">
	<button id="logout" class="navbar-brand btn btn-link" style="font-size:14px"><i class="fa fa-sign-out"> Logout</i></button>
	</div>
	 </nav>
	
	 
	</div>
<!-- 	<div id="masterhead"> -->
<!-- 	<div class="container"> -->
<!-- 		<div class="slideshow"> -->
<!-- 			<div id="slideshow" class="carousel slide" data-ride="carousel"> -->
<!-- 				<div class="carousel-inner"> -->
<!-- 					<h3 align="middle">WaTeR PlAnT</h3> -->
<!-- 					<div class="item active"> -->
<!-- 						<img class="img-responsive" src="http://mechmannindia.com/images/magazine/30.jpg" alt="First Slide"> -->
<!-- 							<div class="container"> -->
<!-- 								<div class="carousel-caption"> -->
<!-- 									<h1>Filling</h1> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 					</div>/. Item Active -->
<!-- 					<div class="item"> -->
<!-- 						<img class="img-responsive" src="https://ak4.picdn.net/shutterstock/videos/15852514/thumb/1.jpg" width="50px" alt="Second Slide"> -->
<!-- 							<div class="container"> -->
<!-- 								<div class="carousel-caption"> -->
<!-- 									<h1>Packing</h1> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 					</div>/. Item -->
<!-- 					<div class="item"> -->
<!-- 						<img class="img-responsive" src="https://www.dewarplumbers.ie/wp-content/uploads/2014/04/canstockphoto-water-bottle.jpg" alt="Third slide"> -->
<!-- 							<div class="container"> -->
<!-- 								<div class="carousel-caption"> -->
<!-- 									<h1>Drinking</h1> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 					</div>/. Item -->
<!-- 				</div>/. Carousel-Inner -->
<!-- 				<div class="controlsBlock"> -->
<!-- 					<div class="controls"> -->
<!-- 						<a class="left carousel-control" href="#slideshow" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> -->
<!-- 						<a class="right carousel-control" href="#slideshow" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a> -->
<!-- 						<a class="left carousel-control" href="#slideshow" data-slide="prev"><i class="fa fa-chevron-left"></i></a>
<!--  						<a class="right carousel-control" href="#slideshow" data-slide="next"><i class="fa fa-chevron-right"></i></a>--> 
<!-- 						<div class="carousel-indicators"> -->
<!-- 							<li data-target="#slideshow" data-slide-to="0" class="active"></li> -->
<!-- 							<li data-target="#slideshow" data-slide-to="1"></li> -->
<!-- 							<li data-target="#slideshow" data-slide-to="2"></li> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div>/# Slideshow .Carousel -->
<!-- 		</div>/. Slideshow -->
<!-- 	</div>/. Container -->
<!-- </div>/# Mastehead -->
	
	<script>
	$('#side-menu').metisMenu();
	$(document).ready(function(){
		$('#logout').click(function(){
			$.post("Logout",function(response){
				window.location.href="Homepage.jsp";
			});
		});
	});
// 	$('#slideshow').carousel({
//         interval: 5000
//      });
	</script>
</body>
</html>