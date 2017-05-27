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
    public <S extends NotifikacijaEntity> S save(S entity);

    public <S extends NotifikacijaEntity> Iterable<S> save(Iterable<S> entities);

    public NotifikacijaEntity findOne(Integer integer);

    public boolean exists(Integer integer);

    public Iterable<NotifikacijaEntity> findAll();

    public Page<NotifikacijaEntity> findAll(Pageable integers);

    public void delete(Integer integer);

    public long count();

    public void delete(NotifikacijaEntity entity);

    public void delete(Iterable<? extends NotifikacijaEntity> entities);

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
