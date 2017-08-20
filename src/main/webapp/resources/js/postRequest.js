$(function(){
	$(".buyBtn").click(function(){
		$("form").attr("action", "moneysuccess");
		$("form").attr("method", "post");
		$("form").submit();
	});
	
	
	
	$(".postRequest_infoBtn").click(function(){
			$('#name').val($('.member_name').val());
			$('#addr').val($('.member_addr').val());
			$('#phone').val($('.member_phone').val());
	});
	
	
//	$(".checkKakao").click(function() {
//		var IMP = window.IMP; // 생략가능
//		IMP.init('imp52773681'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
//
//		IMP.request_pay({
//		    pg : 'kakao', // version 1.1.0부터 지원.
//		    pay_method : 'phone',
//		    merchant_uid : $("#pay_product_name").text() + new Date().getTime(),
//		    name : 'SomebodyPlace_결제 테스트',
//		    amount : $(".productTotalPrice").val(),
//		    buyer_email : $(".member_email").val(),
//		    buyer_name : $(".member_name").val(),
//		    buyer_tel : $(".member_phone").val(),
//		    buyer_addr : $(".member_addr").val(),
//		    buyer_postcode : '123-456',
//		}, function(rsp) {
//		    if ( rsp.success ) {
//		        var msg = '결제가 완료되었습니다.';
//		        msg += '고유ID : ' + rsp.imp_uid;
//		        msg += '상점 거래ID : ' + rsp.merchant_uid;
//		        msg += '결제 금액 : ' + rsp.paid_amount;
//		        msg += '카드 승인번호 : ' + rsp.apply_num;
//		    } else {
//		        var msg = '결제에 실패하였습니다.';
//		        msg += '에러내용 : ' + rsp.error_msg;
//		    }
//		    alert(msg);
//			$("form").attr("action", "moneysuccess");
//			$("form").attr("method", "post");
//			$("form").submit();
//		});
//	});
});