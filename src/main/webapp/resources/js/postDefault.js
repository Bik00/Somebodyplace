var originalPrice = ""; // 원래 가격
var className = ""; // 선택된 옵션들
$(function(){
	//원래 가격을 저장시킨다.
	originalPrice = $(".post_price>h3>b").text().substring($(".post_price>h3>b").text().indexOf(":")+2, $(".post_price>h3>b").text().indexOf("원"));
	//select문에 저장된 가격을 더한다.
	
	className = document.getElementsByClassName("detail_select");
	var presentPrice = originalPrice;
	for(var i=0;i<className.length;i++) {
		presentPrice = parseInt(presentPrice)+parseInt(className[i].value.substring(className[i].value.indexOf("+")+2, className[i].value.indexOf("원")));
	}		
	$(".post_totalPrice>h3>b").text("총 가격 : "+presentPrice+"원");
	
	
	
	var imgBoxW = $(".post_no_img").width();
	var img = new Image();
	img.src = $(".post_no_img img").attr("src");
	img.width = imgBoxW;
	$(".post_no_img").html(img);
	$(".post_no_img").height(img.height);
	$(".type").prev().empty();
	
	
	// 처음 페이지 로드 시 유형에 부트스트랩 버튼 효과 주는 js문
	var x = $(".type").val();
	
	var a = x.split(",");
	for(var i=0;i<a.length;i++) {
		if(a[i]=='예약') {
			$(".type").prev().append("<input type='button' class='btn btn-info' style='color:white; cursor:default' value='"+a[i]+"'>");
		} else if(a[i]=='판매') {
			$(".type").prev().append("<input type='button' class='btn btn-success' style='color:white; cursor:default' value='"+a[i]+"'>");
		} else if(a[i]=='배달') {
			$(".type").prev().append("<input type='button' class='btn btn-warning' style='color:white; cursor:default' value='"+a[i]+"'>");
		}
	}
	
	
	//신청하기 버튼 클릭시 
	$(".post_request").click(function(){
		
		var product_code = $("#product_code").val();
		var member_code = $("#member_code").val();
		var product_price = originalPrice;
		var product_Total = $(".post_totalPrice>h3>b").text().substring($(".post_totalPrice>h3>b").text().indexOf(":")+2, $(".post_totalPrice>h3>b").text().indexOf("원"));
		var type=$(".type").val();
		var dcate_code = $(".dcate_code").val();

		var form = $('<form></form>');
		form.attr('action', 'postRequest');
		form.attr('method', 'post');
		form.appendTo('body');
		
		var className = document.getElementsByClassName("detail_select");
		
		if($("#selectReservation").attr("data-isappear") == "success" && $("#selectReservation").val().length == 0) {
			alert("예약 시간을 입력해주세요.");
			return false;
		} else if($("#selectReservation").attr("data-isappear") == "success" && $("#selectReservation").val().length != 0) {
			var qv = $("#selectReservation").val();
			var ve = $('<input type="text" value="'+qv+'" id="request_list_reserve" name="request_list_reserve">');
			form.append(ve);
		} else {}
		
		for(var i=0;i<className.length;i++) {
			/*var e = $('<input type="text" value="'+parseInt(className[i].value.substring(className[i].value.indexOf("+")+2, className[i].value.indexOf("원")))+'" id="detail_price" name="detail_price">');*/
			var k = className[i].options[className[i].selectedIndex].getAttribute("data-detailCode");
			var e = $('<input type="text" value="'+k+'" id="detail_code" name="detail_code">');
			form.append(e);
		}
		
		var a = $('<input type="hidden" value="'+product_code+'" name="product_code">');
		var b = $('<input type="hidden" value="'+member_code+'" name="member_code">');
		var c = $('<input type="hidden" value="'+product_price+'" name="product_price">');
		var d = $('<input type="hidden" value="'+product_Total+'" name="product_Total">');
		var z = $('<input type="hidden" value="'+type+'" name="type">');
		var j = $('<input type="hidden" value="'+dcate_code+'" name="dcate_code">');
		
		if(typeof $("#getEnableTimesBySeven").attr("id") != "undefined") {
			var selected_date = $("#getEnableTimesBySeven").val();
			var cszw = $('<input type="hidden" value="'+selected_date+'" name="service_option_info_time">');
			form.append(cszw);
		}
		
		if(typeof $("#getDisableTimesByEightList").attr("id") != "undefined") {
			var selected_enter_date = $("#getEnterTimesByEight").val();
			var selected_out_date = $("#getOutTimesByEight").val();
			var cszw = $('<input type="hidden" value="'+selected_enter_date+'" name="selected_enter_date">');
			var qwer = $('<input type="hidden" value="'+selected_out_date+'" name="selected_out_date">');
			form.append(cszw).append(qwer);
		}
		
		
		form.append(a).append(b).append(c).append(d).append(z).append(j);
		form.submit();
	});
	
	$(document).on('change', '.detail_select', function(){
		className = document.getElementsByClassName("detail_select");
		var presentPrice = originalPrice;
		for(var i=0;i<className.length;i++) {
			presentPrice = parseInt(presentPrice)+parseInt(className[i].value.substring(className[i].value.indexOf("+")+2, className[i].value.indexOf("원")));
		}		
		$(".post_totalPrice>h3>b").text("총 가격 : "+presentPrice+"원");
	});
	
	//장바구니(cart)클릭시 
	$(".post_cart").click(function(){
		alert("장바구니에 추가되었어요");
		var product_code = $("#product_code").val();
		var Cmember_code = $("#Cmember_code").val();
		var member_code = $("#member_code").val();
		var product_price = originalPrice;
		var product_Total = $(".post_totalPrice>h3>b").text().substring($(".post_totalPrice>h3>b").text().indexOf(":")+2, $(".post_totalPrice>h3>b").text().indexOf("원"));
		
		

		var form = $('<form></form>');
		form.attr('action', 'postcart');
		form.attr('method', 'post');
		form.appendTo('body');
		
		var className = document.getElementsByClassName("detail_select");
		for(var i=0;i<className.length;i++) {
			/*var e = $('<input type="text" value="'+parseInt(className[i].value.substring(className[i].value.indexOf("+")+2, className[i].value.indexOf("원")))+'" id="detail_price" name="detail_price">');*/
			var k = className[i].options[className[i].selectedIndex].getAttribute("data-detailCode");
			var e = $('<input type="text" value="'+k+'" id="detail_code" name="detail_code">');
			form.append(e);
		}

		var a = $('<input type="hidden" value="'+product_code+'" name="product_code">');
		var b = $('<input type="hidden" value="'+Cmember_code+'" name="Cmember_code">');
		var c = $('<input type="hidden" value="'+product_price+'" name="product_price">');
		var d = $('<input type="hidden" value="'+product_Total+'" name="product_Total">');
		var f = $('<input type="hidden" value="'+member_code+'" name="member_code">');
		
		form.append(a).append(b).append(c).append(d).append(f);
		form.submit();
	});
	
	$(document).on("click", "#getOwnerDcate", function() {
		if($(this).next().children().attr("data-isAppear") != "success") {
			var x = "<input type='text' class='form-control' id='selectReservation' data-isAppear='success'><span class='selectReservationBox'><p>x</p></span>";
			$("#reservationArea").append(x);
			flatpickr("#selectReservation", {
				enableTime: true,
				minDate: "today"
			});
		}
	});
	
	$(document).on("click", ".selectReservationBox", function() {
		$("#reservationArea").html('');
	});
	
	$(document).ready(function() {
		if(typeof $("#getEnableTimesBySeven").attr("id") != "undefined") {
			var str = $("#getEnableTimesBySevenList").text();
			var str2 = str.split(",");

			flatpickr("#getEnableTimesBySeven", {
				inline:true,
				minDate: "today",
				enable : str2
			});
		}
		
		if(typeof $("#getEnterTimesByEight").attr("id")  != "undefined") {
			var str = $("#getDisableTimesByEightList").text();
			var str2 = str.split(",");
			
			var check_in = flatpickr("#getEnterTimesByEight", {
				inline:true,
				minDate: new Date(),
				disable : str2
			});
			var check_out = flatpickr("#getOutTimesByEight", {
				inline:true,
				minDate: new Date(),
				disable : str2
			});
		

			check_in.element.addEventListener("change", function(){
			    check_out.set( "minDate" , check_in.element.value );
			    $("#resultEnterTimesByEightList").html("<h3><b>입실일 : "+check_in.element.value+"</b></h3>")
			});

			check_out.element.addEventListener("change", function(){
			    check_in.set( "maxDate" , check_out.element.value );
			    $("#resultOutTimesByEightList").html("<h3><b>퇴실일 : "+check_out.element.value+"</b></h3>")
			});
		}
	
	});
	
	$(document).on("change", "#getEnableTimesBySeven", function() {
		$("#resultEnableTimesBySevenList").html("<h3><b>예매한 날짜 : "+$("#getEnableTimesBySeven").val()+"</b></h3>");
	});
});