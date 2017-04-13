package AdminServices.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import AdminServices.Models.UserroleEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Šahin on 18.3.2017.
 */
public interface AdminRepository extends CrudRepository<UserroleEntity, Integer>{

    @Override
    Iterable<UserroleEntity> findAll();

    @Override
    void delete(Integer integer);

    @Override
    UserroleEntity findOne(Integer integer);

    Page<UserroleEntity> findAll(Pageable iterable);

    @Override
    long count();

    @Override
    void delete(UserroleEntity userEntity);

    @Override
    void delete(Iterable<? extends UserroleEntity> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends UserroleEntity> Iterable<S> save(Iterable<S> iterable);

    @Override
    <S extends UserroleEntity> S save(S s);

    @Override
    boolean exists(Integer integer);

    @Modifying
    @Transactional
    @Query("Update UserroleEntity u set u.iduserRole=?2 where u.idUser=?1")
    void updateRole(Integer idUser, Integer iduserRole);

    @Query("select u from UserroleEntity u where u.iduserRole=?1")
    List<UserroleEntity> findAllByRoleId(Integer roleId);
    @Query("select r from UserroleEntity r where r.idUser=?1")
    List<UserroleEntity> findAllByUserId(Integer userId);
}