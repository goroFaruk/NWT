package NotificationService.Repository;

import NotificationService.Models.OcjenaEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Enver on 19.3.2017.
 */
public interface OcjeneRepository extends CrudRepository<OcjenaEntity, Integer> {
    @Override
    public <S extends OcjenaEntity> S save(S entity);

    @Override
    public <S extends OcjenaEntity> Iterable<S> save(Iterable<S> entities);

    @Override
    public OcjenaEntity findOne(Integer integer);

    @Override
    public boolean exists(Integer integer);

    @Override
    public Iterable<OcjenaEntity> findAll();

    @Override
    public Iterable<OcjenaEntity> findAll(Iterable<Integer> integers);

    @Override
    public void delete(Integer integer);

    @Override
    public long count();

    @Override
    public void delete(OcjenaEntity entity);

    @Override
    public void delete(Iterable<? extends OcjenaEntity> entities);
    @Override
    public void deleteAll();
}
