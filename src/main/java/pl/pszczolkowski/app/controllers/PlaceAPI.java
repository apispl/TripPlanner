package pl.pszczolkowski.app.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.pszczolkowski.app.DTOs.PlaceDTO;
import pl.pszczolkowski.app.model.Place;
import pl.pszczolkowski.app.services.PlaceService;
import pl.pszczolkowski.app.services.PlaceServiceImpl;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("/places")
@RestController
public class PlaceAPI {

    private PlaceService placeService;
    ModelMapper modelMapper;
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Autowired
    public PlaceAPI(PlaceServiceImpl placeServiceImpl, @Qualifier("getPlaceMapper") ModelMapper modelMapper) {
        this.placeService = placeServiceImpl;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{input}")
    public ResponseEntity<PlaceDTO> getPlace(@PathVariable String input) {
        headers.setContentType(MediaType.APPLICATION_JSON);

        String replace = input.replaceAll(" ", "%20");

        Place place = restTemplate.exchange(placeService.buildPlaceUrl(replace), HttpMethod.GET, new HttpEntity<>(headers), Place.class).getBody();

        PlaceDTO placeDTO = modelMapper.map(place, PlaceDTO.class);

        return new ResponseEntity<>(placeDTO, HttpStatus.OK) ;
    }

    @GetMapping("/photos/{photoReference}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable String photoReference) {
        List<MediaType> acceptableMediaType = new ArrayList<>();

        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        acceptableMediaType.add(MediaType.IMAGE_JPEG);

        headers.setAccept(acceptableMediaType);

        return restTemplate.exchange(placeService.buildPhotoUrl(photoReference), HttpMethod.GET, new HttpEntity<>(headers), byte[].class);
    }
}
