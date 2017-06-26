<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="./resources/js/sockjs.js"/>" ></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="./resources/js/clock.js"></script>
<script type="text/javascript">

/* 채팅에 필요한 변수들 */
var receiver;
var sender;
var distinction = false; // 방 입장 상태 를 구별하는 변수
var flippings = false;
var autoSize = 1;
var isWriteAuto = false;
var title = "";

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
					
		if(keyword=="$키워드") {
			if(autoList.length==0) {
				var result = "<div class='chat system'>상대방이 키워드를 입력하지 않았습니다!<h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
			} else {
				var result = "<div class='chat system'>상대방이 등록한 키워드는 다음과 같습니다.<ul>"+autoList+"</ul><h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
			}
			$(result).appendTo(".chats");
		}
		
		if(keyword=="$도움말") {
			var result = "<div class='chat system'>사용할 수 있는 커멘드는 다음과 같습니다.<ul><li>$도움말 - 사용할 수 있는 커멘드 기능들을 불러옵니다.</li><li>$메뉴판 - 상대방의 플레이스에 등록된 음식 목록들을 불러옵니다.</li><li>$상대상품 - 상대방의 플레이스에 등록된 상품들을 불러옵니다.</li><li>$내상품 - 내 플레이스에 등록된 상품들을 불러옵니다.</li><li>$키워드 - 상대방이 입력한 키워드를 알 수 있습니다.</ul><h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
			$(result).appendTo(".chats");
		}
		if(keyword=="$내상품") {
			var result ="<div class='chat system'>현재 플레이스를 생성하지 않으셨습니다!<h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
			$(result).appendTo(".chats");
		}
		if(keyword=="$상대상품") {//책갈피2
			
			switch(category) {
				
			case 1:
				alert("상대방 플레이스의 카테고리는 '음식' 입니다.")
				break;
			case 2:
				alert("상대방 플레이스의 카테고리는 '꽃' 입니다.")	
				break;
			case 3:
				alert("상대방 플레이스의 카테고리는 '세탁' 입니다.")	
				break;
			case 4:
				alert("상대방 플레이스의 카테고리는 '패션' 입니다.")	
				break;
			case 5:
				alert("상대방 플레이스의 카테고리는 '생활' 입니다.")	
				break;
			case 6:
				alert("상대방 플레이스의 카테고리는 '디지털' 입니다.")	
				break;
			case 7:
				alert("상대방 플레이스의 카테고리는 '공연' 입니다.")	
				break;
			case 8:
				alert("상대방 플레이스의 카테고리는 '숙박' 입니다.")	
				break;
			case 9:
				alert("상대방 플레이스의 카테고리는 '미용실' 입니다.")	
				break;
			case 10:
				alert("상대방 플레이스의 카테고리는 '네일' 입니다.")	
				break;
			case 11:
				alert("상대방 플레이스의 카테고리는 '화장품' 입니다.")	
				break;
			default :
				var result ="<div class='chat system'>현재 상대방의 플레이스에 등록된 상품은 다음과 같습니다.<ul><li>면 티셔츠 --- 7500 원</li><li>청바지 --- 20000 원</li></ul><h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
				$(result).appendTo(".chats");
				break;
			}

		}
		if(keyword=="$메뉴판") {
			var result = "<div class='chat system'>상대방의 플레이스에 등록된 메뉴(음식)는 다음과 같습니다.<ul><li>자장면 --- 4500 원</li><li>짬뽕 --- 5000 원</li></ul><h6>작성자 : 시스템 <br>작성 시간 : "+string+"</h6> </div>";
			$(result).appendTo(".chats");
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
	var checkRequestByApp = "${requestbyapp}";
	if(checkRequestByApp.length != 0) {
		

		$('.chats').empty();
					
			$('.chat_main').hide();
			$('.chat_sub').show();
			$('.chat_more').show();
			$('.backChat').show();
			$('.chatDiv').show('slow');
			$('.chats').css('height', $(window).height()-93);
			$('.chat_more').css('top', $(window).height()-87);
			$('<div class="chat_click" onclick="slideEffect(this)"><div class="chat_welcome">'+$(this).prev().text()+'님과 대화를 시작합니다.</div><div class="chat_welcome_more">'+$(this).prev().text()+'의 이슈 글<br>\''+$(this).parent().parent().next().next().text()+'\'<br>에서 대화를 신청하였습니다.</div></div>').appendTo(".chats");
			distinction = true;
			
			var data = {
				member_nickname : $(this).prev().text()
			}
			$.ajax({
				type : "post", //요청방식
				url : "getReceiver", //요청페이지
				data : data, //피라미터
				success : function(data){ //요청 페이지 처리에 서공 시
					if(data.size!=0) {
						receiver = data;
					}
				}
			});
			sender = $('#code').text();

		setTimeout("$('.closeChat').css('right', '0px')", '1');
	}

	
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

			$('.backChat').click(function() {
				$('.chat_sub').hide();
				$('.chats').empty();
				$('.chat_rooms').empty();
				$('.chat_main').show();
				$('.backChat').hide();
				$('.chat_more').hide();
				$('.chat_flip').hide();
				$('.closeChat').css('right', '');
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
			
			
	
	$(window).resize(function(){
		$('.chats').css('height', $(window).height()-93);
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
			    	if(data[i].chat_content == "$도움말") { // 책갈피1 : 키워드를 입력했을 때 상대가 대화방에 입장하면 로드
			    		$(".chats").append("<div class='chat system'>사용할 수 있는 커멘드는 다음과 같습니다.<ul><li>$도움말 - 사용할 수 있는 커멘드 기능들을 불러옵니다.</li><li>$메뉴판 - 상대방의 플레이스에 등록된 음식 목록들을 불러옵니다.</li><li>$상대상품 - 상대방의 플레이스에 등록된 상품들을 불러옵니다.</li><li>$내상품 - 내 플레이스에 등록된 상품들을 불러옵니다.</li><li>$키워드 - 상대방이 입력한 키워드를 알 수 있습니다.</ul><h6>작성자 : 시스템 <br>작성 시간 : "+data[i].chat_time+"</h6> </div>");
			    	} else if(data[i].chat_content == "$내상품") {
			    		$(".chats").append("<div class='chat system'>현재 플레이스를 생성하지 않으셨습니다!<h6>작성자 : 시스템 <br>작성 시간 : "+data[i].chat_time+"</h6> </div>");
			    	} else if(data[i].chat_content == "$상대상품") {
			    		$(".chats").append("<div class='chat system'>현재 상대방의 플레이스에 등록된 상품은 다음과 같습니다.<ul><li>면 티셔츠 --- 7500 원</li><li>청바지 --- 20000 원</li></ul><h6>작성자 : 시스템 <br>작성 시간 : "+data[i].chat_time+"</h6> </div>");
			    	} else if(data[i].chat_content == "$키워드") {
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
		$('.chats').css('height', $(window).height()-93);
		$('.chat_more').css('top', $(window).height()-87);
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
	
	
	$(".enter").click(function(){
		addMessage();
		sendMessage();
		$('.writeComment').val('');
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
   	
   	
   	if((str.content=='$도움말')&& (distinction == true) && (sender == str.receiver)) {
   		var result = "<div class='chat system'>사용할 수 있는 커멘드는 다음과 같습니다.<ul><li>$도움말 - 사용할 수 있는 커멘드 기능들을 불러옵니다.</li><li>$메뉴판 - 상대방의 플레이스에 등록된 음식 목록들을 불러옵니다.</li><li>$상대상품 - 상대방의 플레이스에 등록된 상품들을 불러옵니다.</li><li>$내상품 - 내 플레이스에 등록된 상품들을 불러옵니다.</li><li>$키워드 - 상대방이 입력한 키워드를 알 수 있습니다.</ul><h6>작성자 : 시스템 <br>작성 시간 : "+str.time+"</h6> </div>";
		$(result).appendTo(".chats");
   	}
   	if((str.content=='$내상품')&& (distinction == true) && (sender == str.receiver)) {
   		var result = "<div class='chat system'>현재 플레이스를 생성하지 않으셨습니다!<h6>작성자 : 시스템 <br>작성 시간 : "+str.time+"</h6> </div>";
		$(result).appendTo(".chats");
   	}
   	if((str.content=='$상대상품')&& (distinction == true) && (sender == str.receiver)) {
   		var result = "<div class='chat system'>현재 상대방의 플레이스에 등록된 상품은 다음과 같습니다.<ul><li>면 티셔츠 --- 7500 원</li><li>청바지 --- 20000 원</li></ul><h6>작성자 : 시스템 <br>작성 시간 : "+str.time+"</h6> </div>";
		$(result).appendTo(".chats");
   	}
   	if((str.content=='$키워드')&& (distinction == true) && (sender == str.receiver)) {
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

</script>
<style>
.chatImg>img {
	position:fixed;
	right: 0px;
	bottom: 0px;
	z-index: 1001;
	width:50px;
	height:50px;
	margin: 0;
}

.chat_info {
	display:none;
}

.chatDiv {
    background-color: #da405c;
    position : fixed;
    right : 0px;
    bottom: 0px;
    z-index : 1002
    border: 1px solid #da405c; 
    width: 100%;
    height: 100%;
    margin: 0;
}

.chat_balloons {
	background-color:red;
	z-index:1002;
	position : fixed;
	right: 40px;
	bottom: 35px;
}

.chat_mini_balloons {
	position:relative;
	background-color:red;
	top:-15px;
	left:55px
}

.chat_sub {
  	display:none; 
}

.chat_content {
	height:100%;
}
.chat_owner {
	width:100%;
	height:auto;
	background-color:#da405c;
	color:white;
}
.chat_rooms {
	width:100%;
    height:auto;
    background-color:#FFD0D0;
	overflow-x:hidden;
    height:440px;
    margin:auto;
}
.chat_room {
    width:100%;
    height:80px;
    background-color:white;
}

.chat_profile {
    font-size: 14px;
    line-height:1.5em;
    float:left;
    margin-top:20px;
    overflow:hidden;
}
.chat_picture>img {
    width:100%;
    border-radius:50%;
}
 
.chat_picture {
    float:left;
    width:80px;
    margin-left:30px;
    margin-right:15px;
}

.chatWrap {
	background-color: #da405c; 
	width:100%;
	overflow-x:hidden;
}
.chats {
    width:80%;
    height:460px;
    margin:auto;
}

.mine {
    position:relative;
    background-color:lightyellow;
    border:1px solid lightyellow;
    border-radius : 5px;
    margin-top:8px;
    margin-bottom:8px;
}
.mine:after  {
    content: '';
    position: absolute;
    border-width: 10px;
    border-style: solid;
    border-color: transparent transparent transparent lightyellow;
    right: -20px;
    top:30px;
}
.yours {
    position:relative;
    background-color:#fff;
    border:1px solid #fff;
    border-radius : 5px;
    margin-top:8px;
    margin-bottom:8px;
}
.yours:after  {
    content: '';
    position: absolute;
    border-width: 10px;
    border-style: solid;
    border-color: transparent #fff transparent transparent;
    left: -20px;
    top:30px;
}

.chat {
    z-index:1002;
    padding : 8px;
}

.chat > h4 { 
    margin-bottom:4px;
}

.chat > h6 {
    margin-bottom: 12px;
}
.chat > p {
    font-size:14px;
}
.comment {
	position:absolute;
    width:100%;
    height:50px;
    z-index:1003;
    background-color: #da405c;
}

.writeComment {
    width:80%;
    height:50px;
    z-index:1004;
    float:left;
}

.enter {
    width:15%;
    height:50px;
    float:right;
}

.backChat {
	position:absolute;
	z-index:1004;
	display:none;
}
.chat_info {
	display:hidden;
}
.chat_more {
	z-index:1004;
	position:absolute;
	top:445px;
	display:none;
}
.chat_welcome {
	margin-top:30px;
	background-color: #AB3349;
	width:100%;
	z-index:1002;
	text-align:center;
}

.chat_welcome_more {
	margin-bottom : 20px;
	background-color: #AB3349;
	width:100%;
	z-index:1002;
	text-align:center;
	display:none;
}

/* 세부 기능 구현 */

.chat_flip {
	-webkit-perspective: 10000;
	width:100%;
	background-color:white;
	height:100px;
	bottom:60px;
	display:none;
	z-index:1003;
	position:fixed;
	margin:auto;
}
.chat_flipped  {
	-webkit-transform: rotateY(180deg);
}
.chat_card  {
	width: 100%;
	height: 100%;
	-webkit-transform-style: preserve-3d;
	-webkit-transition: 1.5s;
}

.system {
    position:relative;
    background-color:lightblue;
    border:1px solid lightblue;
    border-radius : 5px;
    margin-top:8px;
    margin-bottom:8px;
}

.chat_menu  {
	width: 100%;
	height: 100%;
	position: absolute;
	-webkit-backface-visibility: hidden;
	z-index: 1005;
	overflow-x:hidden;
}

#chat_auto_result >ul {
	width:350px;
	word-break: break-all;
}
#chat_auto_result >ul>li {
	margin:10px;
}


.front {
	position: absolute;
	z-index: 1004;
	background: white;
}
.back {
  -webkit-transform: rotateY(180deg);
  background-color: white;
}

.chat_menu_img {
	width:40px;
	height:40px;
	margin-left:80px;
	margin-top : 15px;
}

/* .chatDiv {
    background-color: #da405c;
    position : fixed;
    right : 0px;
    bottom: 0px;
    z-index : 1001;
    border: 1px solid #da405c; 
    width: 100%;
    height: 100%;

}
.chatWrap {
       background-color: #da405c; 
       width:100%;
       height:90%;
       overflow-x:hidden;
}
.chats {
    width:80%;
    height:98.5%;
    margin:auto;
}

.mine {
    position:relative;
    background-color:lightyellow;
    border:1px solid lightyellow;
    border-radius : 5px;
    margin-top:30px;
    margin-bottom:30px;
}
.mine:after  {
    content: '';
    position: absolute;
    border-width: 20px;
    border-style: solid;
    border-color: transparent transparent transparent lightyellow;
    right: -40px;
    top:50px;
}
.yours {
    position:relative;
    background-color:#fff;
    border:1px solid #fff;
    border-radius : 5px;
    margin-top:30px;
    margin-bottom:30px;
}
.yours:after  {
   content: '';
    position: absolute;
    border-width: 20px;
    border-style: solid;
    border-color: transparent #fff transparent transparent;
    left: -40px;
    top:50px;
}

.chat {
    z-index:1002;
    padding : 8px;
}

.chat > h4 { 
    margin-bottom:4px;
}

.chat > h6 {
    margin-bottom: 12px;
}
.chat > p {
    font-size:14px;
}
.comment {
    width:100%;
    height:50px;
    z-index:1003;
    background-color: #da405c;
    position:absolute;
}

.writeComment {
    width:83%;
    height:50px;
    z-index:1004;
    float:left;
}

.enter {
    width:15%;
    height:50px;
    float:right;
} */
</style>
</head>
<body>
	<div id="code" class="chat_info">${code}</div>
	<div id="nickname" class="chat_info">${nickname}</div>
	<div id="sender"></div>
		
 	<div class="chatDiv">
    	<span class="backChat glyphicon glyphicon-chevron-left"></span>
		<div class="chat_content">
		
 			<div class="chat_main">
		    	<div class="chat_owner">
		    		<h3>${nickname} 님의 대화방 목록 입니다.</h3>
		    	</div>
		    	<div class="chat_rooms">	    	
		    	</div>
	    	</div>
	    	
	    	<div class="chat_sub">
		    	<div class="chatWrap">	
					<div class="chats" id="chats">
					</div>
				</div>
				<span class="chat_more glyphicon glyphicon-plus-sign"></span>
					<div class="chat_flip">	
						<div class="chat_card">
							<div class="chat_menu front">
								<img src="./resources/img/chat_addAuto.png" class="chat_menu_img" id="addAuto" style="cursor: pointer;">
								<img src="./resources/img/chat_exitChat.png" class="chat_menu_img" id="exitChat" style="cursor: pointer;">
								<p style="text-align: center; width:400px">에약어 추가&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;대화방 나가기</p>
							</div>
							<div class="chat_menu back">
							</div>
						</div>
					</div>		
				<div class="comment">
					<input type="text" class="writeComment" id="writeComment" name="writeComment" placeholder="채팅 입력하세요" onkeypress="enter(event, this.value)"/>
					<button class="enter">입력</button>
				</div>
			</div> 
		</div>
	</div>   
</body>
</html>