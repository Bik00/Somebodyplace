<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
    <link rel="stylesheet" href="./resources/css/main.css?ver=13">
    <link rel="stylesheet" href="./resources/css/loginForm.css">
    <link rel="stylesheet" href="./resources/css/place.css?ver=7">
    <link rel="stylesheet" href="./resources/css/postForm.css?ver=1">
    <link rel="stylesheet" href="./resources/css/joinForm.css?ver=3">
    <link rel="stylesheet" href="./resources/css/mypage.css">
    <link rel="stylesheet" href="./resources/css/modifyForm.css">
    <link rel="stylesheet" href="./resources/css/orderList.css">
    <link rel="stylesheet" href="./resources/css/cart.css?ver=3">
    <link rel="stylesheet" href="./resources/css/wishList.css">
    <link rel="stylesheet" href="./resources/css/favorites.css?ver=3">
    <link rel="stylesheet" href="./resources/css/interestSetting.css">
    <link rel="stylesheet" href="./resources/css/issue.css?ver=2">
    <link rel="stylesheet" href="./resources/css/placeMain.css?ver=1">
    <link rel="stylesheet" href="./resources/css/placemanager.css?ver=2">
    <link rel="stylesheet" href="./resources/css/placeAddForm.css">
    <link rel="stylesheet" href="./resources/css/requestList.css?ver=2">
    <link rel="stylesheet" href="./resources/css/addBusiness.css?ver=2">
    <link rel="stylesheet" href="./resources/css/categorySetting.css?ver=4">
    <link rel="stylesheet" href="./resources/css/currentBudget.css?ver=2">
    <link rel="stylesheet" href="./resources/css/chatting.css?ver=5">
	<link rel="stylesheet" href="./resources/css/addIssue.css">
	<link rel="stylesheet" href="./resources/css/postDefault.css?ver=2">
	<link rel="stylesheet" href="./resources/css/postRequest.css?ver=2">
	<link rel="stylesheet" href="./resources/css/bootstrap-select.css">
	<link rel="stylesheet" href="./resources/css/issue_alert.css">
	<link rel="stylesheet" href="./resources/css/modifyPlace.css">
<!-- 	<link rel="stylesheet" href="./resources/css/test_main2.css"> -->
	        
	<script src="./resources/js/issue.js?ver=8"></script>      
    <script src="./resources/js/index.js?ver=6"></script>
    <script src="./resources/js/place.js?ver=6"></script>
    <script src="./resources/js/postForm.js?ver=29"></script>
    <script src="./resources/js/mypage.js?ver=2"></script>
    <script src="./resources/js/placeMain.js"></script>
    <script src="./resources/js/placeManager.js"></script>
    <script src="./resources/js/currentBudget.js"></script>  
    <script src="./resources/js/sockjs.js"></script>
    <script src="./resources/js/chatting.js"></script>
    <script src="./resources/js/addIssue.js?ver=8"></script>
    <script src="./resources/js/placeAddForm.js?ver=2"></script>
    <script src="./resources/js/categorySetting.js?ver=2"></script>
    <script src="./resources/js/placeHome.js?ver=6"></script>
	<script src="./resources/js/postDefault.js?ver=6"></script>
	<script src="./resources/js/postRequest.js?ver=4"></script>
	<script src="./resources/js/bootstrap-select.js"></script>
	<script src="./resources/js/main.js?ver=5"></script>
	<script src="./resources/js/favorites.js"></script>
	<script src="./resources/js/modifyPlace.js"></script>
	<script src="./resources/js/interestSetting.js"></script>
	<script src="./resources/js/addBusiness.js?ver=2"></script>
	<script src="./resources/js/cart.js?ver=2"></script>
	<script src="./resources/js/joinForm.js"></script>
	<script src="./resources/js/requestList.js"></script>
	<script src="./resources/js/jssor.slider-25.2.0.min.js"></script>
<!-- 	<script src="./resources/js/test_main2.js"></script> -->
	
    <title>Somebody Place</title>
