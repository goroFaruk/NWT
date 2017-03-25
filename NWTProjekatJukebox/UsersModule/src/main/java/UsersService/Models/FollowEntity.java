package UsersService.Models;

import javax.persistence.*;

/**
 * Created by Šahin on 25.3.2017.
 */
@Entity
@Table(name = "follow", schema = "nwtusers", catalog = "")
public class FollowEntity {
    private int id;
    private int iduser;
    private int idlista;

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
    @Column(name = "iduser", nullable = false)
    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Basic
    @Column(name = "idlista", nullable = false)
    public int getIdlista() {
        return idlista;
    }

    public void setIdlista(int idlista) {
        this.idlista = idlista;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowEntity that = (FollowEntity) o;

        if (id != that.id) return false;
        if (iduser != that.iduser) return false;
        if (idlista != that.idlista) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + iduser;
        result = 31 * result + idlista;
        return result;
    }
}
