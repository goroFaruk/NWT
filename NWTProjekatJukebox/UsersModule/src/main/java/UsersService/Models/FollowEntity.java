package UsersService.Models;

import javax.persistence.*;

/**
 * Created by Šahin on 20.3.2017.
 */
@Entity
@Table(name = "follow", schema = "nwtusers", catalog = "")
@IdClass(FollowEntityPK.class)
public class FollowEntity {
    private int idUser;
    private String idLista;

    @Id
    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "idLista", nullable = false, length = 45)
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

        FollowEntity that = (FollowEntity) o;

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
