package UsersService.Repository;

import UsersService.Models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Šahin on 18.3.2017.
 */
public interface UserRepository  extends CrudRepository<UserEntity, Integer>{

    @Override
    Iterable<UserEntity> findAll();

    @Override
    void delete(Integer integer);

    @Override
    UserEntity findOne(Integer integer);

    Page<UserEntity> findAll(Pageable iterable);

    @Override
    long count();

    @Override
    void delete(UserEntity userEntity);

    @Override
    void delete(Iterable<? extends UserEntity> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends UserEntity> Iterable<S> save(Iterable<S> iterable);

    @Override
    <S extends UserEntity> S save(S s);

    @Override
    boolean exists(Integer integer);
}
