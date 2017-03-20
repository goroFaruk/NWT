package NotificationService.Models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Enver on 19.3.2017.
 */
@Entity
@Table(name = "notifikacija", schema = "nwtjukebox", catalog = "")
public class NotifikacijaEntity {
    private int id;
    private Integer korisnikId;
    private Integer listaId;
    private Timestamp datumNotifikacije;
    private Integer ocjenaId;

    @Basic
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "korisnik_id")
    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    @Basic
    @Column(name = "lista_id")
    public Integer getListaId() {
        return listaId;
    }

    public void setListaId(Integer listaId) {
        this.listaId = listaId;
    }

    @Basic
    @Column(name = "datumNotifikacije")
    public Timestamp getDatumNotifikacije() {
        return datumNotifikacije;
    }

    public void setDatumNotifikacije(Timestamp datumNotifikacije) {
        this.datumNotifikacije = datumNotifikacije;
    }

    @Basic
    @Column(name = "ocjena_id")
    public Integer getOcjenaId() {
        return ocjenaId;
    }

    public void setOcjenaId(Integer ocjenaId) {
        this.ocjenaId = ocjenaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotifikacijaEntity that = (NotifikacijaEntity) o;

        if (id != that.id) return false;
        if (korisnikId != null ? !korisnikId.equals(that.korisnikId) : that.korisnikId != null) return false;
        if (listaId != null ? !listaId.equals(that.listaId) : that.listaId != null) return false;
        if (datumNotifikacije != null ? !datumNotifikacije.equals(that.datumNotifikacije) : that.datumNotifikacije != null)
            return false;
        if (ocjenaId != null ? !ocjenaId.equals(that.ocjenaId) : that.ocjenaId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (korisnikId != null ? korisnikId.hashCode() : 0);
        result = 31 * result + (listaId != null ? listaId.hashCode() : 0);
        result = 31 * result + (datumNotifikacije != null ? datumNotifikacije.hashCode() : 0);
        result = 31 * result + (ocjenaId != null ? ocjenaId.hashCode() : 0);
        return result;
    }
}
