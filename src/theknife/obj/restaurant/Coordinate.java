package theknife.obj.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class that represents geographic coordinates using latitude and longitude.
 * <p>
 * This class provides methods to get and set the latitude and longitude of a location.<br>
 * It can be used to represent the coordinates of a restaurant or any other geographic entity.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public class Coordinate {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The latitude of the geographic location.
     */
    @JsonProperty("latitude")
    private double latitude;
    
    /**
     * The longitude of the geographic location.
     */
    @JsonProperty("longitude")
    private double longitude;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes the {@code Coordinate} object with default values (latitude = 0, longitude = 0).
     * </p>
     */
    public Coordinate() {
    }
    
    /**
     * Constructor that initializes the {@code Coordinate} object with the specified latitude and longitude.
     *
     * @param latitude the latitude of the geographic location
     * @param longitude the longitude of the geographic location
     */
    public Coordinate(double latitude, double longitude) {
        setLatitude(latitude);
        setLongitude(longitude);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Returns the latitude of the geographic location.
     *
     * @return the latitude
     */
    public final double getLatitude() {
        return latitude;
    }
    
    /**
     * Sets the latitude of the geographic location.
     *
     * @param latitude the new latitude to set
     */
    public final void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    /**
     * Returns the longitude of the geographic location.
     *
     * @return the longitude
     */
    public final double getLongitude() {
        return longitude;
    }
    
    /**
     * Sets the longitude of the geographic location.
     *
     * @param longitude the new longitude to set
     */
    public final void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    //</editor-fold>
}
