package NotificationService.Repository;

import NotificationService.Models.NotifikacijaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Enver on 19.3.2017.
 */
public interface NotificationRepository  extends CrudRepository<NotifikacijaEntity, Integer> {
    @Override
    public <S extends NotifikacijaEntity> S save(S entity);

    @Override
    public <S extends NotifikacijaEntity> Iterable<S> save(Iterable<S> entities);

    @Override
    public NotifikacijaEntity findOne(Integer integer);

    @Override
    public boolean exists(Integer integer);

    @Override
    public Iterable<NotifikacijaEntity> findAll();

    public Page<NotifikacijaEntity> findAll(Pageable integers);

    @Override
    public void delete(Integer integer);

    @Override
    public long count();

    @Override
    public void delete(NotifikacijaEntity entity);

    @Override
    public void delete(Iterable<? extends NotifikacijaEntity> entities);
    @Override
    public void deleteAll();
}
