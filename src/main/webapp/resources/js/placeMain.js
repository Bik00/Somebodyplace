
$(function(){
	// 메인화면 로그인버튼
	$(".placeMain_addBtn").click(function(){
			if($('#code').text()=='') {
				alert("로그인을 하지 않으셨습니다. 로그인 페이지로 넘어갑니다.");
				location.href='loginForm';
			} else {
				location.href="placeAddForm";				
			}

	});
});
