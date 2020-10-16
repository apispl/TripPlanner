package pl.pszczolkowski.app.services;

import java.net.URI;

public interface PlaceService {

    URI buildPlaceUrl(String input);
    URI buildPhotoUrl(String photoReference);
}
