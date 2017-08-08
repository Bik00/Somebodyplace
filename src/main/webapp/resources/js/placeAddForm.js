var placeAddForm_resize_img_height = 0;

$(document).ready(function() {
	$(".placeAddBtn").click(function(){
		// 선택된 메인카테고리, 세부카테고리 값
		var dcatecode = $(".dcate option:selected").val();
		var mcatecode =$(".dcate option:selected").attr("name");
		var str = "<input type='hidden' value='"+mcatecode+"' name='mcate_code'>" +
					  "<input type='hidden' value='"+dcatecode+"' name='dcate_code'>" ;
		console.log("mcate : " + mcatecode);
		console.log("dcate : " + dcatecode);
		$("form").append(str);
		
		$("form").attr("action", "create");
		$("form").attr("method", "post");
		$("form").submit();
	});
		
	$('#placeAddForm_ImgUpload').change(function(event) { // file양식으로 이미지를 선택되었을 때// 처리
		
		var fileValue = $("#placeAddForm_ImgUpload").val().split("\\");
		var fileName = fileValue[fileValue.length - 1]; // 파일명
		if($(".jcrop-holder").text().length != 0) {
			var hk = "<div id='preview-pane'><div class='preview-container'></div></div>";
			$(".bodyPlaceAddFormModal").append(hk);
		}
		readPlaceLogoURL(this);
		
		$("#placeAddFormModal").modal();
		setTimeout(function(){ // 잘라내기 이벤트 관련
			var jcrop_api;
	        var $preview = $('#preview-pane'),
	        $pcnt = $('#preview-pane .preview-container'),
	        $pimg = $('#preview-pane .preview-container img'),

	        xsize = $pcnt.width(),
	        ysize = $pcnt.height();
			$(document).find(".placeAddForm_resize_img").Jcrop({
			      onChange: updatePreview,
			      onSelect: updatePreview,
			      aspectRatio: xsize / ysize
			    },function(){
			      // Use the API to get the real image size
			      var bounds = this.getBounds();
			      boundx = bounds[0];
			      boundy = bounds[1];
			      // Store the API in the jcrop_api variable
			      jcrop_api = this;

			      // Move the preview into the jcrop container for css positioning
			      $preview.appendTo(jcrop_api.ui.holder);
			    });
			
			function updatePreview(c)
			{
			    $('#x1').val(c.x);
			    $('#y1').val(c.y);
			    $('#x2').val(c.x2);
			    $('#y2').val(c.y2);
			    $('#w').val(c.w);
			    $('#h').val(c.h);
			    
			  if (parseInt(c.w) > 0)
			  {
			    var rx = xsize / c.w;
			    var ry = ysize / c.h;

			    $pimg.css({
			      width: Math.round(rx * boundx) + 'px',
			      height: Math.round(ry * boundy) + 'px',
			      marginLeft: '-' + Math.round(rx * c.x) + 'px',
			      marginTop: '-' + Math.round(ry * c.y) + 'px'
			    });
			  }
			};
		
		$(".bodyPlaceAddFormModal").css("height", placeAddForm_resize_img_height+200);
		},150);
	});
	
	$(".confrimPlaceAddFormModal").click(function() {
		var x = new Array();
		var y = $('#placeAddForm_ImgUpload').val();
		x = y.split("\\");
		var qx = y.substring(y.indexOf(x[x.length-1]), y.length-4);
		var formData = new FormData();
		formData.append("x", $('#x1').val());
		formData.append("y", $('#y1').val());
		formData.append("x2", $('#x2').val());
		formData.append("y2", $('#y2').val());
		formData.append("w", $('#w').val());
		formData.append("h", $('#h').val());
		formData.append("imageName", qx);
		formData.append("imageSrc", $("#placeAddForm_ImgUpload")[0].files[0]);
		formData.append("oriWidth", $(".placeAddForm_resize_img").width());
		formData.append("oriHeight", $(".placeAddForm_resize_img").height());
		$.ajax({
			url: "cropPlaceLogo",
	        type: "POST", 
	        data :formData,
	        processData: false,
	        contentType: false,
	        success: function (data) {
	        	var dataQuery = "";
	        	var j = data.toString();
	        	
	        	var k = j.split("\\");
	        	
	        	for(var i=3;i<k.length;i++) {
	        		dataQuery += "/"+k[i];
	        	}
	      
	        	str ="<img src='displayPlaceLogo?fileName="+dataQuery+"'/ height='"+$(".placeAddForm_fileBox label").width()+"' width='"+$(".placeAddForm_fileBox label").width()+"'>";
	        	$('.placeAddForm_img').html('');
        		$('.placeAddForm_img').append(str);
        		$("#placeAddForm_ImgUploadPath").val("displayPlaceLogo?fileName="+dataQuery);
	        }
		});
	});
});

function readPlaceLogoURL(input) {
	if(input.files && input.files[0]) {
		var reader = new FileReader(); // 파일을 읽기위한 FileReader 객체 생성
		reader.onload = function(e) { // 파일 읽기를 성공했을 때 호출되는 이벤트 핸들러
			var img = new Image(); // 이미지 태그 생성
			var imgPrev = new Image();
			img.src = e.target.result; // 이미지 태그 src 속성에 file내용을 지정
			imgPrev.src = e.target.result;

			img.onload = function() {
				if(img.width>800) {
					img.width = 800;
				}
				img.className = "placeAddForm_resize_img";
				console.log(img.height);
				placeAddForm_resize_img_height = img.height;
			};
			$('.placeAddForm_width_img').html('');
			$('.placeAddForm_width_img').append(img);
			$('.preview-container').append(imgPrev);
			
		}
		reader.readAsDataURL(input.files[0]); // file내용을 읽어 dataURL 형식의 문자열로 저장
	}
}



	
/*    function readURL(input) {
        if(input.files && input.files[0]) {
            var reader = new FileReader();  // 파일을 읽기위한 FileReader 객체 생성
            reader.onload = function(e) {   // 파일 읽기를 성공했을 때 호출되는 이벤트 핸들러
                var img = new Image();      // 이미지 태그 생성
                img.src = e.target.result;  // 이미지 태그 src 속성에 file내용을 지정
                
                img.width = $(".reserve_fileBox label").width();
                img.onload = function(){
                    console.log(img.height);
                    $('.reserve_fileBox').height(img.height);
                };
                
                $('.reserveForm_img').html('');
                $('.reserveForm_img').append(img);
            }
            reader.readAsDataURL(input.files[0]);   // file내용을 읽어 dataURL 형식의 문자열로 저장
        }
    }
    $('#reserveForm_ImgUpload').change(function(){   // file양식으로 이미지를 선택되었을 때 처리
        readURL(this);
    });*/
