package YoutubeService.Repository;

import YoutubeService.Models.PjesmaEntity;
import YoutubeService.Models.PregledEntity;
import org.apache.derby.client.am.DateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by fare_ on 24.03.2017..
 */
public interface PregledRepository extends CrudRepository<PregledEntity, Integer> {

    @Override
    Iterable<PregledEntity> findAll();

    @Override
    void delete(Integer integer);

    @Override
    PregledEntity findOne(Integer integer);

    Page<PregledEntity> findAll(Pageable iterable);

    @Override
    long count();

    @Override
    void delete(PregledEntity userEntity);

    @Override
    void delete(Iterable<? extends PregledEntity> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends PregledEntity> Iterable<S> save(Iterable<S> iterable);

    @Override
    <S extends PregledEntity> S save(S s);

    @Override
    boolean exists(Integer integer);

    @Query("select f from PregledEntity f where f.id=?1")
    List<PregledEntity> findAllById(Integer userId);

    @Query("select f from PregledEntity f where f.idPjesma=?1")
    List<PregledEntity> findAllBySongId(Integer songId);

    @Modifying
    @Transactional
    @Query("update PregledEntity p set p.brojPregleda=p.brojPregleda+1 where p.idPjesma=?1 and p.id=?2")
    void insertPregled(Integer idPjesme, Integer id);

    @Query("select f from PregledEntity f where f.idPjesma=?1")
    List<PregledEntity> findBySongId(Integer idPjesme);
}
