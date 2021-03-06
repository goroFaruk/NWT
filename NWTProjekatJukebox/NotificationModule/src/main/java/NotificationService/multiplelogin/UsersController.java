package NotificationService.multiplelogin;

import NotificationService.Models.Message;
import NotificationService.Models.User;
import NotificationService.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
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

    @RequestMapping("/badUser")
    public String getBadUserPage() {
        return "badUser";
    }


    @RequestMapping(value = "/registerUser", method = RequestMethod.GET)
    public String getRegisterUser(WebRequest request, Model model) {
        User userDto = new User();
        model.addAttribute("user", userDto);
        return "registerUser";
    }

    @RequestMapping("/registerConfirmation")
    public String getRegisterPage() {
        return "registerConfirmation";
    }


    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public ModelAndView registerUserAccount
        (@ModelAttribute("user") @Valid User accountDto, BindingResult result, HttpServletRequest  request, Model model) throws MalformedURLException{
        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result,request);
        }
        if (registered == null) {
            List<ObjectError> errors=result.getAllErrors();
            model.addAttribute("message",errors.get(0).getDefaultMessage());
            return new ModelAndView("registerUser", "user", accountDto);
        }
        if (result.hasErrors()) {
            return new ModelAndView("registerUser", "user", accountDto);
        }
        else {
            return new ModelAndView("registerConfirmation");
        }
    }

    public String getURLBase(HttpServletRequest request) throws MalformedURLException {
        URL requestURL = new URL(request.getRequestURL().toString());
        String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
        return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
    }

    private User createUserAccount(User accountDto, BindingResult result,HttpServletRequest  SrvltRequest) {
        User registered = null;
        ResponseEntity<String> test;
        try {
            RestTemplate restTemplate = new RestTemplate();
            test=restTemplate.getForEntity("http://localhost:1113/users/seviceRegister?email="+accountDto.getEmail()+"&pass="+accountDto.getPassword()+"&username="+accountDto.getUsername()+"&url=1",String.class);
            String test1=test.toString();
        } catch (Exception e) {
            result.addError(new ObjectError("submit","Greska u komunikaciji"));
            return null;
        }
        if(!result.hasErrors()) {
            if(test.getBody().contains("User is already exists"))
                result.addError(new ObjectError("username","Korisnik vec postoji"));
            if(!result.hasErrors())
                registered = new User();
        }
        return registered;
    }

    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        RestTemplate restTemplate = new RestTemplate();
        UserModel userModel = restTemplate.getForObject("http://localhost:1113/users/getToken?token="+token, UserModel.class);

        String verificationToken=userModel.getToken();

        if (verificationToken.isEmpty()) {
            model.addAttribute("message", "greska");
            return "badUser";
        }
        EnableUser(userModel.getUsername());
        return "loginUser";
    }

    private void EnableUser(String username){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
            map.add("username",username);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> quote = restTemplate.postForEntity("http://localhost:1113/users/enableUser", request, String.class);
        }catch (Exception ex){
            return;
        }
    }

    /*@RequestMapping(value = "/main")
    @ResponseBody
    public int usersController(){
        //return "redirect:/users/testing";
        UserController uc=new UserController();
        return uc.getTestAccount();
    }*/
}
