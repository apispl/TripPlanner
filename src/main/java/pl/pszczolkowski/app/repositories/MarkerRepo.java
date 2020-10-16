package pl.pszczolkowski.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pszczolkowski.app.entities.Marker;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkerRepo extends JpaRepository<Marker, Long> {
    List<Marker> findByTripId(Long id);
    Optional<Marker> findByIdAndTripId(Long id, Long tripId);
}
