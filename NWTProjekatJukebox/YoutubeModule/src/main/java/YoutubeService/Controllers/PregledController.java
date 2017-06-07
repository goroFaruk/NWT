package YoutubeService.Controllers;

import YoutubeService.Models.ListapjesamaEntity;
import YoutubeService.Models.Message;
import YoutubeService.Models.PregledEntity;
import YoutubeService.Repository.PregledRepository;
import org.apache.derby.client.am.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by fare_ on 24.03.2017..
 */
@RestController
@RequestMapping(value = "/pregledi")
public class PregledController {

    @Autowired
    private PregledRepository repo;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String InsertPregled(@RequestParam Integer idPjesme)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date datum= new Date();
            List<PregledEntity> lista=repo.findBySongId(idPjesme);
            if(lista.size()==0){
                PregledEntity pregledEntity=new PregledEntity();
                pregledEntity.setBrojPregleda(1);
                pregledEntity.setDatum(datum);
                pregledEntity.setIdPjesma(idPjesme);
                repo.save(pregledEntity);
            }else {
                for (int i = 0; i < lista.size(); i++) {
                    if(lista.get(i).getDatum().compareTo(datum)==-1)
                       repo.insertPregled(idPjesme,lista.get(i).getId());
                }
            }
        }
        catch (Exception e)
        {
            return e.getMessage();
        }

        return "OK";
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public PregledEntity getById(@PathVariable("id") int id)
    {
        PregledEntity pregledEntity = repo.findOne(id);
        return pregledEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PregledEntity> getAll() {
        List<PregledEntity> pregled = (List<PregledEntity>) repo.findAll();
        return pregled;
    }

    @RequestMapping(value = "/delete", method= RequestMethod.POST)
    public ResponseEntity<Message> deleteById(@RequestParam int id) {
        try {
            repo.delete(id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Pregled"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Pregled is successfully deleted","Pregled"),HttpStatus.OK);
    }

    //NEDEFINISANO ???
    //TODO: srediti ovaj klinac i skontati sta treba
    @RequestMapping(value= "/forUser", method = RequestMethod.GET)
    public ResponseEntity<List<PregledEntity>> getAllViewsForUser(@RequestParam Integer idUser) {
        List<PregledEntity> pregledEntities = repo.findAllById(idUser);
        return new ResponseEntity<>(pregledEntities, HttpStatus.OK) ;
    }

    @RequestMapping(value= "/forSong", method = RequestMethod.GET)
    public ResponseEntity<List<PregledEntity>> getAllFollowsForList(@RequestParam Integer idSong) {
        List<PregledEntity> pregledEntities = repo.findAllBySongId(idSong);
        return new ResponseEntity<List<PregledEntity>>(pregledEntities,HttpStatus.OK) ;
    }
}
