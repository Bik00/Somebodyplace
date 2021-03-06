<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script language="javascript">
   // opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
   //document.domain = "abc.go.kr";

   function goPopup() {
      // 주소검색을 수행할 팝업 페이지를 호출합니다.
      // 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
      var pop = window.open("/somebodyplace/jusoPopup", "pop",
            "width=570,height=420, scrollbars=yes, resizable=yes");

      // 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(http://www.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
      //var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
   }

   function jusoCallBack(roadAddrPart1) {
      // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.

      $('#roadAddrPart1').val(roadAddrPart1);
      //document.form.roadAddrPart1.value = roadAddrPart1;
      //주소를 검색 완료하자마자 지도에 표시 
      loshow();

   }

   //input창에 검색한 주소로 그 주소의 위도, 경도 값을 구해  지도에 표시하는 함수 
   function loshow() {
      var t = $('#roadAddrPart1').val();
      alert(t);
      var key = 'AIzaSyC-f8h17-0IA4BncRf-Npxkwe_NS6PVh0A';

      $.ajax({
         url : "https://maps.googleapis.com/maps/api/geocode/json?",
         data : {
            address : t,
            key : key
         },
         dataType : 'json',
         Type : "GET",
         success : function(data) {

            var a = data.results[0].geometry.viewport.northeast.lat;
            var b = data.results[0].geometry.viewport.northeast.lng;

            $('#result2').val(a);
            $('#result3').val(b);

            var c = "(" + a + "," + b + ")";

            placeMarker(c);
            myMap(a, b);
            initMap(a, b);
            function initMap(a, b) {
               map = new google.maps.Map(document
                     .getElementById('map_canvas'), {
                  zoom : 16,
                  center : new google.maps.LatLng(a, b),
                  mapTypeId : 'roadmap'
               });

               var iconBase = './resources/img/';
               var icons = {
                  parking : {
                     icon : iconBase + 'man-standing-with-arms-up.png'
                  },
                  library : {
                     icon : iconBase + 'library_maps.png'
                  },
                  info : {
                     icon : iconBase + 'info-i_maps.png'
                  }
               };

               function addMarker(feature) {
                  var marker = new google.maps.Marker({
                     position : feature.position,
                     icon : icons[feature.type].icon,
                     map : map
                  });
               }

               var features = [ {
                  position : new google.maps.LatLng(a, b),
                  type : 'parking'
               } ];

               for (var i = 0, feature; feature = features[i]; i++) {
                  addMarker(feature);
               }
            }

            function myMap(test, test2) {

               var amsterdam = new google.maps.LatLng(test, test2);

               var mapCanvas = document.getElementById("map_canvas");
               var mapOptions = {
                  center : amsterdam,
                  zoom : 15
               };
               var map = new google.maps.Map(mapCanvas, mapOptions);

               var myCity = new google.maps.Circle({
                  center : amsterdam,
                  radius : 300,
                  strokeColor : "#0000FF",
                  strokeOpacity : 0.8,
                  strokeWeight : 2,
                  fillColor : "#0000FF",
                  fillOpacity : 0.4
               });

               myCity.setMap(map);
            }

         },
         error : function() {
            alert('오류가 발생');
            isLogin = false;
         }
      });

   }
</script>

 <script>
//    window.onload = function() {
//       var birth = document.getElementsByClassName('inputBirth');
//       var age = document.getElementsByClassName('inputAge');
//       var btn = document.getElementsByClassName('changeBtn');
//       btn.onclick = function() {
//          var inputValue = birth.value;
//          age.innerText = inputValue;
//       };       
//    };


       $(document).ready(function(memberAge) {
         $('.changeBtn').click(function(){
            
             var date = new Date();
             var year = date.getFullYear();
             var month = (date.getMonth() + 1);
             var day = date.getDate();       
             if (month < 10) month = '0' + month;
             if (day < 10) day = '0' + day;
             var monthDay = month + day;
             
             var birthYear = $('.inputBirth').val().substr(0,4); 
             
             console.log(birthYear); // 1996
             
             var birthMonth = $('.inputBirth').val().substr(4,4);
             
             console.log(birthMonth); // 0831
                
             var age = monthDay < birthMonth ? year - birthYear - 1 : year - birthYear;
   
             console.log(age);
             
               $(".inputAge").val(age); // 확인 버튼을 누르면 age input에         
      });
    });
 </script>

