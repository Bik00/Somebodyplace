var score1 = document.getElementsByClassName('check_review_score_star');
var post_code ="";
var countScore = 0;

$(document).ready(function() {
	if($("#comment_review").attr("rows").length != 0) {
		getAllReviewList();
		$("#paper").css("bottom", -$(document).height()-100+$(window).height());
		
	}
	$(window).resize(function(){
		if($("#comment_review").attr("rows").length != 0) {
			$(".enterReviewButton").css("height", $("#comment_review").height()+12);
		}
	});
	
	$(".enterReviewButton").click(function() {
		var member_code = $("#member_code").val();
		var post_code = $("#post_code").val();
		var review_content = $("#comment_review").val();
		
		$.ajax({
			type: 'post',
			url : 'addReview',
			headers : {
				"Content-Type" : "application/json",
				"X-HTTP-Method-Override" : "POST"
			},
			dataType : 'text',
			data : JSON.stringify({
				post_code : post_code,
				member_code : member_code,
				review_content : review_content,
				review_score : countScore
			}),
			success : function(result) {
				if(result == 'SUCCESS') {
					alert("등록되었습니다.");
					getAllReviewList();
					$("#paper").css("bottom", -$(document).height()-100+$(window).height());
				}
			}
		})
	});
});

function scores(star){
	for(i=0;i<=star;i++){
		score1[i].style.backgroundPosition = ' -3519px 0';
		score1[i].style.transition = 'background 1s steps(55)';
		countScore++;
	}
}
function offscores(star){
	countScore = 0;
	for(i=0;i<=star;i++){
		score1[i].style.backgroundPosition = '';
		score1[i].style.transition = '';
	}
}
function setScore(){
	for(i=0;i<=score1.length;i++){
		score1[i].setAttribute('onmouseover', ' ');
		score1[i].setAttribute('onmouseout', ' ');
	}
}

function getAllReviewList() {
	post_code = $("#post_code").val();
	var url = "getReviewList/"+post_code;
	$.getJSON(url, function(data) {
		
		var str = "<br>";
		
		$(data).each(function() {
			str +="<div class='review' data-reviewNo='"+this.review_code+"'>"
				+"<div class='review_profile'><div class='review_picture'><img src='"+this.member_profile+"'>"
				+"</div><div class='review_profile_info'><h3>"+this.member_nickname+"</h3>"
				+"</div><input type='hidden' value='"+this.review_score+"' class='output_review_score'><div class='review_score'>"
				+"<div class='review_score_star'></div>"
				+"<div class='review_score_star'></div>"
				+"<div class='review_score_star'></div>"
				+"<div class='review_score_star'></div>"
				+"<div class='review_score_star'></div>"
				+"</div></div><div class='review_content'><div class='review_content_text'>"+this.review_content
				+"</div></div><div id='setting_review'>"
				+"<button class='btn btn-default' style='color:#337ab7 !important;' id='modifyThisReview'>수정하기</button>"	
				+"<button class='btn btn-default' id='deleteThisReview'>삭제하기</button>"
				+"</div></div><hr>";
		});
		$(".review_list").html(str);
		var totalScore = 0;
		var devideScore = 0;
		$(document).find(".output_review_score").each(function(index) {
			var score = $(this).val()
			$(this).next().find(".review_score_star").each(function(q) {
				if(q<score) {
					$(this).css('backgroundPosition', ' -3519px 0');
				}
			});
			
			totalScore += parseInt($(this).val());
			devideScore++;
		});
		var resultScore = totalScore / devideScore;
		resultScore = resultScore.toFixed(1);
		$("#aveRageReview").text(resultScore+"/5.0");
		kkk = Math.round(resultScore);
		$(".post_score").each(function(index) {
			$(this).find(".post_score_star").each(function(q) {
				if(q<kkk){
					$(this).css('backgroundPosition', ' -3519px 0');
				} else {
					$(this).css('backgroundPosition', '');
				}
			});
		});
	});
}

/*function calAveReviewScore() {
	var totalScore = 0;
	var devideScore = 0;
	$(document).find(".output_review_score").each(function() {
		
		totalScore += $(this).val();
		devideScore++;
	});
	var resultScore = totalScore / devideScore;
	alert(totalScore+", "+devideScore);
	resultScore = resultScore.toFixed(1);
	alert(resultScore+"/5.0");
	$("#aveRageReview").text(resultScore+"/5.0");
}*/