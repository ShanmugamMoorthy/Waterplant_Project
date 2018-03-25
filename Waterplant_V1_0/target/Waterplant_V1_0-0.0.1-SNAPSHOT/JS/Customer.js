var app = angular.module("WaterPlantCustomer", ['ui.bootstrap','datatables']);
app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);
app.controller('customerController', function($scope,$http,$modal,DTOptionsBuilder, DTColumnBuilder){

	$scope.getCustomerList = function(){
		var data = {pageload:"getcustomer"};
		$http({
			url: 'Customer',
			method: 'POST',
			data: data
		}).then(function(response){
			$scope.customerDatalist=angular.fromJson(response.data);
			console.log("customerDatalist "+JSON.stringify(response.data));
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
	
	$scope.addCustomer = function(Customer){
		$scope.customer={};
		Customer['pageload']="addcustomer";
		console.log(Customer,"Customer");
		$http({
			url: 'Customer',
			method: 'POST',
			data: angular.toJson(Customer)
		}).then(function(response){
			//$scope.CustomerDropList=JSON.stringify(response.data);
			console.log("AddCustomer1 "+JSON.stringify(response.data));
			$scope.getCustomerList();
			$('#myModal').modal('hide');
			$('.modal-backdrop').remove();
			
		});
	}		
			
	$scope.getCustomerList();
	$('#btn').on('click', function(){
		//$scope.CustomerDropList();
	});
	$scope.removeData = function(id){
		var customerData = {"pageload":"Delete", "userId":id};
		$http({
			url:"Customer",
			method: "POST",
			data: customerData
		}).then(function(response){
			if(response.data){
				$scope.getCustomerList();
			}
		})
	}
	
	
	$scope.updateData = function(data){
		var modalInstance = $modal.open({
			templateUrl: "editCustomer.html",
			controller: editCustomerCtrl,
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
	    	console.log(selectedItems,"selectedItems");
	        //$log.info('Modal dismissed at: ' + new Date());
	    });
	}
	
});




function editCustomerCtrl($scope,user,$modalInstance,$http){
	$scope.Customer = {};
	$scope.Customer = user;
	console.log("User: "+JSON.stringify(user));
	$scope.customer = user;
	/*$scope.getCustomerList = function(){
		var data = {CustomerType:"CustomerLoad"};
		$http({
			url: 'Customer',
			method: 'POST',
			data: data
		}).then(function(response){
			$scope.CustomerDropList=angular.fromJson(response.data);
			console.log("Customerlist "+JSON.stringify(response.data));
		});
	};
	
	$scope.getCustomerList();*/
	
	$scope.update = function(data){
		data['pageload']="updatecustomer";
		//data = {pageload:"updatecustomer"};
		console.log(data,"data")
		$http({
			url: 'Customer',
			method: 'POST',
			data: angular.toJson(data)
		}).then(function(response){
			//$scope.getCustomerList();
			$modalInstance.dismiss(JSON.stringify(response.data));
			console.log("Customerlist "+JSON.stringify(response.data));
		});
	}
	
	
	$scope.dropvalue = "";
	$scope.getDropChanged = function(selectedItem){
		$scope.dropvalue = selectedItem;
		console.log(selectedItem)
	};
	
	$scope.cancel = function(){
		$scope.customer = {};
		$modalInstance.dismiss('cancel');
	}
}