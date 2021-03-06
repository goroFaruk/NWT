package YoutubeService.Models;

import javax.persistence.*;

/**
 * Created by fare_ on 20.03.2017..
 */
@Entity
@Table(name = "listapjesama", schema = "pjesme", catalog = "")
public class ListapjesamaEntity {
    private int idLista;
    private int idPjesma;
    private int id;

    @Basic
    @Column(name = "idLista")
    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    @Basic
    @Column(name = "idPjesma")
    public int getIdPjesma() {
        return idPjesma;
    }

    public void setIdPjesma(int idPjesma) {
        this.idPjesma = idPjesma;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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

        ListapjesamaEntity that = (ListapjesamaEntity) o;

        if (idLista != that.idLista) return false;
        if (idPjesma != that.idPjesma) return false;
        if(id!=that.id) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = idLista;
        result = 31 * result + idPjesma;
        return result;
    }
}