<script type="text/javascript">
   //input창에 검색한 주소로 그 주소의 위도, 경도 값을 구해  지도에 표시하는 함수 

   var map;
   function initialize() {
      var myLatlng = new google.maps.LatLng(35.896194586026304,
            128.62185716629028);
      var myOptions = {
         zoom : 15,
         center : myLatlng,
         mapTypeId : google.maps.MapTypeId.ROADMAP
      }
      map = new google.maps.Map(document.getElementById("map_canvas"),
            myOptions);
      //지도클릭했을 때 이벤트
      google.maps.event.addListener(map, 'click', function(event) {

         //마커 생성 함수    
         placeMarker(event.latLng);

         //input에 클릯한위치의 경도 위도를 한글 주소로 바꿔 입력  
         $(function() {

            var test = "" + event.latLng + "";

            $('#result').val(test);

            var afterStr = test.split(",");

            //위도경도를 위도 경도로 짤라줌 
            var afterStr2 = afterStr[0].split("(");
            var afterStr3 = afterStr[1].split(")");

            $('#result2').val(afterStr2[1]);

            $('#result3').val($.trim(afterStr3[0]));

            var test = afterStr2[1];
            var test2 = $.trim(afterStr3[0]);
            var seek = $('#seek').val();

            //위도 경도 따로 되있는 값을 합쳐줌 위도,경도 이런식으로 
            var latlng = test + "," + test2;

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

                  //주소 input창에 반환된 data값을 넣어줌 
                  //data.results[0].formatted_address= 대구 복현동 복현로 ....임 
                  $('#roadAddrPart1').val(
                        data.results[0].formatted_address);

               }
            });

         });

         // 인포윈도우의 위치를 클릭한 곳으로 변경한다.
         $(function() {

            var test = "" + event.latLng + "";

            $('#result').val(test);

            var afterStr = test.split(",");
            var afterStr2 = afterStr[0].split("(");
            var afterStr3 = afterStr[1].split(")");

            $('#result2').val(afterStr2[1]);
            $('#result3').val($.trim(afterStr3[0]));
         });

      });
      //클릭 했을때 이벤트 끝
      //인포윈도우의 생성
      var infowindow = new google.maps.InfoWindow({
         content : '<br><b>위치를  클릭한후</b><br>플레이스를 생성해주세요',
         size : new google.maps.Size(50, 50),
         position : myLatlng
      });
      infowindow.open(map);
   } // function initialize() 함수 끝

   // 마커 생성 합수
   function placeMarker(location) {
      var clickedLocation = new google.maps.LatLng(location);
      var marker = new google.maps.Marker({
         position : location,
         map : map
      });
      map.setCenter(location);
   }
</script>
<script>
   function resettest() {
      alert("sd");
      $('#roadAddrPart1').val("");
      var map = new google.maps.Map(document.getElementById('map_canvas'), {
         zoom : 13,
         center : {
            lat : 35.89412604821816,
            lng : 128.61610852181911
         },
         mapTypeId : 'terrain'
      });
   }
</script>

<body leftmargin="0" marginwidth="0" topmargin="0" marginheight="0"
   onload="initialize();">
<form class="addForm">

    <input type="hidden" name="board_name" value="게시판">
    <br>
    <h2><b>플레이스 생성</b></h2><br>
    <div class="form-group">
    	 <input type="hidden" value="${member_email}" name="member_email"><br>
    	 <input type="hidden" value="${member_code}" name="member_code"><br>
           <label>플레이스명</label><input type="text" class="form-control" name="Place_name"  value="광민이네 푸드트럭"/><br>
    </div>
    
