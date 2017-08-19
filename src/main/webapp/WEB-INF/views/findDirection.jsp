<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="./resources/js/jquery-3.2.1.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDUxRDg1Cmj4VUiY8J5MynUUcQX-a8TzE8" type="text/javascript"></script>	
	<script>
	    var directionsDisplay;
	    var directionsService = new google.maps.DirectionsService();
	    var map;
	
	    function initialize() {
	        directionsDisplay = new google.maps.DirectionsRenderer({suppressMarkers: true});
	        var chicago = new google.maps.LatLng(41.850033, -87.6500523);
	        var mapOptions = {
	          zoom:7,
	          mapTypeId: google.maps.MapTypeId.ROADMAP,
	          center: chicago
	        }
	        map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	        directionsDisplay.setMap(map);

		var startLocation = $("#startLocation").val();
		var endLocation = $("#endLocation").val();
		var mode = "TRANSIT";
		
		var icons = {
			start : new google.maps.MarkerImage(
			// URL
			'./resources/img/chatRoute_start.png',
			// (width,height)
			new google.maps.Size(80, 80),
			// The origin point (x,y)
			new google.maps.Point(0, 0),
			// The anchor point (x,y)
			new google.maps.Point(40, 80)),
			end : new google.maps.MarkerImage(
			// URL
			'./resources/img/chatRoute_end.png',
			// (width,height)
			new google.maps.Size(80, 80),
			// The origin point (x,y)
			new google.maps.Point(0, 0),
			// The anchor point (x,y)
			new google.maps.Point(40, 80))
		};
		var request = {
			origin:startLocation,
			destination:endLocation,
			travelMode: eval("google.maps.DirectionsTravelMode."+mode)
		};
			
		directionsService.route(request, function(response, status) {
	   		if (status == google.maps.DirectionsStatus.OK) {
	   			directionsDisplay.setDirections(response);
				var leg = response.routes[ 0 ].legs[ 0 ];
					makeMarker( leg.start_location, icons.start, "현위치 : "+leg.start_address );
					makeMarker( leg.end_location, icons.end, "목적지 : "+leg.end_address );
		    		tryCenter = map.getCenter();
		    		tryZoom = map.getZoom();
	   		}
		});
	
		function makeMarker( position, icon, title ) {
			var markers = new google.maps.Marker({
				position: position,
				map: map,
				icon: icon,
				title: title
			});
	
			var data = title;
			var infowindow = new google.maps.InfoWindow({
				content: data
			});
	
			google.maps.event.addListener(markers, 'click', function() {
				infowindow.open(map,markers);
			});
			
		}
	    }
	    google.maps.event.addDomListener(window, 'load', initialize);
	</script>
 	<style type="text/css">
        html, body {
          height: 100%;
          margin: 0;
          padding: 0;
        }
 
        #map-canvas, #map_canvas {
          height: 100%;
        }
 
        @media print {
          html, body {
            height: auto;
          }
 
          #map_canvas {
            height: 650px;
          }
        }
	</style>
</head>
<body>

	<input type="hidden" value="${startLocation}" id="startLocation">
	<input type="hidden" value="${endLocation}" id="endLocation">
	<div id="map-canvas"></div>
	
</body>
</html>