package theknife.obj.restaurant;

import theknife.obj.lists.ListReview;

/**
 * A class that represents a restaurant, extending {@link Location}.
 * <p>
 * This class includes details about a restaurant such as its name, price, phone number, URL, awards, green star, services available, description, and associated reviews.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */

public final class Restaurant extends Location {
    
    /**
     * The name of the restaurant.
     */
    private String name;
    
    /**
     * The price category of the restaurant.
     */
    private int price;
    
    /**
     * The phone number of the restaurant.
     */
    private String phoneNumber;
    
    /**
     * The URL for the restaurant.
     */
    private String url;
    
    /**
     * The website URL of the restaurant.
     */
    private String websiteUrl;
    
    /**
     * The award received by the restaurant.
     */
    private String award;
    
    /**
     * Indicates if the restaurant has a green star (sustainability recognition).
     */
    private boolean greenStar;
    
    /**
     * A description of the services available at the restaurant.
     */
    private String servicesAvailable;
    
    /**
     * A brief description of the restaurant.
     */
    private String description;
    
    /**
     * The list of reviews associated with the restaurant.
     */
    private ListReview listReview;

    /**
     * Default constructor.
     * <p>
     * Initializes the {@code Restaurant} object with no name, price, phoneNumber and other parameters.
     * </p>
     */
    public Restaurant() {
    }

    /**
     * Constructor that initializes the {@code Restaurant} object with the provided details.
     *
     * @param name the name of the restaurant
     * @param price the price category of the restaurant
     * @param phoneNumber the phone number of the restaurant
     * @param url the URL for the restaurant
     * @param websiteUrl the website URL of the restaurant
     * @param award the award received by the restaurant
     * @param greenStar whether the restaurant has a green star (sustainability recognition)
     * @param servicesAvailable the services available at the restaurant
     * @param description a brief description of the restaurant
     * @param country the country where the restaurant is located
     * @param city the city where the restaurant is located
     * @param address the address of the restaurant
     * @param latitude the latitude of the restaurant's location
     * @param longitude the longitude of the restaurant's location
     */
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

    /**
     * Returns the name of the restaurant.
     *
     * @return the name of the restaurant
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets the name of the restaurant.
     *
     * @param name the name to set for the restaurant
     */
    public final void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price category of the restaurant.
     *
     * @return the price category of the restaurant
     */
    public final int getPrice() {
        return price;
    }

    /**
     * Sets the price category of the restaurant.
     *
     * @param price the price category to set
     */
    public final void setPrice(int price) {
        this.price = price;
    }

    /**
     * Returns the phone number of the restaurant.
     *
     * @return the phone number of the restaurant
     */
    public final String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the restaurant.
     *
     * @param phoneNumber the phone number to set
     */
    public final void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the URL for the restaurant.
     *
     * @return the URL for the restaurant
     */
    public final String getUrl() {
        return url;
    }

    /**
     * Sets the URL for the restaurant.
     *
     * @param url the URL to set
     */
    public final void setUrl(String url) {
        this.url = url;
    }

    /**
     * Returns the website URL of the restaurant.
     *
     * @return the website URL of the restaurant
     */
    public final String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * Sets the website URL of the restaurant.
     *
     * @param websiteUrl the website URL to set
     */
    public final void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    /**
     * Returns the award received by the restaurant.
     *
     * @return the award received by the restaurant
     */
    public final String getAward() {
        return award;
    }

    /**
     * Sets the award received by the restaurant.
     *
     * @param award the award to set
     */
    public final void setAward(String award) {
        this.award = award;
    }

    /**
     * Returns whether the restaurant has a green star (sustainability recognition).
     *
     * @return true if the restaurant has a green star, false otherwise
     */
    public final boolean isGreenStar() {
        return greenStar;
    }

    /**
     * Sets whether the restaurant has a green star (sustainability recognition).
     *
     * @param greenStar the green star status to set
     */
    public final void setGreenStar(boolean greenStar) {
        this.greenStar = greenStar;
    }

    /**
     * Returns the services available at the restaurant.
     *
     * @return the services available at the restaurant
     */
    public final String getServicesAvailable() {
        return servicesAvailable;
    }

    /**
     * Sets the services available at the restaurant.
     *
     * @param servicesAvailable the services available to set
     */
    public final void setServicesAvailable(String servicesAvailable) {
        this.servicesAvailable = servicesAvailable;
    }

    /**
     * Returns a brief description of the restaurant.
     *
     * @return the description of the restaurant
     */
    public final String getDescription() {
        return description;
    }

    /**
     * Sets the description of the restaurant.
     *
     * @param description the description to set
     */
    public final void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the list of reviews associated with the restaurant.
     *
     * @return the list of reviews for the restaurant
     */
    public final ListReview getListReview() {
        return listReview;
    }

    /**
     * Sets the list of reviews for the restaurant.
     *
     * @param listReview the list of reviews to set
     */
    public final void setListReview(ListReview listReview) {
        this.listReview = listReview;
    }
    
}
