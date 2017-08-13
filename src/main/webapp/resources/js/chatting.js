	/* 채팅에 필요한 변수들 */
	var receiver; 		/* 발신자 변수 */
	var sender; 		/* 수신자 변수 */
	var distinction = false; /* 방 입장 상태 를 구별하는 변수 */
	var flippings = false;   /* 뒤집기 이벤트를 위한 변수      */
	var title = "";		/* 이미지를 담을 때 DIV를 담는 변수 */
	var imageStr = ""; // 이미지를 담는 변수 (div 빼고)
	var autoResult = ""; // 키워드의 입력 상태를 확인하는 변수
	var autoList=""; // 자동 답변 목록을 불러오는 변수
	var isCopleteBuying = false ; // 물건 샀는지 여부
		
	/* 길안내 관련 변수 선언 */
    var directionsDisplay;
    var directionsService = new google.maps.DirectionsService();
    var map;
    var tryCenter;
    var isMapLoading = false;
    var tryZoom;
	

	/* 엔터키를 누르면 이 함수가 실행된다. */
	function enter(event, keyword) {
		var d = new Date();
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		var date =  d.getDate();
		var hours = d.getHours();
		var minutes = d.getMinutes();
		var string = year+'-'+month+'-'+date+' ('+hours+':'+minutes+')';
		
		var category = 0;
		
		var x = event.which || event.keyCode;
		
		if(x==13) {
			
			var query = {
				auto_code:receiver
			}
			$.ajax({
				type : "get",
				url : "readAutoList",
				data : query,
				async:false,
				success : function(data){
					if(data.length==0) {
						autoList +="";
					} else {
						for(var i=0; i<data.length;i++) {
							autoList += "<li>"+data[i].auto_title+"</li>";
						}						
					}
				}
			});
			query = {
				auto_title:keyword,
				auto_code : receiver
			}
			$.ajax({
				type : "post",
				url : "readAuto",
				data : query,
				async:false,
				success : function(data){
					if(data.length != 0) {
						var result = "<div class='chat system'>"+data[0].member_nickname+"님의 자동 답변 :<br>"+data[0].auto_content+"<h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
						$(result).appendTo(".chats");
					} else {}
				}
			});
						
			if(keyword=="키워드") {
				if(autoList.length==0) {
					var result = "<div class='chat system'>상대방이 키워드를 입력하지 않았습니다!<h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
				} else {
					var result = "<div class='chat system'>상대방이 등록한 키워드는 다음과 같습니다.<ul>"+autoList+"</ul><h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
				}
				$(result).appendTo(".chats");
			}
			
			if(keyword=="도움말") {
				var result = "<div class='chat system'>사용할 수 있는 커멘드는 다음과 같습니다.<ul><li>도움말 - 사용할 수 있는 커멘드 기능들을 불러옵니다.</li><li>주문 - 상대방의 물품을 구매할 수 있습니다.<br><h6>예시 : 주문 청바지 등</li><li>상대상품 - 상대방의 플레이스에 등록된 상품들을 불러옵니다.</li><li>내상품 - 내 플레이스에 등록된 상품들을 불러옵니다.</li><li>키워드 - 상대방이 입력한 키워드를 알 수 있습니다.</ul><h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
				$(result).appendTo(".chats");
			}
			if(keyword=="내상품") {
				var result ="<div class='chat system'>현재 자신의 플레이스에 등록된 상품은 다음과 같습니다.";

				$.ajax({
					type : "post",
					url : "searchTheirItemList",
					data : {owner:sender},
					async : false,
					success : function(data){
						if(data.length != 0) {
							for(var i=0; i<data.length;i++) {
								var mine = $("#code").text();	
								result += "<button class='accordion'><table><td style='width:100px'><span style='width:100%'>"+data[i].product_name+"</span></td><td>&#10097; "+data[i].product_price+"원</td></table></button><div class='panel'>"
								+"<table><tr><td rowspan='2'><img style='width:100px;height:100px;' src='"+data[i].product_img+"'></td><td style='text-align:center; width:100%;'>가격 : "+data[i].product_price+"원</td></tr><tr><td style='text-align:center; width:100%;'><a href='postDefault?product_code="+data[i].product_code+"&member_code="+mine+"'><input type='button' class='btn btn-default chattingItemListButton' value='상세 보기'></a><br><input type='button' class='btn btn-default chattingItemListButton tryChattingbuying' value='신청하기'><input type='hidden' value='"+data[i].product_code+"'></td></tr><tr><td style='text-align:center;'><b>"+data[i].product_name+"</b></td></tr></table></div>";
							}
						} else {
							result = "<div class='chat system'>플레이스를 생성하지 않았거나 상품을 등록하지 않았습니다!";
						}
						result += "</ul><h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
					}
				});

				$(result).appendTo(".chats");
				
			}
			if(keyword=="상대상품") { //책갈피8
				
				var result ="<div class='chat system'>현재 상대방의 플레이스에 등록된 상품은 다음과 같습니다.";

				$.ajax({
					type : "post",
					url : "searchTheirItemList",
					data : {owner:receiver},
					async : false,
					success : function(data){
						if(data.length != 0) {
							for(var i=0; i<data.length;i++) {
								var mine = $("#code").text();	
								result += "<button class='accordion'><table><td style='width:100px'><span style='width:100%'>"+data[i].product_name+"</span></td><td>&#10097; "+data[i].product_price+"원</td></table></button><div class='panel'>"
								+"<table><tr><td rowspan='2'><img style='width:100px;height:100px;' src='"+data[i].product_img+"'></td><td style='text-align:center; width:100%;'>가격 : "+data[i].product_price+"원</td></tr><tr><td style='text-align:center; width:100%;'><a href='postDefault?product_code="+data[i].product_code+"&member_code="+mine+"'><input type='button' class='btn btn-default chattingItemListButton' value='상세 보기'></a><br><input type='button' class='btn btn-default chattingItemListButton tryChattingbuying' value='신청하기'><input type='hidden' value='"+data[i].product_code+"'></td></tr><tr><td style='text-align:center;'><b>"+data[i].product_name+"</b></td></tr></table></div>";
							}
						} else {
							result = "<div class='chat system'>상대방이 플레이스를 생성하지 않았거나 상품을 등록하지 않았습니다.";
						}
						result += "</ul><h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
					}
				});

				$(result).appendTo(".chats");
			}
			if(keyword=="주문") {
				var result = "<div class='chat system'>구매할 상대방의 물품을 입력하세요.<br><h6>예시 : 주문 청바지 등<br><br>작성자 : 시스템 <br>작성 시간 : "+string+"</h6></div>";
				$(result).appendTo(".chats");//책갈피1
			}
			if(keyword.indexOf("주문") == 0 && keyword.length > 3) {
				$.ajax({
					type : "post",
					url : "searchTheirItem",
					data : {keyword:keyword.substring(3, keyword.length), owner:receiver},
					async : false,
					success : function(data){
						if(data.result == "success") {
							
							var x = data.product_code;
							$.ajax({
								type : "post",
								url : "getTheirItem",
								data : {product_code : x},
								success : function(data) {
				/*					alert(JSON.stringify(data));*/
									$("#chat_requestList").empty();
									$("#chat_totalPrice").empty();
									$(".chat_requestType").empty();
									$("#chat_detail_info").empty();
									var a = data.product_name;
									var b = data.product_price;
									var angel = data.product_code;
									$("#chat_productCode").val(angel);
									
									var e = new Array();
									
									for (var i=0;i<data.product_mcate.length;i++) {
										var c = "<tr><td>"+data.product_mcate[i].mcate_name+"</td><td><select class='form-control chat_detail_select'>";
										for(var j=0;j<data.product_mcate[i].mcate_detail.length;j++) {
											c+="<option value='"+data.product_mcate[i].mcate_detail[j].dcate_code+"'>"+data.product_mcate[i].mcate_detail[j].dcate_name+"</option>";
										}
										c+= "</select></td><td class='chat_detail_additionalPrice'>"+data.product_mcate[i].mcate_detail[0].add_price+"원</td></tr>";
										e[i] = data.product_mcate[i].mcate_detail[0].add_price;
										$("#chat_detail_info").append(c);
									}

									var d = "<td><div>"+a+"<input type='hidden' id='chat_product_originalPrice' value='"+b+"'></div></td><td><div id='chat_option_totalPrice'>옵션별 추가 가격~</div></td><td><div id='chat_totalPrice'>"+b+"</div></td><td><div>1</div></td>";
									$(".chat_requestTable").find("tr:eq(1)").append(d);
									
									var g = 0;
									for(var f = 0; f<e.length;f++) {
										g += e[f];
									}
									$("#chat_option_totalPrice").empty();
									$("#chat_option_totalPrice").text(g+"원");
									
									var h = b + g;
									$("#chat_totalPrice").empty();
									$("#chat_totalPrice").append(h+"원");
									$("#chat_totalPrice2").val(h);
									
									var x = data.product_type;
									$("#chat_TotalType").val(x);
									var result = x.split(",");
									for(var y=0;y<result.length;y++) {
										$(".chat_requestType").append("<label><input type='radio' value='"+result[y]+"' name='request_type'> : "+result[y]+"</label>&nbsp;&nbsp;&nbsp;");
									}
								}
							});
							$("#chattingModal").modal();							
						} else {
							var result = "<div class='chat system'>구매하려는 상품이 존재하지 않습니다!<br>구매할 상대방의 물품을 정확히 입력하세요. <h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6></div>";
							$(result).appendTo(".chats");			
						}
					}
				});
			}
			
			addMessage();			
			setTimeout('sendMessage()', 1);
			setTimeout("$('.writeComment').val('')", 1);
			setTimeout('scrollDown()',1);
			autoList = '';
			autoResult = '';
		}
	}
	
	function enter2(event, cls) {
		var x = event.which || event.keyCode;
		if(x==13) {
			var data = {
					auto_code : sender,
					auto_title: $(cls).val()
			}
			$.ajax({
				type : "post",
				url : "addAutoList",
				data : data,
				async : false,
				success : function(data){
					var d = "\"\"";
					$('#chat_auto_result > ul').append("<li>"+$(cls).val()+"<br><span id='deleteAuto' class='glyphicon glyphicon-remove' style='cursor:pointer'></span><input type='text' style='width:230px;' placeholder='자동으로 완성될 말을 입력해주세요.' onkeypress='enter3(event, this.value, \""+$('#chat_auto_text').val()+"\", this)'></input><input type='button' value='입력' style='float:right'></input></li>");
					$(cls).val('');					
				}
			});
		}
	}
	
	function enter3(event, val, title, cls) {
		var x = event.which || event.keyCode;
		if(x==13) {
			var data = {
					auto_code : sender,
					auto_title : title,
					auto_content : val
			}
			$.ajax({
				type : "post",
				url : "addAuto",
				data : data,
				async : false,
				success : function(data) {
					alert("자동 예약어가 등록되었습니다.");
				}
			});
		}
	}
	
	$(document).on("click", "#deleteAuto", function() {
		var data = {
				auto_code : sender,
				auto_title: $(this).parent().text()
		}
		$.ajax({
			type : "post",
			url : "delAutoList",
			data : data,
			success : function(data) {
			}
		});
		$(this).parent().remove();	
	});
	
	$(document).ready(function(){
		/* 물품 구매 관련 */ //책갈피6
		
		$(document).on("click", ".accordion", function() {

/*			if(typeof $(this).attr("data-lat") != "undefined") {
				var latitude = $(this).attr("data-lat");
				var longitude = $(this).attr("data-lng");
				
				var img = new Image();
				img.src = "http://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude+ "&zoom=13&size=250x250&sensor=false&markers="+latitude+","+longitude+"&key=AIzaSyBRbzjSWzunm0qNKBwcriVdQVaR7vTvQnQ";		
				$(this).next().append(img);
			}*/
			
			var acc = document.getElementsByClassName("accordion");
			var i;

			for (i = 0; i < acc.length; i++) {
			  acc[i].onclick = function() {
			    this.classList.toggle("active");
			    var panel = this.nextElementSibling;
			    if (panel.style.maxHeight){
			      panel.style.maxHeight = null;
			    } else {
			      panel.style.maxHeight = panel.scrollHeight + "px";
			    } 
			  }
			}
		});
		
		$(document).on("click", ".tryChattingbuying", function() { // 신청하기 버튼을 눌렀을 때
			var x = $(this).next().val();
			$.ajax({
				type : "post",
				url : "getTheirItem",
				data : {product_code : x},
				success : function(data) {
/*					alert(JSON.stringify(data));*/
					$("#chat_requestList").empty();
					$("#chat_totalPrice").empty();
					$(".chat_requestType").empty();
					$("#chat_detail_info").empty();
					var a = data.product_name;
					var b = data.product_price;
					var angel = data.product_code;
					$("#chat_productCode").val(angel);
					
					var e = new Array();
					
					for (var i=0;i<data.product_mcate.length;i++) {
						var c = "<tr><td>"+data.product_mcate[i].mcate_name+"</td><td><select class='form-control chat_detail_select'>";
						for(var j=0;j<data.product_mcate[i].mcate_detail.length;j++) {
							c+="<option value='"+data.product_mcate[i].mcate_detail[j].dcate_code+"'>"+data.product_mcate[i].mcate_detail[j].dcate_name+"</option>";
						}
						c+= "</select></td><td class='chat_detail_additionalPrice'>"+data.product_mcate[i].mcate_detail[0].add_price+"원</td></tr>";
						e[i] = data.product_mcate[i].mcate_detail[0].add_price;
						$("#chat_detail_info").append(c);
					}

					var d = "<td><div>"+a+"<input type='hidden' id='chat_product_originalPrice' value='"+b+"'></div></td><td><div id='chat_option_totalPrice'>옵션별 추가 가격~</div></td><td><div id='chat_totalPrice'>"+b+"</div></td><td><div>1</div></td>";
					$(".chat_requestTable").find("tr:eq(1)").append(d);
					
					var g = 0;
					for(var f = 0; f<e.length;f++) {
						g += e[f];
					}
					$("#chat_option_totalPrice").empty();
					$("#chat_option_totalPrice").text(g+"원");
					
					var h = b + g;
					$("#chat_totalPrice").empty();
					$("#chat_totalPrice").append(h+"원");
					$("#chat_totalPrice2").val(h);
					
					
					var x = data.product_type;
					$("#chat_TotalType").val(x);
					var result = x.split(",");
					for(var y=0;y<result.length;y++) {
						$(".chat_requestType").append("<label><input type='radio' value='"+result[y]+"' name='request_type'> : "+result[y]+"</label>&nbsp;&nbsp;&nbsp;");
					}
				}
			});
			$("#chattingModal").modal();
		});
		
		//세부 옵션을 선택하였을 때
		
		$(document).on("change", ".chat_detail_select", function() {
			var x = $(this).parent().next();
			
			$.ajax({
				type : "post",
				url : "getDetailPriceForChat",
				data : {detail_code : $(this).val()},
				async : false,
				success : function(data) {
					x.text(data+"원");					
				}
			});
			var y = parseInt($('#chat_product_originalPrice').val()); // 오리지날 가격
			var z = 0;
			var a = document.getElementsByClassName("chat_detail_additionalPrice");
			for(var i=0;i<a.length;i++) {
				var k = a[i].innerHTML;
				z += parseInt(k.substring(0, k.length-1));
			}
			
			$("#chat_option_totalPrice").empty();
			$("#chat_option_totalPrice").text(z+"원");
			
			var h = y + z;
			$("#chat_totalPrice").empty();
			$("#chat_totalPrice").append(h+"원");
			$("#chat_totalPrice2").val(h);
			
		});
		
		// 신청하기 버튼을 눌렀을 때
		
		$(".confrimChattingModal").on("click", function() {
			var a = $("#cart_myName").val();
			var b = $("#cart_myAddr").val();
			var c = $("#cart_myPhone").val();
			var d = $("#cart_myContent").val();
			var e = $("#chat_totalPrice2").val();
			var f = $("#chat_TotalType").val();
			var g = new Array();
			var j = "";
			var angel = $("#chat_productCode").val();
			$(".chat_detail_select").each(function(h) {
				g[h] = $(this).val();
			});
			
			
			for(var i =0;i<g.length;i++) {
				j+=g[i]+",";
			}
			var query = {
				member_name : a,
				member_addr : b,
				member_phone : c,
				request_content : d,
				request_list_totalprice : e,
				request_type : f,
				detail_code : j,
				product_code : angel
			}
			$.ajax({
				type : "post",
				url : "payByCart",
				data : query,
				async:false,
				success : function(data){
					alert("신청 완료되었습니다.");
					location.href = "orderList"; //책갈피7
					
					
				}
			});
		});
		
		
		$(".chats").on("click", "div a", function(event) {
			var fileLink = $(this).attr("href");
			if(checkImageType(fileLink)) {
				event.preventDefault();
				var imgTag = $("#popup_img");
				imgTag.attr("src", fileLink);
				
				$(".popup").fadeIn("slow");
				imgTag.addClass("show");
			}
		});
		
		$("#popup_img").on("click", function() {
			$(".popup").fadeOut("slow");
		});

		receiver = $('#code').text();
		countDist();
		$('.chat_more').click(function() {
			$('.chat_flip').toggle(600);
			if(flippings == true) {
				$('.back').empty();
	        	$('.chat_card').removeClass('chat_flipped');
	        	flippings = false;
			}
		});
		
	    $('#addAuto').click(function(){
	    	readAutoList();
	    	var autoList = "<label>예약어 추가</label> <input type='text' id='chat_auto_text' onkeypress='enter2(event, this)'/><input type='button' value='추가하기' /><div id='chat_auto_result'><ul>"+autoResult+"</ul></div>";
			$(autoList).appendTo(".back");
        	$(this).parent().parent().addClass('chat_flipped');
        	flippings = true;
        	autoResult = '';
        });
	    
	    $('#helpChat').click(function() { //책갈피9
			var d = new Date();
			var year = d.getFullYear();
			var month = d.getMonth() + 1;
			var date =  d.getDate();
			var hours = d.getHours();
			var minutes = d.getMinutes();
			var string = year+'-'+month+'-'+date+' ('+hours+':'+minutes+')';
	    	$(".chats").append("<div class='chat system'>사용할 수 있는 커멘드는 다음과 같습니다.<ul><li>도움말 - 사용할 수 있는 커멘드 기능들을 불러옵니다.</li><li>주문 - 상대방의 물품을 구매할 수 있습니다.<br><h6>예시 : 주문 청바지 등</li><li>상대상품 - 상대방의 플레이스에 등록된 상품들을 불러옵니다.</li><li>내상품 - 내 플레이스에 등록된 상품들을 불러옵니다.</li><li>키워드 - 상대방이 입력한 키워드를 알 수 있습니다.</ul><h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>");
	    	scrollDown();
	    });
	    
	    $("#lookOtherItem").click(function() {
	    	var result ="<div class='chat system'>현재 상대방의 플레이스에 등록된 상품은 다음과 같습니다.";

			$.ajax({
				type : "post",
				url : "searchTheirItemList",
				data : {owner:receiver},
				async : false,
				success : function(data){
					if(data.length != 0) {
						for(var i=0; i<data.length;i++) {
							var mine = $("#code").text();	
							result += "<button class='accordion'><table><td style='width:100px'><span style='width:100%'>"+data[i].product_name+"</span></td><td>&#10097; "+data[i].product_price+"원</td></table></button><div class='panel'>"
							+"<table><tr><td rowspan='2'><img style='width:100px;height:100px;' src='"+data[i].product_img+"'></td><td style='text-align:center; width:100%;'>가격 : "+data[i].product_price+"원</td></tr><tr><td style='text-align:center; width:100%;'><a href='postDefault?product_code="+data[i].product_code+"&member_code="+mine+"'><input type='button' class='btn btn-default chattingItemListButton' value='상세 보기'></a><br><input type='button' class='btn btn-default chattingItemListButton tryChattingbuying' value='신청하기'><input type='hidden' value='"+data[i].product_code+"'></td></tr><tr><td style='text-align:center;'><b>"+data[i].product_name+"</b></td></tr></table></div>";
						}
					} else {
						result = "<div class='chat system'>상대방이 플레이스를 생성하지 않았거나 상품을 등록하지 않았습니다.";
					}
					result += "</ul><h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
				}
			});

			$(result).appendTo(".chats");
	    	scrollDown();
	    });
	    
	    $("#lookMyItem").click(function() {
	    	// 책갈피11
	    	
	    	var result ="<div class='chat system'>현재 자신의 플레이스에 등록된 상품은 다음과 같습니다.";

			$.ajax({
				type : "post",
				url : "searchTheirItemList",
				data : {owner:sender},
				async : false,
				success : function(data){
					if(data.length != 0) {
						for(var i=0; i<data.length;i++) {
							var mine = $("#code").text();	
							result += "<button class='accordion'><table><td style='width:100px'><span style='width:100%'>"+data[i].product_name+"</span></td><td>&#10097; "+data[i].product_price+"원</td></table></button><div class='panel'>"
							+"<table><tr><td rowspan='2'><img style='width:100px;height:100px;' src='"+data[i].product_img+"'></td><td style='text-align:center; width:100%;'>가격 : "+data[i].product_price+"원</td></tr><tr><td style='text-align:center; width:100%;'><a href='postDefault?product_code="+data[i].product_code+"&member_code="+mine+"'><input type='button' class='btn btn-default chattingItemListButton' value='상세 보기'></a><br><input type='button' class='btn btn-default chattingItemListButton tryChattingbuying' value='신청하기'><input type='hidden' value='"+data[i].product_code+"'></td></tr><tr><td style='text-align:center;'><b>"+data[i].product_name+"</b></td></tr></table></div>";
						}
					} else {
						result = "<div class='chat system'>플레이스를 생성하지 않았거나 상품을 등록하지 않았습니다!";
					}
					result += "</ul><h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
				}
			});

			$(result).appendTo(".chats");
			scrollDown();
	    });
	    
	    $(".back").on("click", 'input[value="추가하기"]', function(){
	    	var x = $(this).prev();
	    	var data = {
					auto_code : sender,
					auto_title: $(this).prev().val()
			}
			$.ajax({
				type : "post",
				url : "addAutoList",
				data : data,
				async : false,
				success : function(data){
					var d = "\"\"";
					$('#chat_auto_result > ul').append("<li>"+x.val()+"<br><span id='deleteAuto' class='glyphicon glyphicon-remove' style='cursor:pointer'></span><input type='text' style='width:230px;' placeholder='자동으로 완성될 말을 입력해주세요.' onkeypress='enter3(event, this.value, \""+$('#chat_auto_text').val()+"\", this)'></input><input type='button' value='입력' style='float:right'></input></li>");
					x.val('');					
				}
			});
	    });
	    
	    $(".back").on("click", 'input[value="입력"]', function(){
	    	var data = {
					auto_code : sender,
					auto_title : $(this).parent().text(),
					auto_content : $(this).prev().val()
			}
			$.ajax({
				type : "post",
				url : "addAuto",
				data : data,
				async : false,
				success : function(data) {
					alert("자동 예약어가 등록되었습니다.");
				}
			});
	    	
	    });
	    $("#chatRoute").click(function() {
			var d = new Date();
			var year = d.getFullYear();
			var month = d.getMonth() + 1;
			var date =  d.getDate();
			var hours = d.getHours();
			var minutes = d.getMinutes();
			var string = year+'-'+month+'-'+date+' ('+hours+':'+minutes+')';
	    	var result ="<div class='chat system'>현재 상대방의 플레이스 주소는 다음과 같습니다.";
	    	$.ajax({
				type : "post",
				url : "searchTheirAddress",
				data : {owner:receiver},
				async : false,
				success : function(data){
					if(data.length != 0) {
						for(var i=0; i<data.length;i++) {
							var mine = $("#code").text();	
							var latitude = data[i].place_lat;
							var longitude = data[i].place_lng;							 
							result += "<button class='accordion'><table><td>"+data[i].place_addr+"</td></table></button><div class='panel'>"
							+"<img src='http://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude+ "&zoom=13&size=250x250&sensor=false&markers="+latitude+","+longitude+"&key=AIzaSyBRbzjSWzunm0qNKBwcriVdQVaR7vTvQnQ'><input type='button' class='btn btn-default findDirection' value='길안내'></div>";
						}
					} else {
						result = "<div class='chat system'>상대방이 플레이스 주소를 입력하지 않았습니다.";
					}
					result += "<h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
				}
			});
			$(result).appendTo(".chats");
    		scrollDown();
	    });
	    
	    $(document).on("click", ".findDirection", function() {
	    	var startLocation = $("#whereIsNow").text();
	    	var endLocation = $(this).parent().prev().children().children().children().children().text();
	    	var mode = "TRANSIT";
	    	
	    	alert("출발지는 : "+startLocation+" , 도착지는 : "+endLocation);
	    	
	    	var icons = {
    			start : new google.maps.MarkerImage(
    			// URL
    			'./resources/img/chatRoute_start.png',
    			// (width,height)
    			new google.maps.Size(80, 80),
    			// The origin point (x,y)
    			new google.maps.Point(0, 0),
    			// The anchor point (x,y)
    			new google.maps.Point(40, 80)),
    			end : new google.maps.MarkerImage(
    			// URL
    			'./resources/img/chatRoute_end.png',
    			// (width,height)
    			new google.maps.Size(80, 80),
    			// The origin point (x,y)
    			new google.maps.Point(0, 0),
    			// The anchor point (x,y)
    			new google.maps.Point(40, 80))
	    	};
    		var request = {
    			origin:startLocation,
    			destination:endLocation,
    			travelMode: eval("google.maps.DirectionsTravelMode."+mode)
    		};
	    		
    		directionsService.route(request, function(response, status) {
           		if (status == google.maps.DirectionsStatus.OK) {
           			directionsDisplay.setDirections(response);
    				var leg = response.routes[ 0 ].legs[ 0 ];
      				makeMarker( leg.start_location, icons.start, "현위치 : "+leg.start_address );
      				makeMarker( leg.end_location, icons.end, "목적지 : "+leg.end_address );
      	    		tryCenter = map.getCenter();
      	    		tryZoom = map.getZoom();
           		}
    		});

    		function makeMarker( position, icon, title ) {
    			var markers = new google.maps.Marker({
    				position: position,
    				map: map,
    				icon: icon,
    				title: title
    			});

    			var data = title;
    			var infowindow = new google.maps.InfoWindow({
    				content: data
    			});

    			google.maps.event.addListener(markers, 'click', function() {
    				infowindow.open(map,markers);
    			});
    			
    		}
	        $("#chatRouteModal").modal();

	    });
	    $('#chatRouteModal').on('shown.bs.modal', function () {
/*	    	map.setCenter(tryCenter);
	    	map.setZoom(tryZoom);*/ //책갈피
	    	google.maps.event.trigger(map, "resize");
	    });
	    
	    $('#exitChat').click(function(){
	    	if(confirm("대화방을 나가시겠습니까?")) {
	    		var query = {
	    				sender : sender,
	    				receiver : receiver
	    		}
	    		$.ajax({
	    			type:"post",
	    			url:"delChat",
	    			data : query,
	    			async:false,
	    			success : function() {
	    				$(".writeComment").val('delChat');
	    				sendMessage();
	    				alert("대화방을 나갔습니다.");
	    				$('.backChat').trigger('click');
	    				$(".writeComment").val('');
	    			}
	    		});
	    	}
        });
	    
	    
	    
	    
		$(".chatWrap").scroll(function(){
	        $(".closeChat").css('right', '20px');
	    });
		
 		$('.chatImg').click(function() {
			var data = {
				chat_receiver : $('#code').text()
			}
			$.ajax({
				type : "post",
				url : "getRooms",
				data : data,
				async : false,
				success : function(data){
					for(var i=0; i<data.length;i++) {
						
						var this_sender = data[i].chat_sender;
						var this_time = data[i].chat_time;
						var this_content = data[i].chat_content;
						if(this_content.length >15) {
							this_content = this_content.substring(0,15)+"...";
						}
						
						
						if(this_content.indexOf("<a href=\'displ") != -1) {
							this_content = "이미지가 도착하였습니다.";
						}
						
						$.ajax({
							type: "post",
							url : "countChatsInRoom",
							data : {
								receiver : $('#code').text(),
								sender : data[i].sender
							},
							async:false,
							success : function(data) {
								if(data == 0) {
									var result = "<div class='chat_room' data-sender='"+this_sender+"'><div class='chat_picture'><img src='./resources/img/clerk2.png'> </div><div class='chat_profile'> "+
									this_sender+"&nbsp;"+this_time+"<div>"+
									this_content+"</div></div><hr></div>";
									$(result).appendTo(".chat_rooms");
								} else {		
									var result = "<div class='chat_room' data-sender='"+this_sender+"'><div class='chat_picture'><img src='./resources/img/clerk2.png'><span class='badge chat_mini_balloons'>"+
									data+"</span> </div><div class='chat_profile'> "+
									this_sender+"&nbsp;"+this_time+"<div>"+
									this_content+"</div></div><hr></div>";
									$(result).appendTo(".chat_rooms");
								}
							}
						});
					}
				}
			});
			
			$('.chatDiv').show('slow');
			receiver=$('#code').text();
		});
		
		$('.closeChat').click(function() {
			$('.chatDiv').hide('slow');
			$('.chat_sub').hide();
			$('.chats').empty();
			$('.chat_main').show();
			$('.chat_more').hide();
			$('.chat_flip').hide();
			$('.chat_rooms').empty();
			$('.back').empty();
			distinction = false;
			isWriteAuto = false;
			receiver = $('#code').text();
			sender = null;
			$('.backChat').hide();
			$('.closeChat').css('right', '385px');
		});
		
		$('.backChat').click(function() {
			$('.chat_sub').hide();
			$('.chats').empty();
			$('.chat_rooms').empty();
			$('.chat_main').show();
			$('.backChat').hide();
			$('.chat_more').hide();
			$('.chat_flip').hide();
			$('.closeChat').css('right', '385px');
			receiver = $('#code').text();
			sender = null;
			distinction = false;
			
			var data = {
					chat_receiver : $('#code').text()
			}
			$.ajax({
				type : "post",
				url : "getRooms",
				data : data,
				async : false,
				success : function(data){
					for(var i=0; i<data.length;i++) {
						
						var this_sender = data[i].chat_sender;
						var this_time = data[i].chat_time;
						var this_content = data[i].chat_content;
						if(this_content.length >15) {
							this_content = this_content.substring(0,15)+"...";
						}
						
						if(this_content.indexOf("<a href=\'displ") != -1) {
							this_content = "이미지가 도착하였습니다.";
						}
						
						$.ajax({
							type: "post",
							url : "countChatsInRoom",
							data : {
								receiver : $('#code').text(),
								sender : data[i].sender
							},
							async:false,
							success : function(data) {
								if(data == 0) {
									var result = "<div class='chat_room' data-sender='"+this_sender+"'><div class='chat_picture'><img src='./resources/img/clerk2.png'> </div><div class='chat_profile'> "+
									this_sender+"&nbsp;"+this_time+"<div>"+
									this_content+"</div></div><hr></div>";
									$(result).appendTo(".chat_rooms");
								} else {		
									var result = "<div class='chat_room' data-sender='"+this_sender+"'><div class='chat_picture'><img src='./resources/img/clerk2.png'><span class='badge chat_mini_balloons'>"+
									data+"</span> </div><div class='chat_profile'> "+
									this_sender+"&nbsp;"+this_time+"<div>"+
									this_content+"</div></div><hr></div>";
									$(result).appendTo(".chat_rooms");
								}
							}
						});
					}
				}
			});
		});
		
			
		$('.chat_rooms').on("click", '.chat_room', function() {

			var data = {
					sender : $(this).attr("data-sender")
			}
			$.ajax({
				type : "post", 
				url : "getSenderNum", 
				data : data,
				async: false,
				success : function(data){ 
					sender = data;
				}
			});

			$('.chat_main').hide();
			$('.chat_rooms').empty();
			$('.chats').empty();
			var data = {
					receiver : receiver,
					sender : sender
			};
			$.ajax({
				type : "post", 
				url : "chatList", 
				data : data,
				success : function(data){ 
					for(var i=0; i<data.length;i++) {
				    	if(data[i].chat_content == "도움말") { // 책갈피3 : 키워드를 입력했을 때 상대가 대화방에 입장하면 로드
				    		$(".chats").append("<div class='chat system'>사용할 수 있는 커멘드는 다음과 같습니다.<ul><li>도움말 - 사용할 수 있는 커멘드 기능들을 불러옵니다.</li><li>주문 - 상대방의 물품을 구매할 수 있습니다.<br><h6>예시 : 주문 청바지 등</li><li>상대상품 - 상대방의 플레이스에 등록된 상품들을 불러옵니다.</li><li>내상품 - 내 플레이스에 등록된 상품들을 불러옵니다.</li><li>키워드 - 상대방이 입력한 키워드를 알 수 있습니다.</ul><h6>작성자 : 시스템 <br>작성 시간 : "+data[i].chat_time+"</h6> </div>");
				    	} else if(data[i].chat_content == "내상품") {
				    		$(".chats").append("<div class='chat system'>자신의 상품 목록을 조회하였습니다!<h6>작성자 : 시스템 <br>작성 시간 : "+data[i].chat_time+"</h6> </div>");
				    	} else if(data[i].chat_content == "상대상품") {
				    		$(".chats").append("<div class='chat system'>상대방의 상품 목록을 조회하였습니다!<h6>작성자 : 시스템 <br>작성 시간 : "+data[i].chat_time+"</h6> </div>");
				    	} else if(data[i].chat_content == "주문") {
				    		$(".chats").append("<div class='chat system'>구매할 상대방의 물품을 입력하세요.<br><h6>예시 : 주문 청바지 등<br><br>작성자 : 시스템 <br>작성 시간 : "+data[i].chat_time+"</h6></div>"); 	
				    	} else if(data[i].chat_content == "키워드") {
				    		if(data[i].chat_sender == sender) {
					    		var query = {
					    				auto_code:receiver
					    		}
				    		} else if(data[i].chat_sender == receiver) {
					    		var query = {
					    				auto_code:$("#code").text()
					    		}				    			
				    		}
			    			$.ajax({
			    				type : "get",
			    				url : "readAutoList",
			    				data : query,
			    				async:false,
			    				success : function(data){
			    					if(data.length==0) {
			    						autoList +="";
			    					} else {
			    						for(var i=0; i<data.length;i++) {
			    							autoList += "<li>"+data[i].auto_title+"</li>";
			    						}						
			    					}
			    				}
			    			});
				    		
				    		if(autoList.length==0) {
								var result = "<div class='chat system'>상대방이 키워드를 입력하지 않았습니다!<h6>작성자 : 시스템 <br>작성 시간 : "+data[i].chat_time+"</h6> </div>";
							} else {
								var result = "<div class='chat system'>상대방이 등록한 키워드는 다음과 같습니다.<ul>"+autoList+"</ul><h6>작성자 : 시스템 <br>작성 시간 : "+data[i].chat_time+"</h6> </div>";
							}
							$(result).appendTo(".chats");
				    		autoList = '';
				    		autoResult = '';
				    	} else if(distinction == true){
				    		if(data[i].chat_sender == sender) {
					    		var query = {
				    				auto_title:data[i].chat_content,
				    				auto_code:receiver
					    		}
				    		} else if(data[i].chat_sender == receiver) {
					    		var query = {
				    				auto_title:data[i].chat_content,
				    				auto_code:$("#code").text()
					    		}				    			
				    		}
				    		var time = data[i].chat_time;
			    			$.ajax({
			    				type : "post",
			    				url : "readAuto",
			    				data : query,
			    				async:false,
			    				success : function(data){
			    					if(data.length != 0) {
			    						var result = "<div class='chat system'>"+data[0].member_nickname+"님의 자동 답변 :<br>"+data[0].auto_content+"<h6>작성자 : 시스템 <br>작성 시간 : "+time+"</h6> </div>";
			    						$(result).appendTo(".chats");
			    					} else {}
			    				}
			    			});
				    	}
				    	
						if(data[i].chat_sender == sender) {
					    	$(".chats").append("<div class='chat mine'> "+data[i].chat_content+"<h6>작성자 : "+data[i].sender+" <br>작성 시간 : "+data[i].chat_time+"</h6> </div>");	
					    } else if (data[i].chat_sender == receiver){
					    	$(".chats").append("<div class='chat yours'> "+data[i].chat_content+"<h6>작성자 : "+data[i].sender+" <br>작성 시간 : "+data[i].chat_time+"</h6> </div>");
					    } else {}
					}
					setTimeout('scrollDown()',1);
				}
			});
			$('.backChat').show();
			$('.closeChat').css('right', '0px');
			$('.chat_sub').show();
			$('.chat_more').show();
			$('.chats').css('height', '410px');
			$('<div class="chat_welcome">'+$(this).attr("data-sender")+'님과 대화를 시작합니다.</div>').appendTo(".chats");
			distinction = true;
			var data = {
				member_nickname : $(this).attr("data-sender")
			}
			$.ajax({
				type : "post", //요청방식
				url : "getReceiver", //요청페이지
				data : data, //피라미터
				async : false,
				success : function(data){ //요청 페이지 처리에 서공 시
					if(data.size!=0) {
						receiver = data;
					}
				}
			});
			var data = {
					receiver : receiver,
					sender : $('#code').text()
				}
			$.ajax({
				type : "post", //요청방식
				url : "readDist", //요청페이지
				data : data, //피라미터
				async : false,
				success : function(data){ //요청 페이지 처리에 서공 시
					countDist();
				}				
			});
			sender = $('#code').text();
			countDist();
		});
		
		$(".enter").click(function(){ //책갈피10 : 이제 구현해야할 기능들
			addMessage();
			sendMessage();
			$('.writeComment').val('');
		}); 
		
		$(".requestChat").on("click", function() {
			if($('#code').text()==''){
				alert("로그인을 하지 않으셨습니다. 로그인을 해주세요.");
				window.location='loginForm';
			}else if($('#code').text() == $(this).attr("data-whoIs")) {
				alert("본인과의 1:1 대화를 사용하실 수 없습니다."); // 책갈피
			}
			else{
				$('.chats').empty();
				var id = $(this).attr("data-chatBtn");
				var id2 = $(this).parent().parent().parent().attr("data-issue");
				if(id==id2) {
					$('.chat_main').hide();
					$('.chat_sub').show();
					$('.chat_more').show();
					$('.backChat').show();
					$('.chatDiv').show('slow');
					$('.chats').css('height', '410px');
					$('<div class="chat_click" onclick="slideEffect(this)"><div class="chat_welcome">'+$(this).prev().text()+'님과 대화를 시작합니다.</div><div class="chat_welcome_more">'+$(this).prev().text()+'의 이슈 글<br>\''+$(this).parent().parent().next().next().text()+'\'<br>에서 대화를 신청하였습니다.</div></div>').appendTo(".chats");
					distinction = true;
					receiver = $(this).attr("data-whoIs");
					sender = $('#code').text();
				}
				setTimeout("$('.closeChat').css('right', '0px')", '1');
			}			
		});

		// 파일 업로드 기능
		
		$(".chatDiv").on("dragenter dragover", function(event) {
			event.preventDefault();
		});
		
		$(".chatDiv").on("drop", function(event){
			event.preventDefault();
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			
			// console.log(file);
			
			var formData = new FormData();
			formData.append("file", file);
			
			$.ajax({
				url:"uploadIntoChat",
				data: formData,
				dataType:"text",
				processData: false, //
				contentType: false,
				type: "POST",
				success: function(data) {
					// alert(data);
					if(checkImageType(data)) {
						//여기서 이제 메세지 발송 이벤트를 실행시켜야됨.

						imageStr = " <a href='displayChatImage?fileName="+getOriginalName(data)+"'> <img src='displayChatImage?fileName="+data+"'/></a>";
						addImageMessage(imageStr);
						sendImageMessage(imageStr);
					} 
				},
			});
		});
	});	
	
	function slideEffect(str) {
        $(str).children().next().slideToggle("slow");
    };
	
	function checkImageType(fileName) {
		var pattern = /jpg|gif|jpeg|png/i;
		return fileName.match(pattern);
	}
	
	function getOriginalName(name) {
		if(checkImageType(name) == false) {
			return;
		}
		var folderPath = name.substr(0, 12) // 파일 이름에서 /2017/05/24/ 추출
		var orgName = name.substring(12+"thumbNail_chatting_".length);
		return folderPath+orgName;
	}
	
	
	//WebSocket을 지정한 URL로 연결한다
	//서버랑 연결 -> 에코핸들러가서 출력
	var sock = new SockJS("/somebodyplace/echo-ws");
	//WebSocket 서버에서 메시지를 보내면 자동으로 실행된다
	//var onMessage = function onMessage //변수
	sock.onmessage = onMessage;
	
	//WebSocket과 연결을 끊고 싶을 때 실행하는 메소드다
	sock.onclose = onClose;
	
	/*     sock.onopen = function() {
	    sock.send( $("#message").val() +"<br/>");
	}; */
	
	function sendMessage() {
		var d = new Date();
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		var date =  d.getDate();
		var hours = d.getHours();
		var minutes = d.getMinutes();
		var string = year+'-'+month+'-'+date+' ('+hours+':'+minutes+')';
		//ajax로 수신자를 다시 읽어
		
		
	    //WebSocket으로 메시지를 전달한다.
	    if(receiver == null) {
	    	receiver = sender;
	    }

	    var json = "{"+"\"sender\":"+sender+", \"receiver\":"+receiver+", \"sender_name\":\""+$('#nickname').text()+"\", \"content\":\""+$(".writeComment").val()+"\", \"time\":\""+string+"\"}";
	    // sock.send("<div class='chat mine'> "+$(".writeComment").val()+"<h6>작성자 : "+$('#chat_name').text()+" <br>작성 시간 : "+string+"</h6> </div>"); 
		sock.send(json);
	}
	
	/**
	 * @returns
	 */
	function addMessage() {
		var query = {
			sender : sender,
			receiver : receiver,
			async:false,
			content : $("#writeComment").val()
		};
		
		$.ajax({
			type : "post",
			url : "addChat",
			data : query,
			success : function(data){
				
			},
		    complete : function() {
		    }
		});
	}
	
	// 이미지 디비 등록 메소드
	function addImageMessage(data) {
		var query = {
			sender : sender,
			receiver : receiver,
			content : data
		};
		
		$.ajax({
			type : "post",
			url : "addChat",
			data : query,
			success : function(data){
				
			},
		    complete : function() {
		    }
		});
	}
	
	// 이미지 상대방으로 전송 메소드
	
	function sendImageMessage (imageStr) {
		var d = new Date();
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		var date =  d.getDate();
		var hours = d.getHours();
		var minutes = d.getMinutes();
		var string = year+'-'+month+'-'+date+' ('+hours+':'+minutes+')';
		//ajax로 수신자를 다시 읽어
		
		
	    //WebSocket으로 메시지를 전달한다.
	    if(receiver == null) {
	    	receiver = sender;
	    }
	    var json = "{"+"\"sender\":"+sender+", \"receiver\":"+receiver+", \"sender_name\":\""+$('#nickname').text()+"\", \"content\":\""+imageStr+"\", \"time\":\""+string+"\"}";
	    // sock.send("<div class='chat mine'> "+$(".writeComment").val()+"<h6>작성자 : "+$('#chat_name').text()+" <br>작성 시간 : "+string+"</h6> </div>"); 
		sock.send(json);
	}
	
	// evt 파라미터는 WebSocket이 보내준 데이터다
	// 변수안에 function을 넣음. 변수 생략 가능
	function onMessage(evt) 
	{
		var data = evt.data;

	   	var str=JSON.parse(data);
		   	
	   	var data = {
    			receiver : receiver
    	}
		$.ajax({
			type : "post",
			url : "addDist",
			data : data,
			success : function(data){
				countDist();
			}
		}); // 읽지 않은 메세지를 추가해주는 메소드
	   	
	   	
	   	
	   
	   	if(str.content!="delChat") {
		   	var query = {
				auto_title:str.content,
				auto_code : sender
			}
			$.ajax({
				type : "post",
				url : "readAuto",
				data : query,
				async:false,
				success : function(data){
					if((data.length != 0)&& (distinction == true) && (sender == str.receiver)) {
						var result = "<div class='chat system'>"+data[0].member_nickname+"님의 자동 답변 :<br>"+data[0].auto_content+"<h6>작성자 : 시스템 <br>작성 시간 : "+str.time+"</h6> </div>";
						$(result).appendTo(".chats");
					} else {}
				}
			});
	   	}
	   	
	   	
	   	if((str.content=='도움말')&& (distinction == true) && (sender == str.receiver)) {
	   		var result = "<div class='chat system'>사용할 수 있는 커멘드는 다음과 같습니다.<ul><li>도움말 - 사용할 수 있는 커멘드 기능들을 불러옵니다.</li><li>주문 - 상대방의 물품을 구매할 수 있습니다.<br><h6>예시 : 주문 청바지 등</li><li>상대상품 - 상대방의 플레이스에 등록된 상품들을 불러옵니다.</li><li>내상품 - 내 플레이스에 등록된 상품들을 불러옵니다.</li><li>키워드 - 상대방이 입력한 키워드를 알 수 있습니다.</ul><h6>작성자 : 시스템 <br>작성 시간 : "+str.time+"</h6> </div>";
			$(result).appendTo(".chats");
	   	} // 책갈피2
	   	if((str.content=='주문')&& (distinction == true) && (sender == str.receiver)) {
		   	var result = "<div class='chat system'>구매할 상대방의 물품을 입력하세요.<br><h6>예시 : 주문 청바지 등<br><br>작성자 : 시스템 <br>작성 시간 : "+str.time+"</h6></div>";
			$(result).appendTo(".chats");
	   	}	   	
	   	if((str.content=='내상품')&& (distinction == true) && (sender == str.receiver)) {
	   		var result = "<div class='chat system'>자신의 상품 목록을 조회하였습니다!<h6>작성자 : 시스템 <br>작성 시간 : "+str.time+"</h6> </div>";
			$(result).appendTo(".chats");
	   	}
	   	if((str.content=='상대상품')&& (distinction == true) && (sender == str.receiver)) {
	   		var result = "<div class='chat system'>상대방의 상품 목록을 조회하였습니다!<h6>작성자 : 시스템 <br>작성 시간 : "+str.time+"</h6> </div>";
			$(result).appendTo(".chats");
	   	}
	   	if((str.content=='키워드')&& (distinction == true) && (sender == str.receiver)) {
			var query = {
					auto_code:$("#code").text()
			}
			$.ajax({
				type : "get",
				url : "readAutoList",
				data : query,
				async:false,
				success : function(data){
					if(data.length==0) {
						autoList +="";
					} else {
						for(var i=0; i<data.length;i++) {
							autoList += "<li>"+data[i].auto_title+"</li>";
						}						
					}
				}
			});
	   		
	   		
	   		var result ="";
	   		if(autoList.length==0) {
				result = "<div class='chat system'>상대방이 키워드를 입력하지 않았습니다!<h6>작성자 : 시스템 <br>작성 시간 : "+str.time+"</h6> </div>";
	   		} else {
				result = "<div class='chat system'>상대방이 등록한 키워드는 다음과 같습니다.<ul>"+autoList+"</ul><h6>작성자 : 시스템 <br>작성 시간 : "+str.time+"</h6> </div>";
			}
	   		$(result).appendTo(".chats");
	   		autoList='';
	   	}
	   	
	   	
	   	//대화방 삭제 코드
	   	
	   	if((str.content == 'delChat') && (distinction == false) && (str.receiver == receiver)) {
	   		if($('.chatDiv').css('display')=='none') {
				countDist();
	    	} else if($('.chatDiv').css('display')=='block') {
	    		$('.chat_rooms').empty();
	    		countDist();
	    		var data = {
	    				chat_receiver : $('#code').text()
	    		}
    			$.ajax({
    				type : "post",
    				url : "getRooms",
    				data : data,
    				async : false,
    				success : function(data){
    					for(var i=0; i<data.length;i++) {
    						
    						var this_sender = data[i].chat_sender;
    						var this_time = data[i].chat_time;
    						var this_content = data[i].chat_content;
    						if(this_content.length >15) {
    							this_content = this_content.substring(0,15)+"...";
    						}
    						
    						if(this_content.indexOf("<a href=\'displ") != -1) {
    							this_content = "이미지가 도착하였습니다.";
    						}
    						
    						$.ajax({
    							type: "post",
    							url : "countChatsInRoom",
    							data : {
    								receiver : $('#code').text(),
    								sender : data[i].sender
    							},
    							async:false,
    							success : function(data) {
    								if(data == 0) {
    									var result = "<div class='chat_room' data-sender='"+this_sender+"'><div class='chat_picture'><img src='./resources/img/clerk2.png'> </div><div class='chat_profile'> "+
    									this_sender+"&nbsp;"+this_time+"<div>"+
    									this_content+"</div></div><hr></div>";
    									$(result).appendTo(".chat_rooms");
    								} else {		
    									var result = "<div class='chat_room' data-sender='"+this_sender+"'><div class='chat_picture'><img src='./resources/img/clerk2.png'><span class='badge chat_mini_balloons'>"+
    									data+"</span> </div><div class='chat_profile'> "+
    									this_sender+"&nbsp;"+this_time+"<div>"+
    									this_content+"</div></div><hr></div>";
    									$(result).appendTo(".chat_rooms");
    								}
    							}
    						});
    					}
    				}
    			});
	    	}	
	   	} else if((str.content == 'delChat') && (distinction == true) && (sender == str.receiver)) {
	   		$.ajax({
				type : "post", //요청방식
				url : "getReceiverName", //요청페이지
				data : {receiver : str.receiver}, //피라미터
				async : false,
				success : function(data){ //요청 페이지 처리에 서공 시
					if(data.size!=0) {
						$('<div class="chat_welcome">'+data+'님이 대화방을 나갔습니다.</div>').appendTo(".chats");
					}
				}
			});
	   		
	    } else {} 
	   	
	   	if (str.content == 'delChat') {
	   		return false; // 반복되지 않기 위한 if문
	   	}
   	
	    if(str.sender == $('#code').text()) {
	    	$(".chats").append("<div class='chat mine'> "+str.content+"<h6>작성자 : "+str.sender_name+" <br>작성 시간 : "+str.time+"</h6> </div>");	
	    } else if((distinction == false) && (str.receiver == receiver)) {
	    	if($('.chatDiv').css('display')=='none') {
	    		alert("메세지가 도착하였습니다.");
				countDist();
	    	}
	    	else if($('.chatDiv').css('display')=='block') {
	    		$('.chat_rooms').empty();
	    		countDist();
	    		var data = {
	    				chat_receiver : $('#code').text()
	    		}
    			$.ajax({
    				type : "post",
    				url : "getRooms",
    				data : data,
    				async : false,
    				success : function(data){
    					for(var i=0; i<data.length;i++) {
    						
    						var this_sender = data[i].chat_sender;
    						var this_time = data[i].chat_time;
    						var this_content = data[i].chat_content;
    						if(this_content.length >15) {
    							this_content = this_content.substring(0,15)+"...";
    						}
    						
    						if(this_content.indexOf("<a href=\'displ") != -1) {
    							this_content = "이미지가 도착하였습니다.";
    						}
    						
    						$.ajax({
    							type: "post",
    							url : "countChatsInRoom",
    							data : {
    								receiver : $('#code').text(),
    								sender : data[i].sender
    							},
    							async:false,
    							success : function(data) {
    								if(data == 0) {
    									var result = "<div class='chat_room' data-sender='"+this_sender+"'><div class='chat_picture'><img src='./resources/img/clerk2.png'> </div><div class='chat_profile'> "+
    									this_sender+"&nbsp;"+this_time+"<div>"+
    									this_content+"</div></div><hr></div>";
    									$(result).appendTo(".chat_rooms");
    								} else {		
    									var result = "<div class='chat_room' data-sender='"+this_sender+"'><div class='chat_picture'><img src='./resources/img/clerk2.png'><span class='badge chat_mini_balloons'>"+
    									data+"</span> </div><div class='chat_profile'> "+
    									this_sender+"&nbsp;"+this_time+"<div>"+
    									this_content+"</div></div><hr></div>";
    									$(result).appendTo(".chat_rooms");
    								}
    							}
    						});
    					}
    				}
    			});
	    	}
	    	
	    } else if((distinction == true) && (sender == str.receiver)) {
	    	var data = {
	    			receiver : receiver,
	    			sender : $('#code').text()
	    	}
	    	$.ajax({
	    		type : "post",
	    		url : "readDist",
	    		data : data,
	    		async : false,
	    		success : function(data){
	    			countDist();
	    		}
	    	});
	    	$(".chats").append("<div class='chat yours'> "+str.content+"<h6>작성자 : "+str.sender_name+" <br>작성 시간 : "+str.time+"</h6> </div>");
	    } else {}
	    setTimeout('scrollDown()',1);
	    //sock.close();
	}
	// var onClose 변수 생략
	function onClose(evt) {
	    $("#data").append("Connection Closed!");
	}
	
	function scrollDown() {
		$(".chatWrap").scrollTop($(".chats>div").last().index()*$(".chatWrap").height()*2);
	}
	
	function countDist() {
		var query = {
			receiver : $('#code').text()
		};
		$.ajax({
			type : "post",
			url : "countDist",
			data : query,
			async : false,
			success : function(data){
				if(data==0) {
					$('.chat_balloons').text('');
					$('.chat_balloons').hide();
				} else {
					$('.chat_balloons').text(data);
					$('.chat_balloons').show();
				}
			},
		    complete : function() {
		    }			
		});
	}
	
	function readAutoList() {
		var query = {
			auto_code:sender
		}
		$.ajax({
			type : "get",
			url : "readAutoList",
			data : query,
			async: false,
			success : function(data){
				var d = "\"\"";
				for(var i=0;i<data.length;i++) {
					autoResult +="<li>"+data[i].auto_title+"<br><span id='deleteAuto' class='glyphicon glyphicon-remove' style='cursor:pointer'></span><input type='text' style='width:230px;' placeholder='자동으로 완성될 말을  해주세요.' onkeypress='enter3(event, this.value, \""+data[i].auto_title+"\", this)' value='"+data[i].auto_content+"'></input><input type='button' value='입력' style='float:right'></input></li>";
				}
			}
		});
	}
	
	function initialize() {
		directionsDisplay = new google.maps.DirectionsRenderer({
			suppressMarkers : true
		});
		var chicago = new google.maps.LatLng(41.850033, -87.6500523);
		var mapOptions = {
			zoom : 7,
			mapTypeId : google.maps.MapTypeId.ROADMAP,
			center : chicago
		}
		map = new google.maps.Map(document.getElementById('mapAboutRoute'), mapOptions);
		directionsDisplay.setMap(map);
	}
	
	if(isMapLoading == false) {
		google.maps.event.addDomListener(window, 'load', initialize);
		isMapLoading = true;
	}
	