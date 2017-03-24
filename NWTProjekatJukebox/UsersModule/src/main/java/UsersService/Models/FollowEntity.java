package UsersService.Models;

import javax.persistence.*;

/**
 * Created by Šahin on 24.3.2017.
 */
@Entity
@Table(name = "follow", schema = "nwtusers", catalog = "")
public class FollowEntity {
    private int idUser;
    private int idLista;
    private int id;

    @Basic
    @Column(name = "idUser", nullable = false)
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "idLista", nullable = false)
    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowEntity that = (FollowEntity) o;

        if (idUser != that.idUser) return false;
        if (idLista != that.idLista) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser;
        result = 31 * result + idLista;
        result = 31 * result + id;
        return result;
    }
}
