package YoutubeService.multiplelogin;

import YoutubeService.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;

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

    @RequestMapping(value = "/registerUser", method = RequestMethod.GET)
    public String getRegisterUser(WebRequest request, Model model) {
        User userDto = new User();
        model.addAttribute("user", userDto);
        return "registerUser";
    }

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView registerUserAccount
        (@ModelAttribute("user") @Valid User accountDto, BindingResult result, HttpServletRequest  request, Errors errors) throws MalformedURLException{
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            //user vec postoji ili nesto tako
            return new ModelAndView("registerUser", "user", accountDto);
        }
        if (result.hasErrors()) {
            return new ModelAndView("registerUser", "user", accountDto);
        }
        else {
            return new ModelAndView("loginUser");
        }
    }

    public String getURLBase(HttpServletRequest request) throws MalformedURLException {

        URL requestURL = new URL(request.getRequestURL().toString());
        String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
        return requestURL.getProtocol() + "://" + requestURL.getHost() + port;

    }

    private User createUserAccount(User accountDto, BindingResult result) {
        User registered = null;
        try {
            //pristup UserModulu i slanje maila
            //registered = service.registerNewUserAccount(accountDto);
        } catch (Exception e) {
            return null;
        }
        return registered;
    }

    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        //VerificationToken verificationToken = service.getVerificationToken(token);
        String verificationToken=new String();//uzmi string sa servisa

        if (verificationToken.isEmpty()) {
            model.addAttribute("message", "greska");
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        User user;// = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        /*if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale)
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }*/

        //user.setEnabled(true);
        //service.saveRegisteredUser(user);
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
    }

    /*@RequestMapping(value = "/main")
    @ResponseBody
    public int usersController(){
        //return "redirect:/users/testing";
        UserController uc=new UserController();
        return uc.getTestAccount();
    }*/
}
