package YoutubeService.Repository;

import YoutubeService.Models.ListapjesamaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fare_ on 24.03.2017..
 */
public interface ListapjesamaRepository extends CrudRepository<ListapjesamaEntity, Integer>{
    @Override
    Iterable<ListapjesamaEntity> findAll();

    @Override
    void delete(Integer integer);

    @Override
    ListapjesamaEntity findOne(Integer integer);

    Page<ListapjesamaEntity> findAll(Pageable iterable);

    @Override
    long count();

    @Override
    void delete(ListapjesamaEntity userEntity);

    @Override
    void delete(Iterable<? extends ListapjesamaEntity> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends ListapjesamaEntity> Iterable<S> save(Iterable<S> iterable);

    @Override
    <S extends ListapjesamaEntity> S save(S s);

    @Override
    boolean exists(Integer integer);
}
