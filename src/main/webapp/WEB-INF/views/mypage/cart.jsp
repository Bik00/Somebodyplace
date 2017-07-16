<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<br><br>
<h2><b>장바구니 목록</b></h2><br><br>
<table class="cartTable table">
	<tr>
		<td><h4><b>상품 이미지</b></h4></td>
		<td><h4><b>상품 이름</b></h4></td>
		<td><h4><b>상품 세부 옵션</b></h4></td>
		<td><h4><b>구매 가격</b></h4></td>
		<td><h4><b>주문</b></h4></td>
	</tr>
	<c:forEach items="${cartlist}" var="list" varStatus="list_status">
		<tr>		 
			<td><img src="./resources/img/${list.product_img}" style="width:100px; height:100px;"></td>
			<td>${list.product_name}<br><input type="hidden" value="${list.cart_code}" id="cart_cartCode"></td>
			<c:forEach items="${cart_option}" var="option" varStatus="option_status" begin="${list_status.count-1}">
				<c:if test="${option_status.first}">	
					<td id="cartTable_detail">
						<c:forEach var="value" items="${option.value}" varStatus="value_status">
							${value.detail_name} : ${value.additional_price} 원 <br>
						</c:forEach>
					</td>
				</c:if>
			</c:forEach>
			<td>${list.cart_total_price}원</td>
			<td>
				<input type="button" class="btn btn-default" id="cart_addBtn" value="신청하기"><br>
				<input type="button" class="btn btn-default cart_cancelBtn" id="cart_cancelBtn" value="삭제하기">
			</td>
		</tr>		 	
	 </c:forEach>
	

</table>
		 		