package theknife.obj.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A class that represents a geographic location, extending {@link Coordinate}.
 * <p>
 * This class adds information about the country, city, and address to the basic geographic coordinates (latitude and longitude) provided by {@link Coordinate}.<br>
 * It can be used to represent the location of a restaurant or other geographic entities.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public class Location extends Coordinate {
    
    /**
     * The country of the location.
     */
    @JsonProperty("country")
    private String country;
    
    /**
     * The city of the location.
     */
    @JsonProperty("city")
    private String city;
    
    /**
     * The address of the location.
     */
    @JsonProperty("address")
    private String address;

    /**
     * Default constructor.
     * <p>
     * Initializes a {@code Location} with no country, city or address.
     * </p>
     */
    public Location() {
    }

    /**
     * Constructor that initializes the {@code Location} object with the specified country, city, address, and geographic coordinates (latitude and longitude).
     *
     * @param country the country of the location
     * @param city the city of the location
     * @param address the address of the location
     * @param latitude the latitude of the location
     * @param longitude the longitude of the location
     */
    public Location(String country, String city, String address, double latitude, double longitude) {
        super(latitude, longitude);
        setCountry(country);
        setCity(city);
        setAddress(address);
    }

    /**
     * Returns the country of the location.
     *
     * @return the country of the location
     */
    public final String getCountry() {
        return country;
    }

    /**
     * Sets the country of the location.
     *
     * @param country the new country to set
     */
    public final void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the city of the location.
     *
     * @return the city of the location
     */
    public final String getCity() {
        return city;
    }

    /**
     * Sets the city of the location.
     *
     * @param city the new city to set
     */
    public final void setCity(String city) {
        this.city = city;
    }

    /**
     * Returns the address of the location.
     *
     * @return the address of the location
     */
    public final String getAddress() {
        return address;
    }

    /**
     * Sets the address of the location.
     *
     * @param address the new address to set
     */
    public final void setAddress(String address) {
        this.address = address;
    }
    
    
}
