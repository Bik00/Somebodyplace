<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="place">
    <div class="place_title">
    	<a href="placeHome?member_code=${member_code}&member_email=${member_email}">
    		<c:if test="${place_logo != ''}">
	    		<img src="./resources/img/${place_logo}" class="place_logo">
	    	</c:if>
	    	<h2 class="place_placename">${place_name}</h2>
    	</a>
    </div>
    <c:if test="${placePage != null}">         
        	<jsp:include page="${placePage}"></jsp:include>
	</c:if>
</div>