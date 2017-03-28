package AdminServices.Models;

import javax.persistence.*;

/**
 * Created by Predrag on 28.03.2017..
 */
@Entity
@Table(name = "userrole", schema = "adminmodule", catalog = "")
public class UserroleEntity {
    private int iduserRole;
    private String idRole;
    private Integer idUser;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iduser_role")
    public int getIduserRole() {
        return iduserRole;
    }

    public void setIduserRole(int iduserRole) {
        this.iduserRole = iduserRole;
    }

    @Basic
    @Column(name = "id_role")
    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    @Basic
    @Column(name = "id_user")
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserroleEntity that = (UserroleEntity) o;

        if (iduserRole != that.iduserRole) return false;
        if (idRole != null ? !idRole.equals(that.idRole) : that.idRole != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iduserRole;
        result = 31 * result + (idRole != null ? idRole.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        return result;
    }
}
