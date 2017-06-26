fn_rollToEx();


function fn_rollToEx(){

      // 롤링할 객체를 변수에 담아둔다.
   
      var lastChild;
      var speed = 3000;
      var timer = 0;

      $('#slide_img').data('prev', $('.slideBtn-left'));   //이전버튼을 data()메서드를 사용하여 저장한다.
      $('#slide_img').data('next', $('.slideBtn-right'));   //다음버튼을 data()메서드를 사용하여 저장한다.
      $('#slide_img').data('size', $('#slide_img').children().outerWidth());      //롤링객체의 자식요소의 넓이를 저장한다.
      $('#slide_img').data('len', $('#slide_img').children().length);            //롤링객체의 전체요소 개수
      $('#slide_img').data('animating',false);

      $('#slide_img').css('width',$('#slide_img').data('size')*$('#slide_img').data('len'));      //롤링객체의 전체넓이 지정한다.

      $(document).ready(function() {
         //$('#slide_img')에 첨부된 prev 데이타를 클릭이벤트에 바인드한다.
         $('.slideBtn-left').click(function(e){
            
               e.preventDefault();
               movePrevSlide();
            });

         //$('#slide_img')에 첨부된 next 데이타를 클릭이벤트에 바인드한다.
         $('.slideBtn-right').click(function(e){
               e.preventDefault();
               moveNextSlide();
         });
      });
      
      function movePrevSlide(){
         if(!$('#slide_img').data('animating')){
            //롤링객체의 끝에서 요소를 선택하여 복사한후 변수에 저장한다.
            var lastItem = $('#slide_img').children().eq(-2).nextAll().clone(true);
            lastItem.prependTo($('#slide_img'));      //복사된 요소를 롤링객체의 앞에 붙여놓는다.
            $('#slide_img').children().eq(-2).nextAll().remove();   //선택된 요소는 끝에서 제거한다
            $('#slide_img').css('left','-'+($('#slide_img').data('size')*1+'px'));   //롤링객체의 left위치값을 재설정한다.
         
            $('#slide_img').data('animating',true);   //애니메이션 중복을 막기 위해 첨부된 animating 데이타를 true로 설정한다.

            $('#slide_img').animate({'left': '0px'},'normal',function(){      //롤링객체를 left:0만큼 애니메이션 시킨다.
               $('#slide_img').data('animating',false);
            });
         }
         return false;
      }

      function moveNextSlide(){
         if(!$('#slide_img').data('animating')){
            $('#slide_img').data('animating',true);

            $('#slide_img').animate({'left':'-'+($('#slide_img').data('size')*1)+'px'},'normal',function(){   //롤링객체를 애니메이션 시킨다.
               //롤링객체의 앞에서 요소를 선택하여 복사한후 변수에 저장한다.
               var firstChild = $('#slide_img').children().filter(':lt('+1+')').clone(true);
               firstChild.appendTo($('#slide_img'));   //복사된 요소를 롤링객체의 끝에 붙여놓는다.
               $('#slide_img').children().filter(':lt('+1+')').remove();   //선택된 요소를 앞에서 제거한다
               $('#slide_img').css('left','0px');   ////롤링객체의 left위치값을 재설정한다.

               $('#slide_img').data('animating',false);
            });
         }
         return false;
      }
   }            
