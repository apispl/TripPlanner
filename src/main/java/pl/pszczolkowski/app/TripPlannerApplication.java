package pl.pszczolkowski.app;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.pszczolkowski.app.DTOs.PlaceDTO;
import pl.pszczolkowski.app.entities.Marker;
import pl.pszczolkowski.app.model.Candidate;
import pl.pszczolkowski.app.model.GoogleMarker;
import pl.pszczolkowski.app.model.Place;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootApplication
public class TripPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TripPlannerApplication.class, args);
    }

    @Bean
    public ModelMapper getPlaceMapper() {
        Converter<Place, PlaceDTO> converter = mappingContext -> {
            PlaceDTO placeDTO = new PlaceDTO();
            placeDTO.setName(mappingContext.getSource().getCandidates().get(0).getName());
            placeDTO.setAdress(mappingContext.getSource().getCandidates().get(0).getFormattedAddress());
            placeDTO.setLocationLat(mappingContext.getSource().getCandidates().get(0).getGeometry().getLocation().getLat().floatValue());
            placeDTO.setLocationLng(mappingContext.getSource().getCandidates().get(0).getGeometry().getLocation().getLng().floatValue());
            placeDTO.setPhotoReference(mappingContext.getSource().getCandidates().get(0).getPhotos().get(0).getPhotoReference());
            placeDTO.setPhotoHeight(mappingContext.getSource().getCandidates().get(0).getPhotos().get(0).getHeight().doubleValue());
            placeDTO.setPhotoWidth(mappingContext.getSource().getCandidates().get(0).getPhotos().get(0).getWidth().doubleValue());
            return placeDTO;
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Place.class, PlaceDTO.class).setConverter(converter);

        return modelMapper;
    }

    @Bean
    public ModelMapper getMarker() {
        Converter<GoogleMarker, Marker> converter = mappingContext -> {
            Marker marker = new Marker();
            marker.setInfoOne(mappingContext.getSource().getInfo());
            marker.setTitle(mappingContext.getSource().getTitle());
            marker.setPositionLatitude(new BigDecimal(mappingContext.getSource().getPosition().getLat().toString()));
            marker.setPositionLongitude(new BigDecimal(mappingContext.getSource().getPosition().getLng().toString()));
            marker.setLabelColor(mappingContext.getSource().getLabel().getColor());
            marker.setLabelText(mappingContext.getSource().getLabel().getText());
            marker.setOptionsAnimation(mappingContext.getSource().getOptions().getAnimation());

            return marker;
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(GoogleMarker.class, Marker.class).setConverter(converter);

        return modelMapper;
    }
}
