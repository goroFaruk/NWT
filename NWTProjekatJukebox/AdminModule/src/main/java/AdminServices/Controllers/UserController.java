package AdminServices.Controllers;

import AdminServices.Models.Message;
import AdminServices.Models.UserroleEntity;
import AdminServices.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Šahin on 18.3.2017.
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private AdminRepository repo;

    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public int getTestAccount() {
        UserroleEntity u = new UserroleEntity();
        u.getIdUser();

        return 0;
    }
    @RequestMapping(value = "/byRole/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<UserroleEntity>>  getUsersByRole(@PathVariable("id") int roleId) {
        List<UserroleEntity> allbyRole = repo.findAllByRoleId(roleId);
        return new ResponseEntity<List<UserroleEntity>>(allbyRole,HttpStatus.OK) ;

    }
    @RequestMapping(value = "/byUser/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<UserroleEntity>>  getRolesByUser(@PathVariable("id") int userId) {
        List<UserroleEntity> allbyUser = repo.findAllByUserId(userId);
        return new ResponseEntity<List<UserroleEntity>>(allbyUser,HttpStatus.OK) ;
    }
    @RequestMapping(value = "/chckRole/{id}", method = RequestMethod.GET)
    public boolean checkRoleForUser(@PathVariable("id") int userId) {
        UserroleEntity user = repo.findOne(userId);
        return repo.exists(user.getIduserRole());
    }

    @RequestMapping(value = "/insert", method=RequestMethod.POST)
    public String insertUser(@RequestParam Integer userId, Integer roleId){
        UserroleEntity userEntity = new UserroleEntity();
        userEntity.setIduserRole(roleId);
        userEntity.setIdUser(userId);
        userEntity.setIdRole(roleId.toString());
        try{
            userEntity=repo.save(userEntity);
        } catch (Exception e){
            return e.getMessage();
        }
        return "User is successfully added. ID: " + String.valueOf(userEntity.getIdUser());
    }
    @RequestMapping(value = "/changeRole", method = RequestMethod.POST)
    public ResponseEntity<Message> changeRole(@RequestParam Integer userId, Integer roleId){

        try{
            repo.updateRole(userId,roleId);
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"User"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("Role name is successfully changed","User"), HttpStatus.OK);
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
    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public UserroleEntity getById(@PathVariable("id") int id) {
        UserroleEntity userEntity = repo.findOne(id);
        return userEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<UserroleEntity> getAll(Pageable pageable) {
        Page<UserroleEntity> users = repo.findAll(pageable);
        return users;
    }
}
