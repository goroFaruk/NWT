package AdminServices.Models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Predrag on 22.03.2017..
 */
public class UserroleEntityPK implements Serializable {
    private int iduserRole;
    private String idRole;

    @Column(name = "iduserRole")
    @Id
    public int getIduserRole() {
        return iduserRole;
    }

    public void setIduserRole(int iduserRole) {
        this.iduserRole = iduserRole;
    }

    @Column(name = "idRole")
    @Id
    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserroleEntityPK that = (UserroleEntityPK) o;

        if (iduserRole != that.iduserRole) return false;
        if (idRole != null ? !idRole.equals(that.idRole) : that.idRole != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iduserRole;
        result = 31 * result + (idRole != null ? idRole.hashCode() : 0);
        return result;
    }
}