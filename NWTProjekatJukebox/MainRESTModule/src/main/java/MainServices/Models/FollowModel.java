package MainServices.Models;


/**
 * Created by Šahin on 25.3.2017.
 */

public class FollowModel {
    private int id;
    private int iduser;
    private int idlista;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public int getIdlista() {
        return idlista;
    }

    public void setIdlista(int idlista) {
        this.idlista = idlista;
    }
}
