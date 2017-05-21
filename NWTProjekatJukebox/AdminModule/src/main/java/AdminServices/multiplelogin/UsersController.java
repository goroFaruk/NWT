package AdminServices.multiplelogin;

import AdminServices.Controllers.MainController;
import AdminServices.Controllers.UserController;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsersController {

    @RequestMapping("/protectedLinks")
    public String getAnonymousPage() {
        return "protectedLinks";
    }

    @RequestMapping("/userPage")
    public String getUserPage() {
        return "userPage";
    }

    @RequestMapping("/adminPage")
    public String getAdminPage() {
        return "adminPage";
    }

    @RequestMapping("/loginAdmin")
    public String getAdminLoginPage() {
        return "loginAdmin";
    }

    @RequestMapping("/loginUser")
    public String getUserLoginPage() {
        return "loginUser";
    }

    @RequestMapping("/403")
    public String getAccessDeniedPage() {
        return "403";
    }

    /*@RequestMapping(value = "/main")
    @ResponseBody
    public int usersController(){
        //return "redirect:/users/testing";
        UserController uc=new UserController();
        return uc.getTestAccount();
    }*/
}
