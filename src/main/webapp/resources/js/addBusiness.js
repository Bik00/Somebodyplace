/**
 * 
 */
$(document).ready(function() {
	$(".addBusiness_addBtn").click(function(){
		if($("#business_1").val().length != "3") {
			$(".alerm_one").show();
			setTimeout(function(){$(".alerm_one").fadeOut("slow")}, 1000);
		}
		else if($("#business_2").val().length != "2") {
			$(".alerm_two").show();
			setTimeout(function(){$(".alerm_two").fadeOut("slow")}, 1000);
		}
		else if($("#business_3").val().length != "5") {
			$(".alerm_three").show();
			setTimeout(function(){$(".alerm_three").fadeOut("slow")}, 1000);
		}
		else {
			var member_code=$('.placeManager_member_code').val();
			var a=$('#business_1').val();
			var b=$('#business_2').val();
		    var c=$('#business_3').val();
		    var place_busino=a+"-"+b+"-"+c;
			location.href="insertBusiness?member_code="+member_code+"&place_busino="+place_busino;
		}
	});
	
	$(".addBusiness_delBtn").click(function() {
		$("#delBusiModal").modal();
	});
	
	$(".confirmDelBusi").click(function() {
		var member_code=$('.placeManager_member_code').val();
		location.href="deleteBusiness?member_code="+member_code;
	});
});