package NotificationService.Models;

import javax.persistence.*;

/**
 * Created by Enver on 19.3.2017.
 */
@Entity
@Table(name = "ocjena", schema = "nwtjukebox", catalog = "")
public class OcjenaEntity {
    private int id;
    private int listaId;
    private Integer ocjena;
    private Integer korisnikId;

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
    @Column(name = "lista_id")
    public int getListaId() {
        return listaId;
    }

    public void setListaId(int listaId) {
        this.listaId = listaId;
    }

    @Basic
    @Column(name = "ocjena")
    public Integer getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }

    @Basic
    @Column(name = "korisnik_id")
    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OcjenaEntity that = (OcjenaEntity) o;

        if (id != that.id) return false;
        if (listaId != that.listaId) return false;
        if (ocjena != null ? !ocjena.equals(that.ocjena) : that.ocjena != null) return false;
        if (korisnikId != null ? !korisnikId.equals(that.korisnikId) : that.korisnikId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + listaId;
        result = 31 * result + (ocjena != null ? ocjena.hashCode() : 0);
        result = 31 * result + (korisnikId != null ? korisnikId.hashCode() : 0);
        return result;
    }
}
