var cartCode = '';

$(document).ready(function() {
	$(".cart_cancelBtn").on('click', function(){
		var x = $(this).parent().prev().prev().prev().find("#cart_cartCode").val();
		location.href="delCart?cart_code="+x;
	});
	
	// 개별 신청하기 버튼 눌렀을 때
	$(".cart_addBtn").on("click", function() {
		$("#cart_requestList").empty();
		$("#cart_totalPrice").empty();
		$(".cart_requestType").empty();
		var a = $(this).parent().prev().prev().prev().text();
		var b = $(this).parent().prev().prev().text();
		var c = $(this).parent().prev().text();
		var d = "<td><div>"+a+"</div></td><td><div>"+b+"</div></td><td><div>"+c+"</div></td><td><div>1</div></td>";
		var e = "<h4><b>총 상품 금액 : "+c+"</b></h4>";
		var f = $(this).parent().prev().prev().prev().find("input[type=hidden]").val();
		cartCode = f;
		var g = '';
		$(this).parent().prev().prev().find("input[type=hidden]").each(function(index){
			g += $(this).val()+",";
		});
		$("#cart_detailCode").val(g);
		$(".cart_requestTable").find("tr:eq(1)").append(d);
		$("#cart_totalPrice").append(e);
		$("#cart_totalPrice2").val(c);
		$("#cart_myInfo").prop('checked', true);
		loadMyInfo();
		loadProductType(f, a);
		$("#RequestFromCartModal").modal();
	});
	
	$("#cart_myInfo").change(function(){
		loadMyInfo();
	});
	
	$("#cart_checkall").click(function() {
		//클릭되었으면
		if ($("#cart_checkall").prop("checked")) {
			//input태그의 name이 "cart_item"인 태그들을 찾아서 checked옵션을 true로 정의
			$("input[name=cart_item]").prop("checked", true);
		//클릭이 안되있으면
		} else {
			//input태그의 name이 "cart_item"인 태그들을 찾아서 checked옵션을 false로 정의
			$("input[name=cart_item]").prop("checked", false);
		}
	});
	
	$(".ConfirmRequestModal").click(function() {
		var a = $("#cart_myName").val();
		var b = $("#cart_myAddr").val();
		var c = $("#cart_myPhone").val();
		var d = $("#cart_myContent").val();
		var e = $("#cart_totalPrice2").val();
		e = e.substring(0, e.indexOf("원"));
		var f = $("#cart_TotalType").val();
		var g = $("#cart_detailCode").val();
		var query = {
			member_name : a,
			member_addr : b,
			member_phone : c,
			request_content : d,
			request_list_totalprice : e,
			request_type : f,
			detail_code : g,
			cart_code : cartCode
		}
		$.ajax({
			type : "post",
			url : "payByCart",
			data : query,
			async:false,
			success : function(data){
				alert("신청 완료되었습니다.");
				location.href = "orderList";
			}
		});
	});
});

function loadMyInfo() {
    if($("#cart_myInfo").is(":checked")){
    	$.ajax({
    		type : "get",
			url : "getMyInfo",
			async:false,
			success : function(data){
				$("#cart_myName").val(data.member_name);
				$("#cart_myAddr").val(data.member_addr);
				$("#cart_myPhone").val(data.member_phone);
			}
    	});
    }
}

function loadProductType(x, k) {
	$.ajax({
		type : "get",
		url : "loadProductType",
		data : {cart_code : x},
		async:false,
		success : function(data){
			var x = data.cart_type;
			$("#cart_TotalType").val(x);
			var result = x.split(",");
			var z = '';
			$(".cart_requestType").append(k+" : ");
			for(var y=0;y<result.length;y++) {
				$(".cart_requestType").append("<label><input type='radio' value='"+result[y]+"' name='request_type'>"+result[y]+"</label>&nbsp;&nbsp;&nbsp;");
			}
		}
	});
}