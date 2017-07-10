<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="./resources/css/test_main.css?ver=3">
    <script src="./resources/js/test_main.js?ver=3"></script>  
    
	<title>테스트용 메인화면 입니다.</title>
</head>
<body>
	<div id="main">
	
		<!-- 윗단 부분 -->
		<div id="main_logo">
			<img src="./resources/img/somebodyplaceLOGO.png" class="index_logo">
			<span class="main_member"> <c:if test="${nickname != null }">         
        			${nickname}님 환영합니다. 
        			<input type="hidden" value="${member_code}"
						class="member_code">
					<span class="main_logoutBtn">로그아웃</span>
				</c:if> <c:if test="${nickname == null }">
					<span class="main_loginBtn">로그인</span>
				</c:if> <span class="main_joinForm">회원가입</span> <c:if
					test="${nickname != null }">
					<span class="main_myPage">마이페이지</span>
				</c:if> <a
				href="place?member_code=${member_code}&member_email=${member_email}">
					<span class="main_myPlaceBtn">마이플레이스</span>
			</a> <span class="main_PlaceBtn">플레이스</span>
			</span>
		</div>
		<!-- 윗단 부분 끝 -->

		<!--  메뉴 부분 시작 -->
		<div id="main_cate">

			<div class="dropdown">
				<span class="main_issue dropdown-left">이슈</span>
			</div>
			<div class="dropdown">
				<span class="dropdown-middle">배달</span>
				<div class="dropdown-content">
					<a href="main?dcate_code=1">음식</a> <a href="main?dcate_code=2">꽃</a>
					<a href="main?dcate_code=3">세탁</a>
				</div>
			</div>
			<div class="dropdown">
				<span class="dropdown-middle">쇼핑</span>
				<div class="dropdown-content">
					<a href="main?dcate_code=4">패션</a> <a href="main?dcate_code=5">생활</a>
					<a href="main?dcate_code=6">디지털</a>
				</div>
			</div>
			<div class="dropdown">
				<span class="dropdown-middle">여가</span>
				<div class="dropdown-content">
					<a href="main?dcate_code=7">공연</a> <a href="main?dcate_code=8">숙박</a>
				</div>
			</div>
			<div class="dropdown">
				<span class="dropdown-right">뷰티</span>
				<div class="dropdown-content">
					<a href="main?dcate_code=9">미용실</a> <a href="main?dcate_code=10">네일</a>
					<a href="main?dcate_code=11">화장품</a>
				</div>
			</div>
		</div>
		<!-- 메뉴 부분 끝 -->

		<div class="main_content">
			<div class="slideAndIssue">
				<!--  슬라이드 부분 시작 -->
				<div id="slider" class="main_slide">
					<button class="slideBtn slideBtn-left">&#10094;</button>
					<div class="slide-wrap">
						<ul id="slide_img" class="slide-list">
							<li><img src="./resources/img/somebodyfamily.jpg" alt=""></li>
							<li><img src="./resources/img/apple.jpg" alt=""></li>
							<li><img src="./resources/img/kin.jpg" alt=""></li>
						</ul>
					</div>
					<button class="slideBtn slideBtn-right">&#10095;</button>
				</div>
				<!--  슬라이드 부분 끝 -->
			
				<!-- 이슈 부분 시작 -->
				<div class="issue_title">
				이슈 화면
				</div>
				<div class="issue_box">
					<div class="issue_top">
						<div class="issue_picture">
							<img src="./resources/img/프로필.PNG">
						</div>
						<div class="issue_profile">
							<span id="issue_receiverName">테스트</span>
							<br>2017-07-01 17:30 <br>
						</div>
					</div>
					
					<div class="issue_content">
						<h1>테스트용 이슈</h1>
						<br> *대구광역시 복현동
						<br> *주변 500m로 보낸 이슈
						<br>#테스트
						<br>
					</div>
				</div>
				<!-- 이슈 부분 끝 -->
			</div>
			<!--  플레이스 부분 시작 -->
			<div class="tutorialPlace">
				<div class="infoAboutPlace">
					<p class="typo"><b>플레이스 생성 안내</b></p>
					SOMEBODY PLACE의<br>
					FAMILY으로 모시고자 합니다.
					<div class="clickTutorialBtn">
						자세히 보기
					</div>
				</div>
				<img src="./resources/img/tutorial_place.jpg" class="tutorialImg"></img>
			</div>
			<!-- 플레이스 부분 끝 -->
			<!-- 검색 부분 시작 -->
			<div class="searcchArea">
				<div class="searchBox">
					<p class="typo"><b>해시 태그로 상품을 검색해보세요.</b></p>
					<input type="text" class="searchTextBox">
					<div type="button" class="searchTextBtn">검색</div>
				</div>			
			</div>
			<!-- 검색 부분 끝 -->
			
			<!-- 아이템 영역 시작 -->
			<div class="itemArea">
				<div class="itemTitle">
					<p class="itemTitle_title"><b>인기 제품</b></p>
					<p class="itemTitle_more"><b>전체보기 ▶</b></p>
				</div>
				<div class="itemContent">
					<div class="itemContent_item">
						<div class="itemContent_image">
							<img src="./resources/img/product1.jpg">
						</div>
						<br>
						<p class="itemContent_title">
							<b>송이 초코</b>
						</p>
						<p class="itemContent_price">
							<b>15000 원</b>
						</p>
						<br>
						<button class="btn btn-default itemContent_more">더 보기</button>
					</div>
					<div class="itemContent_item">
						<div class="itemContent_image">
							<img src="./resources/img/product2.jpg">
						</div>
						<br>
						<p class="itemContent_title">
							<b>크림 머핀</b>
						</p>
						<p class="itemContent_price">
							<b>8000 원</b>
						</p>
						<br>
						<button class="btn btn-default itemContent_more">더 보기</button>
					</div>
					<div class="itemContent_item">
						<div class="itemContent_image">
							<img src="./resources/img/product3.jpg">
						</div>
						<br>
						<p class="itemContent_title">
							<b>초코 브라우니</b>
						</p>
						<p class="itemContent_price">
							<b>12000 원</b>
						</p>
						<br>
						<button class="btn btn-default itemContent_more">더 보기</button>
					</div>
					<div class="itemContent_item">
						<div class="itemContent_image">
							<img src="./resources/img/product4.jpg">
						</div>
						<br>
						<p class="itemContent_title">
							<b>딸기 카스테라</b>
						</p>
						<p class="itemContent_price">
							<b>26000 원</b>
						</p>
						<br>
						<button class="btn btn-default itemContent_more">더 보기</button>
					</div>
					<div class="itemContent_item">
						<div class="itemContent_image">
							<img src="./resources/img/product5.jpg">
						</div>
						<br>
						<p class="itemContent_title">
							<b>초코맛 캔디</b>
						</p>
						<p class="itemContent_price">
							<b>3500 원</b>
						</p>
						<br>
						<button class="btn btn-default itemContent_more">더 보기</button>
					</div>
					<div class="itemContent_item">
						<div class="itemContent_image">
							<img src="./resources/img/product6.jpg">
						</div>
						<br>
						<p class="itemContent_title">
							<b>초코 크림쿠키</b>
						</p>
						<p class="itemContent_price">
							<b>6000 원</b>
						</p>
						<br>
						<button class="btn btn-default itemContent_more">더 보기</button>
					</div>
					<div class="itemContent_item">
						<div class="itemContent_image">
							<img src="./resources/img/product7.jpg">
						</div>
						<br>
						<p class="itemContent_title">
							<b>시나몬 비스켓</b>
						</p>
						<p class="itemContent_price">
							<b>9000 원</b>
						</p>
						<br>
						<button class="btn btn-default itemContent_more">더 보기</button>
					</div>
					<div class="itemContent_item">
						<div class="itemContent_image">
							<img src="./resources/img/product8.jpg">
						</div>
						<br>
						<p class="itemContent_title">
							<b>타코야키</b>
						</p>
						<p class="itemContent_price">
							<b>8000 원</b>
						</p>
						<br>
						<button class="btn btn-default itemContent_more">더 보기</button>
					</div>

				</div>
			</div>
			<!-- 아이템 영역 부분 끝 -->
			<!-- 사이트맵 부분 시작-->
			<div class="siteMapArea">
				<div class="siteMap_title">사이트맵</div>
			</div>
			<!-- 사이트맵 부분 끝 -->
		</div>
	</div>
</body>
</html>