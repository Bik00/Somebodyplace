<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="table-responsive">

		<div class="orderDate">
			<button class="btn btn-default">오늘</button>
			<button class="btn btn-default">1주일</button>
			<button class="btn btn-default">1개월</button>
			<button class="btn btn-default">3개월</button>
			<button class="btn btn-default">6개월</button>

				<form class="form-inline">
	 				<div class="form-group">
	    				<input type="date" class="requestForm form-control" id="startDate"> ~
	 				</div>
	 				<div class="form-group">
	    				<input type="date" class="requestForm form-control" id="endDate">
	  				</div>
	  			<button type="submit" class="btn btn-default">조회</button>
	</form>
		</div><br><br>
		
			<h2><b>Request Current State</b></h2><br>
		   <table class="table requestList">
                        <thead>
                            <tr>
                                <th><h4><b>상품 코드</b></h4></th>
                                <th><h4><b>고객 이름</b></h4></th>
                                <th><h4><b>주소</b></h4></th>
                                  <th><h4><b>폰번호</b></h4></th>
                                <th><h4><b>상품 명</b></h4></th>
                                <th><h4><b>신청 날짜</b></h4></th>
                                <th><h4><b>신청 타입</b></h4></th>
                                <th><h4><b>신청 가격</b></h4></th>
                                <th><h4><b>상태</b></h4></th>
                            </tr>
                        </thead>
                        <tbody>
							<c:forEach items="${request_list}" var="r" varStatus="r_status">
	                            <tr>
	                            	<td>${r.product_code}</td>
	                                <td>${r.member_nickname}</td>
	                                 <td>${r.request_addr}</td>
	                                  <td>${r.request_phone}</td>
	                                <td>${r.product_name}</td>
	                                <td>${r.request_date}</td>
	                                <td>${r.request_type}</td>
	                                <td>${r.request_list_totalprice}</td>
	                                <td>
		                                <c:if test="${r.request_status != '정산완료'}">
											<div class="form-group">
												<select class="selectForType selectpicker" name="selValue">
													<option value="신청됨">신청됨</option>
													<option value="준비중">준비중</option>
													<option value="완료">완료</option>
												</select>
											</div>
											<input type="hidden" class="requestList_myType" value="${r.request_status}">
											<input type="hidden" class="requestList_requestCode" value="${r.request_code}">
										</c:if>
										<c:if test="${r.request_status == '정산완료'}">
											<button type="submit" class="btn btn-danger" style="color:white !important;">정산 완료</button>
										</c:if>
									</td> 
	                            </tr>
							</c:forEach>
                        </tbody>
                    </table><br>
		</div>

</body>
</html>