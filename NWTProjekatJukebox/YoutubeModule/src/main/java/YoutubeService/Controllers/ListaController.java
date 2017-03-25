package YoutubeService.Controllers;


import YoutubeService.Models.ListaEntity;
import YoutubeService.Repository.ListaRepository;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;

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
}
