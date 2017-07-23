var budget_month = new Array();
var reloadNow = false;
$(document).ready(function() {

	if(document.getElementsByClassName("currentBudget_addTotal").length != 0) {
		var x = document.getElementsByClassName("currentBudget_addTotal");
		
		var y = 0;
		
		for(var z = 0;z<x.length;z++) {
			var k = x[z].innerHTML;
			var w = k.indexOf("원");
			var e = k.substring(0, w-1);
			y +=Number(e);
			var v = comma(y);
		}
		$('.budget_amount').append("<h2><b>총 가능 정산 금액 : "+v+" 원</b></h2>");
	} else {
		$('.budget_amount').append("<h2><b>총 가능 정산 금액 : 0 원</b></h2>");
	}
	
	$(".budget_impossible").click(function() {
		$(".modal-body").append('<p>이미 정산 완료된 기간입니다.</p>');
		$(".confirmCurrentBudgetModal").remove();
		$("#dd").text("확인");
		reloadNow == true;
		$("#currentBudgetModal").modal('toggle');
	});
	$(".budget_possible").click(function() {
		budget_month[0] = $(this).next().val();
		$(".modal-body").append('<p>정산하시겠습니까?</p>');
		$("#currentBudgetModal").modal('toggle');
	});
	
	$(".confirmCurrentBudgetModal").on("click", function() {		
		
		var query = {
			budget_month : budget_month[0]
		}
		$.ajax({
			type : "post",
			url : "calculateBudget",
			data : query,
			async: false,
			success : function(data){
				$(".modal-body").fadeOut(400);
				setTimeout(function(){
					$(".modal-body").empty();
					$(".modal-body").append('<p>정산 완료되었습니다.</p>').fadeIn();
					$(".confirmCurrentBudgetModal").remove();
					$("#dd").text("확인");
					reloadNow = true;
				}, 400);
			}
		});
	});

	$(".closeCurrentBudgetModal").click(function() {
		setTimeout(function(){location.reload();}, 300);
		reloadNow = false;
	});
});


function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}