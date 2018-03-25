<%@page import="org.we5.waterplant.javaclass.ExpenseDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="WaterPlantExpense">
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

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<title>Expense - Water Plant</title>
<script type="text/javascript">
	$(document).ready(function(){	
	 /* $.post('Expense',{"ExpenseType":"ExpenseTypeDropDown"}, function(response) {
			$.each(response, function(key, value) {
				$("<option>").val(value).text(value).appendTo('#ExpenseType');
			});
		}); 
	 */	
	 $('#masterload').load("Menu.jsp");
	});
</script>
</head>

<body ng-controller="myExpenseController">
<div id="masterload"></div>
<div id="page-wrapper">
	<div class="container col-md-10 form-group">
		<div class="row" style="margin-top: 20px;">
			<table id="mytable" class="table table-striped table-bordered" datatable="ng" dt-options="dtOptions">
				<thead>
					<tr>
						<th>Expense</th>
						<th>Quantity</th>
						<th>price</th>
						<th>Date</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="user in ExpenseDatalist">
						<td>{{user.Expense_Type}}</td>
						<td>{{user.Quantity}}</td>
						<td>{{user.Price}}</td>
						<td>{{user.Date}}</td>
						<td><button class="btn btn-primary" ng-click="updateData(user)"><i class="glyphicon glyphicon-edit"></i></button>  <button class="btn btn-danger" ng-click="removeData(user.expenseId)"><i class="glyphicon glyphicon-trash"></i></button></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row" style="margin-top: 20px;">
		<div class="col-md-offset-5 col-md-3">
				<input type="button" class="btn btn-default" data-toggle="modal" id="btn"
					value="Add Expense" data-target="#myModal">
		</div>
			<form name="myForm">
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content ">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Add Expense</h4>
							</div>

							<div class="modal-body  ">
								<center>
									<div class="input-group col-sm-8 form-group">
										<span class="input-group-addon " ng-style="myForm.expenseType.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
											class="glyphicon glyphicon-shopping-cart"></i> </span> 
											<!-- <select name="ExpenseType" id="ExpenseType" class="form-control">
											<option>--Select Expense Type--</option></select> -->
											<select ng-model="expense.selectedDropItem" name="expenseType" class="form-control" ng-style="myForm.expenseType.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
												ng-options="item as item for item in ExpenseDropList"
												ng-change="getDropChanged(expense.selectedDropItem)" required>
												<option value="">-- select --</option>
											</select>
										</div>
									<div class="input-group col-sm-8 form-group">
										<span class="input-group-addon " ng-style="myForm.Quantity.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
											class="glyphicon glyphicon-equalizer"></i> </span> <input ng-style="myForm.Quantity.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
											class="form-control" ng-model="expense.Quantity" name="Quantity" size="14" type="number"
											placeholder="Quantity" required />
									</div>
									<div class="input-group col-sm-8 form-group">
										<span class="input-group-addon " ng-style="myForm.Price.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
											class="glyphicon glyphicon-usd"></i> </span> <input ng-style="myForm.Price.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
											class="form-control" ng-model="expense.Price" name="Price" size="14" type="number" placeholder="Price" required />
									</div>
									<div class="input-group col-sm-8 form-group">
										<span class="input-group-addon " ng-style="myForm.Date.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
											class="glyphicon glyphicon-calendar"></i> </span> <input type="date" id="datepicker" ng-style="myForm.Date.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
											class="form-control" ng-model="expense.Date" ng-focus="showDatePick()" name="Date"  placeholder="Date" required />
									</div>
								</center>
							</div>
							<div class="modal-footer">
								<button type="submit" name="Add" ng-click="addExpense(expense)" value="Add"
									class="btn btn-info" ng-disabled="myForm.expenseType.$invalid || myForm.Quantity.$invalid || myForm.Price.$invalid || myForm.Date.$invalid">Add</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal" ng-click="expense={};myForm.$setPristine();expense.Date = new Date();">Close</button>
							</div>
						</div>

					</div>
				</div>
			</form>
	</div>
	</div>
	</div>
	<script src="JS/Expense.js"></script>
	<script src="JS/moment.js"></script>
	
	<script type="text/ng-template" id="editExpense.html">
<form name="myForm">
		<div class="modal-header bg-primary">
			<h3 class="modal-title">Update Data</h3>
		</div>
		<div class="modal-body">
			<div class="input-group col-sm-8 form-group">
	<span class="input-group-addon " ng-style="myForm.expenseType.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
		class="glyphicon glyphicon-shopping-cart"></i> </span> 
		<!-- <select name="ExpenseType" id="ExpenseType" class="form-control">
		<option>--Select Expense Type--</option></select> -->
		<select ng-model="expense.selectedDropItem" class="form-control" name="expenseType" ng-style="myForm.expenseType.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
			ng-options="item as item for item in ExpenseDropList"
			ng-change="getDropChanged(expense.selectedDropItem)">{{item}}
			<option value="">-- select --</option>
		</select>
	</div>
<div class="input-group col-sm-8 form-group">
	<span class="input-group-addon " ng-style="myForm.Quantity.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
		class="glyphicon glyphicon-equalizer"></i> </span> <input ng-style="myForm.Quantity.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
		class="form-control" ng-model="expense.Quantity" name="Quantity" size="14" type="number"
		placeholder="Quantity" required />
</div>
<div class="input-group col-sm-8 form-group">
	<span class="input-group-addon " ng-style="myForm.Price.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
		class="glyphicon glyphicon-usd"></i> </span> <input ng-style="myForm.Price.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
		class="form-control" ng-model="expense.Price" name="Price" size="14" type="number" placeholder="Price" required />
</div>

<div class="input-group col-sm-8 form-group">
										<span class="input-group-addon " ng-style="myForm.Date.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"><i
											class="glyphicon glyphicon-calendar"></i> </span> <input type="text" id="datepicker" ng-style="myForm.Date.$invalid && {'border-color':'red','color':'red'} || {'border-color':'','color':''}"
											class="form-control" ng-model="expense.Date" ng-focus="showDatePick()" name="Date"  placeholder="Date" required />
									</div>
		</div>
		<div class="modal-footer">
			<button class="btn btn-primary" ng-click="update(expense);myForm.$setPristine()" ng-disabled="myForm.expenseType.$invalid || myForm.Quantity.$invalid || myForm.Price.$invalid || myForm.Date.$invalid">Edit</i></button>  
			<button class="btn btn-danger" ng-click="cancel();expense={};myForm.$setPristine()">Cancel</i></button>
		</div>
</form>
	</script>
</body>
<style>
#datepicker{z-index:1151 !important;}
</style>


 <!-- <script>
	$(document).ready(function() {
		$('#mytable').DataTable({
			responsive : true
		});
	});
</script> -->
 </html>