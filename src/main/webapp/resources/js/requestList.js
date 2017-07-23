
$(document).ready(function() {
	
	$('.selectForType').on('change', function(){
		var selected = $('.selectForType option:selected').val();
		$.ajax({
			type : "post",
			url : "setRequestType",
			data : {request_status : selected},
			async:false,
			success : function(data){
				location.reload();	
			}
		});
	});
});