package YoutubeService.Models;

import javax.persistence.*;

/**
 * Created by fare_ on 20.03.2017..
 */
@Entity
@Table(name = "pregled", schema = "pjesme", catalog = "")
public class PregledEntity {
    private int id;
    private int idPjesma;
    private int datum;
    private int brojPregleda;

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
    @Column(name = "datum")
    public int getDatum() {
        return datum;
    }

    public void setDatum(int datum) {
        this.datum = datum;
    }

    @Basic
    @Column(name = "brojPregleda")
    public int getBrojPregleda() {
        return brojPregleda;
    }

    public void setBrojPregleda(int brojPregleda) {
        this.brojPregleda = brojPregleda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PregledEntity that = (PregledEntity) o;

        if (id != that.id) return false;
        if (idPjesma != that.idPjesma) return false;
        if (datum != that.datum) return false;
        if (brojPregleda != that.brojPregleda) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idPjesma;
        result = 31 * result + datum;
        result = 31 * result + brojPregleda;
        return result;
    }
}
