package AdminServices.Models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Predrag on 22.03.2017..
 */
public class MainEntityPK implements Serializable {
    private int idmain;
    private String idUser;

    @Column(name = "idmain")
    @Id
    public int getIdmain() {
        return idmain;
    }

    public void setIdmain(int idmain) {
        this.idmain = idmain;
    }

    @Column(name = "idUser")
    @Id
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

        MainEntityPK that = (MainEntityPK) o;

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
