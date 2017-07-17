
$(document).ready(function() {
	getPlaceLogo();
	var myMainCate = $("#modifyPlace_myMain_cate").val();
	document.getElementById("modifyPlace_main_cate").selectedIndex = myMainCate-1;
	changeDetailCate(myMainCate);
	var myDetailCate = $("#modifyPlace_myDetail_cate").val();	
	$("#modifyPlace_detail_cate").val(myDetailCate).prop("selected", true);

	$("#modifyPlace_main_cate").change(function() {
		var myMainCate = document.getElementById("modifyPlace_main_cate").selectedIndex+1;
		changeDetailCate(myMainCate);
	});
	
	$('#modifyPlace_ImgUpload').change(function(){   // file양식으로 이미지를 선택되었을 때 처리
        
        var fileValue = $("#modifyPlace_ImgUpload").val().split("\\");
        var fileName = fileValue[fileValue.length-1]; // 파일명
        $("#modifyPlace_place_logo").val(fileName);
		readURL(this);
    });
});

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

function readURL(input) {
    if(input.files && input.files[0]) {
        var reader = new FileReader();  // 파일을 읽기위한 FileReader 객체 생성
        reader.onload = function(e) {   // 파일 읽기를 성공했을 때 호출되는 이벤트 핸들러
            var img = new Image();      // 이미지 태그 생성
            img.src = e.target.result;  // 이미지 태그 src 속성에 file내용을 지정
            
            img.width = $(".modifyPlace_fileBox label").width();
            img.onload = function(){
                console.log(img.height);
                $('.modifyPlace_fileBox label').height(img.height);
            };
            
            $('.modifyPlace_img').html('');
            $('.modifyPlace_img').append(img);
        }
        reader.readAsDataURL(input.files[0]);   // file내용을 읽어 dataURL 형식의 문자열로 저장
    }
}

function getPlaceLogo() {
	
}