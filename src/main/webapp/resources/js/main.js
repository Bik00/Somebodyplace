fn_rollToEx();

var isFindGeo = false;
var geoAreaWidth = 0;
var cal_geo = 0;

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

    	  if($("#code").text().length != 0) {
    		  var query = {
  					member_code : $("#code").text()
  				}
  				
  				$.ajax({
  					type : "post",
  					url : "readMyAllTimelines",
  					data : query,
  					async:false,
  					success : function(data){
  						for(var kx = 0;kx<data.length;kx++) {
  	  						var result = "<a href='requestList'><div class='timeline_detail_room' data-sender='3'><div class='timeline_picture'>"
  	  							+"<table><tr><td><img style='width: 50px; border-radius: 50%;' src='"+data[kx].member_profile+"'>"
  	  							+"</td><td style='text-align:left;'>"+data[kx].member_nickname+" 님께서 "+data[kx].product_name+"을(를) 신청하였습니다.</td></tr></table></div></div>"
  	  							+"<hr style='margin-top:5px;margin-bottom:5px;'></a>";				
  	  						$(document).find(".timeline_detail_list").append(result);
  						}
  					}
  				});
    	  }
    	  
    		$("#icon_somebody_timeline").click(function() {
    			if($(".Somebody_timeline_detail").css("display")=="none") {
    				$(".Somebody_timeline_detail").show();			
    			} else {
    				$(".Somebody_timeline_detail").hide();	
    			}
    		});
    	  
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

	var lat = $("#latAtThisTime").val();
	var lng = $("#lngAtThisTime").val();
	
	if(lat != null && lng != null) {
		var key = 'AIzaSyC-f8h17-0IA4BncRf-Npxkwe_NS6PVh0A';
		var latlng = lat+","+lng;
		
		$.ajax({
			url : "https://maps.googleapis.com/maps/api/geocode/json?",
			data : {
				latlng : latlng,
				key : key
			},
			async:false,
			dataType : 'json',
			Type : "GET",
			success : function(data) {

				// 주소 input창에 반환된 data값을 넣어줌
				// data.results[0].formatted_address= 대구 복현동 복현로 ....임
				var k = data.results[0].formatted_address;
				var b = k.split(" ");
				var y = k.substring(5, k.indexOf(b[4]));
				$("#whereIsNow").text(y); 		  
			}
		});
	}
	
	geoAreaWidth = parseInt($("#main_geolocation_info").css('width'));
	$(".index_findAboutGeo").css("left", geoAreaWidth/2);
	cal_geo = geoAreaWidth;  
	
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
		
  	  	geoAreaWidth = parseInt($("#main_geolocation_info").css('width'));
		if(isFindGeo == false) {
			$("#index_findeGeo").animate({
	            left: '310px',
	            width: geoAreaWidth+40+'px'
	        });
			$("#index_searchMyGeolocation").animate({
				width:geoAreaWidth-150+'px',
				display: 'inline-block'
			});
			$("#index_searchAutoGeolocation").animate({
				width:'150px',
				display: 'block'
			});
			isFindGeo = true;
		} else { // 차차
			var cal_geo2 = parseInt((geoAreaWidth -cal_geo)/2);
			$(".index_findAboutGeo").css("left", geoAreaWidth/2+cal_geo2);
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

	
	$(document).on("click", "#resultOfGeo ul li", function() {
		$("#index_searchMyGeolocation").val($(this).text());
		$("#whereIsNow").text($(this).text());
		$("#resultOfGeo").slideUp(500);
		setTimeout(function(){$(".index_findAboutGeo").trigger("click");}, 500);
		
		var x = $(this).text();
		/* 요기 부분 수정 */
		var lat = 0;
		var lng = 0;
		
		
		var query = {
				sensor:false,
				language:"ko",
				address:x
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
					var k = data.results[0].geometry.location;
					lat = k.lat;
					lng = k.lng;
				}
			});
		changeItems(lat, lng);
	});
		
	
	$(".index_myAddrSearchBtn").click(function() {
		var lat = $("#latAtThisTime").val();
		var lng = $("#lngAtThisTime").val();
		var latlng = lat+","+lng;
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
 				var k = data.results[0].formatted_address;
				var b = k.split(" ");
				var y = k.substring(5, k.indexOf(b[4]));
				$("#whereIsNow").text(y);
				geoAreaWidth = parseInt($("#main_geolocation_info").css('width'));
				$(".index_findAboutGeo").css("left", geoAreaWidth/2);
				
				changeItems(lat, lng);
 			}
 		});
	});
	
	$(document).on("click", ".clickForTypeItems", function() { // 체크했을 때 이벤트
		if($(this).text().length == 2) {	
		var x = $(this).text();
		$(this).html(x+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&#10003");
		} else {
			var x = $(this).text();
			$(this).html(x.substring(0,2));
		}	
		var x = $(".clickForTypeItems");
		var k = new Array();
		x.each(function(index) {
			if($(this).text().length != 2) {
				k[index] = $(this).text().substring(0,2);
			}
		});
		jQuery.ajaxSettings.traditional = true;
		$.ajax({
			type : "post",
			url : "readItemListByType",
			data : {type_list : k},
			async:false,
			success : function(data){
				
			}
		});
	});
	
	/*	$(".clickForTypeItems").click(function() {
	$(".fadeEffectItems").fadeOut();
	$(".fadeEffectItems").fadeIn();
	});*/
	
	if($(".product_box").text().length != 0 && $(".place_title").text().length == 0) {
		checkMargin();
	}
	
/*	$("#sitemap").click(function() {
		$(".sitemap").slideToggle();
	});
	*/
	if($("#paper").text().length != 0) {
		$("#paper").css("bottom", -$(document).height()-100+$(window).height());
	}
	
	
	$("#watchAllItems").click(function() {
		$(".main_content").fadeOut();
		$.ajax({
			type : "post",
			url : "main_readAllItems",
			async:false,
			success : function(data){
				var query = "";
				for(var i = 0;i<data.length;i++) {
					
					query += "<div class='product_box' style='display:none;' data='"+data[i].product_code+"'>"+
					"<div class='product_img'><img src='"+data[i].product_img+"'></div>"+
					"<br><div class='product_info'>"+
					"<div><input type='button' value='"+data[i].type+"' class='btn btn-default type' name='type'>"+
					"<h4><b>"+data[i].product_name+"</b></h4>"+
					"</div><div>"+data[i].product_explanation+"</div><div><b>"+
					data[i].product_price+" 원</b></div><br></div></div>";
				}
				$("body").animate({scrollTop: 0}, 500);
				$(".product_box").addClass("del_product_box");
				$(".del_product_box").removeClass("product_box");
				$(".watchOut").append(query).find('div.product_box').fadeIn();
			}
		});
		checkMargin();
		$(".watchOut").fadeIn();
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
            refSize = Math.min(refSize, 1300);
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
		var y = $(this).val()/1;
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
				
				changeItems(latitude, longitude);
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
function checkMargin() {
	$(".product_box").each(function(x) {
		if(x%4 == 0) {
			$(this).css("margin-left", 0);
		}
		else if (x%4 == 3) {
			$(this).css("margin-right", 0);
		}
	});
}

function changeItems(lat, lng) {
	$("#main_issue").fadeOut();
	$(".getBestItem").fadeOut();
	$("#main_issue").fadeIn();
	$(".getBestItem").fadeIn();
	
	var query = {
		"lat" : lat,
		"lng" : lng
	}
	
	$.ajax({
		type : "post",
		url : "changeItems",
		async:false,
		data : query,
		success : function(data){
			$(".BestItemList").empty();
			$(".fadeEffectItems").empty();
			
			var best_query = "";
			
			console.log(data);
			
			best_query += "<div class='BestItem' id='main_newItem' data='"+data.new_items.product_code+"'>"
						+"<img src='./resources/img/main_newItem.png' id='newItem_Mark' class='BestItemList_Mark'>"
						+"<div class='BestItem_Title'><p>"+data.new_items.product_name+"</p></div>"
						+"<img src='"+data.new_items.product_img+"' class='BestItem_img'>"
						+"<div class='BestItem_Info'>"
						+"<img src='./resources/img/bestItem_radius.png' style='width:20px; height:20px; display:inline-block;'>"
						+"<p style='display:inline-block; margin-left:10px;'>"+data.new_items.distance+"km</p>"
						+"<p style='text-align:center; margin-top: -35px;'>"+data.new_items.product_price+" 원</p>"
						+"</div></div>"
						+"<div class='BestItem' id='main_bestItem' data='"+data.best_items.product_code+"'>"
						+"<img src='./resources/img/main_bestItem.png' id='bestItem_Mark' class='BestItemList_Mark'>"
						+"<div class='BestItem_Title'><p>"+data.best_items.product_name+"</p></div>"
						+"<img src='"+data.best_items.product_img+"' class='BestItem_img'>"
						+"<div class='BestItem_Info'>"
						+"<img src='./resources/img/bestItem_radius.png' style='width:20px; height:20px; display:inline-block;'>"
						+"<p style='display:inline-block; margin-left:10px;'>"+data.best_items.distance+"km</p>"
						+"<p style='text-align:center; margin-top: -35px;'>"+data.best_items.product_price+" 원</p>"
						+"</div></div>"
						+"<div class='BestItem' id='main_recommendItem' data='"+data.random_items.product_code+"'>"
						+"<img src='./resources/img/main_recommendItem.png' id='recommendItem_Mark' class='BestItemList_Mark'>"
						+"<div class='BestItem_Title'><p>"+data.random_items.product_name+"</p></div>"
						+"<img src='"+data.random_items.product_img+"' class='BestItem_img'>"
						+"<div class='BestItem_Info'>"
						+"<img src='./resources/img/bestItem_radius.png' style='width:20px; height:20px; display:inline-block;'>"
						+"<p style='display:inline-block; margin-left:10px;'>"+data.random_items.distance+"km</p>"
						+"<p style='text-align:center; margin-top: -35px;'>"+data.random_items.product_price+" 원</p>"
						+"</div></div>";
			$(".BestItemList").append(best_query);
			
			
			
			var product_query = "";
			for(var i = 0;i<data.all_items.length;i++) {
				product_query = "<div class='product_box' data='"+data.all_items[i].product_code+"'>"+
				"<div class='product_img'><img src='"+data.all_items[i].product_img+"'></div>"+
				"<br><div class='product_info'>"+
				"<div><input type='button' value='"+data.all_items[i].type+"' class='btn btn-default type' name='type'>"+
				"<h4><b>"+data.all_items[i].product_name+"</b></h4>"+
				"</div><div>"+data.all_items[i].product_explanation+"</div><div><b>"+
				data.all_items[i].product_price+" 원</b></div><br></div></div>";
				$(".fadeEffectItems").append(product_query);
			}
			checkMargin();
		}
	});
	
}