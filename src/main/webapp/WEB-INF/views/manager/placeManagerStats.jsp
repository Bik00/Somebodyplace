<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- 플레이스 관리 내용 -->
<c:if test="${applogin!= null }">
	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	<link rel="stylesheet"
		href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script
	
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		   <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.css">

    <script src="http://code.jquery.com/mobile/1.0/jquery.mobile-1.0.min.js"></script>
	<link rel="stylesheet" href="./resources/m_css/m_place.css?ver=3">
	<script src="./resources/js/place.js?ver=9"></script> 
	<script src="./resources/js/placeManagerStats.js?ver=9"></script> 
</c:if>
        <div class="placemanager_content">
            <!-- 정산 내역 -->
            <div class="placemanager_budget">
                <div class="placemanager_budget_title">
                    <h1><b>통계 및 정산 현황</b></h1>
                </div>
                <br>
                <div class="placemanager_budget_content">
                    <ul>
                    	<c:if test="${fn:length(budget) != 0}">
                    		<c:forEach items="${budget}" var="b" varStatus="b_status">
	                        	<li><h4>이번달 팔린 금액 - ${b.budget_totalprice}원</h4></li>
	                        	<li><h4>이번달 정산 금액 - ${b.budget_possibleprice}원</h4></li>
							</c:forEach>
						</c:if>
						<c:if test="${fn:length(budget)== 0}">
	                        <li><h4>이번달 팔린 금액 - 0 원</h4></li>
	                        <li><h4>이번달 정산 금액 - 0 원</h4></li>
						</c:if>
                    </ul>
                    <div class="placemanager_request_more">
                    	<a href="currentBudget"><button class="btn btn-default budget_more">상세 보기</button></a>
                	</div>
                </div>
            </div>

            <!-- 신청 내역 -->
            <div class="placemanager_request">
                <div class="placemanager_request_title">
                	<h1><b>신청 내역 - ${fn:length(request_list) }건</b></h1> 
                </div>
                <div class="placemanager_request_content">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                 
                                <th><h3><b>고객 이름</b></h3></th>
                                <th><h3><b>상품명</b></h3></th>
                                <th><h3><b>가격</b></h3></th>
                                <th><h3><b>타입</b></h3></th>
                                   <th><h3><b>요청사항</b></h3></th>
                            </tr>
                        </thead>
                        <tbody>
							<c:forEach items="${request_list}" var="r" varStatus="r_status">
	                            <tr>
	                        
	                                <th>${r.member_nickname}</th>
	                                <th>${r.product_name}</th>
	                                <th>${r.request_list_totalprice}</th>
	                                <th>${r.request_type}</th>
	                                 <th>${r.request_content}</th> 
	                            </tr>
							</c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="placemanager_request_more">
                    <button class="btn btn-default request_listBtn">상세 보기</button>
                </div>
            </div>

            <!-- 상품 내역 -->
            <div class="placemanager_product">
                <div class="placemanager_product_title">
                   <h1><b>상품 현황</b></h1> 
                </div>
                <div class="placemanager_product_content">
                    <ul>
                        <li><h4>등록된 상품 수 - ${productNum}개</h4></li>
                        <li><h4>등록된 후기 수 - 6개 </h4></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>