package UsersService.Controllers;

import UsersService.Models.Message;
import UsersService.Models.UserEntity;
import UsersService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserEntity> getById(@PathVariable("id") int id) {
        UserEntity userEntity = repo.findOne(id);
        return new ResponseEntity<UserEntity>(userEntity,HttpStatus.OK) ;
    }

    @RequestMapping(value= "/", method = RequestMethod.GET)
    public ResponseEntity<Page<UserEntity>> getAll(Pageable pageable) {
        Page<UserEntity> users = repo.findAll(pageable);
        return new ResponseEntity<Page<UserEntity>>(users,HttpStatus.OK);
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public ResponseEntity<Message> insertUser(@RequestParam String email, String pass, String username, Integer idRole){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPasword(pass);
        userEntity.setEmail(email);
        userEntity.setIdrole(idRole);
        UserEntity user;
        try{
            user=repo.save(userEntity);
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"User"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("User is successfully registered","User"), HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method= RequestMethod.POST)
    public ResponseEntity<Message> deleteById(@RequestParam int id) {
        try {
            repo.delete(id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"User"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("User is successfully deleted","User"), HttpStatus.OK);
    }


    @RequestMapping(value="/update",method = RequestMethod.POST)
    public ResponseEntity<Message> updateUsername(@RequestParam String username, Integer id){
        try{
            repo.updateUsername(id,username);
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"User"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("Username is successfully updated","User"), HttpStatus.OK);

    }

    @RequestMapping(value="/changepass",method = RequestMethod.POST)
    public ResponseEntity<Message> changePassword(@RequestParam Integer id, String oldPass, String newPass){
        try{
            repo.changePassword(id,oldPass,newPass);
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"User"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("Password is successfully changed","User"), HttpStatus.OK);
    }

    @RequestMapping(value="/changepassbyemail",method = RequestMethod.POST)
    public ResponseEntity<Message> changePasswordUsingEmail(@RequestParam String email, String oldPass, String newPass){
        try{
            repo.changePasswordUsingEmail(email,oldPass,newPass);
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"User"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("Password is successfully changed","User"), HttpStatus.OK);
    }

    @RequestMapping(value="/changerole",method = RequestMethod.POST)
    public ResponseEntity<Message> changeRole(@RequestParam Integer id, Integer idRole){
        try{
            repo.updateRole(id,idRole);
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"User"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("Role is successfully updated","User"), HttpStatus.OK);
    }
}
