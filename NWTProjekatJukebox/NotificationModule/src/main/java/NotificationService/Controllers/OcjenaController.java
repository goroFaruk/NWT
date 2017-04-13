package NotificationService.Controllers;

import NotificationService.Models.OcjenaEntity;
import NotificationService.Models.Message;
import NotificationService.Repository.OcjenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;


/**
 * Created by Enver on 26.3.2017.
 */
@RestController
@RequestMapping(value = "/ocjena")
public class OcjenaController {
    @Autowired
    private OcjenaRepository repo;

    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public String insertTestOcjena() {
        OcjenaEntity u = new OcjenaEntity();

        try{
            repo.save(u);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ResponseEntity<OcjenaEntity> getById(@PathVariable("id") int id) {
        OcjenaEntity ocjena = repo.findOne(id);
        return new  ResponseEntity<OcjenaEntity>(ocjena, HttpStatus.OK);
    }
    // vraca ocjene po id-u liste
    @RequestMapping(value = "/byListaID/{lista_id}", method= RequestMethod.GET)
    public ResponseEntity<List<OcjenaEntity>> getByListaId(@PathVariable("lista_id") int lista_id) {
        List<OcjenaEntity> ocjene = repo.findAllByListaId(lista_id);
        return new  ResponseEntity<List<OcjenaEntity>>(ocjene, HttpStatus.OK);
    }

    @RequestMapping(value="/",method = RequestMethod.GET)
    public ResponseEntity<Page<OcjenaEntity>> getAll(Pageable pageable) {
        Page<OcjenaEntity> ocjena = (Page<OcjenaEntity>) repo.findAll();
        return new ResponseEntity<Page<OcjenaEntity>>(ocjena,HttpStatus.OK) ;
    }

    @RequestMapping(value="/ocjena",method = RequestMethod.POST)
    public ResponseEntity<Message> insertOcjena(@RequestParam int korisnikID, int listaID, int
            _ocjena){
        OcjenaEntity ocjena=new OcjenaEntity(){};
        ocjena.setKorisnikId(korisnikID);
        ocjena.setListaId(listaID);
        ocjena.setOcjena(_ocjena);
        int id;
        try{
            id=repo.save(ocjena).getId();
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage
                    (),"Ocjena"),HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Ocjena is successfully added","Follow"),HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteocjena", method= RequestMethod.POST)
    public ResponseEntity<Message> deleteById(@RequestParam int id) {
        try {
            repo.delete(id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage
                    (),"Ocjena"),HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Ocjena is successfully deleted","Follow"),HttpStatus.OK);
    }

    @RequestMapping(value = "/changekorisnikid", method= RequestMethod.POST)
    public ResponseEntity<Message> izmjenaKorisnika(@RequestParam int korisnik_id, int id) {
        try {
            repo.updateKorisnik(korisnik_id, id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage
                    (),"Ocjena"),HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Ocjena is successfully changed","Follow"),HttpStatus.OK);
    }


    @RequestMapping(value = "/changelistaid", method= RequestMethod.POST)
    public ResponseEntity<Message> izmjenaListe(@RequestParam int lista_id, int id) {
        try {
            repo.updateLista(lista_id, id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage
                    (),"Ocjena"),HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Ocjena is successfully changed","Follow"),HttpStatus.OK);
    }


    @RequestMapping(value = "/changeocjenaid", method= RequestMethod.POST)
    public ResponseEntity<Message> izmjenaOcjene(@RequestParam int ocjena, int id) {
        try {
            repo.updateOcjena(ocjena, id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage
                    (),"Ocjena"),HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Ocjena is successfully changed","Follow"),HttpStatus.OK);
    }

}
