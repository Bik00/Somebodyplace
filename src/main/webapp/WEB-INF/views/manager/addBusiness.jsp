<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<input type="hidden" value="${member_code}" class="placeManager_member_code">
   <form class="addForm" method="post">
   			<br>
            <h2><b>사업자등록번호 추가</b></h2><br>
        <div class="form-group">
               <label>사업자 등록번호 입력</label>
                <div class="form-group business_form">
                    <input type="text" class="form-control business_text" maxlength="3" placeholder="앞자리 3자리 입력" value="${place_busino0}" id="business_1" name="business_1">
                    <label class="busi">-</label>
                    <input type="text" class="form-control business_text" maxlength="2" placeholder="가운데자리 2자리 입력" value="${place_busino1}" id="business_2" name="business_2">
                    <label class="busi">-</label>
                    <input type="text" class="form-control business_text" maxlength="5" placeholder="뒷자리 5자리 입력" value="${place_busino2}" id="business_3" name="business_3">
                </div>
            <br>
        </div>
        	<p class="interest_alerm alerm_one">앞자리 3자리를 입력해주세요.</p>
         	<p class="interest_alerm alerm_two">가운데자리 2자리를 입력해주세요.</p>
         	<p class="interest_alerm alerm_three">뒷자리 5자리를 입력해주세요.</p>          
            <br>
            <br>

        <input type="button" class="btn btn-default addBusiness_addBtn"  value="등록하기" />
        <input type="button" class="btn btn-default addBusiness_delBtn" value="삭제하기" />
		<a href="place?member_code=${member_code}&member_email=${member_email}">
        	<input type="button" class="btn btn-default cancelBtn" id="cancelBtn" value="돌아가기" />
        </a>
    </form>
    
	<!-- Modal -->
	<div class="modal fade" id="delBusiModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeBusiModal" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">SOMEBODY 알림창</h4>
				</div>
				<div class="modal-body">
					<p>정말로 사업자 번호를 삭제하시겠습니까?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default confirmDelBusi" data-dismiss="modal">삭제하기</button>
					<button type="button" class="btn btn-default closeBusiModal" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>

</body>
</html>