fn_rollToEx();

var isFindGeo = false;

function fn_rollToEx(){

      // 롤링할 객체를 변수에 담아둔다.
   
      var lastChild;
      var speed = 6000;
      var timer = 0;

      $('#slide_img').data('prev', $('.slideBtn-left'));   //이전버튼을 data()메서드를 사용하여 저장한다.
      $('#slide_img').data('next', $('.slideBtn-right'));   //다음버튼을 data()메서드를 사용하여 저장한다.
      $('#slide_img').data('size', $('#slide_img').children().outerWidth());      //롤링객체의 자식요소의 넓이를 저장한다.
      $('#slide_img').data('len', $('#slide_img').children().length);            //롤링객체의 전체요소 개수
      $('#slide_img').data('animating',false);

      $('#slide_img').css('width',$('#slide_img').data('size')*$('#slide_img').data('len'));      //롤링객체의 전체넓이 지정한다.

      $(document).ready(function() {
         //$('#slide_img')에 첨부된 prev 데이타를 클릭이벤트에 바인드한다.
         $('.slideBtn-left').click(function(e){
            
               e.preventDefault();
               movePrevSlide();
            });

         //$('#slide_img')에 첨부된 next 데이타를 클릭이벤트에 바인드한다.
         $('.slideBtn-right').click(function(e){
               e.preventDefault();
               moveNextSlide();
         });
      });
      
      function movePrevSlide(){
         if(!$('#slide_img').data('animating')){
            //롤링객체의 끝에서 요소를 선택하여 복사한후 변수에 저장한다.
            var lastItem = $('#slide_img').children().eq(-2).nextAll().clone(true);
            lastItem.prependTo($('#slide_img'));      //복사된 요소를 롤링객체의 앞에 붙여놓는다.
            $('#slide_img').children().eq(-2).nextAll().remove();   //선택된 요소는 끝에서 제거한다
            $('#slide_img').css('left','-'+($('#slide_img').data('size')*1+'px'));   //롤링객체의 left위치값을 재설정한다.
         
            $('#slide_img').data('animating',true);   //애니메이션 중복을 막기 위해 첨부된 animating 데이타를 true로 설정한다.

            $('#slide_img').animate({'left': '0px'},'normal',function(){      //롤링객체를 left:0만큼 애니메이션 시킨다.
               $('#slide_img').data('animating',false);
            });
         }
         return false;
      }

      function moveNextSlide(){
         if(!$('#slide_img').data('animating')){
            $('#slide_img').data('animating',true);

            $('#slide_img').animate({'left':'-'+($('#slide_img').data('size')*1)+'px'},'normal',function(){   //롤링객체를 애니메이션 시킨다.
               //롤링객체의 앞에서 요소를 선택하여 복사한후 변수에 저장한다.
               var firstChild = $('#slide_img').children().filter(':lt('+1+')').clone(true);
               firstChild.appendTo($('#slide_img'));   //복사된 요소를 롤링객체의 끝에 붙여놓는다.
               $('#slide_img').children().filter(':lt('+1+')').remove();   //선택된 요소를 앞에서 제거한다
               $('#slide_img').css('left','0px');   ////롤링객체의 left위치값을 재설정한다.

               $('#slide_img').data('animating',false);
            });
         }
         return false;
      }
   }            

$(document).ready(function(){		// 롤링배너
	meterWillChange();

	var acc = document.getElementsByClassName("mainissue_border");
	var i;

	for (i = 0; i < acc.length; i++) {
	  acc[i].onmouseover = function() {
	    this.classList.toggle("active");
	    var panel = this.lastElementChild;
	    if (panel.style.height){
	      panel.style.height = null;
	    } else {
	      panel.style.height = "100px";
	    } 
	  }
	  acc[i].onmouseout = function() {
	    this.classList.toggle("active");
	    var panel = this.lastElementChild;
	    if (panel.style.height){
	      panel.style.height = null;
	    } else {
	      panel.style.height = "100px";
	    } 
	  }
	}
	var current =0;
	var banner=$('.main_issueBox li');
	var slide = setInterval(function () {
		var prev = banner.eq(current);
		move(prev, 0, '-100%');
		current++;
		if(current == 5) {current=0;}
		var next =banner.eq(current);
		move(next, '100%', 0);
	}, 8000);
	function move(tg, start, end){
		tg.css({'top':start,'display':'inline-block'}).stop()
		.animate({top:end}, {duration:800, ease:'easeOutCubic'});
	}
	$(".index_findAboutGeo").click(function(){
		if(isFindGeo == false) {
			$("#index_findeGeo").animate({
	            left: '325px',
	            width: '558px'
	        });
			$("#index_searchMyGeolocation").animate({
				width:'390px',
				display: 'inline-block'
			});
			$("#index_searchAutoGeolocation").animate({
				width:'150px',
				display: 'block'
			});
			isFindGeo = true;
		} else {
			$("#index_findeGeo").animate({
	            left: '883px',
	            width: '0px'
	        });
			$("#index_searchMyGeolocation").animate({
				width:'0px',
			});
			$("#index_searchAutoGeolocation").animate({
				width:'0px',
			});
			setTimeout(function(){$("#index_searchMyGeolocation").hide()}, 400);
			setTimeout(function(){$("#index_searchAutoGeolocation").hide()}, 400);
			isFindGeo = false;
		}

    });
	
	$(".clickForTypeItems").click(function() {
		$(".fadeEffectItems").fadeOut();
		$(".fadeEffectItems").fadeIn();
	});
	
	$(document).on("click", "#resultOfGeo ul li", function() {
		$("#index_searchMyGeolocation").val($(this).text());
		$("#whereIsNow").text($(this).text());
		$("#resultOfGeo").slideUp(500);
		setTimeout(function(){$(".index_findAboutGeo").trigger("click");}, 500);
		
		// 임시 설정
		$("#main_issue").fadeOut();
		$(".getBestItem").fadeOut();
		$("#main_issue").fadeIn();
		$(".getBestItem").fadeIn();
	});
	
	$(document).on("click", ".clickForTypeItems", function() {
		if($(this).text().length == 2) {
		var x = $(this).text();
		$(this).html(x+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#10003");
		} else {
			var x = $(this).text();
			$(this).html(x.substring(0,2));
		}
	});
});

jssor_1_slider_init = function() {

    var jssor_1_options = {
      $AutoPlay: 1,
      $Idle: 5000,
      $SlideEasing: $Jease$.$InOutSine,
      $ArrowNavigatorOptions: {
        $Class: $JssorArrowNavigator$
      },
      $BulletNavigatorOptions: {
        $Class: $JssorBulletNavigator$
      }
    };

    var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);

    /*#region responsive code begin*/
    /*remove responsive code if you don't want the slider scales while window resizing*/
    function ScaleSlider() {
        var refSize = jssor_1_slider.$Elmt.parentNode.clientWidth;
        if (refSize) {
            refSize = Math.min(refSize, 980);
            jssor_1_slider.$ScaleWidth(refSize);
        }
        else {
            window.setTimeout(ScaleSlider, 30);
        }
    }
    ScaleSlider();
    $Jssor$.$AddEvent(window, "load", ScaleSlider);
    $Jssor$.$AddEvent(window, "resize", ScaleSlider);
    $Jssor$.$AddEvent(window, "orientationchange", ScaleSlider);
    /*#endregion responsive code end*/
};

