package theknife.obj.user;

import java.time.LocalDate;

/**
 * Represents a generic user in the system.
 * <p>
 * This class contains common attributes and behaviors for all types of users, such as clients and restaurateurs.<br>
 * A user has personal information including name, username, password, birthday, domicile, and a role.
 * </p>
 * 
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */
public class User {
    
    /**
     * The user's first name.
     */
    private String firstName;
    
    /**
     * The user's last name.
     */
    private String lastName;
    
    /**
     * The user's username.
     */
    private String username;
    
    /**
     * The user's password.
     */
    private String password;
    
    /**
     * The user's birthday.
     */
    private LocalDate birthday;
    
    /**
     * The user's domicile (address).
     */
    private String domicile;
    
    /**
     * The role of the user (e.g., "client", "restaurateur").
     */
    private String role;

    /**
     * Default constructor.
     * <p>
     * Initializes a new user without any attributes set.
     * </p>
     */
    public User() {
    }

    /**
     * Constructor that initializes a user with the specified attributes.
     * <p>
     * This constructor initializes the user's name, username, password, birthday, and domicile.
     * </p>
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param username the user's username
     * @param password the user's password
     * @param birthday the user's birthday
     * @param domicile the user's domicile (address)
     */
    public User(String firstName, String lastName, String username, String password, LocalDate birthday, String domicile) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setBirthday(birthday);
        this.setDomicile(domicile);
    }

    /**
     * Returns the user's first name.
     *
     * @return the user's first name
     */
    public String getFirstName() {
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
     * Returns the user's last name.
     *
     * @return the user's last name
     */
    public String getLastName() {
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
     * Returns the user's username.
     *
     * @return the user's username
     */
    public String getUsername() {
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
    public String getPassword() {
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
     * Returns the user's birthday.
     *
     * @return the user's birthday
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * Sets the user's birthday.
     *
     * @param birthday the new birthday to set
     */
    public final void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Returns the user's domicile (address).
     *
     * @return the user's domicile
     */
    public String getDomicile() {
        return domicile;
    }

    /**
     * Sets the user's domicile (address).
     *
     * @param domicile the new domicile to set
     */
    public final void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    /**
     * Returns the user's role.
     * <p>
     * The role determines the type of user, e.g., "client", "restaurateur".
     * </p>
     *
     * @return the user's role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role the new role to set
     */
    public void setRole(String role) {
        this.role = role;
    }
}
