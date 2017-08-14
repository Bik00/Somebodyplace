<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <%@page import="java.sql.*" %>
<c:if test="${applogin!= null }">
   <script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
   <link rel="stylesheet"
      href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
   <script
      src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="./resources/m_css/m_issue.css?ver=3">
   <script src="./resources/js/issue.js?ver=9"></script> 
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport"
   content="width=device-width, initial-scale=1.0, user-scalable=no">
<meta charset="UTF-8">
<script type="text/javascript" src="<c:url value="./resources/js/sockjs.js"/>"></script>
<title>Somebody Place</title>

 <!-- <script async defer
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC-f8h17-0IA4BncRf-Npxkwe_NS6PVh0A&callback=initMap"> -->

 <script> 
 <!-- 위도경도를 맵에 나타내기 위한 스크립트 영역입니다 ! -->
 function initial() { 
    var i = 0;
    $(".issue_content").each(function(){
       
       var myLat = parseFloat($(this).find('.issue_lat').val());
       var myLng = parseFloat($(this).find('.issue_lng').val());
       
       
       var myLatlng = new google.maps.LatLng(myLat, myLng); 
        var mapOptions = { 
              zoom: 17, 
              center: {
                      lat : myLat,
                      lng : myLng
                    },
              mapTypeId: google.maps.MapTypeId.ROADMAP 
        } 
        console.log($(".map_canvas").length);
        var map = new google.maps.Map(document.getElementsByClassName("map_canvas")[i], mapOptions);
       
       
       var marker = new google.maps.Marker({ 
             position: myLatlng, 
             map: map, 
             title: "요기입니다." 
       }); 
       
       i++;
       
    });

    // 지도보기 버튼 클릭시 지도가 show, hidden되는 이벤트
    $(".showMapBtn").click(function() {
       $(".map_canvas").toggle();
    });
} 
 
// <!-- 위도경도를 맵에 나타내기 위한 스크립트 영역 끝 ! -->
 </script> 
</head>



<body onload="initial()"> 
<body onload="showMap()">

<c:if test="${applogin!= null }">
   <div class="requestbyapp" style="display:none">${applogin} </div>
   <div class="sender" style="display:none">${member_code} </div>
</c:if>
   
   <br>
   <div class="issue_searchDiv">
      <br>
      <div class="issue_searchDiv2">      
      <form>
         <input type="text" class="issue_searchForm form-control" name="search_keyword" value="${search_keyword}">
         <input type="submit" value="검색" class="issue_searchbtn">
      
         </form>
            <button class="btn btn-default issue_addIssuebtn">이슈쓰기</button>
      <!--    <button class="btn btn-default issue_searchbtn">검색</button> -->      
         
      </div>   

   </div>

   <c:forEach items="${memberlist}" var="list">
   <%--    
         회원코드가　${list.member_code}<br>위도${list.member_lng}　　경도${list.member_lat}<br>
         이슈와의 거리 ${list.distance}M에게알림 <br> --%>
      <script>
             
      $(document).ready(function(){
         updateData();
      });
   
      member_code +=","+${list.member_code};
         function updateData(){
            $.ajax({
               url:"http://106.249.38.80:8080/somebodyplace/pushServer",
               dataType:"jsonp",
               jsonp:"callback",
               Type : "GET",
               data : {
                  member_code: member_code
               },
                success : function(data) {
                   //alert("맴버코드는"+data.member_code);
                   $('.push').val(data.member_code);
                }
            });
            //setTimeout("updateData()",5000);
         }
         
         count++;
      </script>

   </c:forEach>
   
         <c:if test="${memberlist!= null }">         
         <script>
            //document.write(count);
            //alert(count+"명에게알림");
            //alert(member_code+"님에게 이슈를 전송했습니다.");
         </script>
      </c:if> 

<input type="hidden" class="push">
   <c:forEach items="${list}" var="issue">
      <div class="issue_box" data-issue="${issue.issue_code}">
         <div class="issue_top">
            <div class="issue_picture">
               <img src="./resources/img/프로필.PNG">
            </div>
            <div class="issue_profile">
               <span id="issue_receiverName">${issue.member_nickname}</span>
               <button data-chatBtn="${issue.issue_code}" data-whoIs="${issue.member_code}" class="btn btn-default requestChat">1:1대화신청</button>
               <br>
               <fmt:formatDate pattern="yyyy-MM-dd HH:mm"
                  value="${issue.post_time}" />
               <br>
            </div>
      
      
             <c:if test="${issue.member_code==member_code}">   
                  <div class="issue_setting">
                     <img src="./resources/img/settings.png" >
                  </div>
              </c:if>
         </div>

         <div class="issue_content">

         <h1>${issue.issue_content}</h1>
            <br>
            <c:if test="${issue.issue_img != ''}">
               <img src="./resources/img/${issue.issue_img}">
     
            </c:if>
            <br>
            *${issue.address}<br>
            <button class="btn btn-default showMapBtn">지도보기</button><br><br>
            <!-- 지도보기 버튼을 클릭 시 지도가 나옵니다. 한번 더 클릭시 사라집니다. -->
            
            <!-- 위도경도를 맵에 나타내기 위한 영역입니다 ! -->
            
            <div id="hiddenLatLng" style="display:none;"> 
               경도 <input type="text" value="${issue.lat}" class="issue_lat">
               위도 <input type="text" value="${issue.lng}" class="issue_lng"> <br>
            </div>
            <div class="map_canvas" style="width:90%; height:300px; margin: 0 auto;"></div> <br>
               <!-- 위도경도를 맵에 나타내기 위한 영역 끝 ! -->
               *주변 ${issue.radius}m로 보낸 이슈 <br>
               *${issue.hashtag}<br>
               
               <c:if test="${issue.issue_code=issue_code}">
                  <body onload="showMap()"> 
               </c:if>
               
         

         </div>
         <div class="reply_btn" data-replyBtn="1">
            <button class="btn btn-default replies" id="replies">댓글 보기</button>
            <div class="reply_count">댓글: 개</div>
         </div>
         <div class="reply_list" data-replyList="1">

            <!-- 첫번째 댓글 -->
            <!--  <div class="reply_comment">
               <div class="reply_picture">
                  ${member.profile}
               </div>
               <div class="reply_profile">
                ${member.member_nickname}<br>${reply.reply_regDate}
               </div>
               <button class="btn btn-default reply_setting">삭제하기</button>
               <div class="reply_text">
                 ${reply.reply_text}
               </div> 
            </div>
            <br> -->
            <ul id="reply_box" style="none">
            </ul>
          

            <!-- 댓글 입력창 -->
            <div class="reply_textarea">
               <input type="textarea" class="reply_addText"
                  placeholder="댓글을 입력하세요">
               <button class="btn btn-default reply_addTextBtn">댓글 달기</button>
            </div>
            
            
         </div>
      </div>
      <input type="hidden" value="${issue.issue_code}">

   </c:forEach>
      <div class="settingDiv">
         <input type="hidden" class="issue_code">
         <button class="issue_messagebtn">알림보내기</button><br>
         <button class="issue_modifybtn">수정</button><br>
         <button class="issue_deletebtn">삭제</button>
      </div>
</body>
</html>