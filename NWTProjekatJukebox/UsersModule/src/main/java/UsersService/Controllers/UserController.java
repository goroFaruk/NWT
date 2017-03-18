package UsersService.Controllers;

import UsersService.Models.UserEntity;
import UsersService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Šahin on 18.3.2017.
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserRepository repo;

    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public String insertTestAccounts() {
        UserEntity u = new UserEntity();
        u.setEmail("test");
        u.setPasword("test");
        u.setUsername("test");
        try{
            repo.save(u);
        } catch (Exception e){
            return e.getMessage();
        }
        return "OK";
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public UserEntity getById(@PathVariable("id") int id) {
        UserEntity userEntity = repo.findOne(id);
        return userEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<UserEntity> getAll(Pageable pageable) {
        Page<UserEntity> users = repo.findAll(pageable);
        return users;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertUser(@RequestParam String email, String pass, String username){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPasword(pass);
        userEntity.setEmail(email);
        int id;
        try{
            id=repo.save(userEntity).getId();
        } catch (Exception e){
            return e.getMessage();
        }

        return "ID: " + String.valueOf(id);
    }
}
