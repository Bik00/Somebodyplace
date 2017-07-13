$(function(){
	// 플레이스 수정 버튼 
	$(".placeManager_modifybtn").click(function(){
			location.href="placemodify";
	});
	
	$(".placeManager_requestlist").click(function(){
		location.href="requestList";
	});

	$(".placeManager_addBusiness").click(function(){
	
		location.href="addBusiness";
	});
	

	$(".placeManager_category").click(function(){
		location.href="categorySetting";
	});
	
	$(".placeManager_currentBudget").click(function(){
		location.href="currentBudget";
	});
	
	$(".addBusiness_addBtn").click(function(){
		var member_code=$('.placeManager_member_code').val();
		var a=$('#business_1').val();
		var b=$('#business_2').val();
	    var c=$('#business_3').val();
	    var place_busino=a+b+c;
		
	
		location.href="insertBusiness?member_code="+member_code+"&place_busino="+place_busino;
	});
	
	
	
	
	
});
