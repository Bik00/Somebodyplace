/**
 * 
 */

$(document).ready(function() {
	$(".cart_cancelBtn").on('click', function(){
		var x = $(this).parent().prev().prev().prev().find("#cart_cartCode").val();
		location.href="delCart?cart_code="+x;
	});
	
	// 개별 신청하기 버튼 눌렀을 때
	$("#cart_addBtn").on("click", function() {
		$("#cart_requestList").empty();
		$("#cart_totalPrice").empty();
		var a = $(this).parent().prev().prev().prev().text();
		var b = $(this).parent().prev().prev().text();
		var c = $(this).parent().prev().text();
		var d = "<td><div>"+a+"</div></td><td><div>"+b+"</div></td><td><div>"+c+"</div></td><td><div>1</div></td>";
		var e = "<h4><b>총 상품 금액 : "+c+"</b></h4>";
		$(".cart_requestTable").find("tr:eq(1)").append(d);
		$("#cart_totalPrice").append(e);
		$("#cart_myInfo").prop('checked', true);
		loadMyInfo();
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
});

function loadMyInfo() {
    if($("#cart_myInfo").is(":checked")){
    	$.ajax({
    		type : "get",
			url : "getMyInfo",
			async:false,
			success : function(data){
				$(data.member_name).appendTo()
			}
    	});
    }
}