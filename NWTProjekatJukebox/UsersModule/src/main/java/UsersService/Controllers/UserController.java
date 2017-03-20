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

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public UserEntity getById(@PathVariable("id") int id) {
        UserEntity userEntity = repo.findOne(id);
        return userEntity;
    }

    @RequestMapping(value= "/", method = RequestMethod.GET)
    public Page<UserEntity> getAll(Pageable pageable) {
        Page<UserEntity> users = repo.findAll(pageable);
        return users;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String insertUser(@RequestParam String email, String pass, String username, Integer idRole){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPasword(pass);
        userEntity.setEmail(email);
        userEntity.setIdrole(idRole);
        UserEntity user;
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
        return "User is successfully deleted. ID: " + String.valueOf(id);
    }

}
