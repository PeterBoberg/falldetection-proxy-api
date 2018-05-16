package se.frost.falldetectionproxyapi.dto.request;

public class AlarmRequest {


    private double latitude;
    private double longitude;

    public AlarmRequest() {
    }

    public AlarmRequest(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String prettyString() {
        return "Latitude: " + latitude + ", Longitude: " + longitude;
    }
}
