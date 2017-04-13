package AdminServices.Models;

import javax.persistence.*;

/**
 * Created by Predrag on 28.03.2017..
 */
@Entity
@Table(name = "maintable", schema = "adminmodule", catalog = "")
public class MaintableEntity {
    private int idMain;
    private Integer idNotifikacije;
    private Integer idUser;

    @Id
    @Column(name = "id_main")
    public int getIdMain() {
        return idMain;
    }

    public void setIdMain(int idMain) {
        this.idMain = idMain;
    }

    @Basic
    @Column(name = "id_notifikacije")
    public Integer getIdNotifikacije() {
        return idNotifikacije;
    }

    public void setIdNotifikacije(Integer idNotifikacije) {
        this.idNotifikacije = idNotifikacije;
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

        MaintableEntity that = (MaintableEntity) o;

        if (idMain != that.idMain) return false;
        if (idNotifikacije != null ? !idNotifikacije.equals(that.idNotifikacije) : that.idNotifikacije != null)
            return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idMain;
        result = 31 * result + (idNotifikacije != null ? idNotifikacije.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        return result;
    }
}