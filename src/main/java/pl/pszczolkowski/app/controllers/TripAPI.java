package pl.pszczolkowski.app.controllers;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pszczolkowski.app.entities.Trip;
import pl.pszczolkowski.app.repositories.TripRepo;

import java.util.List;

@CrossOrigin("*")
@RestController
public class TripAPI {

    private TripRepo tripRepo;

    @Autowired
    public TripAPI(TripRepo tripRepo) {
        this.tripRepo = tripRepo;
    }

    @GetMapping("/trips")
    public List<Trip> getAllTrip(){
        return tripRepo.findAll();
    }

    @PostMapping("/trips")
    public Trip createTrip( @RequestBody Trip trip) {
        return tripRepo.save(trip);
    }

    @PutMapping("/trips/{tripId}")
    public Trip updateTrip(@PathVariable Long tripId, @RequestBody Trip tripRequest) throws NotFoundException {
        return tripRepo.findById(tripId).map(trip -> {
            trip.setName(tripRequest.getName());
            return tripRepo.save(trip);
        }).orElseThrow(() -> new NotFoundException("Trip ID: " + tripId + " not found"));
    }

    @DeleteMapping("/trips/{tripId}")
    public ResponseEntity<?> deletePost(@PathVariable Long tripId) throws NotFoundException {
        return tripRepo.findById(tripId).map(post -> {
            tripRepo.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new NotFoundException("Trip ID: " + tripId + " not found"));
    }
}
