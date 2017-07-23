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
	<script src="./resources/js/placeManager.js?ver=9"></script> 
</c:if>


<div class="place" data-role="page">
    <div class="place_title">
    	<c:if test="${!empty Cmember_code}">
    		<a href="placeHome?member_code=${Cmember_code}&member_email=${Cmember_email}">
    	</c:if>
    	<c:if test="${empty Cmember_code}">
    		<a href="placeHome?member_code=${member_code}&member_email=${member_email}">
    	</c:if>
    		<c:if test="${place_logo != ''}">
	    		<img src="./resources/img/${place_logo}" class="place_logo">
	    	</c:if>
	    	<h2 class="place_placename">${place_name}</h2>
    	</a>
    	<div class="fave" onclick="faves()" data-switch="off"></div>
    	<div class="place_placecode">${place_code}</div>
    	 	<c:if test="${place_busino==1}">
	    		<img src="./resources/img/medal.png" >
	    	</c:if>
    </div>
    <c:if test="${placePage != null}">         
        	<jsp:include page="${placePage}"></jsp:include>
	</c:if>
</div>