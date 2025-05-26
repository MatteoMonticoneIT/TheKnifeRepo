package theknife.obj.restaurant;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder(
{
    "ID", 
    "ownerID",
    "name", 
    "normName", 
    "price", 
    "currency",
    "phoneNo", 
    "country", 
    "city", 
    "address", 
    "latitude", 
    "longitude", 
    "url", 
    "webUrl", 
    "award", 
    "greenStar", 
    "cuisine",
    "services", 
    "description", 
    "rating",
    "reviews"
})
public final class Restaurant extends Location 
{    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The restaurant id.
     */
    @JsonProperty("ID")
    private int         id;
    
    /**
     * The restaurant's owner id.
     */
    @JsonProperty("ownerID")
    private int         ownerId;
    
    /**
     * The name of the restaurant.
     */
    @JsonProperty("name")
    private String      name;
    
    /**
     * The normalized name of the restaurant (no accents and other characters that may interfere)
     */
    @JsonProperty("normName")
    private String      normalizedName;
    
    /**
     * The price category of the restaurant.
     */
    @JsonProperty("price")
    private int         price;
    
    /**
     * The price category of the restaurant.
     */
    @JsonProperty("currency")
    private String      currency;
    
    /**
     * The phone number of the restaurant.
     */
    @JsonProperty("phoneNo")
    private String      phoneNumber;
    
    /**
     * The URL for the restaurant.
     */
    @JsonProperty("url")
    private String      url;
    
    /**
     * The website URL of the restaurant.
     */
    @JsonProperty("webUrl")
    private String      websiteUrl;
    
    /**
     * The award received by the restaurant.
     */
    @JsonProperty("award")
    private String      award;
    
    /**
     * Indicates if the restaurant has a green star (sustainability recognition).
     */
    @JsonProperty("greenStar")
    private boolean     greenStar;
    
    /**
     * Indicates the type of cuisine.
     */
    @JsonProperty("cuisine")
    private String      cuisine;
    
    /**
     * A description of the services available at the restaurant.
     */
    @JsonProperty("services")
    private String      servicesAvailable;
    
    /**
     * A brief description of the restaurant.
     */
    @JsonProperty("description")
    private String      description;
    
    /**
     * A brief description of the restaurant.
     */
    @JsonProperty("rating")
    private double      rating;
    
    /**
     * The list of reviews associated with the restaurant.
     */
    @JsonProperty("reviews")
    private ListReview  listReview;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes the {@code Restaurant} object with no name, price, phoneNumber and other parameters.
     * </p>
     */
    public Restaurant() {}
    
    /**
     * Constructor that initializes the {@code Restaurant} object with the provided details.
     *
     * @param id the restaurant id
     * @param ownerId the restaurant's owner id
     * @param name the name of the restaurant
     * @param normalizedName the normalizedName of the name of the restaurant
     * @param price the price category of the restaurant
     * @param currency the currency used by that restaurant
     * @param phoneNumber the phone number of the restaurant
     * @param url the URL for the restaurant
     * @param websiteUrl the website URL of the restaurant
     * @param award the award received by the restaurant
     * @param greenStar whether the restaurant has a green star (sustainability recognition)
     * @param cuisine the type of cuisine
     * @param servicesAvailable the services available at the restaurant
     * @param description a brief description of the restaurant
     * @param rating the overall rating of the quality of the restaurant
     * @param country the country where the restaurant is located
     * @param city the city where the restaurant is located
     * @param address the address of the restaurant
     * @param latitude the latitude of the restaurant's location
     * @param longitude the longitude of the restaurant's location
     */
    public Restaurant(int id, int ownerId, String name, String normalizedName, int price, String currency, String phoneNumber, String url, String websiteUrl, String award, boolean greenStar, String cuisine, String servicesAvailable, String description, double rating,
            String country, String city, String address, double latitude, double longitude) 
    {
        super                       (country, city, address, latitude, longitude);
        this.setId                  (id);
        this.setOwnerId             (ownerId);
        this.setName                (name);
        this.setNormalizedName      (normalizedName);
        this.setPrice               (price);
        this.setCurrency            (currency);
        this.setPhoneNumber         (phoneNumber);
        this.setUrl                 (url);
        this.setWebsiteUrl          (websiteUrl);
        this.setAward               (award);
        this.setGreenStar           (greenStar);
        this.setCuisine             (cuisine);
        this.setServicesAvailable   (servicesAvailable);
        this.setDescription         (description);
        this.setRating              (rating);
        this.setListReview          (new ListReview());
    }
    
    public Restaurant(String name, String normalizedName, int price, String currency, String phoneNumber, String url, String websiteUrl, /*String award, boolean greenStar, */String cuisine, String servicesAvailable, /*String description, double rating,*/
            String country, String city, String address, double latitude, double longitude) 
    {
        super                       (country, city, address, latitude, longitude);
        this.setName                (name);
        this.setNormalizedName      (normalizedName);
        this.setPrice               (price);
        this.setCurrency            (currency);
        this.setPhoneNumber         (phoneNumber);
        this.setUrl                 (url);
        this.setWebsiteUrl          (websiteUrl);
        //this.setAward               (award);
        //this.setGreenStar           (greenStar);
        this.setCuisine             (cuisine);
        this.setServicesAvailable   (servicesAvailable);
        //this.setDescription       (description);
        //this.setRating              (rating);
        this.setListReview          (new ListReview());
    }
    
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Return the id of the restaurant.
     *
     * @return the id of the restaurant
     */
    public final int        getId                  ()                           {return id;}
    
