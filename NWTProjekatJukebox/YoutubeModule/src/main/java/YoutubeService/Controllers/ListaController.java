package YoutubeService.Controllers;


import YoutubeService.Models.ListaEntity;
import YoutubeService.Models.Message;
import YoutubeService.Repository.ListaRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by fare_ on 24.03.2017..
 */

@RestController
@RequestMapping(value = "/lista")
public class ListaController {

    private ListaRepository repo;
    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public String InsertTestLista()
    {
        ListaEntity p = new ListaEntity();
        p.setNaziv("test");
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
    public ListaEntity getById(@PathVariable("id") int id)
    {
        ListaEntity listaEntity = repo.findOne(id);
        return listaEntity;
    }

    @RequestMapping(value= "getAll",method = RequestMethod.GET)
    public Page<ListaEntity> getAll(Pageable pageable) {
        Page<ListaEntity> lista = (Page<ListaEntity>) repo.findAll();
        return lista;
    }

    @RequestMapping(value = "/forUser", method =RequestMethod.GET)
    public ResponseEntity<List<ListaEntity>> getAllListsForUser(@RequestParam Integer idUser)
    {
        List<ListaEntity> listaEntities = repo.findAllByUserId(idUser);
        return new ResponseEntity<List<ListaEntity>>(listaEntities,HttpStatus.OK) ;
    }

    @RequestMapping(value= "/forPjesma", method = RequestMethod.GET)
    public ResponseEntity<List<ListaEntity>> getAllFollowsForList(@RequestParam Integer idPjesma) {
        List<ListaEntity> listaEntities = repo.findAllBySongId(idPjesma);
        return new ResponseEntity<List<ListaEntity>>(listaEntities, HttpStatus.OK);
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Message> deleteById (@RequestParam int id)
    {
        try
        {
            repo.delete(id);
        }
        catch (Exception e)
        {
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Lista"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("Lista is successfully deleted","Lista"), HttpStatus.OK);
    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public ResponseEntity<Message> updateLista(@RequestParam String naziv ) {
        try
        {
            repo.updateNaziv(naziv);
        }
        catch (Exception e)
        {
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Lista"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("Lista is successfully updated","Lista"), HttpStatus.OK);
    }
    @RequestMapping(value = "addLista", method = RequestMethod.POST)
    public ResponseEntity<Message> addLista(@RequestParam String naziv, Integer idUsera )
    {
        ListaEntity listaEntity = new ListaEntity();
        listaEntity.setNaziv(naziv);
        listaEntity.setIdUser(idUsera);
        try
        {
            repo.save(listaEntity);
        }
        catch (Exception e)
        {
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Lista"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("Lista is successfully added","Lista"), HttpStatus.OK);
    }

    @RequestMapping(value = "gradesForList", method = RequestMethod.GET)
    public ResponseEntity<List<ListaEntity>> getAllGradesForList (@RequestParam Integer idListe)
    {
        List<ListaEntity> listaEntities = repo.findAllByListId(idListe);
        return new ResponseEntity<List<ListaEntity>>(listaEntities, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllFollowsForList", method = RequestMethod.GET)
    public ResponseEntity<String> getAllFollowsForList(@RequestParam int idListe){
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject("http://localhost:1113/follows/forList?idList="+idListe, String.class);

        return new ResponseEntity<String>(quote, HttpStatus.OK);

    }
}
