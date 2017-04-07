package MainServices.Controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Šahin on 7.4.2017.
 */
@RestController
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@RequestMapping(value = "/users")
public class Users {
    @RequestMapping(value = "/", method= RequestMethod.GET)
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
}
