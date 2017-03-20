package NotificationService.Controllers;

import NotificationService.Models.NotifikacijaEntity;
import NotificationService.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Enver on 19.3.2017.
 */
@RestController
@RequestMapping(value="/notification")
public class NotificationController {
    @Autowired
    private NotificationRepository repo;

    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public String insertTestAccounts() {
        NotifikacijaEntity u = new NotifikacijaEntity();

        try{
            repo.save(u);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public NotifikacijaEntity getById(@PathVariable("id") int id) {
        NotifikacijaEntity userEntity = repo.findOne(id);
        return userEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<NotifikacijaEntity> getAll(Pageable pageable) {
        Page<NotifikacijaEntity> users = repo.findAll(pageable);
        return users;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertUser(@RequestParam String email, String pass, String username){
        NotifikacijaEntity userEntity=new NotifikacijaEntity();
        //isto ko maloprije
        int id;
        try{
            id=repo.save(userEntity).getId();
        } catch (Exception e){
            return e.getMessage();
        }

        return "ID: " + String.valueOf(id);
    }
}
