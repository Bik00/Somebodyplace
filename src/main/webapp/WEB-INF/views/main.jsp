<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="main_content">
    <div id="slider" class="main_slide">
         <div id="jssor_1" style="position:relative;margin:0 auto;top:0px;left:0px;width:1300px;height:400px;overflow:hidden;visibility:hidden;">
        <!-- Loading Screen -->
      
        <div data-u="slides" style="float:left;cursor:default;position:relative;top:0px;left:0px;width:1300px;height:400px;overflow:hidden;">
            <div>
                <img data-u="image" src="./resources/img/slideTest_01.jpg" />
            </div>
          
            <div>
                <img data-u="image" src="./resources/img/slideTest_02.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/slideTest_03.jpg" />
            </div>
            <div>
                <img data-u="image" src="./resources/img/slideTest_05.jpg" />
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
	<div class="getBestItem">
		<div class="BestItemList">
			<div class="BestItem" id="main_newItem">
				<img src="./resources/img/main_newItem.png" id="newItem_Mark" class="BestItemList_Mark">
				<div class="BestItem_Title"><p>초코 머핀</p></div>
				<img src="./resources/img/product1.jpg" class="BestItem_img">
				<div class="BestItem_Info">
					<img src="./resources/img/bestItem_radius.png" style="width:20px; height:20px; display:inline-block;"><p style="display:inline-block; margin-left:10px;">1.4km</p><p style="text-align:center; margin-top: -35px;">16000 원</p>
				</div>
			</div>
			<div class="BestItem" id="main_bestItem">
				<img src="./resources/img/main_bestItem.png" id="bestItem_Mark" class="BestItemList_Mark">
				<div class="BestItem_Title"><p>크림 케잌</p></div>
				<img src="./resources/img/product2.jpg" class="BestItem_img">
				<div class="BestItem_Info">
					<img src="./resources/img/bestItem_radius.png" style="width:20px; height:20px; display:inline-block;"><p style="display:inline-block; margin-left:10px;">1.2km</p><p style="text-align:center; margin-top: -35px;">23000 원</p>
				</div>
			</div>
		
			<c:forEach items="${random_item}" var="random_item">
				<div class="BestItem" id="main_recommendItem">	
					<img src="./resources/img/main_recommendItem.png" id="recommendItem_Mark" class="BestItemList_Mark">
					<div class="BestItem_Title"><p>${random_item.product_name}</p></div>
					<img src="${random_item.product_img}" class="BestItem_img">
					<div class="BestItem_Info">
						<img src="./resources/img/bestItem_radius.png" style="width:20px; height:20px; display:inline-block;"><p style="display:inline-block; margin-left:10px;">${random_item.distance}km</p><p style="text-align:center; margin-top: -35px;">${random_item.product_price} 원</p>
					</div>
				</div>
			</c:forEach>
		</div>

		<div id="main_product">
			<div class="main_product_title">인기 제품
				<button class="btn btn-default" id="watchAllItems">전체상품보기</button>
			
			</div>
			<div class="fadeEffectItems">
			<c:if test="${Place!= null }">
				<c:forEach items="${Place}" var="list">
					<a href="placeHome?member_code=${list.member_code}&member_email=${list.member_email}">
						<div id="place_box">
							<div id="place_info">
								<h3><b>플레이스명: ${list.place_name}</b></h3><br>
							</div>
							<div id="place_img">
								<img src="${list.place_logo}"><br>
							</div>
						</div>
					</a>
				</c:forEach>
			</c:if>
			
			<c:if test="${BigProduct!= null }">
				<c:forEach items="${BigProduct}" var="p">
					<div id="lodging_box">
						<div id="lodging_img">
							<img src="${p.product_img}">
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
							<img src="${p.product_img}">
						</div>
					</c:if>
					<c:if test="${p.product_img == ''}">
						<div class="product_img">
							<img src="./resources/img/noImage2.png">
						</div>
					</c:if>
					<br>
					<div class="product_info">
					
						<div>
						<input type="button" value="${p.type}" class="btn btn-default type" name="type"> 
							<h4><b>${p.product_name}</b></h4>
								
							
						</div>
						<div>${p.product_explanation}</div> 
						<div><b>${p.product_price} 원</b></div><br>
					<%-- 	   <b>${p.type}</b> --%>
    					
					</div>
						
				</div>
			</c:forEach>
		</div>
		</div>
		
		
		
		
	<div class="float_clear"></div>
	<h3 style="text-align: left;" id="sitemap">사이트맵</h3>
		<div class="sitemap">
			<table class=sitemap_table>
				<tr>
					<td class="sitemap_table_title">상품보기</td>
					<td class="sitemap_table_title">회원 서비스</td>
					<td class="sitemap_table_title">플레이스</td>
					<td class="sitemap_table_title">이슈</td>
				</tr>
				<tr>		
					<td>* 배달</td>
					<td>&nbsp;- 로그인</td>
					<td>&nbsp;- 플레이스 소개</td>
					<td>&nbsp;- 이슈 페이지</td>
				</tr>
				<tr>		
					<td>&nbsp;- 음식</td>
					<td>&nbsp;- 회원가입</td>
					<td>&nbsp;- 플레이스 생성</td>
					<td>&nbsp;- 이슈 쓰기</td>
				</tr>
				<tr>		
					<td>&nbsp;- 꽃</td>
					<td></td>
					<td>&nbsp;- 마이플레이스</td>
					<td>&nbsp;</td>
				</tr>
				<tr>		
					<td>&nbsp;- 세탁</td>
					<td rowspan="2" class="sitemap_table_title">마이 페이지</td>
					<td rowspan="2" class="sitemap_table_title">플레이스 관리</td>
					<td rowspan="2" class="sitemap_table_title">채팅</td>
				</tr>
				<tr>
					<td>* 쇼핑</td>
				</tr>
				<tr>		
					<td>&nbsp;- 패션</td>
					<td>&nbsp;- 회원정보 수정</td>
					<td>&nbsp;- 플레이스 수정</td>
					<td>&nbsp;- 채팅 목록</td>
				</tr>
				<tr>		
					<td>&nbsp;- 생활</td>
					<td>&nbsp;- 신청 내역</td>
					<td>&nbsp;- 신청 현황</td>
					<td>&nbsp;</td>
				</tr>
				<tr>		
					<td>&nbsp;- 디지털</td>
					<td>&nbsp;- 장바구니</td>
					<td>&nbsp;- 사업자 등록</td>
					<td>&nbsp;</td>
				</tr>
				<tr>		
					<td>* 여가</td>
					<td>&nbsp;- 위시리스트</td>
					<td>&nbsp;- 카테고리 관리</td>
					<td>&nbsp;</td>
				</tr>
				<tr>		
					<td>&nbsp;- 공연</td>
					<td>&nbsp;- 즐겨찾기</td>
					<td>&nbsp;- 정산 관리</td>
					<td>&nbsp;</td>
				</tr>
				<tr>		
					<td>&nbsp;- 숙박</td>
					<td>&nbsp;- 관심사 설정</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>		
					<td>* 뷰티</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>		
					<td>&nbsp;- 미용실</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>		
					<td>&nbsp;- 네일</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr>		
					<td>&nbsp;- 화장품</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
			</table>

		</div>
	</div>
</div>
<div class="watchOut">

</div>