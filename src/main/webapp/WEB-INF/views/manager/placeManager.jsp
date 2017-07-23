<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<c:if test="${applogin!= null }">
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script
	
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.css">
    <script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
    <script src="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.js"></script>
</c:if>
<div class="placemanager">
     <div class="placemanager_category">
	
			<button class="btn btn-default placeManager_modifybtn">플레이스 수정</button>
			<button class="btn btn-default placeManager_requestlist">신청현황</button><br>
			<button class="btn btn-default placeManager_addBusiness">사업자등록</button><br>
			<button class="btn btn-default placeManager_category">카테고리관리</button><br>
			<button class="btn btn-default placeManager_currentBudget">정산관리</button>
 	</div>
 	<c:if test="${placeMPage != null }">         
        <jsp:include page="${placeMPage}"></jsp:include>
	</c:if>
</div>