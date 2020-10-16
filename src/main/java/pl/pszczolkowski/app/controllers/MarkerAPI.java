package pl.pszczolkowski.app.controllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pszczolkowski.app.entities.Marker;
import pl.pszczolkowski.app.model.GoogleMarker;
import pl.pszczolkowski.app.repositories.MarkerRepo;
import pl.pszczolkowski.app.repositories.TripRepo;
import pl.pszczolkowski.app.services.MarkerService;

import java.util.List;

@CrossOrigin("*")
@RestController
public class MarkerAPI {

    private MarkerRepo markerRepo;
    private TripRepo tripRepo;
    private MarkerService markerService;

    @Autowired
    public MarkerAPI(MarkerRepo markerRepo, TripRepo tripRepo, MarkerService markerService) {
        this.markerRepo = markerRepo;
        this.tripRepo = tripRepo;
        this.markerService = markerService;
    }

    @GetMapping("/trips/{tripId}/markers")
    public List<Marker> getAllMarkersByTripId(@PathVariable (value = "tripId") Long tripId){
        return markerRepo.findByTripId(tripId);
    }

    @PostMapping("/trips/{tripId}/markersGoogle")
    public List<Marker> addMarkersAll(@PathVariable (value = "tripId") Long tripId, @RequestBody GoogleMarker[] googleMarkers) throws NotFoundException {
        return tripRepo.findById(tripId).map(trip -> {
            List<Marker> markerList = markerService.convertGoogleMarkersToMarkers(googleMarkers);
            markerList.forEach(markerFromList -> {
                markerFromList.setTrip(trip);
            });
            return markerRepo.saveAll(markerList);
        }).orElseThrow(() -> new NotFoundException("Trip ID: " + tripId + " not found"));
    }

    @PostMapping("/trips/{tripId}/markers")
    public Marker addMarker(@PathVariable (value = "tripId") Long tripId, @RequestBody GoogleMarker googleMarker) throws NotFoundException {
        return tripRepo.findById(tripId).map(trip -> {
            Marker marker = markerService.convertGoogleMarkerToMarker(googleMarker);
            marker.setTrip(trip);
            return markerRepo.save(marker);
        }).orElseThrow(() -> new NotFoundException("Trip ID: " + tripId + " not found"));
    }

    @PutMapping("/trips/{tripId}/markers/{markerId}")
    public Marker updateMarker(@PathVariable (value = "tripId") Long tripId,
                              @PathVariable (value = "markerId") Long markerId,
                              @RequestBody Marker markerRequest) throws NotFoundException {
        if(!tripRepo.existsById(tripId)) {
            throw new NotFoundException("Trip ID: " + tripId + " not found");
        }

        return markerRepo.findById(markerId).map(marker -> {
            marker.setTitle(markerRequest.getTitle());
            marker.setInfoOne(markerRequest.getInfoOne());
            marker.setLabelColor(markerRequest.getLabelColor());
            marker.setLabelText(markerRequest.getLabelText());
            marker.setPositionLatitude(markerRequest.getPositionLatitude());
            marker.setPositionLongitude(markerRequest.getPositionLongitude());
            marker.setOptionsAnimation(markerRequest.getOptionsAnimation());
            return markerRepo.save(marker);
        }).orElseThrow(() -> new NotFoundException("Trip ID: " + markerId + " not found"));
    }

    @DeleteMapping("/trips/{tripId}/markers/{markerId}")
    public ResponseEntity<?> deleteMarker(@PathVariable (value = "tripId") Long tripId,
                                           @PathVariable (value = "markerId") Long markerId) throws NotFoundException {
        return markerRepo.findByIdAndTripId(markerId, tripId).map(marker -> {
            markerRepo.delete(marker);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException("Marker not found with id " + markerId + " and trip_Id " + tripId));
    }
}
