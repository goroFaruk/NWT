package UsersService.Controllers;

import UsersService.Models.Message;
import UsersService.Models.UserEntity;
import UsersService.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
    public ResponseEntity<Message> insertUser(@RequestParam String email, String pass, String username){
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPasword(pass);
        userEntity.setEmail(email);
        UserEntity user;
        try{
            List<UserEntity> userList;
            userList=repo.findOne(username,email);
            if(userList.size()==0) {
                user = repo.save(userEntity);


                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
                map.add("id",  Integer.toString(user.getId()));

                HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

                RestTemplate restTemplate = new RestTemplate();

                //ne radi ovaj dio
                Message quote = restTemplate.postForObject("http://localhost:1111/users/userInRole", request, Message.class);
                repo.updateRole(user.getId(),Integer.parseInt(quote.getMessage()));
                //kraj xD

            }else{
                return  new ResponseEntity<Message>( new Message("User is already exists","User"), HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"User"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>( new Message("User is successfully registered","User"), HttpStatus.OK);
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity<Message> login(@RequestParam String username, String pass){
        List<UserEntity> user;
        try{
            user=repo.login(username,pass);
            if (user==null) return  new ResponseEntity<Message>(new Message("Korisnicki podaci su netacni!","User"), HttpStatus.EXPECTATION_FAILED);
            if(user.size()==1) return  new ResponseEntity<Message>(new Message("Uspjesno ste se logovali","User"), HttpStatus.OK);
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