<!--      <div class="reserveForm_type">
	      <label>플레이스로고</label><br>
	      <div class="reserve_fileBox">
		      <label for="reserveForm_ImgUpload" class="reserveForm_img">
		           <p>클릭해서<br>로고를<br>등록하세요</p>
		      </label>
		      <input type="file" id="reserveForm_ImgUpload" name="Place_logo">
	 	  </div>
	</div>
 -->	
	<div class="placeAddForm_fileBox">
		<label for="placeAddForm_ImgUpload" class="placeAddForm_img">
			<p>클릭 후<br>로고 등록</p>
		</label> <input type=file id="placeAddForm_ImgUpload">
	</div>
	<input type="hidden" id="placeAddForm_ImgUploadPath" name="Place_logo"></input>
	
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
   	 <label>플레이스접속 URL　　(변경불가)</label><br>
    	  
     http://localhost:8080/somebodyplace/${member_email}<br><br>
    	  
    <label>카테고리</label><br>


  		메인 카테고리
         <select class="mcate">
             <c:forEach items="${McateList}" var="m">
            	<c:if test="${m.mcate_code == 1}">
            		<option value="${m.mcate_code}" selected>${m.mcate_name}</option>
            	</c:if>
            	<c:if test="${m.mcate_code != 1 }">
            		<option value="${m.mcate_code}">${m.mcate_name}</option>
            	</c:if>
            </c:forEach>
        </select>
             세부 카테고리
        <select class="dcate">
            <c:forEach items="${DcateList}" var="d">
            	<c:if test="${d.mcate_code == 1}"> 
            		<c:if test="${d.dcate_code == 1 }">
            			<option value="${d.dcate_code}" name="${d.mcate_code}" selected>${d.dcate_name}</option>
            		</c:if>
            		<c:if test="${d.dcate_code != 1 }">
            			<option value="${d.dcate_code}" name="${d.mcate_code}">${d.dcate_name}</option>
            		</c:if>
            	</c:if>
            </c:forEach>
            <c:forEach items="${DcateList}" var="d">
            	<input type="hidden" class="dcateList" name="${d.dcate_name}" value="${d.mcate_code}" data="${d.dcate_code}">
            </c:forEach>
        </select><br><br>
	
      
    <label class="YaKwan">약관</label>
    <textarea class="form-control" rows="3" style="width:100%;">
			제 1 장 총 칙
			제 1 조 (목적)
			본 약관은 서비스 이용자가 주식회사 썸바디플레이스(이하 “회사”라 합니다)가 제공하는 온라인상의 인터넷 서비스(이하 “서비스”라고 하며, 접속 가능한 유∙무선 단말기의 종류와는 상관없이 이용 가능한 “회사”가 제공하는 모든 “서비스”를 의미합니다. 이하 같습니다)에 회원으로 가입하고 이를 이용함에 있어 회사와 회원(본 약관에 동의하고 회원등록을 완료한 서비스 이용자를 말합니다. 이하 “회원”이라고 합니다)의 권리•의무 및 책임사항을 규정함을 목적으로 합니다....등등
    </textarea><br>

    <div class="checkbox">
         <label><input type="checkbox" value="">동의</label>
    </div>
    
    	<div class="form-group">
	 		<label class="col-sm-3 control-label">플레이스위치</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="roadAddrPart1" name="place_addr" />
			</div>
		</div>

			<input type="button" class="btn btn-default" onClick="goPopup();" value="주소검색" />
			<input type="button" class="btn btn-default" onclick="resettest();" value="초기화" />


      <input type="hidden" class="form-control" id="result2" name="place_lat" value="" style="width: 50%;">
          <input type="hidden" class="form-control" id="result3" name="place_lng" value="" style="width: 50%;">

</form>
	<div id="map_canvas" style="width: 60%; height: 40%; left:20%"></div><br><br>
	<div class="placeAddForm_placeAddbox">
	<button class="btn btn-default placeAddBtn" style="margin-left:45%;">생성하기</button>
    <input type="button" class="btn btn-default cancelBtn" id="cancelBtn" onclick="self.location='main'" value="취소하기" />
    </div>
</head>



    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC-f8h17-0IA4BncRf-Npxkwe_NS6PVh0A&callback=initMap"
    async defer></script>



<!-- Modal -->
<div class="modal fade" id="addPlaceFirst" role="dialog">
	<div class="modal-dialog">

		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">SOMEBODY 알림창</h4>
			</div>
			<div class="modal-body" id="addPlaceFirstBody"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default closePlaceFirst" data-dismiss="modal">닫기</button>
			</div>
		</div>

	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="placeAddFormModal" role="dialog" ng-show="enableCrop">
	<div class="modal-dialog modal-lg">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closePlaceAddFormModal" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">SOMEBODY 알림창</h4>
			</div>
			<div class="modal-body bodyPlaceAddFormModal">
				<div class="placeAddForm_width_img">
					<img id="placeAddForm_resize_img" src='./resources/img/product1.jpg'>
				</div>
				<p id="preview_title">로고 화면 : </p>
				<div id="preview-pane">
   					<div class="preview-container">
   					</div>
 					</div>
 					<label class="hideMyGeo">X1 <input type="text" size="4" id="x1" name="x1" /></label>
				    <label class="hideMyGeo">Y1 <input type="text" size="4" id="y1" name="y1" /></label>
				    <label class="hideMyGeo">X2 <input type="text" size="4" id="x2" name="x2" /></label>
				    <label class="hideMyGeo">Y2 <input type="text" size="4" id="y2" name="y2" /></label>
				    <label class="hideMyGeo">W <input type="text" size="4" id="w" name="w" /></label>
				    <label class="hideMyGeo">H <input type="text" size="4" id="h" name="h" /></label>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default confrimPlaceAddFormModal" data-dismiss="modal">결정하기</button>
				<button type="button" class="btn btn-default closePlaceAddFormModal" data-dismiss="modal">취소하기</button>
			</div>
		</div>
	</div>
</div>


<c:if test="${PlaceX!= null }">  
	<script>
		$("#addPlaceFirstBody").append('<p>플레이스를 먼저 생성해주세요.</p>');
		isLogin = true;
		$("#addPlaceFirst").modal();			
    </script>
</c:if> 