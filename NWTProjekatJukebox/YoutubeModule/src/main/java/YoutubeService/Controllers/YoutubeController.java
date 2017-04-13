package YoutubeService.Controllers;

import YoutubeService.Models.Message;
import YoutubeService.Models.PjesmaEntity;
import YoutubeService.Repository.YoutubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by fare_ on 21.03.2017..
 */

@RestController
@RequestMapping(value = "/youtube")
public class YoutubeController {

    @Autowired
    private YoutubeRepository repo;

    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public String InsertTestYoutube()
    {
        PjesmaEntity p = new PjesmaEntity();
        p.setUrlPlesme("test");
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
    public PjesmaEntity getById(@PathVariable("id") int id)
    {
        PjesmaEntity pjesmaEntity = repo.findOne(id);
        return pjesmaEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<PjesmaEntity> getAll(Pageable pageable) {
        Page<PjesmaEntity> pjesma = (Page<PjesmaEntity>) repo.findAll();
        return pjesma;
    }

    @RequestMapping(value="/youtube",method = RequestMethod.POST)
    public ResponseEntity<Message> insertSong(@RequestParam Integer id, String urlPjesme){
        PjesmaEntity pjesma=new PjesmaEntity();
        pjesma.setId(id);
        pjesma.setUrlPlesme(urlPjesme);
        try{
            repo.save(pjesma);
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Song"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Song is successfully added","Song"),HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method= RequestMethod.POST)
    public ResponseEntity<Message> deleteById(@RequestParam int id) {
        try {
            repo.delete(id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Song"),HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Song is successfully deleted","Song"),HttpStatus.OK);
    }


    @RequestMapping(value= "/forSong", method = RequestMethod.GET)
    public ResponseEntity<List<PjesmaEntity>> getAllSongsForUser(@RequestParam Integer id) {
        List<PjesmaEntity> pjesmaEntities = repo.findAllById(id);
        return new ResponseEntity<List<PjesmaEntity>>(pjesmaEntities,HttpStatus.OK) ;
    }
    @RequestMapping(value = "/forList", method = RequestMethod.GET)
    public ResponseEntity<List<PjesmaEntity>> getAllSongsForList(@RequestParam Integer idListe)
    {
        List<PjesmaEntity>pjesmaEntities = repo.findAllById(idListe);
        return  new ResponseEntity<List<PjesmaEntity>>(pjesmaEntities,HttpStatus.OK);
    }
    @RequestMapping(value= "/updateSong", method = RequestMethod.POST)
    public  ResponseEntity<Message> updateURL(@RequestParam String url)
    {
        PjesmaEntity pjesmaEntity = new PjesmaEntity();
        pjesmaEntity.setUrlPlesme(url);
        try
        {
            repo.updateURL(url);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Song"),HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Song is successfully edited","Song"),HttpStatus.OK);
    }


}
