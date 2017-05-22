package UsersService.Models;

import javax.persistence.*;

/**
 * Created by Šahin on 20.3.2017.
 */
@Entity
@Table(name = "user", schema = "nwtusers", catalog = "")
public class UserEntity {
    private int id;
    private String email;
    private String pasword;
    private String username;
    private Integer idrole;
    private boolean enabled;
    private String token;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "pasword", nullable = false, length = 30)
    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "idrole", nullable = true)
    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    @Basic
    @Column(name = "enabled")
    public boolean getEnabled(){ return enabled;}

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (pasword != null ? !pasword.equals(that.pasword) : that.pasword != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (idrole != null ? !idrole.equals(that.idrole) : that.idrole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pasword != null ? pasword.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (idrole != null ? idrole.hashCode() : 0);
        return result;
    }
}
