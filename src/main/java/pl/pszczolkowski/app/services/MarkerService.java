package pl.pszczolkowski.app.services;

import org.springframework.stereotype.Service;
import pl.pszczolkowski.app.entities.Marker;
import pl.pszczolkowski.app.model.GoogleMarker;

import java.util.List;

@Service
public interface MarkerService {

    Marker convertGoogleMarkerToMarker(GoogleMarker googleMarker);
    List<Marker> convertGoogleMarkersToMarkers(GoogleMarker[] googleMarkers);
}
