package theknife.obj.restaurant;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class Restaurant extends Location {
    private String name;
    private int price;
    private String phoneNumber;
    private String url;
    private String websiteUrl;
    private String award;
    private boolean greenStar;
    private String servicesAvailable;
    private String description;

    public Restaurant() {
    }

    public Restaurant(String name, int price, String phoneNumber, String url, String websiteUrl, String award, boolean greenStar, String servicesAvailable, String description, 
                      String country, String city, String address, double latitude, double longitude) {
        super(country, city, address, latitude, longitude);
        setName(name);
        setPrice(price);
        setPhoneNumber(phoneNumber);
        setUrl(url);
        setWebsiteUrl(websiteUrl);
        setAward(award);
        setGreenStar(greenStar);
        setServicesAvailable(servicesAvailable);
        setDescription(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public boolean isGreenStar() {
        return greenStar;
    }

    public void setGreenStar(boolean greenStar) {
        this.greenStar = greenStar;
    }

    public String getServicesAvailable() {
        return servicesAvailable;
    }

    public void setServicesAvailable(String servicesAvailable) {
        this.servicesAvailable = servicesAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
