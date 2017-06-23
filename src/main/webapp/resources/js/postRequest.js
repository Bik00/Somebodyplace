$(function(){
	$(".buyBtn").click(function(){
		$("form").attr("action", "moneysuccess");
		$("form").attr("method", "post");
		$("form").submit();
	});
	
	
	
	$(".postRequest_infoBtn").click(function(){
			$('#name').val($('.member_name').val());
			$('#addr').val($('.member_addr').val());
			$('#phone').val($('.member_phone').val());
	});
	
});