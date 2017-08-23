<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="post_default">
    <div class="post_img">
        <div class="post_no_img">
            <img src="${product.product_img}">
        </div>
         <c:if test="${dcate_code == 8}">
        	<h3><b>예약 가능 날짜</b></h3>
        	<table style='margin:auto'>
        		<tr>
        			<td><h3><b>입실일</b></h3></td>
        			<td><h3><b>퇴실일</b></h3></td>
        		</tr>
        		<tr>
        			<td style='padding-right:30px;'><input type="text" class="form-control" id="getEnterTimesByEight" style='display:none'></td>
        			<td><input type="text" class="form-control" id="getOutTimesByEight" style='display:none'></td>
        		</tr>
        	</table>
        	
<!--         	
        	<div id="centerAlignByEight">
        		
        		
        	</div> -->
        </c:if>
    </div>
  	<input type="hidden" value="${post_code}" id="post_code">
    <div class="post_type">
        <div class="post_title"><b>${product.product_name}</b><br>
        
       		<div class="post_score">
				<div class="post_score_star"></div>
				<div class="post_score_star"></div>
				<div class="post_score_star"></div>
				<div class="post_score_star"></div>
				<div class="post_score_star"></div>
			</div>
			<b id="aveRageReview">4.5/5.0</b>
        </div>
      <b>${type}</b>
      <input type='hidden' value="${dcate_code}" class="dcate_code" name="dcate_code">
      <input type="hidden" value="${type}" class="type" name="type">
      <h4><b>가능상품</b></h4>
      <br><br>
      <div class="post_description" style='font-size:22px;'>${product.product_explanation}</div>
       
		<%-- 상품코드<input type="text" value="${product_code}" class="product_code" id="product_code" ><br>
		구매자(로그인한)회원코드<input type="text" value="${member_code}" class="member_code" id="member_code"><br>
		판매자회원코드<input type="text" value="${Cmember_code}" class="Cmember_code" id="Cmember_code"> --%>
       
		<input type="text" value="${product_code}" class="product_code" id="product_code" style='display:none'><br>
		<input type="text" value="${member_code}" class="member_code" id="member_code" style='display:none'><br>
		<input type="text" value="${Cmember_code}" class="Cmember_code" id="Cmember_code" style='display:none'>
       
       
        <div class="post_price"><h3><b>기본가 : ${product.product_price}원</b></h3></div>
       <c:if test="${dcate_code eq '1' || dcate_code eq '2'}">
       		<input type="button" class="btn btn-default" value="특별히 원하는 시간이 있나요?" id="getOwnerDcate">
       		<div id="reservationArea"></div>
       </c:if>
       
        <div class="post_options">
	        <c:forEach items="${option}" var="o">
		        	${o.option_name}
		        	 <select name="color" class="form-control detail_select">
			        	 <c:forEach items="${detailArray}" var="detail">
			        	 	<c:if test="${o.option_code == detail.option_code}">
				        			<option data-detailCode="${detail.detail_code}">${detail.detail_name} &nbsp;&nbsp;&nbsp;+ ${detail.add_price}원</option>
				        	</c:if>
			        	</c:forEach>
		        	 </select>
	        </c:forEach>
        </div>
        
       <c:if test="${dcate_code == 8 }">
        	<div id="getDisableTimesByEightList" style='display:none'>
	        	${enable_time}
        	</div>
        	<div id="resultEnterTimesByEightList"><h3><b>입실일 : </b></h3></div>
        	<div id="resultOutTimesByEightList"><h3><b>퇴실일 : </b></h3></div>
        </c:if>
        
        <div class="post_totalPrice"><h3><b>총 가격 : ${product.product_price}원</b></h3></div>
        <c:if test="${dcate_code == 7}">
        	<h3><b>예매 가능 날짜</b></h3>
        	<div id="centerAlignBySeven">
        		<input type="text" id="getEnableTimesBySeven" style="display:none;">
        	</div>
        	<div id="getEnableTimesBySevenList" style='display:none'>
	        	${enable_time}
        	</div>
        	<div id="resultEnableTimesBySevenList"><h3><b>예매한 날짜 : </b></h3></div>
        </c:if>

        <div class="post_btns">
            <button class="btn btn-default post_request">신청하기</button><br>
            <button class="btn btn-default post_cart">CART</button>
       
        </div>
    </div>
    <div class="post_content">
		<c:forEach items="${postContent}" var="post">
			<c:if test="${post.content.matches('.*jpg.*')}">
				<img src="./resources/img/${post.content}">
			</c:if>
			<c:if test="${post.content.matches('.*JPG.*')}">
				<img src="./resources/img/${post.content}">
			</c:if>
			<c:if test="${post.content.matches('.*png.*')}">
				<img src="./resources/img/${post.content}">
			</c:if>
			<c:if test="${!post.content.matches('.*jpg.*')}">
				<div>${post.content}</div>
			</c:if>
			
		</c:forEach>
	</div>
    
</div>
<div class="review_area">
	<h3><b>후기 목록</b></h3>
	<div class="check_review_score">
		<div class="check_review_score_star" onmouseover="scores(0)" onmouseout="offscores(0)" onclick="setScore()"></div>
		<div class="check_review_score_star" onmouseover="scores(1)" onmouseout="offscores(1)" onclick="setScore()"></div>
		<div class="check_review_score_star" onmouseover="scores(2)" onmouseout="offscores(2)" onclick="setScore()"></div>
		<div class="check_review_score_star" onmouseover="scores(3)" onmouseout="offscores(3)" onclick="setScore()"></div>
		<div class="check_review_score_star" onmouseover="scores(4)" onmouseout="offscores(4)" onclick="setScore()"></div>
	</div>
	<textarea class="form-control" rows="3" id="comment_review"></textarea>
	<button class="btn btn-default enterReviewButton">후기 등록</button>
	<div class="review_list">
		<hr>		
	</div>
</div>
