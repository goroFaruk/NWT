package YoutubeService.Controllers;


import YoutubeService.Models.ListaEntity;
import YoutubeService.Models.ListapjesamaEntity;
import YoutubeService.Models.Message;
import YoutubeService.Repository.ListaRepository;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.GET)
    public Page<ListaEntity> getAll(Pageable pageable) {
        Page<ListaEntity> lista = (Page<ListaEntity>) repo.findAll();
        return lista;
    }

    @RequestMapping(value = "/forUser", method =RequestMethod.GET)
    public ResponseEntity<List<ListaEntity>> getAllFollowsForUser(@RequestParam Integer idUser)
    {
        List<ListaEntity> followEntities = repo.findAllByUserId(idUser);
        return new ResponseEntity<List<ListaEntity>>(followEntities,HttpStatus.OK) ;
    }

    @RequestMapping(value= "/forPjesma", method = RequestMethod.GET)
    public ResponseEntity<List<ListaEntity>> getAllFollowsForList(@RequestParam Integer idPjesma) {
        List<ListaEntity> followEntities = repo.findAllBySongId(idPjesma);
        return new ResponseEntity<List<ListaEntity>>(followEntities, HttpStatus.OK);
    }
}
