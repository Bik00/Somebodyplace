$(function(){
	
	
	// 제목 가운데 정렬 부분 소스
	
	// x값은 제목의 길이.
	// k값은 제목 영역 전체의 길이.
	// y 값은 (k - x)/2 값 (한쪽 여백의 길이).
	// 이때 y값을 백분화시키는 공식은  'y * 100% / k' 이다.
	// 그후 % 기호를 붙여서 css에 적용시켜주면된다.
	// 이때, 로고가 등록되어 있으면  제목의 길이에 100px를 더해준 후 계산을 하면 된다.
	// - 본일
	
	if($(".place_title").find('img').attr('src')==null) {
		var x = Math.round($(".place_placename").width());
		var k = Math.round($(".place_title").width());
		var y = Math.round(x*(-1/2)+k*(1/2));
		$('.place_placename').css('margin-left', Math.round(100*y/k)+"%");	
	} else {
		var x = Math.round($(".place_placename").width())+100;
		var k = Math.round($(".place_title").width());
		var y = Math.round(x*(-1/2)+k*(1/2));
		$('.place_logo').css('margin-left', Math.round(100*y/k)+"%");
	}

	
	// 플레이스 글쓰기 버튼
	$(".postForm_btn").click(function(){
		location.href = "postForm";	
	});
	
	$(".placeManager_btn").click(function(){
		var myNum = $("#code").text();
		
		var form = $('<form></form>');
		form.attr('action', 'place');
		form.attr('method', 'get');
		form.appendTo('body');
		
		var a = $('<input type="hidden" value="'+myNum+'" name="member_code">');
		form.append(a);
		form.submit();
	});
});