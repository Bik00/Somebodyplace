var modifyPlace_resize_img_height = "";

$(document).ready(function() {
	
	var myMainCate = $("#modifyPlace_myMain_cate").val();
	document.getElementById("modifyPlace_main_cate").selectedIndex = myMainCate-1;
	changeDetailCate(myMainCate);
	var myDetailCate = $("#modifyPlace_myDetail_cate").val();	
	$("#modifyPlace_detail_cate").val(myDetailCate).prop("selected", true);

	$("#modifyPlace_main_cate").change(function() {
		var myMainCate = document.getElementById("modifyPlace_main_cate").selectedIndex+1;
		changeDetailCate(myMainCate);
	});
	
	$("#modifyPlace_ImgUpload").change(function() { // file양식으로 이미지를 선택되었을 때// 처리
		var fileValue = $("#modifyPlace_ImgUpload").val().split("\\");
		var fileName = fileValue[fileValue.length - 1]; // 파일명
		if($(".jcrop-holder").text().length != 0) {
			var hk = "<div id='preview-pane'><div class='preview-container'></div></div>";
			$(".bodyModifyPlaceModal").append(hk);
		}

/*		$("#modifyPlace_place_logo").val(fileName);*/
		readURL2(this);
		
		$("#modifyPlaceModal").modal();
		setTimeout(function(){ // 잘라내기 이벤트 관련
			var jcrop_api;
	        var $preview = $('#preview-pane'),
	        $pcnt = $('#preview-pane .preview-container'),
	        $pimg = $('#preview-pane .preview-container img'),

	        xsize = $pcnt.width(),
	        ysize = $pcnt.height();
			$(document).find(".modifyPlace_resize_img").Jcrop({
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
		
		$(".bodyModifyPlaceModal").css("height", modifyPlace_resize_img_height+200);
		},150);
	});

	$(".confrimModifyPlaceModal").click(function() {
		var x = new Array();
		var y = $('#modifyPlace_ImgUpload').val();
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
		formData.append("imageSrc", $("#modifyPlace_ImgUpload")[0].files[0]);
		formData.append("oriWidth", $(".modifyPlace_resize_img").width());
		formData.append("oriHeight", $(".modifyPlace_resize_img").height());
		$.ajax({
			url: "modifyPlaceLogo",
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
	      
	        	str ="<img src='displayPlaceLogo?fileName="+dataQuery+"'/ height='"+$(".modifyPlace_fileBox label").width()+"' width='"+$(".modifyPlace_fileBox label").width()+"'>";
	        	$('.modifyPlace_img').html('');
        		$('.modifyPlace_img').append(str);
        		$("#modifyPlace_place_logo").val("displayPlaceLogo?fileName="+dataQuery);
	        }
		});
	});
});

function readURL2(input) {
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
				img.className = "modifyPlace_resize_img";
				console.log(img.height);
				modifyPlace_resize_img_height = img.height;
			};
			$('.modifyPlace_width_img').html('');
			$('.modifyPlace_width_img').append(img);
			$('.preview-container').append(imgPrev);
			
		}
		reader.readAsDataURL(input.files[0]); // file내용을 읽어 dataURL 형식의 문자열로 저장
	}
}

function changeDetailCate(myMainCate) {
	switch(parseInt(myMainCate)) {
		case 1:
			$("#modifyPlace_detail_cate").empty();
			$("#modifyPlace_detail_cate").append("<option value='1'>음식</option>");
			$("#modifyPlace_detail_cate").append("<option value='2'>꽃</option>");
			$("#modifyPlace_detail_cate").append("<option value='3'>세탁</option>");
			break;
		case 2:
			$("#modifyPlace_detail_cate").empty();
			$("#modifyPlace_detail_cate").append("<option value='4'>패션</option>");
			$("#modifyPlace_detail_cate").append("<option value='5'>생활</option>");
			$("#modifyPlace_detail_cate").append("<option value='6'>디지털</option>");
			break;
		case 3:
			$("#modifyPlace_detail_cate").empty();
			$("#modifyPlace_detail_cate").append("<option value='7'>공연</option>");
			$("#modifyPlace_detail_cate").append("<option value='8'>숙박</option>");
			break;
		case 4:
			$("#modifyPlace_detail_cate").empty();
			$("#modifyPlace_detail_cate").append("<option value='9'>미용실</option>");
			$("#modifyPlace_detail_cate").append("<option value='10'>네일</option>");
			$("#modifyPlace_detail_cate").append("<option value='11'>화장품</option>");
			break;
	}
}