package NotificationService.Controllers;
import NotificationService.Models.Message;
import NotificationService.Models.NotifikacijaEntity;
import NotificationService.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

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

    @RequestMapping(value = "/getById/{id}", method= RequestMethod.GET)
    public ResponseEntity<NotifikacijaEntity> getById(@PathVariable("id") int id) {
        NotifikacijaEntity notifikacija = repo.findOne(id);
        return new ResponseEntity<NotifikacijaEntity>(notifikacija, HttpStatus.OK);
    }

    @RequestMapping(value= "/getAll", method = RequestMethod.GET)
    public ResponseEntity<Page<NotifikacijaEntity>> getAll(Pageable pageable) {
        Page<NotifikacijaEntity> notifikacije = repo.findAll(pageable);
        return new ResponseEntity<Page<NotifikacijaEntity>>(notifikacije,HttpStatus.OK) ;
    }

    //vraca notifikacije za korisnika po ID-u
    @RequestMapping(value= "/byKorisnikID/{korisnikID}", method = RequestMethod.GET)
    public ResponseEntity<List<NotifikacijaEntity>> getByKorisnikId(@PathVariable("korisnikID")
                                                                            int korisnikID)
    {
        List<NotifikacijaEntity> ocjene = repo.findAllByKorisnikId(korisnikID);
        return new  ResponseEntity<List<NotifikacijaEntity>>(ocjene, HttpStatus.OK);
    }


    @RequestMapping(value="/insertNotification",method = RequestMethod.POST)
    public ResponseEntity<Message> insertNotification(@RequestParam int korisnikID, int listaID,
                                                      int ocjenaID){
        NotifikacijaEntity notifikacija=new NotifikacijaEntity();
        notifikacija.setKorisnikId(korisnikID);
        notifikacija.setListaId(listaID);
        notifikacija.setOcjenaId(ocjenaID);
        int id;
        try{
            id=repo.save(notifikacija).getId();
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage
                    (),"Notification"),HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<Message>(new Message("Notification is successfully added","Follow"),HttpStatus.OK);

    }


    @RequestMapping(value = "/deletenotifikacija", method= RequestMethod.POST)
    public ResponseEntity<Message> deleteById(@RequestParam int id) {
        try {
            repo.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<Message>( new Message(e.getMessage
                    (),"Notification"),HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<Message>(new Message("Notification is successfully deleted","Follow"),HttpStatus.OK);
    }


    @RequestMapping(value = "/changekorisnikid", method= RequestMethod.POST)
    public ResponseEntity<Message> izmjenaKorisnika(@RequestParam int korisnik_id, int id) {
        try {
            repo.updateKorisnik(korisnik_id, id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage
                    (),"Notification"),HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<Message>(new Message("Notification is successfully changed","Follow"),HttpStatus.OK);
    }


    @RequestMapping(value = "/changelistaid", method= RequestMethod.POST)
    public ResponseEntity<Message> izmjenaListe(@RequestParam int lista_id, int id) {
        try {
            repo.updateLista(lista_id, id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage
                    (),"Notification"),HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<Message>(new Message("Notification is successfully changed","Follow"),HttpStatus.OK);
    }


    @RequestMapping(value = "/changeocjenaid", method= RequestMethod.POST)
    public ResponseEntity<Message> izmjenaOcjene(@RequestParam int ocjena, int id) {
        try {
            repo.updateOcjena(ocjena, id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage
                    (),"Notification"),HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<Message>(new Message("Notification is successfully changed","Follow"),HttpStatus.OK);
    }

    @RequestMapping(value = "/changedatum", method= RequestMethod.POST)
    public ResponseEntity<Message> izmjenaDatuma(@RequestParam Date datum, int id) {
        try {
            repo.updateDatumNotifikacije(datum, id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage
                    (),"Notification"),HttpStatus.EXPECTATION_FAILED);
        }

        return new ResponseEntity<Message>(new Message("Notification is successfully changed","Follow"),HttpStatus.OK);
    }


}
