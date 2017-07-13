<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="table">
	<tr>
		<c:forEach items="${cartlist}" var="list" varStatus="list_status">
		 
			<td><img src="./resources/img/${list.product_img}" sttyle="width:100px; height:100px;"></td>
			<td>${list.product_name}</td>
			<td>${list.detail_name}</td>
			<c:forEach items="${cart_option}" var="option">
				<td>
					${option.value}
				<td>
			</c:forEach>
		 	
		 </c:forEach>
	
	</tr>
</table>
		 		