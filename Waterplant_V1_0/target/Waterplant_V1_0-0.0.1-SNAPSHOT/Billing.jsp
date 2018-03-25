<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="WaterPlant">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"	href="css/responsive.dataTables.min.css">
<link rel="stylesheet"	href="css/dataTables.bootstrap.min.css">
<link rel="stylesheet"	href="css/bootstrap.min.css">
<link rel="stylesheet"	href="css/metisMenu.min.css">
<link rel="stylesheet"	href="css/font-awesome.min.css">
<link rel="stylesheet" href="JS/Autocomplete/angucomplete.js">

<script	src="external-js/jquery.min.js"></script>
<script	src="external-js/bootstrap.min.js"></script>
<script	src="external-js/jquery.dataTables.min.js"></script>
<script	src="external-js/dataTables.bootstrap.min.js"></script>
<script	src="external-js/dataTables.responsive.min.js"></script>
<script	src="external-js/metisMenu.min.js"></script>
<script	src="external-js/angular.min.js"></script>
<script src="JS/Billing.js"></script>
<script src="JS/typeahead.bundle.js"></script>
<script src="JS/Autocomplete/angucomplete.js"></script>
<script>
	$(document).ready(function() {
		$('#masterload').load("Menu.jsp");
	});
</script>
<title>Billing</title>
</head>
<body ng-controller="BillingCtrl">

<div id="masterload"></div>
<!-- Place the code inside the below div tag -->
<div id="page-wrapper">
	<div  class="col-md-10">

          <!--  <button ng-click="FromServlet()">Fetch data from server</button>
           <p>First Name : {{person.CustName}}</p>
           <p>Last Name : {{person.price}}</p> -->
     
		<div class="row col-sm-12" ng-hide="printMode">
			<div class="col-sm-3"></div>
			<div class="col-sm-6">
				<div class="panel panel-primary">
					<div class="panel-heading text-center">Billing</div>
					<div class="panel-body">
						<form role="form" name="myproduct" ng-submit="submitForm()" novalidate>
							<div class="row form-group" >
								<div class="col-sm-6 ">
									<label>Product</label>
								</div>
								<div class="col-sm-6 dropdown" ng-class="{ 'has-error' : myproduct.p_pro.$dirty  && myproduct.p_pro.$invalid }">
									<select class="form-control" ng-model="Product" name="p_pro"
										ng-options="x for (x, y) in Products" required>
										<option value="" disabled>- Select Product -</option>
									</select>
									<p ng-show="myproduct.p_pro.$dirty  && myproduct.p_pro.$invalid" class="help-block">Select Product.</p>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-sm-6 ">
									<label>Price</label>
								</div>
								<div class="col-sm-6  " ng-class="{ 'has-error' : myproduct.productprice.$invalid && !myproduct.productprice.$pristine }">
									<input class="form-control text-right" name="productprice" ng-model="Product.price" oninput="this.value=this.value.replace(/[^0-9]/g,'');" required>
									<p ng-show="myproduct.productprice.$invalid && !myproduct.productprice.$pristine" class="help-block">Price is required.</p>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-sm-6 ">
									<label>Quantity</label>
								</div>
								<div class="col-sm-6 text-right " ng-class="{ 'has-error' : myproduct.productqty.$invalid && !myproduct.productqty.$pristine }">
									<input class="form-control text-right" ng-model="Quantity" name="productqty" oninput="this.value=this.value.replace(/[^0-9]/g,'');" required>
									<p ng-show="myproduct.productqty.$invalid && !myproduct.productqty.$pristine" class="help-block">Quantity is required.</p>
								</div>
							</div>
							<div class="row form-group">
								<div class="col-sm-6 ">
									<label>Customer Name {{selectedCountry.originalObject.code}}</label>
								</div>
								<div class="col-sm-6"  id="the-basics" ng-class="{ 'has-error' : myproduct.cust.$invalid && !myproduct.cust.$pristine }">
									<!-- <input class="form-control typeahead" ng-model="Customer" name="cust" oninput="this.value=this.value.replace(/[^a-zA-Z]/g,'');" required> -->
									<angucomplete id="ex1" placeholder="Search customer name" pause="100" selectedobject="selectedCountry" localdata="countries" searchfields="name" titlefield="name" minlength="1" inputclass="form-control form-control-sm
