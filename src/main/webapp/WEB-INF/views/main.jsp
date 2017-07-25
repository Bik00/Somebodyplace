<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="main_content">
    <div id="slider" class="main_slide">
         <div id="jssor_1" style="position:relative;margin:0 auto;top:0px;left:0px;width:800px;height:550px;overflow:hidden;visibility:hidden;">
        <!-- Loading Screen -->
      
        <div data-u="slides" style="float:left;cursor:default;position:relative;top:0px;left:0px;width:800px;height:550px;overflow:hidden;">
            <div>
                <img data-u="image" src="./resources/img/016.PNG" />
            </div>
          
            <div>
                <img data-u="image" src="./resources/img/015.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/018.PNG" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/019.PNG" />
            </div>
         
            <a data-u="any" href="https://www.jssor.com" style="display:none">html carousel</a>
        </div>
        <!-- Bullet Navigator -->
        <div data-u="navigator" class="jssorb052" style="position:absolute;bottom:12px;right:12px;" data-autocenter="1" data-scale="0.5" data-scale-bottom="0.75">
            <div data-u="prototype" class="i" style="width:16px;height:16px;">
                <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
                    <circle class="b" cx="8000" cy="8000" r="5800"></circle>
                </svg>
            </div>
        </div>
        <!-- Arrow Navigator -->
        <div data-u="arrowleft" class="jssora053" style="width:55px;height:55px;top:0px;left:25px;" data-autocenter="2" data-scale="0.75" data-scale-left="0.75">
            <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
                <polyline class="a" points="11040,1920 4960,8000 11040,14080 "></polyline>
            </svg>
        </div>
        <div data-u="arrowright" class="jssora053" style="width:55px;height:55px;top:0px;right:25px;" data-autocenter="2" data-scale="0.75" data-scale-right="0.75">
            <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
                <polyline class="a" points="4960,1920 11040,8000 4960,14080 "></polyline>
            </svg>
        </div>
    </div>
    <script type="text/javascript">jssor_1_slider_init();</script>
    </div>  
<%-- 	 <div id="jssor_1" style="position:relative;margin:0 auto;top:0px;left:0px;width:900px;height:320px;overflow:hidden;visibility:hidden;">
        <!-- Loading Screen -->
      
        <div data-u="slides" style="float:left;cursor:default;position:relative;top:0px;left:0px;width:600px;height:320px;overflow:hidden;">
            <div>
                <img data-u="image" src="./resources/img/013.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/014.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/015.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/017.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/019.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/020.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/021.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/022.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/025.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/018.jpg" />
            </div>
            <a data-u="any" href="https://www.jssor.com" style="display:none">html carousel</a>
        </div>
        <!-- Bullet Navigator -->
        <div data-u="navigator" class="jssorb052" style="position:absolute;bottom:12px;right:12px;" data-autocenter="1" data-scale="0.5" data-scale-bottom="0.75">
            <div data-u="prototype" class="i" style="width:16px;height:16px;">
                <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
                    <circle class="b" cx="8000" cy="8000" r="5800"></circle>
                </svg>
            </div>
        </div>
        <!-- Arrow Navigator -->
        <div data-u="arrowleft" class="jssora053" style="width:55px;height:55px;top:0px;left:25px;" data-autocenter="2" data-scale="0.75" data-scale-left="0.75">
            <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
                <polyline class="a" points="11040,1920 4960,8000 11040,14080 "></polyline>
            </svg>
        </div>
        <div data-u="arrowright" class="jssora053" style="width:55px;height:55px;top:0px;right:25px;" data-autocenter="2" data-scale="0.75" data-scale-right="0.75">
            <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
                <polyline class="a" points="4960,1920 11040,8000 4960,14080 "></polyline>
            </svg>
        </div>
    </div>
    <script type="text/javascript">jssor_1_slider_init();</script> --%>
	<div class="main_issueBox"><h3 style="font-family : '나눔바른펜';">hot Issue</h3><ul>
		<c:if test="${mainIssue != null }">
			<c:forEach items="${mainIssue}" var="issue" varStatus="status">
				<c:if test="${status.index == 0}">
					<li style="top: 0">
				</c:if>
				<c:if test="${status.index != 0}">
					<li style="display:none">
				</c:if>
						<div class="mainissue_border">
							<div class="mainissue_picture">
								<img src="./resources/img/프로필.PNG">
							</div>
							<div class="mainissue_profile">
								<span id="issue_receiverName">${issue.member_nickname}</span>
								<button data-chatBtn="${issue.issue_code}" class="btn btn-default requestChat">1:1대화신청</button>
								<br>
								<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
									value="${issue.post_time}" />
								<br>
							</div>
						    <div class="mainissue_content">
								<h3>${issue.issue_content}
								<br>
								<c:if test="${issue.issue_img != ''}">
									<img src="./resources/img/${issue.issue_img}">
								</c:if>
								<br>
								</h3>
								<h5>*${issue.address}<br> 
									*주변 ${issue.radius}m로 보낸 이슈 <br>
									*${issue.hashtag}<br>
								</h5>
							</div>
						</div>
					</li>
			</c:forEach>
		</c:if>
	</ul></div>


	
	
	
	<div class="bot">
		<div class="imsi">
			<div class="imsi_1">
				<img src="./resources/img/mainplace.jpg" class="imsi_img">
			</div>
			<div class="imsi_1"></div>
		
			<div class="imsi_1">	
				<img src="./resources/img/mainissue.PNG" class="imsi_img">
			</div>
		
		</div>
		
		<div id="main_product">
			<div class="main_product_title">인기 제품</div>
			<c:if test="${Place!= null }">
				<c:forEach items="${Place}" var="list">
					<a href="placeHome?member_code=${list.member_code}&member_email=${list.member_email}">
						<div id="place_box">
							<div id="place_info">
								<h3><b>플레이스명: ${list.place_name}</b></h3><br>
							</div>
							<div id="place_img">
								<img src="./resources/img/${list.place_logo}"><br>
							</div>
						</div>
					</a>
				</c:forEach>
			</c:if>
			
			<c:if test="${BigProduct!= null }">
				<c:forEach items="${BigProduct}" var="p">
					<div id="lodging_box">
						<div id="lodging_img">
							<img src="./resources/img/${p.product_img}">
						</div>
						<div id="lodging_info">
							<div>
								<h3>
									<b>${p.product_name}</b>
								</h3>
							</div>
							<div>${p.product_explanation}</div>
							<div>${p.product_price}원</div>
	
						</div>
					</div>
				</c:forEach>
			</c:if>
	
			<c:forEach items="${Product}" var="p">
				<div class="product_box" data="${p.product_code}">
					<c:if test="${p.product_img != ''}">
						<div class="product_img">
							<img src="./resources/img/${p.product_img}">
						</div>
					</c:if>
					<c:if test="${p.product_img == ''}">
						<div class="product_img">
							<img src="./resources/img/noImage2.png">
						</div>
					</c:if>
					<div class="product_info">
						<div>
							<h3>
								<b>${p.product_name}</b>
							</h3>
						</div>
						<div>${p.product_explanation}</div> 
						<div><b>${p.product_price} 원</b></div>
					</div>
				</div>
			</c:forEach>
	
		</div>
		<div class="float_clear"></div>
		
		<div class="sitemap">
		
		</div>
	</div>
</div>