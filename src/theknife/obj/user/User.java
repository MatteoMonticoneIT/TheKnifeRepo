package theknife.obj.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import simple.util.StringUtils;

/**
 * Represents a generic user in the system.
 * <p>
 * This class contains common attributes and behaviors for all types of users, such as clients and restaurateurs.<br>
 * A user has personal information including name, username, password, birthDate, address, and a role.
 * </p>
 * 
 * @author Damiano De Mutiis    761348 (CO)
 * @author Matteo Porto Bonacci 761396 (CO)
 * @author Matteo Monticone     761701 (CO)
 * @author Mattia Tamburo       761743 (CO)
 */
public class User {
    
    //<editor-fold defaultstate="collapsed" desc="Fields">
    /**
     * The user's id.
     */
    @JsonProperty("ID")
    private int id;
    
    /**
     * The user's first name.
     */
    @JsonProperty("firstName")
    private String firstName;
    
    /**
     * The user's first name normalized (No combined characters such as à, é, ò, ...).
     */
    @JsonProperty("firstNameNormalized")
    private String firstNameNormalized;
    
    /**
     * The user's last name.
     */
    @JsonProperty("lastName")
    private String lastName;
    
    /**
     * The user's last name normalized (No combined characters such as à, é, ò, ...).
     */
    @JsonProperty("lastNameNormalized")
    private String lastNameNormalized;
    
    /**
     * The user's username.
     */
    @JsonProperty("username")
    private String username;
    
    /**
     * The user's username.
     */
    @JsonProperty("email")
    private String email;
    
    /**
     * The user's password.
     */
    @JsonProperty("password")
    private String password;
    
    /**
     * The user's birthDate.
     */
    @JsonProperty("birthDate")
    private String birthDate;
    
    /**
     * The user's address.
     */
    @JsonProperty("address")
    private String address;
    
    /**
     * The role of the user (e.g., "client", "restaurateur").
     */
    @JsonIgnore
    private String role;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default constructor.
     * <p>
     * Initializes a new {@code User} object without any attributes set.
     * </p>
     */
    public User() {}
    
    /**
     * Constructor that initializes a {@code User} object with the specified attributes.
     * <p>
     * This constructor initializes the {@code User} object's name, username, password, birthDate, and address.
     * </p>
     *
     * @param id the user's id
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param birthDate the user's birthDate
     * @param address the user's address
     * @param username the user's username
     * @param email the user's email
     * @param password the user's password
     */
    public User(int id, String firstName, String lastName, String birthDate, String address, String username, String email, String password) 
    {
        this(firstName, lastName, birthDate, address, username, email, password);
        this.setId(id);    
    }
    
    public User(String firstName, String lastName, String birthDate, String address, String username, String email, String password)
    {
        this.setFirstNameNormalized(StringUtils.normalize(this.getFirstName()));
        this.setLastName(lastName);
        this.setLastNameNormalized(StringUtils.normalize(this.getLastName()));
        this.setUsername(username);
        this.setPassword(password);
        this.setBirthDate(birthDate);
        this.setAddress(address);        
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    /**
     * Returns the user's id.
     *
     * @return the user's id
     */
    public final int getId() {
        return id;
    }
    
    /**
     * Sets the user's id.
     *
     * @param id the new id to set
     */
    public final void setId(int id) {
        this.id = id;
    }
    
    /**
     * Returns the user's first name.
     *
     * @return the user's first name
     */
    public final String getFirstName() {
        return firstName;
    }
    
    /**
     * Sets the user's first name.
     *
     * @param firstName the new first name to set
     */
    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the user's first name normalized.
     *
     * @return the user's first name normalized
     */
    public final String getFirstNameNormalized() {
        return firstNameNormalized;
    }

    /**
     * Sets the user's first name normalized.
     *
     * @param firstNameNormalized the new first name normalized to set
     */
    private void setFirstNameNormalized(String firstNameNormalized) {
        this.firstNameNormalized = firstNameNormalized;
    }
    
    /**
     * Returns the user's last name.
     *
     * @return the user's last name
     */
    public final String getLastName() {
        return lastName;
    }
    
    /**
     * Sets the user's last name.
     *
     * @param lastName the new last name to set
     */
    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the user's last name normalized.
     *
     * @return the user's last name normalized
     */
    public final String getLastNameNormalized() {
        return lastNameNormalized;
    }

    /**
     * Sets the user's first name normalized.
     *
     * @param lastNameNormalized the new last name normalized to set
     */
    private void setLastNameNormalized(String lastNameNormalized) {
        this.lastNameNormalized = lastNameNormalized;
    }
    
    /**
     * Returns the user's birthDate.
     *
     * @return the user's birthDate
     */
    public final String getBirthDate() {
        return birthDate;
    }
    
    /**
     * Sets the user's birthDate.
     *
     * @param birthDate the new birthDate to set
     */
    public final void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    /**
     * Returns the user's email.
     *
     * @return the user's email
     */
    public final String getEmail() {
        return email;
    }
    
    /**
     * Sets the user's email
     *
     * @param email the new email to set
     */
    public final void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Returns the user's address.
     *
     * @return the user's address
     */
    public final String getAddress() {
        return address;
    }
    
    /**
     * Sets the user's address.
     *
     * @param address the new address to set
     */
    public final void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Returns the user's username.
     *
     * @return the user's username
     */
    public final String getUsername() {
        return username;
    }
    
    /**
     * Sets the user's username.
     *
     * @param username the new username to set
     */
    public final void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * Returns the user's password.
     *
     * @return the user's password
     */
    public final String getPassword() {
        return password;
    }
    
    /**
     * Sets the user's password.
     *
     * @param password the new password to set
     */
    public final void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Returns the user's role.
     * <p>
     * The role determines the type of user, e.g., "client", "restaurateur".
     * </p>
     *
     * @return the user's role
     */
    @JsonIgnore
    public final String getRole() {
        return role;
    }
    
    /**
     * Sets the user's role.
     *
     * @param role the new role to set
     */
    public final void setRole(String role) {
        this.role = role;
    }
    //</editor-fold>
}
