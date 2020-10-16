package pl.pszczolkowski.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.pszczolkowski.app.entities.Marker;
import pl.pszczolkowski.app.model.GoogleMarker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class MarkerServiceImpl implements MarkerService{

    private ModelMapper modelMapper;

    public MarkerServiceImpl(@Qualifier("getMarker") ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Marker convertGoogleMarkerToMarker(GoogleMarker googleMarker) {
        return modelMapper.map(googleMarker, Marker.class);
    }

    @Override
    public List<Marker> convertGoogleMarkersToMarkers(GoogleMarker[] googleMarkers) {
        List<Marker> markerList = new ArrayList<>();
        Stream.of(googleMarkers).forEach(googleMarker -> {
            Marker map = modelMapper.map(googleMarker, Marker.class);
            markerList.add(map);
        });
        return markerList;
    }
}
