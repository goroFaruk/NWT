package UsersService.Repository;

import UsersService.Models.FollowEntity;
import UsersService.Models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Šahin on 20.3.2017.
 */
public interface FollowRepository extends CrudRepository<FollowEntity, Integer> {

    @Override
    Iterable<FollowEntity> findAll();

    @Override
    void delete(Integer integer);

    @Override
    FollowEntity findOne(Integer integer);

    Page<UserEntity> findAll(Pageable iterable);

    @Override
    long count();

    @Override
    void delete(FollowEntity followEntity);

    @Override
    void delete(Iterable<? extends FollowEntity> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends FollowEntity> Iterable<S> save(Iterable<S> iterable);

    @Override
    <S extends FollowEntity> S save(S s);

    @Override
    boolean exists(Integer integer);
}
