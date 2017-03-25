package YoutubeService.Controllers;

import YoutubeService.Models.ListaEntity;
import YoutubeService.Models.ListapjesamaEntity;
import YoutubeService.Repository.ListapjesamaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = RequestMethod.GET)
    public Page<ListapjesamaEntity> getAll(java.awt.print.Pageable pageable) {
        Page<ListapjesamaEntity> listapjesama = (Page<ListapjesamaEntity>) repo.findAll();
        return listapjesama;
    }

}
