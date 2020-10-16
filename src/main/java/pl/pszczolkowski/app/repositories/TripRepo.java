package pl.pszczolkowski.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pszczolkowski.app.entities.Trip;

@Repository
public interface TripRepo extends JpaRepository<Trip, Long> {
}
