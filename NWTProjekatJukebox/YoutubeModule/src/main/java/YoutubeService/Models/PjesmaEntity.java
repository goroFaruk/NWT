package YoutubeService.Models;

import javax.persistence.*;

/**
 * Created by fare_ on 20.03.2017..
 */
@Entity
@Table(name = "pjesma", schema = "pjesme", catalog = "")
public class PjesmaEntity {
    private String urlPlesme;
    private int id;

    @Basic
    @Column(name = "urlPlesme")
    public String getUrlPlesme() {
        return urlPlesme;
    }

    public void setUrlPlesme(String urlPlesme) {
        this.urlPlesme = urlPlesme;
    }

    @Id
    @Basic
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

        PjesmaEntity that = (PjesmaEntity) o;

        if (id != that.id) return false;
        if (urlPlesme != null ? !urlPlesme.equals(that.urlPlesme) : that.urlPlesme != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = urlPlesme != null ? urlPlesme.hashCode() : 0;
        result = 31 * result + id;
        return result;
    }
}
