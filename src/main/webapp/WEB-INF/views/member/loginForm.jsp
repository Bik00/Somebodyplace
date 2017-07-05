<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="loginForm">
	<h1 class="loginForm_title"><b>로그인</b></h1><br><br>
	<form class="form-horizontal" method="post" action="login" style="width: 50%; margin:auto;">
	    <div class="form-group">
	        <label class="col-sm-2 control-label loginForm_text">Email</label>
	        <div class="col-sm-10">
	        <input type="email" class="form-control" name="email" value="f@naver.com">
	        </div>
	    </div>
	    <div class="form-group">
	        <label class="col-sm-2 control-label loginForm_text">PW</label>
	        <div class="col-sm-10">
	        <input type="password" class="form-control" name="pw" value="f" >
	        </div>
	    </div>
	    <div id="btnBox" class="form-group">
	  	    <button type="submit" class="loginForm_btn btn btn-default">로그인</button>
	  	    <a href="join" class="loginForm_btn"><input type="button" class="loginForm_btn btn btn-default" value="회원가입"></input></a>
	    </div>
	</form>
</div>