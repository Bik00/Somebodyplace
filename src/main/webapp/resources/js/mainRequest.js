$(document).ready(function() {
	
	if($("#mainRequest_dCateCode").val() == 3) {
		flatpickr("#DrySeparateDay", {
			minDate: "today",
			inline: true,
			enableTime: true
		});
		
		flatpickr("#DryDeliveryDay", {
			minDate: "today",
			inline: true,
			enableTime: true
		});
	}

	$("#tryMainRequest").click(function() {
		
		var a = $("#DrySeparateDay").val();
		var b = $("#DryDeliveryDay").val();
		$("#mainRequestDateInfo").html(a+"<br>"+b);
		$("#tryMainRequestModal").modal();
	});
	
	$(document).on("click", ".confrimTryMainRequestModal", function() {
		var a = $("#cart_myName").val();
		var b = $("#cart_myAddr").val();
		var c = $("#cart_myPhone").val();
		var d = $("#cart_myContent").val();
		var e = $("#DrySeparateDay").val();
		var f = $("#DryDeliveryDay").val();
		
		var query = {
			member_name : a,
			member_addr : b,
			member_phone : c,
			request_content : d,
			request_firstDate : e,
			request_secondDate : f
		}

		$.ajax({
			type : "post",
			url : "payAboutDry",
			data : query,
			async:false,
			success : function(data){
				alert("신청 완료되었습니다.");
				location.href = "orderList";
			}
		});
	});
});