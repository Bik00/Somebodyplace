/**
 * 
 */$(function(){
	//select 크기 조절
	 
	$(".favorites_btn").click(function(){
		var a=$(this).prevAll(".favorite_input").val();
		var b=$('.favorite_membercode').val();
		location.href="delFavorite2?place_code="+a+"&member_code="+b;
	});
	
});