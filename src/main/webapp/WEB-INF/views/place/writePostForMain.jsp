<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Summernote</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="./resources/css/summernote.css" rel="stylesheet">
<script src="./resources/js/summernote.js"></script>
<script src="./resources/lang/summernote-ko-KR.js"></script>

</head>
<body>
  <script>
  $(document).ready(function() {
      $('#content').summernote({
         height : 300, // 에디터의 높이    
         minHeight : null,
         maxHeight : null,
         focus : true,
         lang : 'ko-KR' // 기본 메뉴언어 US->KR로 변경
      });
         
   });
         
   var postForm = function() {
      var contents =  $('textarea[name="writePostForMain_content"]').text($('#content').code());
   }
</script>
  <br>
  <br>
  <br>
  <form style='text-align:center' action='writePostAsMain' method='post'>
  <div class="form-group">
         <textarea class="form-control" id="content" name="writePostForMain_content"></textarea>
  </div>
  <input type='hidden' name='board_code' value='${board_code}'>
      <input type="submit" class='btn btn-default' value="작성하기">
      <input type="button" class='btn btn-default' value="리셋"  onclick="window.history.go(0)">
      <input type="button" class='btn btn-default' value="취소하기" onclick="window.history.go(-1)">
  </form>
</body>
</html>