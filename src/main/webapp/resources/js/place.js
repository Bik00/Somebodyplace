$(function(){
	// 플레이스 글쓰기 버튼
	$(".postForm_btn").click(function(){
		location.href = "postForm";
	});
	
	$(".placeManager_btn").click(function(){
		var myNum = $("#code").text();
		
		var form = $('<form></form>');
		form.attr('action', 'place');
		form.attr('method', 'get');
		form.appendTo('body');
		
		var a = $('<input type="hidden" value="'+myNum+'" name="member_code">');
		form.append(a);
		form.submit();
	});
});