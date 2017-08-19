<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1">
    <title>카카오페이 결제창</title>
  </head>
        <script src="./resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.2.js"></script>
<script src="./resources/js/responsive-switch.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

  <body>
  
		<input type="hidden" id="member_name" value="${member_name}">
		<input type="hidden" id="member_email" value="${member_email}">
		<input type="hidden" id="member_phone" value="${member_phone}">
		<input type="hidden" id="member_addr" value="${member_addr}">
		<input type="hidden" id="request_list_totalprice" value="${request_list_totalprice}">
		<input type="hidden" id="product_name" value="${product_name}">

    <script>

    var IMP = window.IMP; // 생략가능
	IMP.init('imp52773681'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용

	IMP.request_pay({
	    pg : 'kakao', // version 1.1.0부터 지원.
	    pay_method : 'card',
	    merchant_uid : $("#product_name").val()+ new Date().getTime(),
	    name : $("#product_name").val(),
	    amount : $("#request_list_totalprice").val(),
	    buyer_email : $("#member_email").val(),
	    buyer_name : $("#member_name").val(),
	    buyer_tel : $("#member_phone").val(),
	    buyer_addr : $("#member_addr").val(),
	    buyer_postcode : '123-456'
	}, function(rsp) {
	    if ( rsp.success ) {
	        var msg = '결제가 완료되었습니다.';
	        msg += '고유ID : ' + rsp.imp_uid;
	        msg += '상점 거래ID : ' + rsp.merchant_uid;
	        msg += '결제 금액 : ' + rsp.paid_amount;
	        msg += '카드 승인번호 : ' + rsp.apply_num;
	    } else {
	        var msg = '결제에 실패하였습니다.';
	        msg += '에러내용 : ' + rsp.error_msg;
	    }
	    alert(msg); 
	});
    </script>
  </body>
</html>