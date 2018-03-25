var app = angular.module("WaterPlantExpense", ['ui.bootstrap','datatables']);
app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);

app.controller('myExpenseController', function($scope,$http,$modal,DTOptionsBuilder, DTColumnBuilder){

	$scope.getExpenseList = function(){
		var data = {ExpenseType:"expenseLoad"};
		$http({
			url: 'Expense',
			method: 'POST',
			data: data
		}).then(function(response){
			$scope.ExpenseDatalist=angular.fromJson(response.data);
			console.log("expenselist "+JSON.stringify(response.data));
		});
	};
	$scope.dropvalue = "";
	$scope.getDropChanged = function(selectedItem){
		$scope.dropvalue = selectedItem;
		console.log(selectedItem)
	};
	
	$scope.dtOptions = DTOptionsBuilder.newOptions()
	.withOption('order', [1, 'asc'])
	.withOption('lengthMenu', [5,10,20]);
	
	$('#datepickerFrom').datepicker({
	    dateFormat: 'yy-mm-dd'
	});
	
	$scope.expense ={};
	$('#datepicker').datepicker({
	    dateFormat: 'yy-mm-dd'
	});
	
	$("#datepicker").val(new Date());
	$scope.expense.Date = new Date();
	
	$scope.showDatePick = function(){
		$("#ui-datepicker-div").css("z-index", "9999");
	}
	
	$scope.setDateValue = function(){
		$scope.expense.date = $('#datepicker').val();
		console.log($scope.expense.date,"date");
	}
	
	$scope.addExpense = function(expense){
		$scope.expense={};
		expense['ExpenseType']="Add";
		expense.Date = moment(expense.Date).format('YYYY-MM-DD h:mm:ss');
		console.log("Test date "+JSON.stringify(expense)+ "    moment: "+moment().format('MMMM Do YYYY, h:mm:ss a'));
		$http({
			url: 'Expense',
			method: 'POST',
			data: angular.toJson(expense)
		}).then(function(response){
			$scope.ExpenseDropList=JSON.stringify(response.data);
			console.log("AddExpense1 "+JSON.stringify(response.data));
			$scope.getExpenseList();
			$('#myModal').modal('hide');
			$('.modal-backdrop').remove();
			
		});
	}		
			
	$scope.ExpenseDropList=function(){
		var data = {ExpenseType:"ExpenseTypeDropDown"};
		$http({
			url: 'Expense',
			method: 'POST',
			data: data
		}).then(function(response){
			$scope.ExpenseDropList=angular.fromJson(response.data);
			console.log("expenselist "+JSON.stringify(response.data));
		});
	}
	
	
	
	$scope.getExpenseList();
	$('#btn').on('click', function(){
		$scope.ExpenseDropList();
	});
	
	$scope.removeData = function(id){
		var expenseData = {"ExpenseType":"Delete", "expenseId":id};
		$http({
			url:"Expense",
			method: "POST",
			data: expenseData
		}).then(function(response){
			if(response.data){
				$scope.getExpenseList();
			}
		})
	}
	
	
	$scope.updateData = function(data){
		var modalInstance = $modal.open({
			templateUrl: "editExpense.html",
			controller: editExpenseCtrl,
			backdrop:'static',
			resolve: {
				user: function(){
					return data;
				}
			}
		})
		
		modalInstance.result.then(function (selectedItems) {
			console.log(selectedItems,"selectedItems");
	    }, function () {
	        $log.info('Modal dismissed at: ' + new Date());
	    });
	}
	
});




function editExpenseCtrl($scope,user,$modalInstance,$http){
	$scope.expense = {};
	$scope.expense = user;
	console.log("User: "+JSON.stringify(user));
	$scope.expense.selectedDropItem = user.Expense_Type;
	$scope.expense.Date = moment(user.Date).format("DD-MM-YYYY");
	/*$scope.getExpenseList = function(){
		var data = {ExpenseType:"expenseLoad"};
		$http({
			url: 'Expense',
			method: 'POST',
			data: data
		}).then(function(response){
			$scope.ExpenseDropList=angular.fromJson(response.data);
			console.log("expenselist "+JSON.stringify(response.data));
		});
	};
	
	$scope.getExpenseList();*/
	
	$scope.update = function(data){
		data['ExpenseType'] = "Edit";
		data.Date = moment().format('YYYY-MM-DD h:mm:ss');
		console.log(data,"data");
		$http({
			url: 'Expense',
			method: 'POST',
			data: data
		}).then(function(response){
			//$scope.ExpenseDropList=angular.fromJson(response.data);
			$modalInstance.dismiss(JSON.stringify(response.data));
			console.log("expenselist "+JSON.stringify(response.data));
		});
	}
	
	$scope.ExpenseDropList=function(){
		var data = {ExpenseType:"ExpenseTypeDropDown"};
		$http({
			url: 'Expense',
			method: 'POST',
			data: data
		}).then(function(response){
			$scope.ExpenseDropList=angular.fromJson(response.data);
			console.log("expenselist "+JSON.stringify(response.data));
		});
	}
	
	$scope.ExpenseDropList();
	$scope.dropvalue = "";
	$scope.getDropChanged = function(selectedItem){
		$scope.dropvalue = selectedItem;
		console.log(selectedItem)
	};
	
	$scope.cancel = function(){
		$modalInstance.dismiss('cancel');
	}
}