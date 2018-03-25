var app = angular.module("WaterPlant", ['nvd3']);
app.controller("DashboardCtrl",function($scope, $http) {
$scope.data=new Array;
	var lableValue=[];
	var lableKey=[];
	var pageload={"pageload" :"reportGeneration"}
	$scope.getChartData=function(){
		$http({
			method:'POST',
			url:"Dashboard",
			data:pageload
			
		}).then(function(response){
			alert("dashboard response :::"+JSON.stringify(response));
			$scope.data=response.data;
			angular.forEach($scope.data,function(val){
				lableValue.push(val.y);
				lableKey.push(val.key)
			});
			$scope.lableValue=lableValue;
			$scope.lableKey=lableKey;
			showLineChart();
		})
		
	}
	
	function showLineChart(){
		/*var ctxB = document.getElementById("barChart").getContext('2d');
		var myBarChart = new Chart(ctxB, {
		    type: 'bar',
		    data: {
		        //labels: ["Expense", "Profit", "Loss", "RawMaterial", "Electricity", "overAll"],
		        labels:$scope.lableKey,
		    	datasets: [{
		            label: 'WaterPlants LineChart',
		            //data: [12, 19, 3, 5, 2, 3],
		            data: $scope.lableValue,
		            backgroundColor: [
		                'rgba(255, 99, 132, 0.2)',
		                'rgba(54, 162, 235, 0.2)',
		                'rgba(255, 206, 86, 0.2)',
		                'rgba(75, 192, 192, 0.2)',
		                'rgba(153, 102, 255, 0.2)'
		            ],
		            borderColor: [
		                'rgba(255,99,132,1)',
		                'rgba(54, 162, 235, 1)',
		                'rgba(255, 206, 86, 1)',
		                'rgba(75, 192, 192, 1)',
		                'rgba(153, 102, 255, 1)'
		            ],
		            borderWidth: 1
		        }]
		    },
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		    }
		});*/
		
		var chart = new CanvasJS.Chart("barChart",
			    {
			      title:{
			        text: "A Multi-series Column Chart"
			      
			      },   
			      data: [{        
			        type: "column",
			        dataPoints: [
			        { x: 10, y: 171 },
			        { x: 20, y: 155},
			        { x: 30, y: 150 },
			        { x: 40, y: 165 },
			        { x: 50, y: 195 },
			        { x: 60, y: 168 },
			        { x: 70, y: 128 },
			        { x: 80, y: 134 },
			        { x: 90, y: 114}
			        ]
			      },
			      {        
			        type: "column",
			        dataPoints: [
			        { x: 10, y: 71 },
			        { x: 20, y: 55},
			        { x: 30, y: 50 },
			        { x: 40, y: 65 },
			        { x: 50, y: 95 },
			        { x: 60, y: 68 },
			        { x: 70, y: 28 },
			        { x: 80, y: 34 },
			        { x: 90, y: 14}
			        ]
			      }        
			      ]
			    });

			    chart.render();
	}
	
	$('#datepickerFrom').datepicker({
	    dateFormat: 'yy-mm-dd'
	});
	$('#datepickerTo').datepicker({
	    dateFormat: 'yy-mm-dd'
	});
	
	$scope.dateApply = function(){
		var lableValue=[];
		var lableKey=[];
		var fromDate = $('#datepickerFrom').val();
		var toDate = $('#datepickerTo').val();
		alert(fromDate+"--"+toDate);
		var pageload={"pageload" :"reportGenerationDate","fromDate":fromDate,"toDate":toDate};
		$http({
			method:'POST',
			url:"Dashboard",
			data:pageload
			
		}).then(function(response){
			alert("dashboard response :::"+JSON.stringify(response));
			$scope.data=response.data;
			angular.forEach($scope.data,function(val){
				lableValue.push(val.y);
				lableKey.push(val.key)
			});
			$scope.lableValue=lableValue;
			$scope.lableKey=lableKey;
			showLineChart();
		})
	}
	
	$('#example')
	.removeClass( 'display' )
	.addClass('table table-striped table-bordered');
	$scope.getChartData();
	$scope.options = {
			chart: {
                type: 'pieChart',
                height: 500,
                x: function(d){return d.key;},
                y: function(d){return d.y;},
                showLabels: true,
                duration: 500,
                labelThreshold: 0.01,
                labelSunbeamLayout: true,
                legend: {
                    margin: {
                        top: 5,
                        right: 35,
                        bottom: 5,
                        left: 0
                    }
                }
            },title: {   
				enable: true,
				text: "OverAll Validation Count",
				className: "h4",
				css: {   
					width:"nullpx",
					textAlign: "center",
					color: "black"
				}
			},caption: {   
				enable: true,
					text: "Figure 1. OverAll Validation Count.",
					css: {
						width:"nullpx",
						textAlign: "center",
						color: "black"
					} 
			} 
	};
	
	
	
	
	/*$scope.data = [
	               {
	                   key: "Expense",
	                   y: 5
	               },
	               {
	                   key: "Two",
	                   y: 2
	               },
	               {
	                   key: "Three",
	                   y: 9
	               },
	               {
	                   key: "Four",
	                   y: 7
	               },
	               {
	                   key: "Five",
	                   y: 4
	               },
	               {
	                   key: "Six",
	                   y: 3
	               },
	               {
	                   key: "Seven",
	                   y: .5
	               }
	           ];
*/
});