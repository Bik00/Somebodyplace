<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br>
<br>
<h2 class="favorites_title"><b>즐겨찾기 목록</b></h2>
<br>
<br>
<table class="favorites_table table">
	<tr>
		<td><h4><b>스토어명</b></h4></td>
		<td><h4><b>메인 카테고리</b></h4></td>
		<td><h4><b>세부 카테고리</b></h4></td>
		<td><h4><b>닉네임</b></h4></td>
		<td><h4><b>플레이스 이동</b></h4></td>
		<td><h4><b>즐겨찾기 삭제</b></h4></td>
	</tr>
	
	<c:forEach items="${favorite_info}" var="favorite">
			
		<tr>
		
			<td>${favorite.place_name}<img src="./resources/img/${favorite.place_logo}" style="width:40px; height:40px;" class="favorites_place_logo"></td>
			<td style="line-height:2.5em;">${favorite.mcate_name}</td>
			<td style="line-height:2.5em;">${favorite.dcate_name}</td>
			<td style="line-height:2.5em;">${favorite.member_nickname}</td>
			<td>
				<a href="/somebodyplace/${favorite.member_email}"><button class="favorites_btn btn btn-default" type="button">이동</button></a>
			</td>
			<td>
				<input type="hidden" value="${favorite.place_code}" class="favorite_input">
				<input type="hidden" value="${member_code}" class="favorite_membercode">
				<button class="favorites_btn btn btn-default" type="button">삭제하기</button>
			</td>
		</tr>
	</c:forEach>
</table>
<br>
