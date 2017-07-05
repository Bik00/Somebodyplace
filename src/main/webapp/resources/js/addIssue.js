var issue_receiver = '';

 $(function(){
	 
	   $(document).on("click", ".inputHash", function(){
	       var index = $(this).index();
	       $('.post_hashBox').find("div").eq(index).remove();
	   });
	 
	$('.addIssue_addBtn').click(function(){
		var query = {
				lat : $('#result2').val(),
				lng : $('#result3').val(),
				radius : $('#seek').val()
		}
		 
		$.ajax({
			type : "post",
			url : "getIssueReceiver",
			data : query,
			async: false,
			success : function(data){
				for(var i=0; i<data.length;i++) {
					if(issue_receiver != '') {
						issue_receiver += ',';
					}
					issue_receiver += data[i].issue_receiver;
				}
			}
		});
		 
		 alert("보내는 놈은"+$("#code").text()+", 받는 놈은 : "+issue_receiver);
		 sendIssue();
	      
	      var s = $(".post_hashBox").contents().find('span');
	        $("input[name=hashtag]").val(s.text());
	        // post방식으로 게시글 작성
	      $("form").attr("action", "createIssue");
	      $("form").attr("method", "post");
	      $("form").submit();
	   });
	
	var sock = new SockJS("/somebodyplace/echo-ws");

	sock.onmessage = onMessage;

	sock.onclose = onClose;
	 
	function sendIssue() {
		
		var json = "{"+"\"issue_receiver\":\""+issue_receiver+"\"}";
	    // sock.send("<div class='chat mine'> "+$(".writeComment").val()+"<h6>작성자 : "+$('#chat_name').text()+" <br>작성 시간 : "+string+"</h6> </div>"); 
		sock.send(json);
	}
	
    function onMessage(evt) {
    	var data = evt.data;
    	alert(data);
       	var str=JSON.parse(data);
       	var strArray = str.issue_receiver.split(',');
       	var mine = $('#code').text();
       	for(var c = 0; c<strArray.length;c++) {
       		if(mine != strArray[c]) {
       			layer_open('layer2');
       		} else if(mine == strArray[c]) {
       			
       		}
       	}
    }
    
    function onClose(evt) {
        $("#data").append("Connection Closed!");
    }
	
    function layer_open(el){

        var temp = $('#' + el);
        var bg = temp.prev().hasClass('bg');	//dimmed 레이어를 감지하기 위한 boolean 변수

        if(bg){
            $('.layer').fadeIn();	//'bg' 클래스가 존재하면 레이어가 나타나고 배경은 dimmed 된다. 
        }else{
            temp.fadeIn();
        }

        // 화면의 중앙에 레이어를 띄운다.
        if (temp.outerHeight() < $(document).height() ) temp.css('margin-top', '-'+temp.outerHeight()/2+'px');
        else temp.css('top', '0px');
        if (temp.outerWidth() < $(document).width() ) temp.css('margin-left', '-'+temp.outerWidth()/2+'px');
        else temp.css('left', '0px');

        temp.find('a.cbtn').click(function(e){
            if(bg){
                $('.layer').fadeOut(); //'bg' 클래스가 존재하면 레이어를 사라지게 한다. 
            }else{
                temp.fadeOut();
            }
            e.preventDefault();
        });

        $('.layer .bg').click(function(e){	//배경을 클릭하면 레이어를 사라지게 하는 이벤트 핸들러
            $('.layer').fadeOut();
            e.preventDefault();
        });

    }
    
    
	
    function readURL(input) {
        if(input.files && input.files[0]) {
            var reader = new FileReader();  // 파일을 읽기위한 FileReader 객체 생성
            reader.onload = function(e) {   // 파일 읽기를 성공했을 때 호출되는 이벤트 핸들러
                var img = new Image();      // 이미지 태그 생성
                img.src = e.target.result;  // 이미지 태그 src 속성에 file내용을 지정
                
                img.width = $(".addIssue_fileBox label").width();
                img.onload = function(){
                    console.log(img.height);
                    $('.addIssue_fileBox').height(img.height);
                };
                
                $('.addIssue_img').html('');
                $('.addIssue_img').append(img);
            }
            reader.readAsDataURL(input.files[0]);   // file내용을 읽어 dataURL 형식의 문자열로 저장
        }
    }
    $('#addIssue_ImgUpload').change(function(){   // file양식으로 이미지를 선택되었을 때 처리
        readURL(this);
    });

    
    $('#addIssue_addBtn').click(function(){
    		alert("거리테스트");
    		alert($('#result2').val());
    		alert($('#result3').val());
    		alert($('.bangung').val());
    });
    
    
    $('#addBtn').click(function(){
    		alert("jstest");
    		location.href="push";
    	
    });
        
 });
 
 
 
