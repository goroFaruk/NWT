package YoutubeService.Controllers;

import YoutubeService.Models.ListapjesamaEntity;
import YoutubeService.Models.PregledEntity;
import YoutubeService.Repository.PregledRepository;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
