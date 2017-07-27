<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
    
    
    
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
						<div><b>${p.product_price} 원</b></div><br>
					<%-- 	   <b>${p.type}</b> --%>
    <input type="button" value="${p.type}" class="type" name="type"> <h5><b>가능상품</b></h5> 
					</div>
						
				</div>
			</c:forEach>
	