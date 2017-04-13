package AdminServices.Models;

import javax.persistence.*;

/**
 * Created by Predrag on 28.03.2017..
 */
@Entity
@Table(name = "roles", schema = "adminmodule", catalog = "")
public class RolesEntity {
    private int idroles;
    private String nazivRole;

    @Id
    @Column(name = "idroles")
    public int getIdroles() {
        return idroles;
    }

    public void setIdroles(int idroles) {
        this.idroles = idroles;
    }

    @Basic
    @Column(name = "naziv_role")
    public String getNazivRole() {
        return nazivRole;
    }

    public void setNazivRole(String nazivRole) {
        this.nazivRole = nazivRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RolesEntity that = (RolesEntity) o;

        if (idroles != that.idroles) return false;
        if (nazivRole != null ? !nazivRole.equals(that.nazivRole) : that.nazivRole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idroles;
        result = 31 * result + (nazivRole != null ? nazivRole.hashCode() : 0);
        return result;
    }
}