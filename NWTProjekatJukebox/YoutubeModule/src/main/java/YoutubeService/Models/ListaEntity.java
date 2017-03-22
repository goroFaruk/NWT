package YoutubeService.Models;

import javax.persistence.*;

/**
 * Created by fare_ on 20.03.2017..
 */
@Entity
@Table(name = "lista", schema = "pjesme", catalog = "")
public class ListaEntity {
    private int id;
    private int idPjesma;
    private int idUser;
    private int idFollow;
    private String naziv;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "idPjesma")
    public int getIdPjesma() {
        return idPjesma;
    }

    public void setIdPjesma(int idPjesma) {
        this.idPjesma = idPjesma;
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
    @Column(name = "idFollow")
    public int getIdFollow() {
        return idFollow;
    }

    public void setIdFollow(int idFollow) {
        this.idFollow = idFollow;
    }

    @Basic
    @Column(name = "Naziv")
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListaEntity that = (ListaEntity) o;

        if (id != that.id) return false;
        if (idPjesma != that.idPjesma) return false;
        if (idUser != that.idUser) return false;
        if (idFollow != that.idFollow) return false;
        if (naziv != null ? !naziv.equals(that.naziv) : that.naziv != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idPjesma;
        result = 31 * result + idUser;
        result = 31 * result + idFollow;
        result = 31 * result + (naziv != null ? naziv.hashCode() : 0);
        return result;
    }
}
