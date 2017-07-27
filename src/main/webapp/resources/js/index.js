$(function(){
	//select 크기 조절
	
	setTimeout(function() {$(".selectForSearch").css('width', '10%');}, 60);

	//***로고 클릭 했을때 메인으로 보냄***** 
	$(".index_logo").click(function(){
		location.href="main";
	});
	
	// 메인화면 로그인버튼
	$(".main_loginBtn").click(function(){
			location.href="loginForm";
	});
	
	// 메인화면 마이플레이스 버튼
/*	$(".main_myPlaceBtn").click(function(){
		
		var a=$(".member_code").val();
		alert(a);
		var b=$(".member_email").val();

			location.href ="place?member_code="+a+"&member_email="+b;
	
	});*/
	
	// 메인화면 로그아웃버튼
	$(".main_logoutBtn").click(function(){
		location.href="logout";
	});

	// 메인화면 회원가입 버튼
	$(".main_joinForm").click(function(){
		location.href = "join";
	});
	
	// 메인화면 마이페이지 버튼
	$(".main_myPage").click(function(){
		location.href = "myPage";
	});
	
	// 메인화면 플레이스 버튼
	$(".main_PlaceBtn").click(function(){
		location.href = "placeMain";
	});
	$("#main_imsi1").click(function(){
		location.href = "placeMain";
	});
	
	
	
	// 이슈 클릭시
	$(".main_issue").click(function(){
		location.href = "issue";
	});
	
	$("#main_imsi2").click(function(){
		location.href = "addIssue";
	});
	
	
});