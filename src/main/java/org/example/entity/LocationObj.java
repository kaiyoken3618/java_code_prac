package org.example.entity;

import java.io.Serializable;

public class LocationObj implements Serializable {

    private Double longitude;
    private Double latitude;

    public LocationObj(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocationObj() {
    }

    public Double getLongitude() {
        return longitude;
    }

    public LocationObj setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public LocationObj setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }
}
