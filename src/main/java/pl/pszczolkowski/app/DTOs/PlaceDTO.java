package pl.pszczolkowski.app.DTOs;

public class PlaceDTO {
    String name;
    String adress;
    double locationLat;
    double locationLng;
    String photoReference;
    double photoHeight;
    double photoWidth;

    public PlaceDTO() {
    }

    public PlaceDTO(String name, String adress, double locationLat, double locationLng, String photoReference, double photoHeight, double photoWidth) {
        this.name = name;
        this.adress = adress;
        this.locationLat = locationLat;
        this.locationLng = locationLng;
        this.photoReference = photoReference;
        this.photoHeight = photoHeight;
        this.photoWidth = photoWidth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(float locationLat) {
        this.locationLat = locationLat;
    }

    public double getLocationLng() {
        return locationLng;
    }

    public void setLocationLng(float locationLng) {
        this.locationLng = locationLng;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public double getPhotoHeight() {
        return photoHeight;
    }

    public void setPhotoHeight(double photoHeight) {
        this.photoHeight = photoHeight;
    }

    public double getPhotoWidth() {
        return photoWidth;
    }

    public void setPhotoWidth(double photoWidth) {
        this.photoWidth = photoWidth;
    }
}
