package AdminServices.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Predrag on 20.03.2017..
 */
@Entity
@Table(name = "userrole", schema = "adminmodule", catalog = "")
public class UserroleEntity {
    private int idUser;

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
}
