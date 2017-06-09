package YoutubeService.Controllers;


import YoutubeService.Models.ListaEntity;
import YoutubeService.Models.Message;
import YoutubeService.Repository.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ListaRepository repo;

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String InsertLista(@RequestParam String naziv)
    {
        ListaEntity p = new ListaEntity();
        p.setNaziv(naziv);
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
    public List<ListaEntity> getAll() {
        List<ListaEntity> lista = (List<ListaEntity>) repo.findAll();
        return lista;
    }

    @RequestMapping(value = "/getAllListForUser", method =RequestMethod.GET)
    public ResponseEntity<List<ListaEntity>> getAllListsForUser(@RequestParam Integer idUser)
    {
        List<ListaEntity> listaEntities = repo.findAllByUserId(idUser);
        return new ResponseEntity<List<ListaEntity>>(listaEntities,HttpStatus.OK) ;
    }

    @RequestMapping(value= "/getAllListsForSongId", method = RequestMethod.GET)
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
    public ResponseEntity<Message> updateLista(@RequestParam String naziv, Integer id) {
        try
        {
            repo.updateNaziv(naziv,id);
        }
        catch (Exception e)
        {
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Lista"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("Lista is successfully updated","Lista"), HttpStatus.OK);
    }
    @RequestMapping(value = "dodijeliListu", method = RequestMethod.POST)
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

    //NEDEFINISANO JOS NE RADI NISTA
    // TODO:skontati sta i kako
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
