package YoutubeService.Controllers;

import YoutubeService.Models.ListaEntity;
import YoutubeService.Models.ListapjesamaEntity;
import YoutubeService.Models.Message;
import YoutubeService.Models.PjesmaEntity;
import YoutubeService.Repository.ListaRepository;
import YoutubeService.Repository.ListapjesamaRepository;
import YoutubeService.Repository.YoutubeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fare_ on 24.03.2017..
 */

@RestController
@RequestMapping (value = "/listapjesama")
public class ListapjesamaController {

    @Autowired
    private ListapjesamaRepository repo;

    @Autowired
    private YoutubeRepository youtubeRepo;

    @Autowired
    private ListaRepository listaRepo;

    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public ListapjesamaEntity getById(@PathVariable("id") int id)
    {
        ListapjesamaEntity listapjesamaEntity = repo.findOne(id);
        return listapjesamaEntity;
    }

    @RequestMapping(value ="getAll",method = RequestMethod.GET)
    public List<ListapjesamaEntity> getAll() {
        List<ListapjesamaEntity> listapjesama = (List<ListapjesamaEntity>) repo.findAll();
        return listapjesama;
    }

    @RequestMapping(value="/dodijeliPjesmuListi",method = RequestMethod.POST)
    public ResponseEntity<Message> insertlistaPjesama(@RequestParam Integer idPjesma, Integer idLista){
        ListapjesamaEntity lista=new ListapjesamaEntity();
        PjesmaEntity pjesmaEntity=youtubeRepo.findOne(idPjesma);
        if(pjesmaEntity==null)
            return new ResponseEntity<Message>( new Message("Pjesma ne postoji!","Lista"), HttpStatus.EXPECTATION_FAILED);
        ListaEntity listaEntity=listaRepo.findOne(idLista);
        if(listaEntity==null)
            return new ResponseEntity<Message>( new Message("Lista ne postoji!","Lista"), HttpStatus.EXPECTATION_FAILED);

        lista.setIdLista(idLista);
        lista.setIdPjesma(idPjesma);
        try{
            repo.save(lista);
        } catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Lista"), HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Listapjesama is successfully added","Lista"),HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method= RequestMethod.POST)
    public ResponseEntity<Message> deleteById(@RequestParam int id) {
        try {
            repo.delete(id);
        }catch (Exception e){
            return new ResponseEntity<Message>( new Message(e.getMessage(),"Lista"),HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<Message>(new Message("Listapjesama is successfully deleted","Listapjesama"),HttpStatus.OK);
    }


    @RequestMapping(value= "/dajSvePjesmeZaListu", method = RequestMethod.GET)
    public ResponseEntity<List<PjesmaEntity>> getAllSongForList(@RequestParam Integer idListe) {
        List<ListapjesamaEntity> listapjesamaEntities = repo.findAllBySongForList(idListe);
        if(listapjesamaEntities.size()==0)
            return null;
        List<PjesmaEntity> pjesmaEntityList=new ArrayList();

        for (int i=0; i<listapjesamaEntities.size(); i++){
            PjesmaEntity pj=youtubeRepo.findOne(listapjesamaEntities.get(i).getIdPjesma());
            pjesmaEntityList.add(pj);
        }
        return new ResponseEntity<>(pjesmaEntityList, HttpStatus.OK);
    }

    @RequestMapping(value= "/forList", method = RequestMethod.GET)
    public ResponseEntity<List<ListapjesamaEntity>> getAllSongsForList(@RequestParam Integer idList) {
        List<ListapjesamaEntity> listapjesamaEntities = repo.findAllByListaId(idList);
        return new ResponseEntity<>(listapjesamaEntities, HttpStatus.OK) ;
    }
}
