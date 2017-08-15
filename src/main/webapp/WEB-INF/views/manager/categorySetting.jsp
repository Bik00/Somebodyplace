<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${applogin!= null }">
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script
	
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.css">

    <script src="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.js"></script>
	<link rel="stylesheet" href="./resources/m_css/m_categorySetting.css?ver=3">
	<script src="./resources/js/place.js?ver=9"></script> 
	<script src="./resources/js/placeManagerStats.js?ver=9"></script> 
</c:if>
    
    
    
<form class="categorySetting">
    <h4><b>카테고리 관리ㆍ설정</b></h4>
    <div class="category">
        <input type="button" class="btn btn-default cateAddBtn" value="+ 카테고리 추가" />
        <input type="button" class="btn btn-default cateDelBtn" value="- 삭제" />
            
        <ul class="myCategory">
            <h5><b>&nbsp;&nbsp;카테고리 전체보기</b></h5><br>
            <c:forEach items="${BoardList}" var="board">
            	<li><input type="text" class="categoryInput" name="board_name" value="${board.board_name}" data="${board.board_code}" data-boardType="${board.board_type}"></li>
            </c:forEach>
    	</ul>

	    <div class="setting">
	        <form class="form-horizontal">
	            <div class="form-group">
	                <label>카테고리명</label> &nbsp;&nbsp;
	                <input type="text" class="categoryTitle form-control" id="aa">
	            </div>
	            <div class="form-group">
	                <label>카테고리 유형</label> &nbsp;&nbsp;
	                     <input type="radio" class="category_type" name="category_type" checked="checked" value='postForm'> 상품 등록 형식
	                     <input type="radio" class="category_type" name="category_type" value='mainForm'> 공지사항 형식
 				         <div id="selectedCategoryArea">
	 				         <c:forEach items="${BoardList}" var="board">
				            	<input type="hidden" class="her" name="selectedCategory_type" value="${board.board_type}">
				            </c:forEach> 
	            		</div>
	            </div>
	            
	            
	            <div class="form-group">
	                <label>공개설정</label> &nbsp;&nbsp;
	                     <input type="radio" name="open" checked="checked">공개
	                     <input type="radio" name="private">비공개
	            </div>
	
	        </form>
	    </div>
    	<input type="hidden" name="place_code" value="${place_code}">
     	<input type="button" class="btn btn-default cateSubmit" value="확인" />
	</div>
</form>