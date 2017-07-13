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
            <h2>사업자등록번호 추가</h2><br>
        <div class="form-group">
               <label>사업자 등록번호 입력</label>
                <div class="form-group business_form">
                    <input type="text" class="form-control business_text" value="${place_busino0}" id="business_1" name="business_1">
                    <label class="busi">-</label>
                    <input type="text" class="form-control business_text" value="${place_busino1}" id="business_2" name="business_2">
                    <label class="busi">-</label>
                    <input type="text" class="form-control business_text" value="${place_busino2}" id="business_3" name="business_3">
                </div>
            <br>
        </div>
           
            <br>
            <br>

        <input type="button" class="btn btn-default addBusiness_addBtn"  value="등록하기" />
		<a href="place?member_code=${member_code}&member_email=${member_email}">
        	<input type="button" class="btn btn-default cancelBtn" id="cancelBtn" value="돌아가기" />
        </a>
    </form>
</body>
</html>