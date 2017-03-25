package UsersService.Repository;

import UsersService.Models.FollowEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Šahin on 20.3.2017.
 */
public interface FollowRepository extends CrudRepository<FollowEntity, Integer> {

    Iterable<FollowEntity> findAll();

    void delete(Integer integer);

    FollowEntity findOne(Integer integer);

    Page<FollowEntity> findAll(Pageable iterable);

    long count();

    void delete(FollowEntity followEntity);

    void delete(Iterable<? extends FollowEntity> iterable);

    void deleteAll();

    <S extends FollowEntity> Iterable<S> save(Iterable<S> iterable);

    <S extends FollowEntity> S save(S s);

    boolean exists(Integer integer);

    @Query("select f from FollowEntity f where f.iduser=?1")
    List<FollowEntity> findAllByUserId(Integer userId);

    @Query("select f from FollowEntity f where f.idlista=?1")
    List<FollowEntity> findAllByListId(Integer listId);

}
