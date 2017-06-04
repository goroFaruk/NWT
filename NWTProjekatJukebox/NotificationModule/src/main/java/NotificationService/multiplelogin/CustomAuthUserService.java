package NotificationService.multiplelogin;

import NotificationService.Models.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by Šahin on 4.6.2017.
 */

@Service
public class CustomAuthUserService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RestTemplate restTemplate = new RestTemplate();
        UserModel userInfo = restTemplate.getForObject("http://localhost:1113/users/getUserByUsername?username="+username,UserModel.class);

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        UserDetails userDetails = (UserDetails)new User(userInfo.getUsername(), userInfo.getPasword(), Arrays.asList(authority));
        return userDetails;
    }
}
