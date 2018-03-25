<%@page import="org.we5.waterplant.javaclass.GetRawmaterial"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"	href="css/responsive.dataTables.min.css">
<link rel="stylesheet"	href="css/dataTables.bootstrap.min.css">

<link rel="stylesheet"	href="css/bootstrap.min.css">
<link rel="stylesheet"	href="css/metisMenu.min.css">
<link rel="stylesheet"	href="css/font-awesome.min.css">

<script	src="external-js/jquery.min.js"></script>
<script	src="external-js/bootstrap.min.js"></script>
<script	src="external-js/jquery.dataTables.min.js"></script>
<script	src="external-js/dataTables.bootstrap.min.js"></script>
<script	src="external-js/dataTables.responsive.min.js"></script>
<script	src="external-js/metisMenu.min.js"></script>

<script src="JS/RawmaterialScript.js"></script>
<script>
$(document).ready(function(){
    $('#excelbutton').click(function(){
        var url='data:application/vnd.ms-excel,' + encodeURIComponent($('#mytable').html()) 
        location.href=url
        return false
    });
});
</script>
<title>Raw material - Water Plant</title>
</head>
<body>
	<div id="masterload"></div>
	<div id="page-wrapper">
		<div class="container col-md-10 form-group">
			<div class="row panel panel-default"
				style="margin-top: 20px; padding: 20px">
				<br>
				<table width="100%"
					class="panel panel-default table table-striped table-bordered table-hover"
					id="mytable">
					<thead>
						<tr>
							<th>ID</th>
							<th>Vendor Name</th>
							<th>Product Name</th>
							<th>Product Description</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Action</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="col-md-offset-4 form-group">
				<input type="button" class="btn btn-primary col-md-3"
					data-toggle="modal" value="Add Product" data-target="#myModal">&nbsp;
				<%
					if (session.getAttribute("checkrole").equals("Admin")) {
				%>
				<button type="button"
					class="col-md-offset-1 btn btn-primary col-md-3"
					data-toggle="modal" data-target="#myModal3">Add Vendor</button>
				<%
					}
				%>
				<button type="button" id="excelbutton" class="btn btn-primary col-md-3">Export</button>
			</div>
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content ">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Add Product</h4>
						</div>

						<div class="modal-body  ">
							<center>
								<!-- 								<div class="input-group col-sm-8 form-group"> -->
								<!-- 									<span class="input-group-addon "><i -->
								<!-- 										class="glyphicon glyphicon-user"></i> </span> <input -->
								<!-- 										class="form-control" name="vendorname" id="addvendor" -->
								<!-- 										size="14" placeholder="Vendor Name" required autofocus /> -->
								<!-- 								</div> -->
								<div class="input-group col-sm-8 form-group">
									<span class="input-group-addon "><i
										class="glyphicon glyphicon-user"></i> </span> <select
										name="vendordrop" id="vendordrop" class="form-control">
										<option>--Select Vendor --</option>
									</select>
								</div>
								<div class="input-group col-sm-8 form-group">
									<span class="input-group-addon "><i
										class="glyphicon glyphicon-shopping-cart"></i> </span> <select
										name="dropdown" id="dropdown" class="form-control">
										<option>--Select Product--</option>
									</select>
								</div>
								<div class="input-group col-sm-8 form-group">
									<span class="input-group-addon "><i
										class="glyphicon glyphicon-equalizer"></i> </span>
									<textarea class="form-control" name="adddesc" id="adddesc"
										size="14" rows="2" cols="50" placeholder="Description"
										required></textarea>
								</div>
								<div class="input-group col-sm-8 form-group">
									<span class="input-group-addon "><i
										class="glyphicon glyphicon-equalizer"></i> </span> <input
										class="form-control" name="quantity" id="addquantity"
										size="14" type="number" placeholder="Quantity" min="1"
										required />
								</div>
								<div class="input-group col-sm-8 form-group">
									<span class="input-group-addon "><i
										class="glyphicon glyphicon-usd"></i> </span> <input
										class="form-control" name="price" id="addprice" size="14"
										type="number" placeholder="Price" min="1" required />
								</div>
							</center>
						</div>
						<div class="modal-footer">
							<button type="button" id="addbutton" name="Add" value="Add"
								class="btn btn-info">Add</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="myModal2" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content ">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Edit Product</h4>
						</div>

						<div class="modal-body">
							<center>
								<div class="input-group col-sm-8 form-group">
									<span class="input-group-addon "><i
										class="glyphicon glyphicon-user"></i> </span> <input
										class="form-control" name="vendorname" id="venname" size="14"
										placeholder="Vendor Name" required autofocus />
								</div>
								<div class="input-group col-sm-8 form-group">
									<span class="input-group-addon "><i
										class="glyphicon glyphicon-shopping-cart"></i> </span> <select
										name="dropdown" id="dropdown2" class="form-control">
										<option>--Select Product--</option>
									</select>
								</div>
								<div class="input-group col-sm-8 form-group">
									<span class="input-group-addon "><i
										class="glyphicon glyphicon-equalizer"></i> </span>
									<textarea class="form-control" name="editdesc" id="editdesc"
										size="14" rows="2" cols="50" placeholder="Description"
										required></textarea>
								</div>
								<div class="input-group col-sm-8 form-group">
									<span class="input-group-addon "><i
										class="glyphicon glyphicon-equalizer"></i> </span> <input
										class="form-control" name="quantity" size="14" id="qtyname"
										type="number" placeholder="Quantity" min="1" required />
								</div>
								<div class="input-group col-sm-8 form-group">
									<span class="input-group-addon "><i
										class="glyphicon glyphicon-usd"></i> </span> <input
										class="form-control" name="price" size="14" id="priname"
										type="number" placeholder="Price" min="1" required />
								</div>
							</center>
						</div>
						<div class="modal-footer">
							<button type="button" name="Add" value="Add" id="editclick"
								class="btn btn-info">Edit</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal3" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Add Vendor</h4>
				</div>

				<div class="modal-body">
					<center>
						<div class="input-group col-sm-8 form-group">
							<span class="input-group-addon "><i
								class="glyphicon glyphicon-user"></i> </span> <input
								class="form-control" name="vendorname" id="newvenname" size="14"
								placeholder="Vendor Name" required autofocus />
						</div>
						<div class="input-group col-sm-8 form-group">
							<span class="input-group-addon "><i
								class="glyphicon glyphicon-shopping-cart"></i> </span> <input
								class="form-control" name="companyname" id="newcompanyname"
								size="14" placeholder="Company Name" required />
						</div>
						<div class="input-group col-sm-8 form-group">
							<span class="input-group-addon "><i
								class="glyphicon glyphicon-equalizer"></i> </span> <input
								class="form-control" name="email" size="14" id="newemail"
								type="email" placeholder="Email" required />
						</div>
						<div class="input-group col-sm-8 form-group">
							<span class="input-group-addon "><i
								class="glyphicon glyphicon-usd"></i> </span> <input
								class="form-control" name="mobilenumber" size="14"
								id="newmobilenumber" type="number" placeholder="Mobile Number"
								required />
						</div>
					</center>
				</div>
				<div class="modal-footer">
					<button type="button" name="Add" value="Add" id="addnewvendor"
						class="btn btn-info">Add</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
</body>
</html>