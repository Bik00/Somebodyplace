<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<br>
<br>
<h2><!-- class="interest_title" --><b>관심사설정</b></h2><br>
   <div id="SettingDiv">
   <form>
		<h3><b>현재 관심사</b></h3>
		${member_interest}
		<c:if test="${empty member_interest}">
			<div class="interest_hashBox"></div>
		</c:if>
		<c:if test="${!empty member_interest}">
			<div class="interest_hashBox"></div>
		</c:if>
		<h3><b>태그</b><h3>
		<input type="text" class="tegname" id="interest_insertHash"><br><br>
	
	
	
		<input type="text" value="${member_code}" name="member_code">
		<input type="hidden" name="member_interest">
			<div class="post_hashBox"></div>
		<input type="text" class="post_hash form-control" placeholder="해시태그 입력 후 엔터를 치세요">
	</form>
	<input type="button" class="SettingFinish_btn btn btn-default" value="설정완료" style="color:white; background-color:#EF546F;">
	<a href="myPage"><input type="button" class="btn btn-default cancelBtn" id="cancelBtn" value="취소하기" /></a>
</div>