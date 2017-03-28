package AdminServices.Controllers;

import AdminServices.Models.RolesEntity;
import AdminServices.Models.UserroleEntity;
import AdminServices.Repository.AdminRepository;
import AdminServices.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Predrag on 21.03.2017..
 */
@RestController
@RequestMapping(value = "/roles")
public class RoleController {
    @Autowired
    private RoleRepository repo;

    @RequestMapping(method = RequestMethod.POST)
    public String insertUser(@RequestParam String nazivRole, Integer idRole){
        RolesEntity userEntity=new RolesEntity();
        userEntity.setIdroles(idRole);
        userEntity.setNazivRole(nazivRole);
        try{
            userEntity=repo.save(userEntity);
        } catch (Exception e){
            return e.getMessage();
        }
        return "Role is successfully added. ID: " + String.valueOf(userEntity.getIdroles());
    }
    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public RolesEntity getById(@PathVariable("id") int id) {
        RolesEntity RolesEntity = repo.findOne(id);
        return RolesEntity;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<RolesEntity> getAll(Pageable pageable) {
        Page<RolesEntity> roles = repo.findAll(pageable);
        return roles;
    }
}
