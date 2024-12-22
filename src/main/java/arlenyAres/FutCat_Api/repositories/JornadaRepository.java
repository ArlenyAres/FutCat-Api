package arlenyAres.FutCat_Api.repositories;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import arlenyAres.FutCat_Api.models.Jornada;

@Repository
public interface JornadaRepository extends JpaRepository<Jornada, Integer> {

    @Query("SELECT j FROM Jornada j JOIN j.fixture f JOIN f.torneo t WHERE t.idTorneo = :idTorneo")
    List<Jornada> findByFixtureTorneoId(@Param("idTorneo") Integer idTorneo);
}
