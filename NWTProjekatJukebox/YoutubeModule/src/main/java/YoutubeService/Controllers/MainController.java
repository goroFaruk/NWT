package YoutubeService.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by fare_ on 21.03.2017..
 */
@Controller
public class MainController {
    @RequestMapping("/")
    @ResponseBody
    public String index () {return "Test test";}
}
