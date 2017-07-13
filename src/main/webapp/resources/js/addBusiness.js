/**
 * 
 */
$(document).ready(function() {
	$(".addBusiness_addBtn").click(function(){
		var member_code=$('.placeManager_member_code').val();
		var a=$('#business_1').val();
		var b=$('#business_2').val();
	    var c=$('#business_3').val();
	    var place_busino=a+"-"+b+"-"+c;
		location.href="insertBusiness?member_code="+member_code+"&place_busino="+place_busino;
		
	});
});