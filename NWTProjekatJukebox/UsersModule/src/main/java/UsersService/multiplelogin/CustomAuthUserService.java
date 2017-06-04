package UsersService.multiplelogin;

import UsersService.Controllers.UserController;
import UsersService.Models.UserEntity;
import UsersService.Models.UserModel;
import UsersService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Šahin on 4.6.2017.
 */

@Service
public class CustomAuthUserService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserController userController=new UserController();
        List<UserEntity> users= userRepository.findOneMyUsername(username);
        if(users.size()==0){
            return null;
        }
        UserEntity userInfo=users.get(0);
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
        UserDetails userDetails = (UserDetails)new User(userInfo.getUsername(), userInfo.getPasword(), Arrays.asList(authority));
        return userDetails;
    }
}
