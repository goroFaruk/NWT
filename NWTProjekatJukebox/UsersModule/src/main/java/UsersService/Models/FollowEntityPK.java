package UsersService.Models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Šahin on 20.3.2017.
 */
public class FollowEntityPK implements Serializable {
    private int idUser;
    private String idLista;

    @Column(name = "idUser", nullable = false)
    @Id
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Column(name = "idLista", nullable = false, length = 45)
    @Id
    public String getIdLista() {
        return idLista;
    }

    public void setIdLista(String idLista) {
        this.idLista = idLista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowEntityPK that = (FollowEntityPK) o;

        if (idUser != that.idUser) return false;
        if (idLista != null ? !idLista.equals(that.idLista) : that.idLista != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + (idLista != null ? idLista.hashCode() : 0);
        return result;
    }
}
