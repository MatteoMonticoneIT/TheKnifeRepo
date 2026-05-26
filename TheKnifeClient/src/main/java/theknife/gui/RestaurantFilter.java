package theknife.gui;

import java.util.HashMap;
import java.util.List;

/**
 * This class serves as a {@link HashMap} for filtering the restaurant search.
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */
public final class RestaurantFilter {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * Rating in stars from 0.5 to 5.0.
     */
    private double rating;
    
    /**
     * Location of the restaurant.
     */
    private String location;
    
    /**
     * Price scale from 1 (cheap) to 4 (expensive).
     */
    private int price;
    
    /**
     * List of cuisines that a restaurant has to be.
     */
    private List<String> cuisines;
    
    /**
     * List of services that a restaraunt must have.
     */
    private List<String> services;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructior.
     * <p>
     * Initializes the {@code RestaurantFilter} without any attributes set.
     * </p>
     */
    public RestaurantFilter() {
    }
    
    /**
     * Constructor that initializes a {@code RestaurantFilter} object with the specified attributes.
     * <p>
     * This constructor initializes the {@code RestaurantFilter} object's rating, location, price, cuisines and services.
     * </p>
     *
     * @param rating how good is the restaurant overall
     * @param location restaurant location
     * @param price how expensive is the restaurant
     * @param cuisines type of cuisine that the restaurant must provide
     * @param services all services that a restaurant must have
     */
    public RestaurantFilter(double rating, String location, int price, List<String> cuisines, List<String> services) {
        this.rating = rating;
        this.location = location;
        this.price = price;
        this.cuisines = cuisines;
        this.services = services;
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Returns the overall rating filter.
     * @return the restaurant's rating that must be
     */
    public final double getRating() {
        return rating;
    }
    
    /**
     * Returns the location filter.
     * @return the restaurant's location that has to be
     */
    public final String getLocation() {
        return location;
    }
    
    /**
     * Return the price interval filter (1 to 4)
     * @return the price interval (1 - 4).
     */
    public final int getPrice() {
        return price;
    }
    
    /**
     * Return the cuisine list filter
     * @return the list of cuisines selected from {@link AdvancedSearch} {@code JPanel}
     */
    public final List<String> getCuisines() {
        return cuisines;
    }
    
    /**
     * Return the services list filter.
     * @return the list of services selected from {@link AdvancedSearch} {@code JPanel}
     */
    public final List<String> getServices() {
        return services;
    }
    //</editor-fold>
}
