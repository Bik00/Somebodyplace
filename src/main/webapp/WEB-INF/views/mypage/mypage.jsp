<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${applogin!= null }">
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	<link rel="stylesheet" href="./resources/m_css/m_mypage.css?ver=3">
	<script src="./resources/js/mypage.js?ver=9"></script> 
	
</c:if>

   <input type="hidden" value="${member_code}" class="member_code">
    <div class="mypage_outdiv">
        <div class="mypage_indiv" id="mypage_indiv_modifyForm">
            <img src="./resources/img/회원정보.png" class="mypage_icon"><br>
                 <h5>회원정보수정</h5>              		                  	
        </div>
        
         <div class="mypage_indiv" id="mypage_indiv_orderList" >
            <img src="./resources/img/신청내역.png" class="mypage_icon"><br>
              		 <h5>신청내역</h5>
        </div>
         <div class="mypage_indiv" id="mypage_indiv_cart" >
             <img src="./resources/img/장바구니.png" class="mypage_icon"><br>
           			 <h5>장바구니</h5>
        </div>
      
         <div class="mypage_indiv" id="mypage_indiv_favorites" >
             <img src="./resources/img/즐겨찾기.png" class="mypage_icon"><br>
            		 <h5>즐겨찾기</h5>
        </div> 
           <div class="mypage_indiv" id="mypage_indiv_interestSetting">
             <img src="./resources/img/관심사설정.PNG" class="mypage_icon"><br>
        			  <h5>관심사설정</h5>
        </div> 
       
    </div>
