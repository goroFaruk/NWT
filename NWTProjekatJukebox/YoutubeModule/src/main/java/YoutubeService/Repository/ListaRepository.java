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
    Iterable<ListaEntity> findAll();

    @Override
    void delete(Integer integer);

    @Override
    ListaEntity findOne(Integer integer);

    Page<ListaEntity> findAll(Pageable iterable);

    @Override
    long count();

    @Override
    void delete(ListaEntity userEntity);

    @Override
    void delete(Iterable<? extends ListaEntity> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends ListaEntity> Iterable<S> save(Iterable<S> iterable);

    @Override
    <S extends ListaEntity> S save(S s);


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
    @Query("Update ListaEntity l set l.naziv=?2 where l.id=?1")
    void updateNaziv(String naziv);

}
