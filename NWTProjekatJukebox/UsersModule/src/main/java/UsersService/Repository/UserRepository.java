package UsersService.Repository;

import UsersService.Models.FollowEntity;
import UsersService.Models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;
import java.beans.Transient;
import java.util.List;

/**
 * Created by Šahin on 18.3.2017.
 */
public interface UserRepository  extends CrudRepository<UserEntity, Integer>{

    Iterable<UserEntity> findAll();

    void delete(Integer integer);

    UserEntity findOne(Integer integer);

    Page<UserEntity> findAll(Pageable iterable);

    long count();

    void delete(UserEntity userEntity);

    void delete(Iterable<? extends UserEntity> iterable);

    void deleteAll();

    <S extends UserEntity> Iterable<S> save(Iterable<S> iterable);

    <S extends UserEntity> S save(S s);

    boolean exists(Integer integer);

    @Modifying
    @Transactional
    @Query("Update UserEntity u set u.username=?2 where u.id=?1")
    void updateUsername(Integer id, String username);

    @Modifying
    @Transactional
    @Query("Update UserEntity u set u.idrole=?2 where u.id=?1")
    void updateRole(Integer id, Integer idRole);

    @Modifying
    @Transactional
    @Query("Update UserEntity u set u.email=?2 where u.id=?1")
    void updateEmail(Integer id, String email);

    @Modifying
    @Transactional
    @Query("Update UserEntity u set u.pasword=?3 where u.id=?1 and u.pasword = ?2")
    void changePassword(Integer id, String oldPass, String newPass);

    @Modifying
    @Transactional
    @Query("Update UserEntity u set u.pasword=?3 where u.email=?1 and u.pasword = ?2")
    void changePasswordUsingEmail(String email, String oldPass, String newPass);

    @Query("select u from UserEntity u where u.username=?1 and u.pasword=?2")
    List<UserEntity> login(String username, String pass);

    @Query("select u from UserEntity u where u.username=?1 and u.email=?2")
    List<UserEntity> findOne(String username, String email);

}
