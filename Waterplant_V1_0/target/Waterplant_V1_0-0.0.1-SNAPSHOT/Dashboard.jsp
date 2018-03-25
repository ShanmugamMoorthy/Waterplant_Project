<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="WaterPlant">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard - Water Plant</title>
<link rel="stylesheet"	href="css/responsive.dataTables.min.css">
<link rel="stylesheet"	href="css/dataTables.bootstrap.min.css">

<link rel="stylesheet"	href="css/bootstrap.min.css">
<link rel="stylesheet"	href="css/metisMenu.min.css">
<link rel="stylesheet"	href="css/font-awesome.min.css">

<link href="css/nv.d3.min.css" />

<script type="text/javascript"	src="external-js/jquery.min.js"></script>
<script type="text/javascript"  src="external-js/angular.min.js"></script>	

<script type="text/javascript" charset="utf8"	src="external-js/jquery.dataTables.js"></script>

<script	src="external-js/bootstrap.min.js"></script>
<script	src="external-js/jquery.dataTables.min.js"></script>
<script	src="external-js/dataTables.bootstrap.min.js"></script>
<script	src="external-js/dataTables.responsive.min.js"></script>
<script	src="external-js/metisMenu.min.js"></script>
<script	src="external-js/d3.min.js"></script>
<script	src="external-js/nv.d3.min.js"></script>
<script	src="external-js/angular-nvd3.min.js"></script>
<script	src="JS/Dashboard.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
	
<script>
	$(document).ready(function() {
		$('#masterload').load("Menu.jsp");
	});
</script>
</head>
<body ng-controller="DashboardCtrl">
	<div id="masterload"></div>

	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-6">
				<p>From: <input type="text" id="datepickerFrom" style="width:120px"> &nbsp &nbsp To: <input type="text" id="datepickerTo" style="width:120px"> &nbsp &nbsp <button type="button" ng-click="dateApply()" class="btn btn-primary" style="height:31px">Submit</button></p>
				<div class="col-md-12">
					<nvd3 options="options" data="data"></nvd3>
				</div>
			</div>
			<div class="col-lg-6">
				<canvas id="barChart"></canvas>
			</div>
		</div>
		<br><br>
		<div class="row">
			<div class="col-lg-12">
			<h3>Raw Material</h3>
			<table id="example" class="table table-striped table-bordered" style="width:100%">
		        <thead>
		            <tr>
		                <th>S.No</th>
		                <th>Prod_Name</th>
		                <th>Quantity</th>
		                <th>Price</th>
		                <th>Date</th>
		            </tr>
		        </thead>
	        	<tbody>
		            <tr>
		                <td>01</td>
		                <td>Anti-Scale Chemical</td>
		                <td>61</td>
		                <td>320,800</td>
		                <td>2018/03/15</td>
		            </tr>
		            <tr>
		                <td>02</td>
		                <td>Cap</td>
		                <td>63</td>
		                <td>170</td>
		                <td>2018/02/25</td>
		            </tr>
	            </tbody>
            </table>
		</div>
		</div>
		<script type="text/javascript">
		
		</script>
	</div>
	<script	src="external-js/excanvas.min.js"></script>
	<script	src="external-js/jquery.flot.js"></script>
	<script	src="external-js/jquery.flot.pie.js"></script>
	<script	src="external-js/jquery.flot.resize.js"></script>
	<script	src="external-js/jquery.flot.time.js"></script>
	<script	src="external-js/jquery.flot.tooltip.js"></script>
	</body>
</html>