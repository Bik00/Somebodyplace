<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="post_default">
    <div class="post_img">
        <div class="post_no_img">
            <img src="${product.product_img}">
        </div>
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
      <input type="hidden" value="${type}" class="type" name="type"> <h5><b>가능상품</b></h5><br><br>
      
      
       설명: <div class="post_description">${product.product_explanation}</div>
		상품코드<input type="text" value="${product_code}" class="product_code" id="product_code" ><br>
		구매자(로그인한)회원코드<input type="text" value="${member_code}" class="member_code" id="member_code"><br>
		판매자회원코드<input type="text" value="${Cmember_code}" class="Cmember_code" id="Cmember_code">
        <div class="post_price"><h3><b>기본가 : ${product.product_price}원</b></h3></div>
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
        <div class="post_totalPrice"><h3><b>총 가격 : ${product.product_price}원</b></h3></div>
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
<!-- 		<div class="review">
			<div class="review_profile">
				<div class="review_picture">
					<img src="./resources/img/loginNow.jpg">
				</div>
				<div class="review_profile_info">
					<h3>닉네임 : 하하하하</h3>
				</div>
				<div class="review_score">
					<div class="review_score_star"></div>
					<div class="review_score_star"></div>
					<div class="review_score_star"></div>
					<div class="review_score_star"></div>
					<div class="review_score_star"></div>
				</div>
			</div>
			<div class="review_content">
				<div class="review_content_text">
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					너~~~무 좋아요!<br>
					
				</div>
			</div>
			<div id="setting_review">
				<button class="btn btn-default" style="color:#337ab7 !important;">수정하기</button>	
				<button class="btn btn-default">삭제하기</button>
			</div>
		</div>
		<hr> -->
		
	</div>
</div>
