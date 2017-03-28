package AdminServices.Models;

import javax.persistence.*;

/**
 * Created by Predrag on 19.03.2017..
 */
@Entity
@Table(name = "maintable", schema = "adminmodule", catalog = "")
public class MaintableEntity {
    private int idMain;
    private int idUser;
    private int idNotifikacije;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMain")
    public int getIdMain() {
        return idMain;
    }

    public void setIdMain(int idMain) {
        this.idMain = idMain;
    }

    @Basic
    @Column(name = "idUser")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "idNotifikacije")
    public int getIdNotifikacije() {
        return idNotifikacije;
    }

    public void setIdNotifikacije(int idNotifikacije) {
        this.idNotifikacije = idNotifikacije;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MaintableEntity that = (MaintableEntity) o;

        if (idMain != that.idMain) return false;
        if (idUser != that.idUser) return false;
        if (idNotifikacije != that.idNotifikacije) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMain;
        result = 31 * result + idUser;
        result = 31 * result + idNotifikacije;
        return result;
    }
}
