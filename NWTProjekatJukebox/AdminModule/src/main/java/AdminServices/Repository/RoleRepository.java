package AdminServices.Repository;

import AdminServices.Models.RolesEntity;
import AdminServices.Models.UserroleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Modifying
    @Transactional
    @Query("Update RolesEntity u set u.nazivRole=?2 where u.idroles=?1")
    void updateRole(Integer idRole, String nazivRole);
}