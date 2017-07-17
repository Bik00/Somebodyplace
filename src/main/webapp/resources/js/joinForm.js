$(document).ready(function() {
		$('#joinForm_ImgUpload').change(function() { // file양식으로 이미지를 선택되었을 때// 처리
		var fileValue = $("#joinForm_ImgUpload").val().split("\\");
		var fileName = fileValue[fileValue.length - 1]; // 파일명
		readURL(this);
	});
})

function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader(); // 파일을 읽기위한 FileReader 객체 생성
		reader.onload = function(e) { // 파일 읽기를 성공했을 때 호출되는 이벤트 핸들러
			var img = new Image(); // 이미지 태그 생성
			img.src = e.target.result; // 이미지 태그 src 속성에 file내용을 지정

			img.width = $(".joinForm_fileBox label").width();
			img.onload = function() {
				console.log(img.height);
				$('.joinForm_fileBox label').height(img.height);
			};
			$('.joinForm_img').html('');
			$('.joinForm_img').append(img);
		}
		reader.readAsDataURL(input.files[0]); // file내용을 읽어 dataURL 형식의 문자열로 저장
	}
}