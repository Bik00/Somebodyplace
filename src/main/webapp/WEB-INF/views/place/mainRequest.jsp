<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<input type="hidden" value="${dcate_code}" id="mainRequest_dCateCode">

<c:if test="${dcate_code == 3}">

	<div class="form-group row" style='margin: auto;text-align: center;'>
		<label style='margin-left:250px;' for="inputEmail3" class="col-sm-2 col-form-label"><h3>수거일</h3></label>
		<div class="col-sm-5" style='margin-bottom:50px;'>
			<input type="text" class="form-control" id="DrySeparateDay"
				style='background: white !important;'>
		</div>
	</div>

	<div class="form-group row" style='margin: auto;text-align: center;'>
		<label style='margin-left:250px;' for="inputEmail3" class="col-sm-2 col-form-label"><h3>배송일</h3></label>
		<div class="col-sm-5" style='margin-bottom:50px;'>
			<input type="text" class="form-control" id="DryDeliveryDay"
				style='background: white !important;'>
		</div>
	</div>
	<div style='width:100%; text-align:center;'>
		<input type='button' class='btn btn-default' value='신청하기' id='tryMainRequest'>
		<input type="button" class='btn btn-default' value="취소하기" onclick="window.history.go(-1)">
	</div>
	<!-- Modal -->
	<div class="modal fade" id="tryMainRequestModal" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeTryMainRequestModal" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">SOMEBODY 알림창</h4>
				</div>
				<div class="modal-body bodyTryMainRequestModal">
					<h3><b>서비스 정보</b></h3>
					<table class="table mainRequest_requestTable">
						<tr>
							<td><h4><b>서비스 이름</b></h4></td>
							<td><h4><b>세부 서비스 종류</b></h4></td>
							<td><h4><b>서비스 제공 날짜</b></h4></td>
						</tr>
						<tr id="mainRequest_requestList">
							<td>세탁 서비스</td>
							<td>수거일<br>배송일</td>
							<td id="mainRequestDateInfo"></td>
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
					<button type="button" class="btn btn-default confrimTryMainRequestModal" data-dismiss="modal">신청하기</button>
					<button type="button" class="btn btn-default closeTryMainRequestModal" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</c:if>