package theknife.obj.restaurant;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class Location extends Coordinate {
    private String country;
    private String city;
    private String address;

    public Location() {
    }

    public Location(String country, String city, String address, double latitude, double longitude) {
        super(latitude, longitude);
        setCountry(country);
        setCity(city);
        setAddress(address);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
