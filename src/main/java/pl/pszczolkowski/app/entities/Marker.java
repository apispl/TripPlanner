package pl.pszczolkowski.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "MARKER")
public class Marker {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    @JsonIgnore
    Trip trip;

    @Column(precision = 16, scale = 14)
    private BigDecimal positionLatitude;

    @Column(precision = 16, scale = 14)
    private BigDecimal positionLongitude;

    private String labelColor;
    private String labelText;
    private String title;
    private String infoOne;
    private int optionsAnimation;

    public Marker() {
    }

    public Marker(Trip trip, BigDecimal positionLatitude, BigDecimal positionLongitude,
                  String labelColor, String labelText, String title, String infoOne,
                  int optionsAnimation) {
        this.trip = trip;
        this.positionLatitude = positionLatitude;
        this.positionLongitude = positionLongitude;
        this.labelColor = labelColor;
        this.labelText = labelText;
        this.title = title;
        this.infoOne = infoOne;
        this.optionsAnimation = optionsAnimation;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPositionLatitude() {
        return positionLatitude;
    }

    public void setPositionLatitude(BigDecimal positionLatitude) {
        this.positionLatitude = positionLatitude;
    }

    public BigDecimal getPositionLongitude() {
        return positionLongitude;
    }

    public void setPositionLongitude(BigDecimal positionLongitude) {
        this.positionLongitude = positionLongitude;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfoOne() {
        return infoOne;
    }

    public void setInfoOne(String info) {
        this.infoOne = info;
    }

    public int getOptionsAnimation() {
        return optionsAnimation;
    }

    public void setOptionsAnimation(int optionsAnimation) {
        this.optionsAnimation = optionsAnimation;
    }
}
