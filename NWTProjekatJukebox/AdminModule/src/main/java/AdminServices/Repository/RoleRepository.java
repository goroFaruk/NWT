package AdminServices.Repository;

import AdminServices.Models.RolesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Predrag on 22.03.2017..
 */
public interface RoleRepository extends CrudRepository<RolesEntity, Integer> {

    @Override
    Iterable<RolesEntity> findAll();

    @Override
    void delete(Integer integer);

    @Override
    RolesEntity findOne(Integer integer);

    Page<RolesEntity> findAll(Pageable iterable);

    @Override
    long count();

    @Override
    void delete(RolesEntity userEntity);

    @Override
    void delete(Iterable<? extends RolesEntity> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends RolesEntity> Iterable<S> save(Iterable<S> iterable);

    @Override
    <S extends RolesEntity> S save(S s);

    @Override
    boolean exists(Integer integer);
}