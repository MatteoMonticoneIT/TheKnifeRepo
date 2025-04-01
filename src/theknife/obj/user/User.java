package theknife.obj.user;

import java.time.LocalDate;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)
 */

public class User {
    private String name;
    private String surname;
    private String username;
    private String password;
    private LocalDate birthday;
    private String domicile;
    private String role;

    public User() {
    }

    public User(String name, String surname, String username, String password, LocalDate birthday, String domicile) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.domicile = domicile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