    /**
     * Return the id of the restaurant's owner.
     *
     * @return the id of the restaurant's owner
     */
    public final int        getOwnerId             ()                           {return ownerId;}
    
    /**
     * Returns the name of the restaurant.
     *
     * @return the name of the restaurant
     */
    public final String     getName                 ()                          {return name;}
    
    /**
     * Returns the normalized name of the name of the restaurant.
     *
     * @return the normalized name of the name of the restaurant
     */
    public final String     getNormalizedName       ()                          {return normalizedName;}
    
    /**
     * Returns the price category of the restaurant.
     *
     * @return the price category of the restaurant
     */
    public final int        getPrice                ()                          {return price;}
    
    /**
     * Returns the currency of the restaurant.
     * 
     * @return the currency of the restaurant
     */
    public       String     getCurrency             ()                          {return currency;}
    
    /**
     * Returns the phone number of the restaurant.
     *
     * @return the phone number of the restaurant
     */
    public final String     getPhoneNumber          ()                          {return phoneNumber;}
    
    /**
     * Returns the URL for the restaurant.
     *
     * @return the URL for the restaurant
     */
    public final String     getUrl                  ()                          {return url;}
    
    /**
     * Returns the website URL of the restaurant.
     *
     * @return the website URL of the restaurant
     */
    public final String     getWebsiteUrl           ()                          {return websiteUrl;}
  
    /**
     * Returns the award received by the restaurant.
     *
     * @return the award received by the restaurant
     */
    public final String     getAward                ()                          {return award;}
    
    /**
     * Returns whether the restaurant has a green star (sustainability recognition).
     *
     * @return true if the restaurant has a green star, false otherwise
     */
    public final boolean    isGreenStar             ()                          {return greenStar;}
    
    /**
     * Returns the type of cuisine of the restaurant.
     *
     * @return the type of cuisine of the restaurant
     */
    public final String     getCuisine              ()                          {return cuisine;}
          
    /**
     * Returns the services available at the restaurant.
     *
     * @return the services available at the restaurant
     */
    public final String     getServicesAvailable    ()                          {return servicesAvailable;}
    
    /**
     * Returns a brief description of the restaurant.
     *
     * @return the description of the restaurant
     */
    public final String     getDescription          ()                          {return description;}
    
    /**
     * Returns the rating of the restaurant.
     *
     * @return the rating of the restaurant
     */
    public       double     getRating               ()                          {return rating;}
    
    /**
     * Returns the list of reviews associated with the restaurant.
     *
     * @return the list of reviews for the restaurant
     */
    public final ListReview getListReview           ()                          {return listReview;}
    
    /**
     * Sets the id of the restaurant.
     *
     * @param id the id to set for the restaurant
     */
    public final void       setId                   (int id)                    {this.id                = id;}
    
    /**
     * Sets the id of the restaurant's owner.
     *
     * @param ownerId the id to set for the restaurant's owner
     */
    public final void       setOwnerId              (int ownerId)               {this.ownerId           = ownerId; }
    
    /**
     * Sets the name of the restaurant.
     *
     * @param name the name to set for the restaurant
     */
    public final void       setName                 (String name)               {this.name              = name;}
    
    /**
     * Sets the name normalized removing accents to prevent search issues
     *
     * @param normalizedName the normalized name to set
     */
    public final void       setNormalizedName       (String normalizedName)     {this.normalizedName    = normalizedName;}
     
    /**
     * Sets the price category of the restaurant.
     *
     * @param price the price category to set
     */
    public final void       setPrice                (int price)                 {this.price             = price;}

    /**
     * Sets the currency of the restaurant
     * @param currency the currency to set
     */
    public       void       setCurrency             (String currency)           {this.currency          = currency.substring(0, 1);}
 
    /**
     * Sets the phone number of the restaurant.
     *
     * @param phoneNumber the phone number to set
     */
    public final void       setPhoneNumber          (String phoneNumber)        {this.phoneNumber       = phoneNumber;}
    
    /**
     * Sets the URL for the restaurant.
     *
     * @param url the URL to set
     */
    public final void       setUrl                  (String url)                {this.url               = url;}

    /**
     * Sets the website URL of the restaurant.
     *
     * @param websiteUrl the website URL to set
     */
    public final void       setWebsiteUrl           (String websiteUrl)         {this.websiteUrl        = websiteUrl;}
   
    /**
     * Sets the award received by the restaurant.
     *
     * @param award the award to set
     */
    public final void       setAward                (String award)              {this.award             = award;}

    /**
     * Sets whether the restaurant has a green star (sustainability recognition).
     *
     * @param greenStar the green star status to set
     */
    public final void       setGreenStar            (boolean greenStar)         {this.greenStar         = greenStar;}

    /**
     * Sets the type of cuisine of the restaurant.
     *
     * @param cuisine the type of cuisine to set
     */
    public final void       setCuisine              (String cuisine)            {this.cuisine           = cuisine; }

    /**
     * Sets the services available at the restaurant.
     *
     * @param servicesAvailable the services available to set
     */
    public final void       setServicesAvailable    (String servicesAvailable)  {this.servicesAvailable = servicesAvailable;}
 
    /**
     * Sets the description of the restaurant.
     *
     * @param description the description to set
     */
    public final void       setDescription          (String description)        {this.description       = description;}
 
    /**
     * Sets the rating of the restaurant.
     *
     * @param rating the rating to set
     */
    public       void       setRating               (double rating)             {this.rating            = rating;}

    /**
     * Sets the list of reviews for the restaurant.
     *
     * @param listReview the list of reviews to set
     */
    public final void       setListReview           (ListReview listReview)     {this.listReview        = listReview;}
    //</editor-fold>
}
