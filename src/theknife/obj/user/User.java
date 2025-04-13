package theknife.obj.user;

import java.time.LocalDate;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private LocalDate birthday;
    private String domicile;
    private String role;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, LocalDate birthday, String domicile) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setBirthday(birthday);
        this.setDomicile(domicile);
    }

    public String getFirstName() {
        return firstName;
    }

    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public final void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getDomicile() {
        return domicile;
    }

    public final void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
