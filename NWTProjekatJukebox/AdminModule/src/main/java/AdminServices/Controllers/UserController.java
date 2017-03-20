package AdminServices.Controllers;

import AdminServices.Models.UserroleEntity;
import AdminServices.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Šahin on 18.3.2017.
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private AdminRepository repo;

    @RequestMapping(value = "/testing", method = RequestMethod.GET)
    public int getTestAccount() {
        UserroleEntity u = new UserroleEntity();
        u.getIdUser();

        return 0;
    }

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public UserroleEntity getById(@PathVariable("id") int id) {
        UserroleEntity userEntity = repo.findOne(id);
        return userEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<UserroleEntity> getAll(Pageable pageable) {
        Page<UserroleEntity> users = repo.findAll(pageable);
        return users;
    }
}
