package YoutubeService.Repository;

import YoutubeService.Models.ListaEntity;
import YoutubeService.Models.ListapjesamaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by fare_ on 24.03.2017..
 */
public interface ListaRepository extends CrudRepository<ListaEntity, Integer> {
    @Override
    boolean exists(Integer integer);
    @Query("select f from ListaEntity f where f.idUser=?1")
    List<ListaEntity> findAllByUserId(Integer userId);

    @Query("select f from ListaEntity f where f.idPjesma=?1")
    List<ListaEntity> findAllBySongId(Integer songId);

    @Query("select f from ListaEntity f where f.ocjena=?1")
    List<ListaEntity> findAllByListId(Integer listId);

    @Modifying
    @Transactional
    @Query("Update ListaEntity l set l.naziv=?1 where l.id=?2")
    void updateNaziv(String naziv, Integer id);
}
