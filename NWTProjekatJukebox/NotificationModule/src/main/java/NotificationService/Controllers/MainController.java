package NotificationService.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Enver on 19.3.2017.
 */
@Controller
public class MainController {
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Test Notification";
    }
}
