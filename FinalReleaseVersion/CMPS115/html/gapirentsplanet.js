//script for google maps API calls
//initializes map, autocomplete, and callback for a successful auto-complete
function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {lat: 36.9741, lng: -122.0308},
        zoom: 13
    });
    var originalCenter = map.getCenter();
    var center = map.getCenter();

    // setup autocomplete
    var input = document.getElementById('pac-input');
    var autocomplete = new google.maps.places.Autocomplete(input);

    // create marker for the searched address
    var marker = new google.maps.Marker({
        map: map,
        anchorPoint: new google.maps.Point(0, 0)
    });

    // clear the map when input is cleared or 'clear' is pressed
    input.addEventListener('input', function() {
        if (input.value == '') {
            searchinfo.cancelSearch();
            window.setTimeout( window.onresize, 100);
        }
    });

    // set callback to auto-refocus center upon resizing window
    window.onresize = function(){
        google.maps.event.trigger(map, 'resize');
        map.setCenter(center);
    }

    // set the marker for the newly found place
    // also tell the vue element that the address has changed
    autocomplete.addListener('place_changed', function() {
        marker.setVisible(false);
        var place = autocomplete.getPlace();
        if (!place.geometry) {
            window.alert("Manually Inputted Addresses Not Allowed");
            return; 
        }

        center = place.geometry.location;
        // communicate with vue framework to handle the search
        searchinfo.address = input.value;
        searchinfo.handleSearch();
        // recenter upon search because map prior to search may have different dimensions
        window.setTimeout(window.onresize, 200);
        if (searchinfo.searched == true)
            map.setZoom(17);
        else
            map.setZoom(13);
        marker.setPosition(center);
        marker.setVisible(true);
    });
}

