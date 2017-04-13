package AdminServices.Models;

import javax.persistence.*;

/**
 * Created by Predrag on 22.03.2017..
 */
@Entity
@Table(name = "main", schema = "adminmodule", catalog = "")
@IdClass(MainEntityPK.class)
public class MainEntity {
    private int idmain;
    private String idUser;

    @Id
    @Column(name = "idmain")
    public int getIdmain() {
        return idmain;
    }

    public void setIdmain(int idmain) {
        this.idmain = idmain;
    }

    @Id
    @Column(name = "idUser")
    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MainEntity that = (MainEntity) o;

        if (idmain != that.idmain) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idmain;
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        return result;
    }
}