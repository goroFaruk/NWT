package NotificationService.Controllers;

import NotificationService.Models.NotifikacijaEntity;
import NotificationService.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * Created by Enver on 19.3.2017.
 */
@RestController
@RequestMapping(value="/notification")
public class NotificationController {
    @Autowired
    private NotificationRepository repo;

    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public String insertTestNotification() {
        NotifikacijaEntity u = new NotifikacijaEntity();

        try{
            repo.save(u);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public NotifikacijaEntity getById(@PathVariable("id") int id) {
        NotifikacijaEntity notifikacija = repo.findOne(id);
        return notifikacija;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<NotifikacijaEntity> getAll(Pageable pageable) {
        Page<NotifikacijaEntity> notifikacije = repo.findAll(pageable);
        return notifikacije;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertNotification(@RequestParam int korisnikID, int listaID, int ocjenaID){
        NotifikacijaEntity notifikacija=new NotifikacijaEntity();
        notifikacija.setKorisnikId(korisnikID);
        notifikacija.setListaId(listaID);
        notifikacija.setOcjenaId(ocjenaID);
        int id;
        try{
            id=repo.save(notifikacija).getId();
        } catch (Exception e){
            return e.getMessage();
        }

        return "ID: " + String.valueOf(id);

    }


    @RequestMapping(value = "/deletenotifikacija", method= RequestMethod.POST)
    public String deleteById(@RequestParam int id) {
        try {
            repo.delete(id);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Uspjesno ste izbrisali notifikaciju.";
    }


    @RequestMapping(value = "/changekorisnikid", method= RequestMethod.POST)
    public String izmjenaKorisnika(@RequestParam int korisnik_id, int id) {
        try {
            repo.updateKorisnik(korisnik_id, id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Uspjesno ste izmjenili korisnika notifikacije.";
    }


    @RequestMapping(value = "/changelistaid", method= RequestMethod.POST)
    public String izmjenaListe(@RequestParam int lista_id, int id) {
        try {
            repo.updateLista(lista_id, id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Uspjesno ste izmjenili listu notifikacije.";
    }


    @RequestMapping(value = "/changeocjenaid", method= RequestMethod.POST)
    public String izmjenaOcjene(@RequestParam int ocjena, int id) {
        try {
            repo.updateOcjena(ocjena, id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Uspjesno ste izmjenili ocjenu notifikacije.";
    }

    @RequestMapping(value = "/changedatum", method= RequestMethod.POST)
    public String izmjenaDatuma(@RequestParam Date datum, int id) {
        try {
            repo.updateDatumNotifikacije(datum, id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Uspjesno ste izmjenili datum notifikacije.";
    }


}
