<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
    <title>Map</title>
    <style>
        html,
        body,
        #map-canvas {
            height: 100%;
            margin: 0px;
            padding: 0px
        }
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <script>
        function initialize() {
            var locations = ${requestScope.locationList};
            var myLatlng = new google.maps.LatLng(locations[0].latitude, locations[0].longitude);

            var mapOptions = {
                zoom: 14,
                center: myLatlng
            }

            var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

            var infoWindow = new google.maps.InfoWindow(),
                marker;

            <!-- Multiple marker source code is based on
            http: //wrightshq.com/playground/placing-multiple-markers-on-a-google-map-using-api-3/ -->

                var bounds = new google.maps.LatLngBounds();
            var iconBase = 'http://icons.iconarchive.com/icons/chrisbanks2/cold-fusion-hd/128/wifi-icon.png'

            for (var i = 0; i < locations.length; i++) {
                var currentLatLng = new google.maps.LatLng(locations[i].latitude, locations[i].longitude);
                var img;
                if (i == 0) img = 'user.png';
                else img = 'wifi-icon.png';
                bounds.extend(currentLatLng);
                marker = new google.maps.Marker({
                    position: currentLatLng,
                    title: locations[i].name,
                    map: map,
                    icon: {
                        size: new google.maps.Size(20, 30),
                        scaledSize: new google.maps.Size(20, 30),
                        url: '/icons/' + img
                    }
                });

                // Allow each marker to have an info window
                google.maps.event.addListener(marker, 'click', (function(marker, i) {
                    return function() {
                        infoWindow.setContent('<h5>' + locations[i].name + '<br><br>' + locations[i].address + '</h5>');
                        infoWindow.open(map, marker);
                    }
                })(marker, i));

                // Automatically center the map fitting all markers on the screen
                // map.fitBounds(bounds);
            }
        }
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>
    <div id="map-canvas"></div>
</body>
</html>