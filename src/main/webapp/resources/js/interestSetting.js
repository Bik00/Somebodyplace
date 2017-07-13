/**
 * 
 */

$(document).ready(function() {
	// 등록한 해시태그 클릭시 삭제
	$(document).on("click", ".inputHash", function(){
	    var index = $(this).index();
	    $('.interest_hashBox').find("div").eq(index).remove();
	});
	// 해시태그 입력
	$('#interest_insertHash').keypress(function(e){
	    if(e.keyCode == 32 || e.keyCode == 13) {
	        var hashtxt = '<div class="inputHash">&nbsp;<span>#' + $('#interest_insertHash').val() + '</span>'+
	                        '<p class="arrow_box">클릭하면 삭제됩니다.</p></div>';
	        $('.interest_hashBox').append(hashtxt);
	        $('#interest_insertHash').val("");
	    }
	});
	
	$(".SettingFinish_btn").click(function(){
		alert("s");
	     var s = $(".post_hashBox").contents().find('span');
	        $("input[name=member_interest]").val(s.text());
	        
		 $("form").attr("action", "updateinterest");
	      $("form").attr("method", "post");
	      $("form").submit();
		
	});

});

