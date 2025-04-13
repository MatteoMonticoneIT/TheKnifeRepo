package theknife.obj.restaurant;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class Coordinate {
    private double latitude;
    private double longitude;

    public Coordinate() {
    }

    public Coordinate(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public final double getLatitude() {
        return latitude;
    }

    public final void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public final double getLongitude() {
        return longitude;
    }

    public final void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
}
