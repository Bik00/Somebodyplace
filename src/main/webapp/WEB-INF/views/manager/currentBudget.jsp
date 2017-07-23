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
			<div class="budgetDate">
				<button class="btn btn-default budget_button">오늘</button>
				<button class="btn btn-default budget_button">1주일</button>
				<button class="btn btn-default budget_button">1개월</button>
				<button class="btn btn-default budget_button">3개월</button>
				<button class="btn btn-default budget_button">6개월</button>
				<form class="form-inline">
	 				<div class="form-group">
	    				<input type="date" class="form-control" id="startDate"> ~
	 				</div>
	 				<div class="form-group">
	    				<input type="date" class="form-control" id="endDate">
	  				</div>
	  				<button type="submit" class="btn btn-default budget_button">조회</button>
				</form>
			</div><br><br>
			
			<h2><b>정산 내역</b></h2><br>
			<div class="budget_content">
				<table class="table">
			 		<tr>
			 			<td><input type="checkbox"></td>
		                <td>정산 기간</td>
			 			<td>판매 수량</td>
			 			<td>판매 가격</td>
			 			<td>정산 가격</td>
			 			<td>수수료</td>
			 			<td>상태</td>
			 		</tr>
			 		
			 		
			 		<c:forEach items="${budget_info}" var="budget">
	 			 		<tr>
				 			<td><input type="checkbox"></td>
			                <td>${budget.budget_period}</td>
				 			<td>${budget.budget_count}</td>
				 			<td>${budget.budget_totalprice} 원</td>
				 			<td class="currentBudget_addTotal">${budget.budget_possibleprice} 원</td>
				 			<td>${budget.budget_charge} 원</td>
				 			<td>
				 				<button type="submit" class="btn btn-success budget_possible" id="budget_possible">정산 하기</button>
				 				<input type="hidden" value="${budget.budget_month}">
				 			</td>
				 		</tr>			 		
			 		</c:forEach>
			 		
			 		<c:forEach items="${budget_info_impo}" var="budget">
	 			 		<tr>
				 			<td><input type="checkbox"></td>
			                <td>${budget.budget_period}</td>
				 			<td>${budget.budget_count}</td>
				 			<td>${budget.budget_totalprice} 원</td>
				 			<td>${budget.budget_possibleprice} 원</td>
				 			<td>${budget.budget_charge} 원</td>
				 			<td>
								<button type="submit" class="btn btn-danger budget_impossible" id="budget_impossible">정산 완료</button>
				 				<input type="hidden" value="${budget.budget_month}">
				 			</td>	
				 		</tr>			 		
			 		</c:forEach>			 		
		
			 	</table><br>
			 	<div class="budget_amount"></div>
			 	<br>
			 	<br>
		 	</div>

		 	<div class="budget_option">
				<button class="btn btn-default budget_button">모든 기간 정산하기</button>
				<button class="btn btn-default budget_button">선택한 기간만 정산하기</button>
				<button class="btn btn-default budget_button">돌아가기</button>
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div class="modal fade" id="currentBudgetModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeCurrentBudgetModal"
						data-dismiss="modal">&times;</button>
					<h4 class="modal-title">SOMEBODY 알림창</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default confirmCurrentBudgetModal">확인</button>
					<button type="button" class="btn btn-default closeCurrentBudgetModal" id="dd"
						data-dismiss="modal">취소</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>