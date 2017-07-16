/**
 * 
 */

$(document).ready(function() {
	$(".cart_cancelBtn").on('click', function(){
		var x = $(this).parent().prev().prev().prev().find("#cart_cartCode").val();
		location.href="delCart?cart_code="+x;
	});
});