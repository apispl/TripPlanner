package pl.pszczolkowski.app.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class PlaceServiceImpl implements PlaceService{

    @Value("${google-maps.user.key}")
    String googleMapsUserKey;
    String baseUrl = "https://maps.googleapis.com/maps/api/place";

    @Override
    public URI buildPlaceUrl(String input) {
        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/findplacefromtext/json")
                .queryParam("input", input)
                .queryParam("inputtype", "textquery")
                .queryParam("fields", "photos,formatted_address,name,rating,opening_hours,geometry")
                .queryParam("key", googleMapsUserKey)
                .build();
        return uri.toUri();
    }

    @Override
    public URI buildPhotoUrl(String photoReference) {
        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl(baseUrl + "/photo")
                .queryParam("maxwidth", 400)
                .queryParam("maxheight", 400)
                .queryParam("photoreference", photoReference)
                .queryParam("key", googleMapsUserKey)
                .build();
        return uri.toUri();
    }
}
