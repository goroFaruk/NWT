package AdminServices.Controllers;

import AdminServices.Models.Message;
import AdminServices.Models.RolesEntity;
import AdminServices.Models.UserroleEntity;
import AdminServices.Repository.AdminRepository;
import AdminServices.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.management.relation.Role;
import java.util.List;

/**
 * Created by Šahin on 18.3.2017.
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private AdminRepository repo;

    @Autowired
    private RoleRepository roleRepo;

    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public int getTestAccount() {
        /*UserroleEntity u = new UserroleEntity();
        u.getIdUser();*/

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
    @RequestMapping(value = "/chckUserRole", method = RequestMethod.GET)
    public String checkUserRole(@RequestParam int userId) {
        List<UserroleEntity> user = repo.findAllByUserId(userId);
        if(user.size()==0) return "Error, korisnik ne postoji!";
        RolesEntity role=roleRepo.findOne(Integer.parseInt(user.get(0).getIdRole()));
        return role.getNazivRole();
    }

    @RequestMapping(value = "/allListsById/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserroleEntity> getListsById(@PathVariable("id") int id){
        RestTemplate restTemplate = new RestTemplate();
        UserroleEntity quote = restTemplate.getForObject("http://localhost:1114/lists/"+id, UserroleEntity.class);
        return new ResponseEntity<UserroleEntity>(quote, HttpStatus.OK) ;
    }
    @RequestMapping(value = "/allLists", method = RequestMethod.GET)
    public ResponseEntity<UserroleEntity> getLists(){
        RestTemplate restTemplate = new RestTemplate();
        UserroleEntity quote = restTemplate.getForObject("http://localhost:1114/lists/", UserroleEntity.class);
        return new ResponseEntity<UserroleEntity>(quote, HttpStatus.OK) ;
    }
    @RequestMapping(value = "/allSongs/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserroleEntity> getListsOfSongs(@PathVariable("id") int id){
        RestTemplate restTemplate = new RestTemplate();
        UserroleEntity quote = restTemplate.getForObject("http://localhost:1114/listapjesama/forList"+id, UserroleEntity.class);
        return new ResponseEntity<UserroleEntity>(quote, HttpStatus.OK) ;
    }
    @RequestMapping(value = "/getSong/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserroleEntity> getSong(@PathVariable("id") int id){
        RestTemplate restTemplate = new RestTemplate();
        UserroleEntity quote = restTemplate.getForObject("http://localhost:1114/listapjesama/forSong"+id, UserroleEntity.class);
        return new ResponseEntity<UserroleEntity>(quote, HttpStatus.OK) ;
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

    @RequestMapping(value = "/userInRole", method = RequestMethod.POST)
    public ResponseEntity<Message> getByRole(@RequestParam int id)
    {
        try{
            UserroleEntity ur=new UserroleEntity();
            ur.setIdUser(id);
            ur.setIdRole(Integer.toString(1));
            UserroleEntity tmp;
            tmp= repo.save(ur);
            return new ResponseEntity<Message>( new Message(Integer.toString(tmp.getIduserRole()),"UserRole"),HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Message>(new Message(e.getMessage(),"UserRole"), HttpStatus.EXPECTATION_FAILED) ;
        }
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