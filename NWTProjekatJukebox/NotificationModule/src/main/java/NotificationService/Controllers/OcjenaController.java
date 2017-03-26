package NotificationService.Controllers;

import NotificationService.Models.OcjenaEntity;
import NotificationService.Repository.OcjenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;


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
    public OcjenaEntity getById(@PathVariable("id") int id) {
        OcjenaEntity ocjena = repo.findOne(id);
        return ocjena;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<OcjenaEntity> getAll(Pageable pageable) {
        Page<OcjenaEntity> ocjena = (Page<OcjenaEntity>) repo.findAll();
        return ocjena;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertOcjena(@RequestParam int korisnikID, int listaID, int _ocjena){
        OcjenaEntity ocjena=new OcjenaEntity(){};
        ocjena.setKorisnikId(korisnikID);
        ocjena.setListaId(listaID);
        ocjena.setOcjena(_ocjena);
        int id;
        try{
            id=repo.save(ocjena).getId();
        } catch (Exception e){
            return e.getMessage();
        }

        return "ID: " + String.valueOf(id);
    }

    @RequestMapping(value = "/deleteocjena", method= RequestMethod.POST)
    public String deleteById(@RequestParam int id) {
        try {
            repo.delete(id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Uspjesno ste izbrisali ocjenu.";
    }

    @RequestMapping(value = "/changekorisnikid", method= RequestMethod.POST)
    public String izmjenaKorisnika(@RequestParam int korisnik_id, int id) {
        try {
            repo.updateKorisnik(korisnik_id, id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Uspjesno ste izmjenili korisnika.";
    }


    @RequestMapping(value = "/changelistaid", method= RequestMethod.POST)
    public String izmjenaListe(@RequestParam int lista_id, int id) {
        try {
            repo.updateLista(lista_id, id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Uspjesno ste izmjenili listu.";
    }


    @RequestMapping(value = "/changeocjenaid", method= RequestMethod.POST)
    public String izmjenaOcjene(@RequestParam int ocjena, int id) {
        try {
            repo.updateOcjena(ocjena, id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Uspjesno ste izmjenili ocjenu.";
    }

}
