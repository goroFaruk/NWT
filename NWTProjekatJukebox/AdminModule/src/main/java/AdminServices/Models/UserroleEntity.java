package AdminServices.Models;

import javax.persistence.*;

/**
 * Created by Predrag on 20.03.2017..
 */
@Entity
@Table(name = "userrole", schema = "adminmodule", catalog = "")
@IdClass(UserroleEntityPK.class)
public class UserroleEntity {
    private int idUser;
    private int iduserRole;
    private String idRole;

    @Id
    @Column(name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserroleEntity that = (UserroleEntity) o;

        if (idUser != that.idUser) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idUser;
    }

    @Id
    @Column(name = "iduserRole")
    public int getIduserRole() {
        return iduserRole;
    }

    public void setIduserRole(int iduserRole) {
        this.iduserRole = iduserRole;
    }

    @Id
    @Column(name = "idRole")
    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }
}
