package YoutubeService.Controllers;

import YoutubeService.Models.ListapjesamaEntity;
import YoutubeService.Models.Message;
import YoutubeService.Models.PregledEntity;
import YoutubeService.Repository.PregledRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by fare_ on 24.03.2017..
 */
public class PregledController {

    private PregledRepository repo;

    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public String InsertTestPregled()
    {
        PregledEntity p = new PregledEntity();
        p.setBrojPregleda(4);
        try
        {
            repo.save(p);
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
    public Page<PregledEntity> getAll(java.awt.print.Pageable pageable) {
        Page<PregledEntity> pregled = (Page<PregledEntity>) repo.findAll();
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
