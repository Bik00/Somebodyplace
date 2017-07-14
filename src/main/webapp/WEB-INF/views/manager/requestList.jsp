<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
		   <table class="table">
                        <thead>
                            <tr>
                              <th><h4><b>고객 번호</b></h4></th>
                                <th><h4><b>고객 이름</b></h4></th>
                                <th><h4><b>상품명</b></h4></th>
                                <th><h4><b>상품사진</b></h4></th>
                                <th><h4><b>가격</b></h4></th>
                                <th><h4><b>타입</b></h4></th>
                                 <th><h4><b>신청날짜</b></h4></th>
                                   <th><h4><b>요청사항</b></h4></th>
                            </tr>
                        </thead>
                        <tbody>
							<c:forEach items="${request_list}" var="r" varStatus="r_status">
	                            <tr>
	                                <th>${r.member_code}</th>
	                                <th>${r.member_nickname}</th>
	                                <th>${r.product_name}</th>
	                                 <th><img src="./resources/img/${r.product_img}"></th>
	                                <th>${r.request_list_totalprice}</th>
	                                <th>${r.request_type}</th>
	                                 <th>${r.request_date}</th>
	                                 <th>${r.request_content}</th> 
	                            </tr>
	                           <!--  <tr>
	                                <td>나비</td>
	                                <td>청바지</td>
	                                <td>1</td>
	                            </tr>
	                            <tr>
	                                <td>키위</td>
	                                <td>장갑</td>
	                                <td>3</td>
	                            </tr>
	                            <tr>
	                                <td>보리</td>
	                                <td>구두</td>
	                                <td>1</td>
	                            </tr> -->
							</c:forEach>
                        </tbody>
                    </table><br>
		</div>

</body>
</html>