$(function(){
	
	/* mypage 에서 회원정보 수정 클릭 했을시 */
	$(".index_searchbtn ").click(function(){
		
		location.href="main_search";
	});
});

function meterWillChange() {
	var x = $(".index_meterWillChange");
	x.each(function(index) {
		var y = $(this).val()/1000;
		$(this).next().text("주변 "+y.toFixed(1)+"km에서 알림");
	});
}

function geoFindMe() {
/*    var output = document.getElementById("out");*/

    if (!navigator.geolocation){
//      output.innerHTML = "<p>사용자의 브라우저는 지오로케이션을 지원하지 않습니다.</p>";
    	alert("사용자의 브라우저는 지오로케이션을 지원하지 않습니다.");
    	return;
    }

    function success(position) {
      var latitude  = position.coords.latitude;
      var longitude = position.coords.longitude;
      var latlng = latitude+","+longitude;

/*      output.innerHTML = '<p>위도 : ' + latitude + '° <br>경도 : ' + longitude + '°</p>';*/
      /*
      	alert("위도 : "+latitude+", 경도 : "+longitude);*/

		var key = 'AIzaSyC-f8h17-0IA4BncRf-Npxkwe_NS6PVh0A';

		$.ajax({
			url : "https://maps.googleapis.com/maps/api/geocode/json?",
			data : {
				latlng : latlng,
				key : key
			},
			dataType : 'json',
			Type : "GET",
			success : function(data) {

				// 주소 input창에 반환된 data값을 넣어줌
				// data.results[0].formatted_address= 대구 복현동 복현로 ....임
				alert("검색된 주소는 : "+data.results[0].formatted_address+" 입니다.");
				var k = data.results[0].formatted_address;
				var b = k.split(" ");
				var y = k.substring(5, k.indexOf(b[4]));
				$("#whereIsNow").text(y);
				$(".index_findAboutGeo").trigger("click");
				
				// 임시 설정
				$("#main_issue").fadeOut();
				$(".getBestItem").fadeOut();
				$("#main_issue").fadeIn();
				$(".getBestItem").fadeIn();
			}
		});
      
      
      
      
      
      
//      var img = new Image();
//      img.src = "http://maps.googleapis.com/maps/api/staticmap?center=" + latitude + "," + longitude + "&zoom=13&size=300x300&sensor=false";
//
//      output.appendChild(img);
    };

    function error() {
      /*output.innerHTML = "사용자의 위치를 찾을 수 없습니다.";*/
    	alert("사용자의 위치를 찾을 수 없습니다.");
    };

/*    output.innerHTML = "<p>Locating…</p>";*/

    navigator.geolocation.getCurrentPosition(success, error);
}

function findGeoAddr(event, keyword) {
	var x = event.which || event.keyCode;
	if(x==13) {
		if(keyword.indexOf("동") != -1) {
			var query = {
				sensor:false,
				language:"ko",
				address:keyword
			}
			$.ajax({
				type : "get",
				url : "http://maps.googleapis.com/maps/api/geocode/json",
				data : query,
				async:false,
				success : function(data){
					$("#resultOfGeo ul").empty();
					if(data.results.length == 0) {
						alert('검색 결과가 없습니다.');
					}
					for(var i=0;i<data.results.length;i++) {
						var k = data.results[i].formatted_address;
						$("#resultOfGeo ul").append("<li>"+k.substring(5, k.length)+"</li>");
						$("#resultOfGeo").slideDown(500);
					}
				}
			});
		} else {
			alert("동명을 입력해주세요!");
		}
	}
}