<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${applogin!= null }">
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script
	
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		   <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.css">

    <script src="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.js"></script>
	<link rel="stylesheet" href="./resources/m_css/m_place.css?ver=3">
	<script src="./resources/js/place.js?ver=9"></script> 
		<script src="./resources/js/placeHome.js?ver=9"></script> 
	<script src="./resources/js/placeManager.js?ver=9"></script>
	 <div class="place_manage">
	        <button class="placeManager_btn btn btn-default">관리</button>
	        <button class="postForm_btn btn btn-default">글쓰기</button>
	    </div> 
</c:if>



<div class="place_menu" data-role="page">
	
	<c:if test="${Cmember_code == member_code}">
	    <div class="place_manage">
	        <button class="placeManager_btn btn btn-default">관리</button>
	        <button class="postForm_btn btn btn-default">글쓰기</button>
	    </div>
	</c:if>
	
    <div class="place_cate">
        <c:forEach items="#{BoardList}" var="b">
              <a href="#" data-boardcode="${b.board_code}">${b.board_name}</a> 
        </c:forEach>
    </div>
</div>


<div class="place_post" data-role="page">
	<c:forEach items="${ProductList}" var="p">
		<div class="product_box" data="${p.product_code}">
	        <div class="product_img">
	        	<img src="${p.product_img}">
	        </div>
	        <div class="product_info">
	            <div>${p.product_name}</div>
	            <div>${p.product_explanation}</div>
	            <div>${p.product_price}</div>
	        </div>
	        <br>
	        <c:if test="${member_code == Cmember_code}">
		        <a class="btn icon-btn btn-info" id="modifyItem" href="#">
					<span class="glyphicon btn-glyphicon glyphicon-share img-circle text-info"></span>
					수정하기
				</a>
				<a class="btn icon-btn btn-danger" id="deleteItem" href="deleteItem">
					<span class="glyphicon btn-glyphicon glyphicon-trash img-circle text-danger"></span>
					삭제하기
				</a>
			</c:if>
	    </div>
	</c:forEach>
	<c:if test="${!empty mainFormTime}">
		<c:if test="${!empty main_type}">
			<img src="./resources/img/place_writeNow.jpg" style='width: 400px;margin-top: 50px;'>
		</c:if>
		<c:if test="${!empty board_content}">
			<div id="board_contentArea">
				${board_content}
			</div>
		</c:if>
		<br>
		<c:if test="${Cmember_code == member_code}">
			<input data-boardCode='${board_code}' class="btn btn-default" type='button' style='width: 300px;' value='작성하기' id='place_writeNow'>
		</c:if>
		
		<c:if test="${Cmember_code != member_code}">
			<button class="mainForm_btn btn btn-default" style='width: 300px;' data-dcateCode='${dcate_code}'>서비스 신청하기</button>
		</c:if>
	</c:if>
</div>

<div style="display:none" id="member_code">${member_code}</div>
<div style="display:none" id="Cmember_code">${Cmember_code}</div>