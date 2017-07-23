<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<br><br>
<h2><b>장바구니 목록</b></h2><br><br>
<table class="cartTable table">
	<tr>
		<td><input type="checkbox" name="vehicle" value="Bike" style="margin-top:15px;" id="cart_checkall"></td>
		<td><h4><b>상품 이미지</b></h4></td>
		<td><h4><b>상품 이름</b></h4></td>
		<td><h4><b>상품 세부 옵션</b></h4></td>
		<td><h4><b>구매 가격</b></h4></td>
		<td><h4><b>주문</b></h4></td>
	</tr>
	<c:forEach items="${cartlist}" var="list" varStatus="list_status">
		<tr>
			<td>
				<div>
					<input type="checkbox" name="cart_item" value="Bike">
				</div>
			</td>
			<td><img src="./resources/img/${list.product_img}" style="width:100px; height:100px;"></td>
			<td>${list.product_name}<br><input type="hidden" value="${list.cart_code}" id="cart_cartCode"></td>
			<c:forEach items="${cart_option}" var="option" varStatus="option_status" begin="${list_status.count-1}">
				<c:if test="${option_status.first}">	
					<td id="cartTable_detail">
						<c:forEach var="value" items="${option.value}" varStatus="value_status">
							${value.detail_name} : ${value.additional_price} 원 <input type="hidden" class="detailCodeNum" value="${value.detail_code}"><br>
						</c:forEach>
					</td>
				</c:if>
			</c:forEach>
			<td>${list.cart_total_price}원</td>
			<td>
				<input type="button" class="btn btn-default cart_addBtn" id="cart_addBtn" value="신청하기"><br>
				<input type="button" class="btn btn-default cart_cancelBtn" id="cart_cancelBtn" value="삭제하기">
			</td>
		</tr>		 	
	 </c:forEach>
</table>
<br>
<%-- <c:forEach items="${cartlist}" var="list" varStatus="list_status">
	<c:forEach items="${cart_option}" var="option" varStatus="option_status" begin="${list_status.count-1}">
		<c:if test="${option_status.first}">
			<div class="cart_good${option_status.count}">
			<c:forEach var="value" items="${option.value}" varStatus="value_status">
				<input type="hidden" class="detailCodeNum" value="${value.detail_code}">
			</c:forEach>
			</div>
		</c:if>
	</c:forEach>
</c:forEach> --%>
<div class="cart_buttonArea">
	<input type="submit" class="btn btn-default addBtn" id="addBtn" value="선택한 물품을 모두 신청하기" />
	<a href="myPage">
		<input type="button" class="btn btn-default cancelBtn" id="cancelBtn" value="돌아가기" />
	</a>
</div>

<!-- Modal -->
<div class="modal fade" id="RequestFromCartModal" role="dialog">
	<div class="modal-dialog">
	
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closeCartModal" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">SOMEBODY 알림창</h4>
			</div>
			<div class="modal-body">
				<h3><b>상품 정보</b></h3>
				<table class="table cart_requestTable">
					<tr>
						<td><h4><b>상품 이름</b></h4></td>
						<td><h4><b>상품 세부 옵션</b></h4></td>
						<td><h4><b>개별 가격</b></h4></td>
						<td><h4><b>수량</b></h4></td>
					</tr>
					<tr id="cart_requestList">
					
					</tr>
					<tr>
						<td colspan="4" id="cart_totalPrice"></td>
						<input type="hidden" id="cart_totalPrice2">
						<input type="hidden" id="cart_detailCode">
					</tr>
				</table>			
				<h3><b>주문자 정보</b></h3>
				<table class="table">
					<tr>
						<td>
							<div class="checkbox"> <label><input type="checkbox" id="cart_myInfo"> 기존 정보와 동일</label> </div>
							<div class="form-group">
								<label class="control-label col-sm-2">이름</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="cart_myName">								
								</div>
							</div>
							<br>
							<br>
							<div class="form-group">
								<label class="control-label col-sm-2">주소</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="cart_myAddr">
								</div>
							</div> 
							<br>
							<br>
							<div class="form-group">
								<label class="control-label col-sm-2">연락처</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="cart_myPhone">
								</div>
							</div>
							<br>
							<br>
							<div class="form-group">
								<label class="control-label col-sm-2">요청<br>사항</label>
								<div class="col-sm-10">
									<textarea class="form-control" id="cart_myContent"></textarea>
								</div>
							</div> 
							<br>
							<br>
							<div class="form-group">
								<label class="control-label col-sm-2">유형<br>선택</label>
								<div class="radio col-sm-10 cart_requestType"></div>
								<input type="hidden" id="cart_TotalType">
							</div>
							<br>
							<br>
							<div class="form-group">
								<label class="control-label col-sm-2">결제<br>수단</label>
								<div class="radio col-sm-10">
									<label>
										<input type="radio" id="inlineCheckbox1" value="option1" name="text^^"> 신용 카드
									</label>
									<label>
										<input type="radio" id="inlineCheckbox2" value="option2" name="text^^"> 실시간 계좌이체
									</label>
									<br>
									<label>
										<input type="radio" id="inlineCheckbox3" value="option3" name="text^^"> 휴대폰 결제
									</label>
									<label>
										<input type="radio" id="inlineCheckbox4" value="option5" name="text^^"> 현장 지불
									</label>
								</div>
							</div> 
						</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default ConfirmRequestModal" data-dismiss="modal">신청하기</button>
				<button type="button" class="btn btn-default closeCartModal" data-dismiss="modal">닫기</button>
			</div>
		</div>
	</div>
</div>