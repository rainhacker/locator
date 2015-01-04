<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>WiFi Hotspot Locator</title>

    <!-- Bootstrap core CSS -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Smart device friendly display -->
    <link href="/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script>
        function submitCurrentLocationAndLocationChoice(id) {
            document.getElementById('show').value = id;
            getLocation();
        }

        function getLocation() {
            event.preventDefault();
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(setPosition, showError);
            } else {
                alert("Geolocation is not supported by this browser.");
            }
        }

        function setPosition(position) {
            var lng = position.coords.longitude,
                lat = position.coords.latitude;

            document.getElementById('curr_long').value = lng;
            document.getElementById('curr_lat').value = lat;

            document.getElementById("home").submit();
        }

        function showError(error) {
            switch (error.code) {
                case error.PERMISSION_DENIED:
                    alert("Request for geolocation permission denied.");
                    break;
                case error.POSITION_UNAVAILABLE:
                    alert("Location information is unavailable.");
                    break;
                case error.TIMEOUT:
                    alert("The request to get user location timed out.");
                    break;
                case error.UNKNOWN_ERROR:
                    alert("An unknown error occurred.");
                    break;
            }
        }
    </script>

</head>

<body>

    <form action="home" id="home" method="POST">

        <div class="container">
            <h3>Find WiFi Hotspots Near You!</h3>
            <br>
            <p><a class="btn btn-primary" onclick="submitCurrentLocationAndLocationChoice(this.id);" id="one" type="submit" role="button">Nearest One</a>&nbsp;&nbsp;&nbsp;
                <a class="btn btn-primary" onclick="submitCurrentLocationAndLocationChoice(this.id);" id="ten" type="submit" role="button">Nearest Ten</a>
            </p>
            <br><br>
                <div class="footer">
                    <nav>
                        <ul class="nav nav-pills pull-right">
                            <li ><a href="https://github.com/">GitHub</a>
                            </li>
                            <li role="presentation"><a href="https://data.cityofnewyork.us/Social-Services/NYC-Wi-Fi-Hotspot-Locations/a9we-mtpn">NYC OpenData</a>
                            </li>
                            <li role="presentation"><a href="https://www.linkedin.com/pub/parth-solanki/11/a98/6aa">About Me</a>
                            </li>
                        </ul>
                    </nav>
                    <div>
                </div>
                <!-- /container -->
                <input type="hidden" id="show" name="show">
                <input type="hidden" id="curr_long" name="curr_long">
                <input type="hidden" id="curr_lat" name="curr_lat">

    </form>

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/bootstrap/js/ie10-viewport-bug-workaround.js"></script>

</body>

</html>
</html>