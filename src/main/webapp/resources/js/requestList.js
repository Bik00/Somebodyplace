
$(document).ready(function() {
	
	if(document.getElementsByClassName("requestList_myType").length != 0) {
		var x = document.getElementsByClassName("selectpicker");
		
		var y = document.getElementsByClassName("requestList_myType");

		for (var z=0;z<x.length;z++) {
			$(x[z]).selectpicker('val', y[z].value);
		}
	}
	
	$('.selectForType').on('change', function(){
		$(this).addClass("dd");
		var a = document.getElementsByClassName("selectpicker");
		
		var b = new Array();
		
		for(var c = 0; c<a.length;c++) {
			b[c] = a.value;
		}
		// 배열 값 가져오기		
		var selected = $(this).children().children().eq(0).text();
		var request_code = $(".dd").parent().next().next().val();
		$(this).removeClass("dd") ;
		$.ajax({
			type : "post",
			url : "setRequestType",
			data : {request_status : selected, request_code : request_code},
			async:false,
			success : function(data){	
			}
		});
	});
});