<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	
		<!-- 윗단 부분 끝 -->

		<!--  메뉴 부분 시작 -->
	

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
	
