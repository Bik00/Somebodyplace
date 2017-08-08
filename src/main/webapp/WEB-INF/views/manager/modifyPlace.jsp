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
			<c:if test="${!empty place_logo}">
				<img src="${place_logo}" style="width:100px; height:100px;">
			</c:if>
		</label> <input type="file" id="modifyPlace_ImgUpload" name="modifyPlace_ImgUpload">
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

	<!-- Modal -->
	<div class="modal fade" id="modifyPlaceModal" role="dialog" ng-show="enableCrop">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closeModifyPlaceModal" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">SOMEBODY 알림창</h4>
				</div>
				<div class="modal-body bodyModifyPlaceModal">
					<div class="modifyPlace_width_img">
						<img id="modifyPlace_resize_img" src='./resources/img/product1.jpg'>
					</div>
					<p id="preview_title">로고 화면 : </p>
					<div id="preview-pane">
	   					<div class="preview-container">
	   					</div>
	 					</div>
	 					<label class="hideMyGeo">X1 <input type="text" size="4" id="x1" name="x1" /></label>
					    <label class="hideMyGeo">Y1 <input type="text" size="4" id="y1" name="y1" /></label>
					    <label class="hideMyGeo">X2 <input type="text" size="4" id="x2" name="x2" /></label>
					    <label class="hideMyGeo">Y2 <input type="text" size="4" id="y2" name="y2" /></label>
					    <label class="hideMyGeo">W <input type="text" size="4" id="w" name="w" /></label>
					    <label class="hideMyGeo">H <input type="text" size="4" id="h" name="h" /></label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default confrimModifyPlaceModal" data-dismiss="modal">결정하기</button>
					<button type="button" class="btn btn-default closeModifyPlaceModal" data-dismiss="modal">취소하기</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>