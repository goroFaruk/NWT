package YoutubeService.Controllers;

import YoutubeService.Models.PjesmaEntity;
import YoutubeService.Repository.YoutubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

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




}