all" matchclass="highlight" />
									<p ng-show="myproduct.cust.$invalid && !myproduct.cust.$pristine" class="help-block">Customer Name is required.</p>
								</div>
							</div>
							
							<div class="row form-group">
								<div class="col-sm-6 ">
									<label>Payment Type</label>
								</div>
								<div class="col-sm-6 dropdown" ng-class="{ 'has-error' : myproduct.p__type.$dirty  && myproduct.p_payment_type.$invalid }">

										<select ng-model="selectedItem" class="form-control" name="p_payment_type"
											ng-options="item as item.type for item in PaymentTypeArr"
											ng-change="getChanged(selectedItem)">
											<option value="">-- select --</option>
										</select>

										<!-- <select class="form-control" ng-model="PaymentType" name="p_payment_type"
									ng-change="getPaymentType(paymentType)"
										ng-options="y for (x, y) in PaymentTypeArr" required>
										<option value="x" disabled>- Select Payment Type -</option>
									</select> -->
									<!-- <select ng-change="getPaymentType(PaymentType)" ng-options="Payment as payment.type for payment in PaymentTypeArr track by payment.id" class="form-control" ng-model="PaymentType"></select>
									{{PaymentType}}
									<p ng-show="myproduct.p_pro_type.$dirty  && myproduct.p_pro.$invalid" class="help-block">Select Payment Type</p> -->
								</div>
							</div>
							
							<div class="row form-group">
								<div class="col-sm-6 ">
									<label>GST</label>
								</div>
								<div class="col-sm-6 text-right " ng-class="{ 'has-error' : myproduct.gst1.$invalid && !myproduct.gst1.$pristine }">
									<input class="form-control text-right" ng-model="gst" name="gst1" oninput="this.value=this.value.replace(/[^0-9]/g,'');" required>
									<p ng-show="myproduct.gst1.$invalid && !myproduct.gst1.$pristine" class="help-block"> GST is required.</p>
								</div>

									<!-- <select ng-model="selectedItem" class="form-control"
										ng-options="item as item.name for item in items"
										ng-change="getChanged(selectedItem)">
										<option value="">-- select --</option>
									</select> -->
								</div>
							<center><button type="submit" class="btn btn-primary" ng-click="addItem(); ToServlet();" ng-hide="printMode" ng-disabled="myproduct.$invalid">Add
				Product</button><center>
						</form>

					</div>
					<div class="panel-footer">
						<div class="row form-group">
							<div class="col-sm-5 "></div>
							<div class="col-sm-7 ">
								<div class="col-sm-6 ">
									<label>Total :</label>
								</div>
								<div class="col-sm-6 text-right ">{{SubTotal()}}</div>
							</div>
							<div class="col-sm-5 "></div>
							<div class="col-sm-7 ">
								<div class="col-sm-6 ">
									<label>GST :</label>
								</div>
								<div class="col-sm-6 text-right ">{{GST()}}</div>
							</div>
							<div class="col-sm-5 "></div>
							<div class="col-sm-7 ">
								<div class="col-sm-8 ">
									<label>Grand Total :</label>
								</div>
								<div class="col-sm-4 text-right ">{{GrandTotal()}}</div>
							</div>
							<div class="col-sm-5 "></div>
							<div class="col-sm-7 ">
								<div class="col-sm-4 text-right "></div>
							</div>
						</div>

					</div>

				</div>
			</div>
			<div class="col-sm-3"></div>
		</div>



		<div class="panel panel-primary" ng-show="printMode">
			<div class="panel-heading text-center">Products</div>
			<!-- /.panel-heading -->
			<div class="panel-body col-sm-12">
				<div class="table col-sm-6">
					<table width="10%" class="table table-striped">
						<thead>
							<div class="row">
								<tr>
									<th class="col-sm-2">Product</th>
									<th class="col-sm-2">Price</th>
									<th class="col-sm-2">Quantity</th>
									<th class="col-sm-2">Customer Name</th>
									<th class="col-sm-2">GST</th>
									<th class="col-sm-2">Total</th>
									<th>
									<th>
									<th></th>
								</tr>
							</div>
						</thead>
						<tbody>
							<tr ng-repeat="item in invoice.items">
								<td>{{item.PRoduct}}</td>
								<td>{{item.PRice}}</td>
								<td>{{item.QUantity}}</td>
								<td>{{item.CUstomer}}</td>
								<td>{{item.GSt}}</td>
								<td>{{item.GrandTotal}}</td>


								<td><a href ng-click="remove($index)"
									class="btn btn-danger">X</a>
							</tr>


						</tbody>

					</table>
				</div>
				<div class="col-sm-6"></div>
				<!-- /.table-responsive -->
			</div>
			<!-- /.panel-body -->

			<!-- /.panel -->
		</div>
		
			<div class=" col-sm-12 text-center" ng-show="printMode">
				<h3>
					Grand Total :{{ getSubTotal() }}
					<h3>
					<br>
			</div>
			
		

		<div class="row noPrint actions text-center">
				<button class="btn btn-primary" ng-show="printMode"
				ng-click="printInfo() ">Print</button> 
				
				<button class="btn btn-primary" ng-hide="printMode"
				ng-click="printMode = true; count = count + 1; removeItem(0,count)"
				ng-init="count=0">Turn On Print Mode</button>
				<button
				class="btn btn-primary" ng-show="printMode"
				ng-click="printMode = false;">Turn Off Print Mode</button>
		</div>




	</div>
</div>
<script>
	var substringMatcher = function(strs) {
  return function findMatches(q, cb) {
    var matches, substringRegex;

    // an array that will be populated with substring matches
    matches = [];

    // regex used to determine if a string contains the substring `q`
    substrRegex = new RegExp(q, 'i');

    // iterate through the pool of strings and for any string that
    // contains the substring `q`, add it to the `matches` array
    $.each(strs, function(i, str) {
      if (substrRegex.test(str)) {
        matches.push(str);
      }
    });

    cb(matches);
  };
};

var states = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California',
  'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii',
  'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana',
  'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota',
  'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
  'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota',
  'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island',
  'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont',
  'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'
];

$('#the-basics .typeahead').typeahead({
  hint: true,
  highlight: true,
  minLength: 2
},
{
  name: 'states',
  source: substringMatcher(states)
});
</script>

</body>
</html>