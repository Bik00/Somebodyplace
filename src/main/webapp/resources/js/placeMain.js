var isLogin = false;
var isAlready = false;
$(function(){
	// 플레이스 추가하기 버튼 누르면
	$(".placeMain_addBtn").click(function(){
		if($('#code').text()=='') {
			$(".modal-body").append('<p>로그인을 하지 않으셨습니다. 로그인 페이지로 넘어갑니다.</p>');
			isLogin = true;
			$("#addPlaceModal").modal();
		} else {
			var query = {
					member_code : $("#code").text()
			}
			$.ajax({
				type : "post",
				url : "hasPlaceCode",
				data : query,
				async:false,
				success : function(data) {
					if(data!=0) {  // 플레이스가 생성되어 있지 않은 유저일 경우
						$(".modal-body").append('<p>이미 플레이스를 생성하였습니다. 해당 플레이스로 이동합니다.</p>');
						isAlready = true;
						$("#addPlaceModal").modal();
	
					} else {
						location.href="placeAddForm";							
					}
				}
			});
		}
	});
	
	$('#addPlaceModal').click(function(e) {
		if(isLogin) {
			if(!$(".modal-content").has(e.target).length){
				setTimeout(function(){location.href='loginForm';}, 400);
			}
		}
		if(isAlready) {
			if(!$(".modal-content").has(e.target).length){
				setTimeout(function(){location.href="place?member_code="+$("#code").text();}, 400);
			}
		}
	});
	
	$('.closePlaceModal').click(function() {
		if(isLogin) {
			setTimeout(function(){location.href='loginForm';}, 400);
		}
		if(isAlready) {
			setTimeout(function(){location.href="place?member_code="+$("#code").text();}, 400);
		}
	});
});
