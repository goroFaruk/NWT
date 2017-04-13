package NotificationService.Repository;

import NotificationService.Models.NotifikacijaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

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

    @Modifying
    @Transactional
    @Query("Update NotifikacijaEntity n set n.korisnikId=?1 where n.id=?2")
    void updateKorisnik(Integer username, Integer id);

    @Query("select f from NotifikacijaEntity f where f.korisnikId=?1")
    List<NotifikacijaEntity> findAllByKorisnikId(Integer korisnikID);

    @Modifying
    @Transactional
    @Query("Update NotifikacijaEntity n set n.listaId=?1 where n.id=?2")
    void updateLista(Integer listaID, Integer id);

    @Modifying
    @Transactional
    @Query("Update NotifikacijaEntity n set n.ocjenaId=?1 where n.id=?2")
    void updateOcjena(Integer ocjena, Integer id);

    @Modifying
    @Transactional
    @Query("Update NotifikacijaEntity n set n.datumNotifikacije=?1 where n.id=?2")
    void updateDatumNotifikacije(Date datum, Integer id);

}
