package theknife.server;

public class QueryServer {
    //TODO mettere tutte le query con le funzioni
    public QueryServer(){

    }

    public String getAllRestaurant(){
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

    public String getAllReviews(){
        return "SELECT * FROM reviews";
    }


}