/**
 * 
 */

$(document).ready(function() {
	// 등록한 해시태그 클릭시 삭제
	
	var myInterest = $('.member_interest').val().split("#");
	
	for(var i=1;i<myInterest.length;i++) {
		var hashtxt = '<div class="inputHash" data-hash="'+myInterest[i]+'">&nbsp;<span>#' +myInterest[i]+ '</span>'+
        '<p class="arrow_box">클릭하면 삭제됩니다.</p></div>';
		$('.interest_hashBox').append(hashtxt);
	}
	
	
	$(document).on("click", ".inputHash", function(){
	    var index = $(this).index();
	    $('.interest_hashBox').find("div").eq(index).remove();
	});
	// 해시태그 입력
	$('#interest_insertHash').keypress(function(e){
	    if(e.keyCode == 32 || e.keyCode == 13) {
			if($('.interest_hashBox').text() == '아무런 관심사를 입력하지 않았어요!') {
				$('.interest_hashBox').empty();
			}
	        var hashtxt = '<div class="inputHash" data-hash="'+$("#interest_insertHash").val()+'">&nbsp;<span>#' + $('#interest_insertHash').val() + '</span>'+
	                        '<p class="arrow_box">클릭하면 삭제됩니다.</p></div>';
	        $('.interest_hashBox').append(hashtxt);
	        $('#interest_insertHash').val("");
	    }
	});
	
	$(".SettingFinish_btn").click(function(){
		
		if($('.interest_hashBox').text() == '아무런 관심사를 입력하지 않았어요!') {
			$('.interest_hashBox').empty();
		}
		
		var a = $(".inputHash");
		var c = '';
		for(var b=0;b<a.length;b++) {
			c += "#"+a.eq(b).data("hash");
		}
		$(".member_interest").val(c);
		$("form").attr("action", "updateinterest");
		$("form").attr("method", "post");
		$("form").submit();
		
	});

});

