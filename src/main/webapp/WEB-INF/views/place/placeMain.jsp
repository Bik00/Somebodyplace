<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="placeMain">
   <img src="./resources/img/p_main0.PNG" width="100%">
   <img src="./resources/img/p_main5.PNG" width="100%">
   <img src="./resources/img/p_main4.PNG" width="100%"> 
   <button class="placeMain_addBtn btn btn-default" type="button">플레이스 생성하기</button><br>
</div>


<!-- Modal -->
<div class="modal fade" id="addPlaceModal" role="dialog">
	<div class="modal-dialog">
	
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close closePlaceModal" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">SOMEBODY 알림창</h4>
			</div>
			<div class="modal-body" id="addPlaceModalBody"></div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default closePlaceModal" data-dismiss="modal">닫기</button>
			</div>
		</div>
		
	</div>
</div>