<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="table">
		<c:forEach items="${cartlist}" var="list" varStatus="list_status">
			<tr>		 
			<td><img src="./resources/img/${list.product_img}" sttyle="width:100px; height:100px;"></td>
			<td>${list.product_name}</td>
			<td>${list.detail_name}</td>
			<c:forEach items="${cart_option}" var="option" varStatus="option_status" begin="${list_status.count-1}">
				<c:if test="${option_status.first}">
					<td>
						<c:forEach var="value" items="${option.value}" varStatus="value_status">
							${value.detail_name} : ${value.additional_price} 원 <br>
						</c:forEach>
					<td>
				</c:if>
			</c:forEach>
			<td>${list.cart_total_price}원</td>
			</tr>		 	
		 </c:forEach>
	

</table>
		 		