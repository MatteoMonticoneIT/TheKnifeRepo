package theknife.obj.restaurant;

import theknife.obj.lists.ListReview;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public final class Restaurant extends Location {
    private String name;
    private int price;
    private String phoneNumber;
    private String url;
    private String websiteUrl;
    private String award;
    private boolean greenStar;
    private String servicesAvailable;
    private String description;
    private ListReview listReview;

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

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final int getPrice() {
        return price;
    }

    public final void setPrice(int price) {
        this.price = price;
    }

    public final String getPhoneNumber() {
        return phoneNumber;
    }

    public final void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public final String getUrl() {
        return url;
    }

    public final void setUrl(String url) {
        this.url = url;
    }

    public final String getWebsiteUrl() {
        return websiteUrl;
    }

    public final void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public final String getAward() {
        return award;
    }

    public final void setAward(String award) {
        this.award = award;
    }

    public final boolean isGreenStar() {
        return greenStar;
    }

    public final void setGreenStar(boolean greenStar) {
        this.greenStar = greenStar;
    }

    public final String getServicesAvailable() {
        return servicesAvailable;
    }

    public final void setServicesAvailable(String servicesAvailable) {
        this.servicesAvailable = servicesAvailable;
    }

    public final String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        this.description = description;
    }

    public final ListReview getListReview() {
        return listReview;
    }

    public final void setListReview(ListReview listReview) {
        this.listReview = listReview;
    }
    
}
