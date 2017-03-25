package UsersService.Controllers;

import UsersService.Models.FollowEntity;
import UsersService.Models.Message;
import UsersService.Repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Šahin on 20.3.2017.
 */
@RestController
@RequestMapping(value = "/follows")
public class FollowController {
    @Autowired
    private FollowRepository repo;

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ResponseEntity<FollowEntity> getById(@PathVariable("id") int id) {
        FollowEntity followEntity = repo.findOne(id);
        return new  ResponseEntity<FollowEntity>(followEntity, HttpStatus.OK);
    }

    @RequestMapping(value= "/", method = RequestMethod.GET)
    public ResponseEntity<Page<FollowEntity>> getAll(Pageable pageable) {
        Page<FollowEntity> followEntities = repo.findAll(pageable);
        return new ResponseEntity<Page<FollowEntity>>(followEntities,HttpStatus.OK) ;
    }

    @RequestMapping(value="/follow",method = RequestMethod.POST)
    public ResponseEntity<Message> insertFolow(@RequestParam Integer idUser, Integer idLista){
        FollowEntity follow=new FollowEntity();
        follow.setIdlista(idLista);
        follow.setIduser(idUser);
        try{
            repo.save(follow);
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Follow"),HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Follow is successfully added","Follow"),HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method= RequestMethod.POST)
    public ResponseEntity<Message> deleteById(@RequestParam int id) {
        try {
            repo.delete(id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Follow"),HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Follow is successfully deleted","Follow"),HttpStatus.OK);
    }


    @RequestMapping(value= "/forUser", method = RequestMethod.GET)
    public ResponseEntity<List<FollowEntity>> getAllFollowsForUser(@RequestParam Integer idUser) {
        List<FollowEntity> followEntities = repo.findAllByUserId(idUser);
        return new ResponseEntity<List<FollowEntity>>(followEntities,HttpStatus.OK) ;
    }

    @RequestMapping(value= "/forList", method = RequestMethod.GET)
    public ResponseEntity<List<FollowEntity>> getAllFollowsForList(@RequestParam Integer idList) {
        List<FollowEntity> followEntities = repo.findAllByListId(idList);
        return new ResponseEntity<List<FollowEntity>>(followEntities,HttpStatus.OK) ;
    }
}
