<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form>
 <div class="post_register">
     <button class="btn btn-default post_register_btn">등록하기</button>
     <button class="btn btn-default">취소하기</button>
 </div>
 <input type="hidden" name="place_code" value="${placeCode}">
 <input type="hidden" name="hashtag">
 <div class="postRegisterBox">
  <div class="postRegisterForm form-group">
      <div class="postRegisterForm_delBtn">X</div>
      <div class="postRegisterForm_input">
          <label>유형 선택 (다중선택가능)</label>
          <input type="checkbox" checked value="판매">판매
          <input type="checkbox" value="배달">배달
          <input type="checkbox" value="예약">예약
          <div class="">
              메인 카테고리
              <select class="mcate">
                  <c:forEach items="${McateList}" var="m">
                 	<c:if test="${m.mcate_code == 1}">
                 		<option value="${m.mcate_code}" selected>${m.mcate_name}</option>
                 	</c:if>
                 	<c:if test="${m.mcate_code != 1 }">
                 		<option value="${m.mcate_code}">${m.mcate_name}</option>
                 	</c:if>
                 </c:forEach>
             </select>
             세부 카테고리
             <select class="dcate">
                 <c:forEach items="${DcateList}" var="d">
                 	<c:if test="${d.mcate_code == 1}"> 
                 		<c:if test="${d.dcate_code == 1 }">
                 			<option value="${d.dcate_code}" name="${d.mcate_code}" selected>${d.dcate_name}</option>
                 		</c:if>
                 		<c:if test="${d.dcate_code != 1 }">
                 			<option value="${d.dcate_code}" name="${d.mcate_code}">${d.dcate_name}</option>
                 		</c:if>
                 	</c:if>
                 </c:forEach>
                 <c:forEach items="${DcateList}" var="d">
                 	<input type="hidden" class="dcateList" name="${d.dcate_name}" value="${d.mcate_code}" data="${d.dcate_code}">
                 </c:forEach>
            </select>
               게시판
            <select class="bcate">
                <c:forEach items="#{BoardList}" var="b">
                	<c:if test="${b.board_code == minBoardCode}">
                		<option value="${b.board_code}" selected>${b.board_name}</option>
                	</c:if>
                	<c:if test="${b.board_code != minBoardCode}">
                		<option value="${b.board_code}">${b.board_name}</option>
                	</c:if>
                </c:forEach>
            </select>
            </div>
            <div>
                해시태그
                <div class="post_hashBox"></div>
                <input type="text" class="post_hash form-control" placeholder="해시태그 입력 후 스페이스바 또는 엔터를 치세요">
            </div>
            <button class="btn btn-default RegisterBoxBtn">게시글 등록하기</button>
        </div>
    </div>
</div>
   
   <div class="postForm">
       <div class="post_fileBox">
           <label for="postForm_ImgUpload" class="postForm_img">
               <p>이곳을 클릭한 후 이미지를 등록하세요</p>
           </label>
           <input type=file id="postForm_ImgUpload">
           <input type="hidden" id="postForm_ImgUploadPath" name="product_img">
       </div>
       
       <div class="postForm_type">
           <input type="text" class="postForm_title form-control" name="product_name" placeholder="상품명을 입력하세요 혜수 지분 90%" value="왕꼬지">
           <textarea name="product_explanation" class="postForm_description form-control" placeholder="상품 설명을 입력하세요" value="">맛있어요!</textarea>
           
           <div class="postForm_price">
               가격
               <span class="form-inline"><input type="number" class="form-control" name="product_price" value=1500 step="10" >원</span>
           </div>
           <div class="postForm_options"></div>
           <div class="postForm_optionBtn"><p>추가옵션을 등록하세요</p></div>
           <div class="postForm_quantity form-inline">
               수량 
               <input type="number" class="form-control" min="1" value="1" style="float:right">
         </div>
         <div class="postForm_btns">
             <button class="btn btn-default postForm_cart">장바구니 담기</button>
             <button class="btn btn-default postForm_request">신청하기</button>
         </div>
     </div>
     
 </div>

 <div class="bulletinForm_editor">
     <h3>상세 입력 창</h3>
     
     <ul class="drag_box">
         <li class="drag_txtbox">
             <span class="txtbox_plus"><p>+</p></span>
             <textarea class="autosize" name="content" placeholder="내용을 입력하세요." >순한맛, 보통맛, 매운맛  선택 가능합니다!</textarea>
             <span class="txtbox_minus"><p>-</p></span>
         </li>
         <li class="drag_imgbox">
             <span class="imgbox_plus"><p>+</p></span>
             <label for="imgbox_upload" class="imgbox_label">
                 <p>이곳을 클릭한 후 이미지를 등록하세요</p>
             </label>
             <span class="imgbox_minus"><p>-</p></span>
             <input type=file id="imgbox_upload" class="imgbox_img" name="content">
         </li>
     </ul>
 </div>
</form>

	<!-- Modal -->
	<div class="modal fade" id="postFormModal" role="dialog" ng-show="enableCrop">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close closePostFormModal" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">SOMEBODY 알림창</h4>
				</div>
				<div class="modal-body bodyPostFormModal">
					<div class="postForm_width_img">
						<img id="postForm_resize_img" src='./resources/img/product1.jpg'>
					</div>
<!-- 					<p id="preview_title">로고 화면 : </p> -->
<!-- 					<div id="preview-pane">
	   					<div class="preview-container">
	   					</div>
	 					</div> -->
	 					<label class="hideMyGeo">X1 <input type="text" size="4" id="x1" name="x1" /></label>
					    <label class="hideMyGeo">Y1 <input type="text" size="4" id="y1" name="y1" /></label>
					    <label class="hideMyGeo">X2 <input type="text" size="4" id="x2" name="x2" /></label>
					    <label class="hideMyGeo">Y2 <input type="text" size="4" id="y2" name="y2" /></label>
					    <label class="hideMyGeo">W <input type="text" size="4" id="w" name="w" /></label>
					    <label class="hideMyGeo">H <input type="text" size="4" id="h" name="h" /></label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default confrimPostFormModal" data-dismiss="modal">결정하기</button>
					<button type="button" class="btn btn-default closePostFormModal" data-dismiss="modal">취소하기</button>
				</div>
			</div>
		</div>
	</div>