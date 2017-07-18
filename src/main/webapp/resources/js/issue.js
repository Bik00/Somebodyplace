var receiver = '';
var sender = '';

$(document).ready(function(){
	$(".reply_list").hide();

	$(".issue_setting").click(function(){ 
	
		$('.settingDiv').css('left', event.pageX);
		$('.settingDiv').css('top', event.pageY);
		$(".settingDiv").show("slow");		
		
		
		$('.issue_code').val($(this).parent().parent().next().val());
    });

	$(document).mouseup(function (e) {
		// 팝업 아이디
		var container = $(".settingDiv");
		if (!container.is(e.target) && container.has(e.target).length === 0){
			container.hide("slow");
		}	
	});

	$(".replies").on("click", function() {
		var id = $(this).parent().attr("data-replyBtn");
		var id2 = $(this).parent().next().attr("data-replyList");

		if(id == id2) {
			if($(this).text() == "댓글 보기") {
				$(this).parent().next().show("slow");
				$(this).text("댓글 닫기");
			}
			else if($(this).text() == "댓글 닫기") {
				$(this).parent().next().hide("slow");
				$(this).text("댓글 보기");
			}				
		}
	});
});


$(function(){

	
	
	/*이슈검색 버튼 */
	$(".issue_searchbtn").click(function(){
	

		$("form").attr("action", "searchIssue");
		$("form").attr("method", "post");
	      $("form").submit();
	});
	

	/*이슈쓰기 버튼 */
	$(".issue_addIssuebtn").click(function(){
		location.href="addIssue";
	});
	
	
	
	/*이슈수정 버튼 */
	$(".issue_modifybtn").click(function(){
		
	
		location.href="addIssue";
			
	});
	
	
	/*이슈삭제 버튼 */
	$(".issue_deletebtn").click(function(){
		
		//alert($(this).parent().find("input").val());
		//alert($(this).parent().prev("input").val());
	
		var issue_code=$(this).parent().find("input").val();
		location.href="deleteIssue?issue_code="+issue_code;
			
	});
	
	
	/* 1:1 대화 버튼  (앱 기능) */
	
	$(".requestChat").on("click", function() {
		
		if($(".requestbyapp").text().length!=0) {
			var form = $('<form></form>');
			form.attr('action', 'http://106.249.38.79:8080/somebodyplace/chat');
			form.attr('method', 'get');
			form.appendTo('body');
			
			var data = {
					member_nickname : $("#issue_receiverName").text()
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
			
			var a = $("<input type='hidden' value='"+$(".requestbyapp").text()+"' name='requestbyapp'>");
			var b = $("<input type='hidden' value='"+ $(".sender").text()+"' name='sender'>");
			var c = $("<input type='hidden' value='"+receiver+"' name='receiver'>");
			form.append(a).append(b).append(c);
			form.submit();			
		}
	});
	
});