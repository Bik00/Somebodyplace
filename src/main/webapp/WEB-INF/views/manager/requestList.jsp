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
			<button class="btn btn-default">����</button>
			<button class="btn btn-default">1����</button>
			<button class="btn btn-default">1����</button>
			<button class="btn btn-default">3����</button>
			<button class="btn btn-default">6����</button>

				<form class="form-inline">
	 				<div class="form-group">
	    				<input type="date" class="requestForm form-control" id="startDate"> ~
	 				</div>
	 				<div class="form-group">
	    				<input type="date" class="requestForm form-control" id="endDate">
	  				</div>
	  			<button type="submit" class="btn btn-default">��ȸ</button>
	</form>
		</div><br><br>
		
			<h2><b>Request Current State</b></h2><br>
		   <table class="table">
                        <thead>
                            <tr>
                              <th><h4><b>�� ��ȣ</b></h4></th>
                                <th><h4><b>�� �̸�</b></h4></th>
                                <th><h4><b>��ǰ��</b></h4></th>
                                <th><h4><b>��ǰ����</b></h4></th>
                                <th><h4><b>����</b></h4></th>
                                <th><h4><b>Ÿ��</b></h4></th>
                                 <th><h4><b>��û��¥</b></h4></th>
                                   <th><h4><b>��û����</b></h4></th>
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
	                                <td>����</td>
	                                <td>û����</td>
	                                <td>1</td>
	                            </tr>
	                            <tr>
	                                <td>Ű��</td>
	                                <td>�尩</td>
	                                <td>3</td>
	                            </tr>
	                            <tr>
	                                <td>����</td>
	                                <td>����</td>
	                                <td>1</td>
	                            </tr> -->
							</c:forEach>
                        </tbody>
                    </table><br>
		</div>

</body>
</html>