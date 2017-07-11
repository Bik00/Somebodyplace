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
	$('.product_box').on('click', function(){
		var product_code = $(this).attr("data");
		var member_code = $('.member_code').val();
		location.href = "postDefault?product_code="+product_code;
	});
	$('.place_cate a').click(function(){
		var member_code = $("#member_code").text();
		var boardcode = $(this).attr("data-boardcode");
		location.href = "placeHome?member_code="+member_code+"&board_code="+boardcode;
	});
});