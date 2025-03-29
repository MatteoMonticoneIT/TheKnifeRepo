package theknife.obj.user;

import java.util.Date;

/**
 * @author Matteo Monticone     761701 (CO)
 * @author Damiano De Mutiis    ------ (CO)f
 * @author Matteo Porto Bonacci ------ (CO)
 * @author Mattia Tamburo       ------ (CO)f
 */

public class User {
    private String name;
    private String surname;
    private String username;
    private String password;
    private Date birthday;
    private String domicile;
    private String role;

    public User() {
    }

    public User(String name, String surname, String username, String password, Date birthday, String domicile, String role) {
        setName(name);
        setSurname(surname);
        setUsername(username);
        setPassword(password);
        setBirthday(birthday);
        setDomicile(domicile);
        setRole(role);
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
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
