<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<title>통장 페이지</title>
<style>
    body { 
       background-color: lightyellow;
    }
    .bank_profile{
        text-align: center;
        width: 80%;
        margin-top:3%;
        
    }
    .bank_profileImg{
       width: 50%;
         display: inline;
    }
    .bank_profileImg img {
        float: left;
        width: 200px;
        height: 200px;
    }
    .bank_content{
        width: 90%;
        font-size: 12px;
        text-align: left;
        margin: 0 auto;

        display: inline-block;
       
        margin-left: 5%;
        height: 500px;
        
    }
    .bank_profileTable{
        width: 50%;
        margin: 0 auto;
        float: left;
        display: inline;
        margin-left: 25%;
        

    }
    .bank_contentTable{
        font-size: 12px;
        text-align: center;
        border: solid 2px #8FBD24;
    }
    .bank_title{
        color: green;
        font-size: 20px;
        text-align: center;
    }
    .bank_tableTitle{
        text-align: center;
    }
    .bank_commission{
        float: left;
    }
    .bank_calculate{
        float: right;
    }
    .bank_innerBox{
        
    }
    .somebody_title{
        font-size: 20px;
        color: gray;
    }
</style>
</head>
<body style="background-color: lightyellow;">
<div class="bank_profile">
   <table class="table bank_profileTable">
      <tr>
         <td style="float: right;"> ${member_nickname}</td>
         <td> 님 </td>
      </tr>
      <tr>
         <td> 거래예금</td>
            <td> 자립예탁금</td>      
      </tr>
      <tr>
         <td> 계좌번호</td>
            <td> 012345-67-8910112</td>
      </tr>
        <tr>
         <td> 상점생성일</td>
            <td> 20170801</td>
      </tr>
        <tr>
         <td> 통장발행일</td>
            <td> 20170801</td>
      </tr>
   </table>
    <div class="bank_profileImg">
        <img src="profile.png">
    </div>
    
     <br><br><br>
</div>
 <!--<span class="somebody_title"><strong>썸바디 플레이스</strong></span>-->

<div class="bank_content">
    <div class="bank_tableTitle">
        <br><br><br>
        <span class="bank_title"><strong>거래내역 확인서</strong></span>
    </div>
    계좌번호: 123456-78-****** 거래일: 2017.08.01
   <table class="table bank_contentTable">
      <tr>
         <td> 거래 날짜 </td>
         <td> 거래 금액 </td>
         <td> 거래 내용 </td>
      </tr>
      <tr>
         <td> 20170801</td>
            <td> 5,000</td>
            <td> 푸드트럭 왕꼬지</td>
         
      </tr>
      <tr>
         <td> 20170802</td>
            <td> 18,000</td>
            <td> 뼈없는 닭발</td>
      </tr>
        <tr>
         <td> 20170803</td>
            <td> 3,000</td>
            <td> 참치마요주먹밥</td>
      </tr>
        <tr>
         <td> 20170804</td>
            <td> 10,000</td>
            <td> 염통꼬지 모둠</td>
      </tr>
   </table>
    <span class="bank_commission">수수료: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;원</span>
    <span class="bank_calculate">정산금액: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;원</span>
</div>

</body>
</html>