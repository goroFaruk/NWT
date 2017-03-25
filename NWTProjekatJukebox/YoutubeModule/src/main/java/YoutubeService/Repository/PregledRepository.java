package YoutubeService.Repository;

import YoutubeService.Models.PjesmaEntity;
import YoutubeService.Models.PregledEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

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
}
