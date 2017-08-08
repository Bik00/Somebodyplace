$(function(){
	// 이미지 width 크기 맞춘 후 다시 표시
	$('.product_img img').each(function(){
			var imgBoxW = $(".product_img").width();

			var img = new Image();
			img.src = $(this).attr("src");
			img.width = imgBoxW;
			
			$(this).parent().html(img);
	});
	// 상품 게시글 보기
	$(document).on('click', '.product_box', function(){
		var product_code = $(this).attr("data");
		var Cmember_code = $('.member_code').val();
		if(typeof Cmember_code == "undefined") {
			Cmember_code = 0;
		}
		location.href = "postDefault?product_code="+product_code+"&member_code="+Cmember_code;
	});
	
	$('.place_cate a').click(function(){
		var member_code = $("#Cmember_code").text();
		var boardcode = $(this).attr("data-boardcode");
		location.href = "placeHome?member_code="+member_code+"&board_code="+boardcode;
	});
});