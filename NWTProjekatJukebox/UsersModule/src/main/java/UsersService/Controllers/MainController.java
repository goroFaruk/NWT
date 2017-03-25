package UsersService.Controllers;

import UsersService.Models.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Šahin on 18.3.2017.
 */
@Controller
public class MainController {
    @RequestMapping(value="/", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Message> index() {
        return new ResponseEntity<Message>( new Message("Testing UsersService MainControler.... ","MainController") , HttpStatus.OK);
    }
}
