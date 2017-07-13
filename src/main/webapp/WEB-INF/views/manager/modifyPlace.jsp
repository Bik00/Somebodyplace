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
<form class="modifyPlace" action="modifyPlace" method="post">
	<input type="text" id="modifyPlace_myMain_cate" value="${place_mcate}" style="display:none">
	<input type="text" id="modifyPlace_myDetail_cate" value="${place_dcate}" style="display:none">
	<h2><b>플레이스 수정</b></h2><br>
	
	<div class="modifyPlace_fileBox">
		<label for="modifyPlace_ImgUpload" class="modifyPlace_img">
			<c:if test="${empty place_logo}">
				<p>클릭 후<br>로고 수정</p>
			</c:if>
			<c:if test="${!empty place_logo }">
				<img src="./resources/img/${place_logo}" style="width:100px; height:100px;">
			</c:if>
		</label> <input type=file id="modifyPlace_ImgUpload" name="modifyPlace_ImgUpload">
		<input type='text' id="modifyPlace_place_logo" style='display:none' name="place_logo" value="${place_logo}">
	</div>

		<div class="form-group modifyPlace_title">
			<label>플레이스명</label><input type="text" class="form-control" value="${place_name}" name="place_name"/><br>
		</div>
			<label>카테고리</label><br>
                <select class="form-control" id="modifyPlace_main_cate" name="main_cate">
                    <option>배달</option>
                    <option>쇼핑</option>
                    <option>여가</option>
                    <option>뷰티</option>
                </select><br>
                <select class="form-control" id="modifyPlace_detail_cate" name="detail_cate">
                </select><br>

        <input type="submit" class="btn btn-default addBtn" id="addBtn" value="수정하기" />
        <a href="place?member_code=${member_code}&member_email=${member_email}">
        	<input type="button" class="btn btn-default cancelBtn" id="cancelBtn" value="취소하기" />
        </a>
    </form>

</body>
</html>