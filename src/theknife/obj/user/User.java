package theknife.obj.user;

import java.time.LocalDate;

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
     * The user's birthDate.
     */
    private LocalDate birthDate;
    
    /**
     * The user's address.
     */
    private String address;
    
    /**
     * The role of the user (e.g., "client", "restaurateur").
     */
    private String role;

    /**
     * Default constructor.
     * <p>
     * Initializes a new {@code User} object without any attributes set.
     * </p>
     */
    public User() {
    }

    /**
     * Constructor that initializes a {@code User} object with the specified attributes.
     * <p>
     * This constructor initializes the {@code User} object's name, username, password, birthDate, and address.
     * </p>
     *
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param username the user's username
     * @param password the user's password
     * @param birthDate the user's birthDate
     * @param address the user's address
     */
    public User(String firstName, String lastName, String username, String password, LocalDate birthDate, String address) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setBirthDate(birthDate);
        this.setAddress(address);
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
     * Returns the user's birthDate.
     *
     * @return the user's birthDate
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the user's birthDate.
     *
     * @param birthDate the new birthDate to set
     */
    public final void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Returns the user's address.
     *
     * @return the user's address
     */
    public String getAddress() {
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
