$(document).ready(function(){		
	
	$('#masterload').load("Menu.jsp");
	var table=null;
	$.post('Rawmaterial', {pageload : "getContent"}, function(response) {
		console.log(response);
		if (response != null) {
//			$.noConflict();
			table=$('#mytable').DataTable({
				//destroy : true,
				responsive : true,
				data : response,
				columnDefs:[
					{	"targets":-1,
						"data":null,
						defaultContent:"<button type='button' class='editbutton btn btn-primary' ><span class='glyphicon glyphicon-edit'></span></button> | <button type='button' class='deletebutton btn btn-primary'><span class='glyphicon glyphicon-trash'></span></button>"
					},
					{	"targets":0,
						"searchable":false,
						"visible":false
					}
					],
				"pageLength" : 5,
				"lengthMenu" : [ 5, 10, 25, 50 ]
			});
		}
	});
	
	
	$.post('Rawmaterial', {pageload:"vendordropdown",check:"vendor"}, function(response){
		$.each(response,function(key,value){
				$('<option>').val(value[0]).text(value[1]).appendTo('#vendordrop');
		});
	});
	
	
	$.post('Rawmaterial',{pageload:"dropdown"}, function(response) {
		$.each(response, function(key, value) {
			$("<option>").val(value).text(value).appendTo('#dropdown');
		});
		$.each(response, function(key, value) {
			$("<option>").val(value).text(value).appendTo('#dropdown2');
		});
	});
	
	$('#addnewvendor').click(function(){
		var data={
				vendorname:$('#newvenname').val(),
				companyname:$('#newcompanyname').val(),
				email:$('#newemail').val(),
				mobilenumber:$('#newmobilenumber').val(),
				pageload:"addnewvendor"
		};
		$.post('Rawmaterial',$.param(data),function(response){
			console.log(response,"response");
			//alert(response)
			if(response == "1"){
				location.reload();
			}
		});
	});
	
	$('#addbutton').click(function(){
		var data={vendordrop:$('#vendordrop').val(),
			dropdown:$('#dropdown').val(),
			quantity:$('#addquantity').val(),
			desc:$('#adddesc').val(),
			price:$('#addprice').val(),
			pageload:"addnew"};
	//alert($('#priname').val());
		console.log(data,"data	 add");
	$.post('Rawmaterial',$.param(data),function(response){
		//alert(response)
		console.log(response,"response add");
		if(response == "1"){
			location.reload();
		}
	}).done(function() {
	    alert( "second success" );
	  })
	  .fail(function(err) {
	    console.log(err.responseJSON,"err");
	  })
	});
	
	
	var rowindex;
	$(document).on("click",".editbutton",function(){
		var data=table.row($(this).parents('tr')).data();
		$(this).attr({
			'data-toggle':'modal',
			'data-target':'#myModal2'
				});
		rowindex=data[0];
		$.post('Rawmaterial',{pageload:"editcontent",rowid:rowindex},function(response){
			//alert(response);
			if(response!=null){
				//alert(response[0]);
				$('#venname').val(response[1]);
				$('#venname').prop('disabled',true);
				$('#dropdown2').val(response[2]);
				$('#editdesc').val(response[3])
				$('#qtyname').val(response[4]);
				$('#priname').val(response[5]);
			}
		});
		//alert(rowindex);
	});
	
	$('#editclick').click(function(){
		var data={vendorname:$('#venname').val(),
			dropdown:$('#dropdown2').val(),
			editdesc:$('#editdesc').val(),
			quantity:$('#qtyname').val(),
			price:$('#priname').val(),
			pageload:"addedit",
			rowid:rowindex};
	//alert($('#priname').val());
	$.post('Rawmaterial',$.param(data),function(response){
		if(response == "1"){
			location.reload();
		}
	});
	});
	
	$(document).on("click",".deletebutton",function(){
		if(confirm("Are you sure you want to delete"))
		{
			var data=table.row($(this).parents('tr')).data();
			rowindex=data[0];
			//alert(rowindex);
			$.post('Rawmaterial',{pageload:"delete",rowid:rowindex},function(response){
				if(response == "1"){
					location.reload();
				}
			});
		}
	});
});