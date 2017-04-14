package MainServices.Controllers;


import MainServices.Models.Message;
import MainServices.Models.UserModel;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Šahin on 7.4.2017.
 */
@RestController
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@RequestMapping(value = "/users")
public class Users {
    @RequestMapping(value = "/init", method= RequestMethod.GET)
    public ResponseEntity<String> init() {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", String.class);
        return new ResponseEntity<String>(quote, HttpStatus.OK) ;
    }

    @RequestMapping(value = "/getAllUsers", method= RequestMethod.GET)
    public ResponseEntity<String> getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject("http://localhost:1113/users/", String.class);
        return new ResponseEntity<String>(quote, HttpStatus.OK) ;
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ResponseEntity<UserModel> getById(@PathVariable("id") int id) {
        RestTemplate restTemplate = new RestTemplate();
        UserModel quote = restTemplate.getForObject("http://localhost:1113/users/"+id, UserModel.class);
        return new ResponseEntity<UserModel>(quote, HttpStatus.OK) ;
    }

    @RequestMapping(value="/register",method = RequestMethod.POST)
    public ResponseEntity<String> insertUser(@RequestParam String email, String pass, String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("email", email);
        map.add("pass",pass);
        map.add("username",username);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> quote = restTemplate.postForEntity("http://localhost:1113/users/register", request, String.class);

        return quote;
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam String username, String pass) {
        UserModel model=new UserModel();
        model.setPasword(pass);
        model.setUsername(username);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("pass",pass);
        map.add("username",username);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> quote = restTemplate.postForEntity("http://localhost:1113/users/login", request,String.class);
        return quote;
    }

    @RequestMapping(value = "/getRoleForUser/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Message> getRoleForUser(@PathVariable("userId") int userId) {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject("http://localhost:1113/users/getRoleForUser?userId="+userId, String.class);

        return new ResponseEntity<Message>(new Message(quote,"UserService"), HttpStatus.OK);
    }

}
