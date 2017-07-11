<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>





				<c:forEach items="${cartlist}" var="list">
		 	
		 			<td><img src="./resources/img/${list.product_img}"></td>
		 			<td>${list.product_name}</td>
		 			<td>${list.detail_name}</td><br>
		 			
		 	
		 		
		 		</c:forEach>
		 		
		 			<c:forEach items="${tt}" var="a">
		 
			${tt.get(0)}<br>			
	${tt.get(1)}<br>
		 		
		 		</c:forEach>
		 		