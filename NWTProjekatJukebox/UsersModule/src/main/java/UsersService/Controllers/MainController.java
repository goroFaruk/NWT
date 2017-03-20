package UsersService.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Šahin on 18.3.2017.
 */
@Controller
public class MainController {
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Testing UsersService MainControler.... ";
    }
}
