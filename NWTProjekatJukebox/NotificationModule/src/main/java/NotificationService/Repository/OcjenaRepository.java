package NotificationService.Repository;

import NotificationService.Models.OcjenaEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Enver on 19.3.2017.
 */
public interface OcjenaRepository extends CrudRepository<OcjenaEntity, Integer> {
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

    @Modifying
    @Transactional
    @Query("Update OcjenaEntity o set o.korisnikId=?1 where o.id=?2")
    void updateKorisnik(Integer username, Integer id);

    @Query("select f from OcjenaEntity f where f.listaId=?1")
    List<OcjenaEntity> findAllByListaId(Integer listaId);

    @Modifying
    @Transactional
    @Query("Update OcjenaEntity o set o.listaId=?1 where o.id=?2")
    void updateLista(Integer listaID, Integer id);

    @Modifying
    @Transactional
    @Query("Update OcjenaEntity o set o.ocjena=ocjena where o.id=id")
    void updateOcjena(Integer ocjena, Integer id);


}