</head>
<body>
    <div id="main">
    	<div id="main_header">
    	<div id="main_logo">
    		<img src="./resources/img/somebodyplaceLOGO.png" class="index_logo">
    	</div>
    	<div id="main_issue">
    	
    	<div class="main_issueBox"><h3 style="font-family : '나눔바른펜'; display:none;">hot Issue</h3><ul id="main_issueBox_ul">
		<c:if test="${mainIssue != null }">
			<c:forEach items="${mainIssue}" var="issue" varStatus="status">
				<c:if test="${status.index == 0}">
					<li style="top: 0">
				</c:if>
				<c:if test="${status.index != 0}">
					<li style="display:none">
				</c:if>
						<div class="mainissue_border">
							<div class="mainissue_picture">
								<img src="./resources/img/프로필.PNG">
							</div>
							<div class="mainissue_profile">
								<span id="issue_receiverName">${issue.member_nickname}<br></span>
								<br>
								<span style="margin-left:-170px !important;"></span>
								<span><h5 class="geoOfIssue"><%-- *${issue.address} --%><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${issue.post_time}" /><br><img src="./resources/img/radiusForIssue.png" style="width:20px; height:20px;"><input type="hidden" value="${issue.radius}" class="index_meterWillChange"> <p style="display:inline;">${issue.radius}m</p><br>
								</h5></span>
								<table style="margin-right:10px; float:right;">
									<tr><td><button data-chatBtn="${issue.issue_code}" class="btn btn-default requestChat">1:1대화신청</button></td></tr>
								<%-- 	<tr><td><button data-chatBtn="${issue.issue_code}" class="btn btn-default requestChat">상세 보기</button></td></tr> --%>
								</table>								
								<br>
							</div>
						    <div class="mainissue_content">
								<h3>${issue.issue_content}
								<br>
								<c:if test="${issue.issue_img != ''}">
									<img src="./resources/img/${issue.issue_img}">
								</c:if>
								<br>
								</h3>
							</div>
						</div>
					</li>
			</c:forEach>
		</c:if>
	</ul></div>
    	
    	
    	
    	
    	</div>
        <div id="main_info">
            <span class="main_member">
            	<c:if test="${nickname != null }">         
        			${nickname}님 환영합니다. 
        			
        			<input type="text" style="display:none" value="${member_code}"  class="member_code">
        			<input type="text" style="display:none" value="${member_email}" class="member_email">
        			<input type="text" style="display:none" value="${Cmember_code}" class="Cmember_code">
        			
        	    	<span class="main_logoutBtn">로그아웃</span>
				</c:if>
				<c:if test="${nickname == null }">  
                	<span class="main_loginBtn">로그인</span>
                	<span class="main_joinForm">회원가입</span>
             	</c:if>  
                <c:if test="${nickname != null }">
                	<span class="main_myPage">마이페이지</span>
                </c:if>
                
                <c:if test="${nickname != null }">  
	             	<a href="place?member_code=${member_code}&member_email=${member_email}">
	                	<span class="main_myPlaceBtn">마이플레이스</span>
	                </a>
             	</c:if>
             	  
               	<span class="main_PlaceBtn">플레이스</span>
            </span>
        </div>
        <div id="main_search">
        	
         	<input type="text" class="index_keyword form-control">
        	<button class="index_searchbtn btn btn-default">검색</button> 
        </div>
        <div id="main_geolocation">
        	<span id="whereIsNow">대구광역시 북구 복현2동</span><span id="main_notifyAboutGeo">에 대한 정보를 알려드려요!</span>
        	<div id="index_findeGeo">
        		<input type="text" id="index_searchMyGeolocation" class="form-control" value="대구광역시 북구 복현2동" placeholder="동명을 입력하세요" onkeypress="findGeoAddr(event, this.value)">
        		<input type="button" id="index_searchAutoGeolocation" class="btn btn-default" value="현재위치 자동검색" onclick="geoFindMe()">
        	</div>
        	<input type="button" value="위치 찾기" class="btn btn-default index_findAboutGeo">
        	<div id="resultOfGeo"><ul style='list-style:none; padding-left:0 !important;'></ul></div>
        </div>
        
        <!-- 채팅 내용 -->
    <c:if test="${nickname!= null && applogin == null}">
		<div class="chatImg">
			<img src="./resources/img/chat.png">
			<span class="badge chat_balloons"></span>		
		</div>
		
		<div id="code" class="chat_info">${code}</div>
		<div id="nickname" class="chat_info">${nickname}</div>
		<div class="layer">
			<div class="bg"></div>
			<div id="layer2" class="pop-layer">
				<div class="pop-container">
					<div class="pop-conts">
						<!--content //-->
						<p class="ctxt mb20">이슈가 도착하였습니다!<br>
							맛있는 사과 가게가 오픈했어요~<br>
							다들 한번씩 구경하러 오세요!<br><br>
							대구광역시 북구 복현로 37-2
						</p>
		
						<div class="btn-r">
							<br>
							<a href="#" class="cbtn">확인</a>
							<a href="#" class="cbtn">닫기</a>
						</div>
						<!--// content-->
					</div>
				</div>
			</div>
		</div>
	</c:if>
    <div class="chatDiv">
    	<span class="backChat glyphicon glyphicon-chevron-left"></span>
    	<span class="closeChat glyphicon glyphicon-remove"></span>
		<div class="chat_content">
		
 			<div class="chat_main">
		    	<div class="chat_owner">
		    		<h3>${nickname} 님의 대화방 목록 입니다.</h3>
		    	</div>
		    	<div class="chat_rooms">	    	
		    	</div>
	    	</div>
	    	
	    	<div class="chat_sub">
		    	<div class="chatWrap">	
					<div class="chats" id="chats">
					</div>
				</div>
				<span class="chat_more glyphicon glyphicon-plus-sign"></span>
					<div class="chat_flip">	
						<div class="chat_card">
							<div class="chat_menu front"> <!-- 책갈피 -->
								<table class="chat_moreTable" id="helpChat">
									<tr><td><img src="./resources/img/chat_helpChat.png" class="chat_menu_img"></td></tr>
									<tr><td>채팅 도움말</td></tr>
								</table>
								<table class="chat_moreTable" id="lookOtherItem">
									<tr><td><img src="./resources/img/chat_lookOtherItem.png" class="chat_menu_img" ></td></tr>
									<tr><td>상대 상품조회</td></tr>
								</table>
								<table class="chat_moreTable" id="lookMyItem">
									<tr><td><img src="./resources/img/chat_lookMyItem.png" class="chat_menu_img" ></td></tr>
									<tr><td>내 상품조회</td></tr>
								</table>
								<table class="chat_moreTable" id="addAuto">
									<tr><td><img src="./resources/img/chat_addAuto.png" class="chat_menu_img" ></td></tr>
									<tr><td>예약어 추가</td></tr>
								</table>
								<table class="chat_moreTable" id="exitChat">
									<tr><td><img src="./resources/img/chat_exitChat.png" class="chat_menu_img"></td></tr>
									<tr><td>대화방 나가기</td></tr>
								</table>
							</div>
							<div class="chat_menu back">
							</div>
						</div>
					</div>		
				<div class="comment">
					<input type="text" class="writeComment" id="writeComment" name="writeComment" placeholder="채팅 입력하세요" onkeypress="enter(event, this.value)"/>
					<button class="enter">입력</button>
				</div>
			</div> 
		</div>
	</div> 
        
        <div id="main_category">
        	<div class="dropdown">
        		<span class="main_issue">이슈</span>
        	</div>
            <div class="dropdown">
                <span>배달</span>
                <div class="dropdown-content">
                    <a href="main?dcate_code=1">음식</a>
                    <a href="main?dcate_code=2">꽃</a>
                    <a href="main?dcate_code=3">세탁</a>
                </div>
            </div>
            <div class="dropdown">
                <span>쇼핑</span>
                <div class="dropdown-content">
                    <a href="main?dcate_code=4">패션</a>
                    <a href="main?dcate_code=5">생활</a>
                    <a href="main?dcate_code=6">디지털</a>
                </div>
            </div>
            <div class="dropdown">
                <span>여가</span>
                <div class="dropdown-content">
                    <a href="main?dcate_code=7">공연</a>
                    <a href="main?dcate_code=8">숙박</a>
                </div>
            </div>
            <div class="dropdown">
                <span>뷰티</span>
                <div class="dropdown-content">
                    <a href="main?dcate_code=9">미용실</a>
                    <a href="main?dcate_code=10">네일</a>
                    <a href="main?dcate_code=11">화장품</a>
                </div>
            </div>
            
        </div>
        </div>
        <!--  여기까지가 헤더부분 -->
        <c:if test="${cont != null }">         
        	<jsp:include page="${cont}"></jsp:include>
		</c:if>
		
    </div>
    
    <%-- <!-- 채팅 내용 -->
    <c:if test="${nickname!= null && applogin == null}">
		<div class="chatImg">
			<img src="./resources/img/chat.png">
			<span class="badge chat_balloons"></span>		
		</div>
		
		<div id="code" class="chat_info">${code}</div>
		<div id="nickname" class="chat_info">${nickname}</div>
		<div class="layer">
			<div class="bg"></div>
			<div id="layer2" class="pop-layer">
				<div class="pop-container">
					<div class="pop-conts">
						<!--content //-->
						<p class="ctxt mb20">이슈가 도착하였습니다!<br>
							맛있는 사과 가게가 오픈했어요~<br>
							다들 한번씩 구경하러 오세요!<br><br>
							대구광역시 북구 복현로 37-2
						</p>
		
						<div class="btn-r">
							<br>
							<a href="#" class="cbtn">확인</a>
							<a href="#" class="cbtn">닫기</a>
						</div>
						<!--// content-->
					</div>
				</div>
			</div>
		</div>
	</c:if>
    <div class="chatDiv">
    	<span class="backChat glyphicon glyphicon-chevron-left"></span>
    	<span class="closeChat glyphicon glyphicon-remove"></span>
		<div class="chat_content">
		
 			<div class="chat_main">
		    	<div class="chat_owner">
		    		<h3>${nickname} 님의 대화방 목록 입니다.</h3>
		    	</div>
		    	<div class="chat_rooms">	    	
		    	</div>
	    	</div>
	    	
	    	<div class="chat_sub">
		    	<div class="chatWrap">	
					<div class="chats" id="chats">
					</div>
				</div>
				<span class="chat_more glyphicon glyphicon-plus-sign"></span>
					<div class="chat_flip">	
						<div class="chat_card">
							<div class="chat_menu front"> <!-- 책갈피 -->
								<table class="chat_moreTable" id="helpChat">
									<tr><td><img src="./resources/img/chat_helpChat.png" class="chat_menu_img"></td></tr>
									<tr><td>채팅 도움말</td></tr>
								</table>
								<table class="chat_moreTable" id="lookOtherItem">
									<tr><td><img src="./resources/img/chat_lookOtherItem.png" class="chat_menu_img" ></td></tr>
									<tr><td>상대 상품조회</td></tr>
								</table>
								<table class="chat_moreTable" id="lookMyItem">
									<tr><td><img src="./resources/img/chat_lookMyItem.png" class="chat_menu_img" ></td></tr>
									<tr><td>내 상품조회</td></tr>
								</table>
								<table class="chat_moreTable" id="addAuto">
									<tr><td><img src="./resources/img/chat_addAuto.png" class="chat_menu_img" ></td></tr>
									<tr><td>예약어 추가</td></tr>
								</table>
								<table class="chat_moreTable" id="exitChat">
									<tr><td><img src="./resources/img/chat_exitChat.png" class="chat_menu_img"></td></tr>
									<tr><td>대화방 나가기</td></tr>
								</table>
							</div>
							<div class="chat_menu back">
							</div>
						</div>
					</div>		
				<div class="comment">
					<input type="text" class="writeComment" id="writeComment" name="writeComment" placeholder="채팅 입력하세요" onkeypress="enter(event, this.value)"/>
					<button class="enter">입력</button>
				</div>
			</div> 
		</div>
	</div>  --%>
	
	
	<!-- Modal -->
	<div class="modal fade" id="chattingModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeChattingModal" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">SOMEBODY 알림창</h4>
				</div>
				<div class="modal-body bodyChattingModal">
				
					<h3><b>상품 정보</b></h3>
					<table class="table chat_requestTable">
						<tr>
							<td><h4><b>상품 이름</b></h4></td>
							<td><h4><b>옵션 추가 가격</b></h4></td>
							<td><h4><b>총 구매 가격</b></h4></td>
							<td><h4><b>수량</b></h4></td>
						</tr>
						<tr id="chat_requestList">
						
						</tr>
						<tr>
							<td colspan="4" id="chat_totalPrice"></td>
							<input type="hidden" id="chat_totalPrice2">
							<input type="hidden" id="chat_productCode">
						</tr>
					</table>
					<h3><b>세부 옵션 정보</b></h3>
					<table class="table" id="chat_optionTable">
						<tr>
							<td id="chat_optionName"><h4><b>옵션 이름</b></h4></td>
							<td id="chat_optionSelect"><h4><b>세부 옵션</b></h4></td>
							<td id="chat_optionAdditionalPrice"><h4><b>세부 옵션 추가 가격</b></h4></td>
						</tr>
						<tbody id='chat_detail_info'>
						
						</tbody>
					</table>
								
					<h3><b>주문자 정보</b></h3>
					<table class="table">
						<tr>
							<td>
								<div class="checkbox"> <label><input type="checkbox" id="cart_myInfo"> 기존 정보와 동일</label> </div>
								<div class="form-group">
									<label class="control-label col-sm-2">이름</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="cart_myName">								
									</div>
								</div>
								<br>
								<br>
								<div class="form-group">
									<label class="control-label col-sm-2">주소</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="cart_myAddr">
									</div>
								</div> 
								<br>
								<br>
								<div class="form-group">
									<label class="control-label col-sm-2">연락처</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" id="cart_myPhone">
									</div>
								</div>
								<br>
								<br>
								<div class="form-group">
									<label class="control-label col-sm-2">요청<br>사항</label>
									<div class="col-sm-10">
										<textarea class="form-control" id="cart_myContent"></textarea>
									</div>
								</div> 
								<br>
								<br>
								<div class="form-group">
									<label class="control-label col-sm-2">유형<br>선택</label>
									<div class="radio col-sm-10 chat_requestType"></div>
									<input type="hidden" id="chat_TotalType">
								</div>
								<br>
								<br>
								<div class="form-group">
									<label class="control-label col-sm-2">결제<br>수단</label>
									<div class="radio col-sm-10">
										<label>
											<input type="radio" id="inlineCheckbox1" value="option1" name="text^^"> 신용 카드
										</label>
										<label>
											<input type="radio" id="inlineCheckbox2" value="option2" name="text^^"> 실시간 계좌이체
										</label>
										<br>
										<label>
											<input type="radio" id="inlineCheckbox3" value="option3" name="text^^"> 휴대폰 결제
										</label>
										<label>
											<input type="radio" id="inlineCheckbox4" value="option5" name="text^^"> 현장 지불
										</label>
									</div>
								</div> 
							</td>
						</tr>
					</table>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default confrimChattingModal" data-dismiss="modal">신청하기</button>
					<button type="button" class="btn btn-default closeChattingModal" data-dismiss="modal">닫기</button>
				</div>
			</div>
			
		</div>
	</div>
	
	<c:if test="${isProduct == 'true'}">
		<div id="isProduct_mySidenav" class="isProduct_sidenav">
			<p href="#" id="isProduct_menu">유형 선택 메뉴</p>
			<p href="#" id="isProduct_1" class="clickForTypeItems">예약</p>
			<p href="#" id="isProduct_2" class="clickForTypeItems">판매</p>
			<p href="#" id="isProduct_3" class="clickForTypeItems">배달</p>
		</div>
	</c:if>
</body>
</html>