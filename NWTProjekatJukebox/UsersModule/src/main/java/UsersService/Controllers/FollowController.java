package UsersService.Controllers;

import UsersService.Models.FollowEntity;
import UsersService.Repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Šahin on 20.3.2017.
 */
@RestController
@RequestMapping(value = "/follows")
public class FollowController {
    @Autowired
    private FollowRepository repo;

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public FollowEntity getById(@PathVariable("id") int id) {
        FollowEntity followEntity = repo.findOne(id);
        return followEntity;
    }

    @RequestMapping(value= "/", method = RequestMethod.GET)
    public Page<FollowEntity> getAll(Pageable pageable) {
        Page<FollowEntity> followEntities = repo.findAll(pageable);
        return followEntities;
    }

    //prepravitiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii
    @RequestMapping(method = RequestMethod.POST)
    public String insertFolow(@RequestParam String email, String pass, String username, Integer idRole){
        FollowEntity userEntity=new FollowEntity();

        FollowEntity user;
        try{
            user=repo.save(userEntity);
        } catch (Exception e){
            return e.getMessage();
        }
        return "User is successfully added. ID: " + String.valueOf(user.getId());
    }

    @RequestMapping(value = "/delete", method= RequestMethod.POST)
    public String deleteById(@RequestParam int id) {
        try {
            repo.delete(id);
        }catch (Exception e){
            return e.getMessage();
        }
        return "Follow is successfully deleted. ID: " + String.valueOf(id);
    }
}
