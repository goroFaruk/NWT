package YoutubeService.Controllers;

import YoutubeService.Models.ListaEntity;
import YoutubeService.Models.ListapjesamaEntity;
import YoutubeService.Models.Message;
import YoutubeService.Repository.ListapjesamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by fare_ on 24.03.2017..
 */

@RestController
@RequestMapping (value = "/listapjesama")
public class ListapjesamaController {

    @Autowired
    private ListapjesamaRepository repo;

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ListapjesamaEntity getById(@PathVariable("id") int id)
    {
        ListapjesamaEntity listapjesamaEntity = repo.findOne(id);
        return listapjesamaEntity;
    }

    @RequestMapping(value ="getAll",method = RequestMethod.GET)
    public Page<ListapjesamaEntity> getAll(java.awt.print.Pageable pageable) {
        Page<ListapjesamaEntity> listapjesama = (Page<ListapjesamaEntity>) repo.findAll();
        return listapjesama;
    }

    @RequestMapping(value="/listapjesama",method = RequestMethod.POST)
    public ResponseEntity<Message> insertlistaPjesama(@RequestParam Integer idPjesma, Integer idLista){
        ListapjesamaEntity lista=new ListapjesamaEntity();
        lista.setIdLista(idLista);
        lista.setIdPjesma(idPjesma);
        try{
            repo.save(lista);
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Lista"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Listapjesama is successfully added","Lista"),HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method= RequestMethod.POST)
    public ResponseEntity<Message> deleteById(@RequestParam int id) {
        try {
            repo.delete(id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Lista"),HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Listapjesama is successfully deleted","Listapjesama"),HttpStatus.OK);
    }


    @RequestMapping(value= "/forSong", method = RequestMethod.GET)
    public ResponseEntity<List<ListapjesamaEntity>> getAllSongForUser(@RequestParam Integer idpjesme) {
        List<ListapjesamaEntity> listapjesamaEntities = repo.findAllBySongId(idpjesme);
        return new ResponseEntity<>(listapjesamaEntities, HttpStatus.OK) ;
    }

    @RequestMapping(value= "/forList", method = RequestMethod.GET)
    public ResponseEntity<List<ListapjesamaEntity>> getAllSongsForList(@RequestParam Integer idList) {
        List<ListapjesamaEntity> listapjesamaEntities = repo.findAllByListaId(idList);
        return new ResponseEntity<>(listapjesamaEntities, HttpStatus.OK) ;
    }
}
