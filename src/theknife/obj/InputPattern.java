package theknife.obj;

import java.util.regex.Pattern;

/**
 * A utility class to manage all input patterns.
 * <p>
 * This class stores all input patterns used to validate all input required using regular expression also known as regex.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public final class InputPattern {
    
    //<editor-fold defaultstate="collapsed" desc="Consts">
    /**
     * The first name regex.
     */
    public static final Pattern FIRST_NAME      = Pattern.compile("^[\\p{L}][\\p{L}\\p{M}'\\- ]{1,98}[\\p{L}]$");
    
    /**
     * The last name regex.
     */
    public static final Pattern LAST_NAME       = Pattern.compile("^[\\p{L}'-]{2,100}$");
    
    /**
     * The birth date regex.
     */
    public static final Pattern BIRTH_DATE      = Pattern.compile("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$");
    
    /**
     * The address regex.
     */
    public static final Pattern ADDRESS         = Pattern.compile("^(?=.{1,256}$)(\\d{1,5}[A-Za-z]?\\s?[\\p{L}\\s\\.'-]+|[\\p{L}\\s\\.'-]+\\s\\d{1,5}[A-Za-z]?)(,\\s?[\\p{L}\\s\\.'-]+){1,3}$");
    
    /**
     * The email regex.
     */
    public static final Pattern EMAIL           = Pattern.compile("^(?=.{6,256}$)[\\w.-]+@[a-zA-Z\\d-]+\\.[a-zA-Z]{2,}$");
    
    /**
     * The username regex.
     */
    public static final Pattern USERNAME        = Pattern.compile("^(?![_.])(?!.*\\.\\.)(?!.*____)[a-zA-Z0-9._]{3,32}(?<![_.])$");
    
    /**
     * The password regex.
     */
    public static final Pattern PASSWORD        = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$!%*?&\\(\\)])[a-zA-Z\\d@#$!?%*?&\\(\\)\\.,]{8,128}$");
    
    /**
     * The {@code Restaurant} name regex.
     */
    public static final Pattern RESTAURANT_NAME = Pattern.compile("^[\\p{L}\\p{N}][\\p{L}\\p{N}'&.,\\- ]{1,49}$");
    
    /**
     * The {@code Restaurant} address regex.
     */
    public static final Pattern RESTAURANT_ADDRESS = Pattern.compile("^(?=.{1,256}$)(\\d{1,5}[A-Za-z]?\\s?[\\p{L}\\s\\.'-]+|[\\p{L}\\s\\.'-]+\\s\\d{1,5}[A-Za-z]?)(,\\s?[\\p{L}\\s\\.'-]+){1,3}$");
    
    /**
     * The {@code Restaurant} country regex.
     */
    public static final Pattern RESTAURANT_COUNTRY = Pattern.compile("^(?=.{2,70}$)\\p{L}(?:[\\p{L}'\\- ]*\\p{L})$");
    
    /**
     * The {@code Restaurant} city regex.
     */
    public static final Pattern RESTAURANT_CITY = Pattern.compile("^(?=.{2,70}$)\\p{L}(?:[\\p{L}'\\-\\. ]*\\p{L})$");
    
    /**
     * The {@code Restaurant} latitude and longitude regex.
     */
    public static final Pattern RESTAURANT_LATITUDE_AND_LONGITUDE = Pattern.compile("^[\\d\\.\\+\\-]{1,15}$");
    
    /**
     * The {@code Restaurant} currency regex.
     */
    public static final Pattern RESTAURANT_CURRENCY = Pattern.compile("^[\\p{Sc}]$");
    
    /**
     * The {@code Restaurant} phone number regex.
     */
    public static final Pattern RESTAURANT_PHONE_NUMBER = Pattern.compile("^\\+?[\\d\\s\\-\\(\\)\\.]{7,20}$");
    
    /**
     * The {@code Restaurant} URL regex.
     */
    public static final Pattern RESTAURANT_URL = Pattern.compile("^(https?):\\/\\/(www\\.)?([a-zA-Z0-9_-]+\\.)+[a-zA-Z]{2,}(:\\d{1,5})?(\\/[^\\s?#]*)?(\\?[^\\s#]*)?(#\\S*)?$");
    
    /**
     * The {@code Restaurant} Website URL regex.
     */
    public static final Pattern RESTAURANT_WEBURL = Pattern.compile("^(https?):\\/\\/(([a-zA-Z0-9_-]+\\.)+[a-zA-Z]{2,})(\\/[^\\s]*)?$");

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Methods">
    /**
     * Matches a pattern with an input.
     *
     * @param pattern the pattern to use
     * @param input the input to validate
     * @return true if the validation is ok, false otherwise
     */
    public static final boolean match(Pattern pattern, String input) {
        return pattern.matcher(input).matches();
    }
    //</editor-fold>
}
