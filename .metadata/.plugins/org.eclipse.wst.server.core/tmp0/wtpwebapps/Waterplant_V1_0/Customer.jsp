<%@page import="org.we5.waterplant.javaclass.CustomerVo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="WaterPlantCustomer">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"	href="css/responsive.dataTables.min.css">
<link rel="stylesheet"	href="css/dataTables.bootstrap.min.css">
<link rel="stylesheet"	href="css/bootstrap.min.css">
<link rel="stylesheet"	href="css/metisMenu.min.css">
<link rel="stylesheet"	href="css/font-awesome.min.css">

<script	src="external-js/jquery.min.js"></script>	
<script	src="external-js/angular.min.js"></script>	
<script	src="external-js/bootstrap.min.js"></script>
<script	src="external-js/jquery.dataTables.min.js"></script>
<script	src="external-js/dataTables.bootstrap.min.js"></script>
<script	src="external-js/dataTables.responsive.min.js"></script>
<script	src="external-js/metisMenu.min.js"></script>
	
<script type="text/javascript" src="external-js/ui-bootstrap-tpls.js"></script>
<script type="text/javascript" src="JS/moment.js"></script>
<script src="JS/angular-datatables.min.js"></script>

<title>Customer - Water Plant</title>
<script type="text/javascript">
	$(document).ready(function(){
	 $('#masterload').load("Menu.jsp");
	});
</script>
</head>

<body ng-controller="customerController">
<div id="masterload"></div>
<center><h4>Customer</h4></center>
<div id="page-wrapper">
	<div class="container col-md-10 form-group">
		<div class="row" style="margin-top: 20px;">
			<table class="table table-striped table-bordered" datatable="ng" dt-options="dtOptions">
				<thead>
					<tr>
						<th>Name</th>
						<th>Company</th>
						<th>Mobile</th>
						<th>Email</th>
						<th>Credit</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="user in customerDatalist">
						<td>{{user.name}}</td>
						<td>{{user.company}}</td>
						<td>{{user.mobileNumber}}</td>
						<td>{{user.email}}</td>
						<td>{{user.credit}}</td>
						<td><button class="btn btn-primary" ng-click="updateData(user)"><i class="glyphicon glyphicon-edit"></i></button>  <button class="btn btn-danger" ng-click="removeData(user.code)"><i class="glyphicon glyphicon-trash"></i></button></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row" style="margin-top: 20px;">
		<div class="col-md-offset-5 col-md-3">
				<input type="button" class="btn btn-default" data-toggle="modal" id="btn"
					value="Add Customer" data-target="#myModal">
		</div>
			<form action="Customer" method="post" name="myForm" novalidate>
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content ">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add Customer</h4>
							</div>

							<div class="modal-body  ">
								<center>
			<div class="input-group col-sm-8 form-group">
				<span class="input-group-addon " ng-style="myForm.Name.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
					class="glyphicon glyphicon-user"></i> </span> 
					<input ng-style="myForm.Name.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
					class="form-control" ng-model="customer.name" name="Name" type="text"
					placeholder="Name" required />
					<input class="form-control" ng-model="customer.code" name="CustId" type="hidden" />
				</div>
			<div class="input-group col-sm-8 form-group">
				<span class="input-group-addon " ng-style="myForm.Company.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
					class="glyphicon glyphicon-blackboard"></i> </span> <input ng-style="myForm.Company.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
					class="form-control" ng-model="customer.company" name="Company" type="text"
					placeholder="Company" required />
			</div>
			<div class="input-group col-sm-8 form-group">
				<span class="input-group-addon " ng-style="myForm.Email.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
					class="glyphicon glyphicon-envelope"></i> </span> <input ng-style="myForm.Email.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
					class="form-control" ng-model="customer.email" name="Email" type="email" placeholder="Email" required />
			</div>
			<div class="input-group col-sm-8 form-group">
				<span class="input-group-addon " ng-style="myForm.mobileNumber.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
					class="glyphicon glyphicon-phone-alt"></i> </span> <input type="tel" ng-style="myForm.mobileNumber.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
					class="form-control" ng-model="customer.mobileNumber" name="mobileNumber"  placeholder="Mobile Number" required />
			</div>
			<div class="input-group col-sm-8 form-group">
				<span class="input-group-addon " ng-style="myForm.Credit.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
					class="glyphicon glyphicon-usd"></i> </span> <input type="number" ng-style="myForm.Credit.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
					class="form-control" ng-model="customer.credit" name="Credit" placeholder="Credit" required />
			</div>
								</center>
							</div>
							<div class="modal-footer">
								<button type="submit" name="Add" ng-click="addCustomer(customer)" value="Add"
									data-dismiss="modal" class="btn btn-info" ng-disabled="myForm.Name.$invalid || myForm.Company.$invalid || myForm.Email.$invalid || myForm.mobileNumber.$invalid || myForm.Credit.$invalid">Add</button>
								<button type="button" class="btn btn-default" ng-click="customer={};"
									data-dismiss="modal">Close</button>
							</div>
						</div>

					</div>
				</div>
			</form>
	</div>
	</div>
	</div>
	<script src="JS/Customer.js"></script>
	<script src="JS/moment.js"></script>
	
	<script type="text/ng-template" id="editCustomer.html">
<form name="myForm">		
<div class="modal-header bg-primary">
			<h3 class="modal-title">Update Data</h3>
		</div>
		<div class="modal-body">
			<div class="input-group col-sm-8 form-group">
	<span class="input-group-addon " ng-style="myForm.Name.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
		class="glyphicon glyphicon-user"></i> </span> 
		<input ng-style="myForm.Name.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
		class="form-control" ng-model="customer.name" name="Name" type="text"
		placeholder="Name" required />
<input class="form-control" ng-model="customer.code" name="CustId" type="hidden" />
	</div>
<div class="input-group col-sm-8 form-group">
	<span class="input-group-addon " ng-style="myForm.Company.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
		class="glyphicon glyphicon-blackboard"></i> </span> <input ng-style="myForm.Company.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
		class="form-control" ng-model="customer.company" name="Company" type="text"
		placeholder="Company" required />
</div>
<div class="input-group col-sm-8 form-group">
	<span class="input-group-addon " ng-style="myForm.Email.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
		class="glyphicon glyphicon-envelope"></i> </span> <input ng-style="myForm.Email.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
		class="form-control" ng-model="customer.email" name="Email" type="email" placeholder="Email" required />
</div>
<div class="input-group col-sm-8 form-group">
	<span class="input-group-addon " ng-style="myForm.mobileNumber.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
		class="glyphicon glyphicon-phone-alt"></i> </span> <input type="tel" ng-style="myForm.mobileNumber.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
		class="form-control" ng-model="customer.mobileNumber" name="mobileNumber"  placeholder="Mobile Number" required />
</div>
<div class="input-group col-sm-8 form-group">
	<span class="input-group-addon " ng-style="myForm.Credit.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
		class="glyphicon glyphicon-usd"></i> </span> <input type="number" ng-style="myForm.Credit.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
		class="form-control" ng-model="customer.credit" name="Credit" placeholder="Credit" required />
</div>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" ng-click="update(customer);" ng-disabled="myForm.Name.$invalid || myForm.Company.$invalid || myForm.Email.$invalid || myForm.mobileNumber.$invalid || myForm.Credit.$invalid">Edit</i></button>  
			<button class="btn btn-danger" ng-click="cancel()">Cancel</i></button>
		</div>
</form>
	</script>
</body>


<!-- <script>
	$(document).ready(function() {
		$('#mytable').DataTable({
			responsive : true
		});
	});
</script>
 -->
 </html>