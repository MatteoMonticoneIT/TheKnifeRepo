package theknife.server;

/*
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       760743 (CO)
 */

public class QueryServer {
    //TODO mettere tutte le query con le funzioni
    public QueryServer() {
    }

    public String getAllRestaurant() {
        return "SELECT" +
                "    r.*," +
                "    CASE" +
                "        WHEN COUNT(c.id) = 0 THEN ''" +
                "        ELSE STRING_AGG(DISTINCT c.description, ', ')" +
                "    END AS cuisines," +
                "CASE" +
                "        WHEN COUNT(s.id) = 0 THEN ''" +
                "        ELSE STRING_AGG(DISTINCT s.description, ', ')" +
                "    END AS service" +
                "FROM restaurant r" +
                "LEFT JOIN listcuisine lc" +
                "    ON lc.restaurantId = r.id" +
                "LEFT JOIN cuisine c" +
                "    ON c.id = lc.cuisineId" +
                "LEFT JOIN listservice ls" +
                "ON ls.restaurantId = r.id" +
                "LEFT JOIN service s" +
                "ON ls.serviceId = s.id" +
                "GROUP BY r.id;";
    }

    public String getAllReviews() {
        return "SELECT * FROM reviews";
    }

    public String getFavorites(int id) {
        return "SELECT ID FROM review r WHERE r.IDCustomer = " + id;
    }
    
    public String getOwned(int id) {
        return "SELECT ID FROM restaurant WHERE ownerID = " + id;
    }

    public String getAllReview() {
        return "SELECT * FROM review";
    }

    public String getAllCuisine() {
        return "SELECT * FROM cuisine";
    }

    public String getAllServices() {
        return "SELECT * FROM service";
    }


}