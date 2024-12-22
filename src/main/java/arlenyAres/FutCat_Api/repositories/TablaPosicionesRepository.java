package arlenyAres.FutCat_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import arlenyAres.FutCat_Api.models.TablaPosiciones;

import java.util.List;

@Repository
public interface TablaPosicionesRepository extends JpaRepository<TablaPosiciones, Integer> {

    @Query("SELECT tp FROM TablaPosiciones tp WHERE tp.torneo.idTorneo = :idTorneo")
    List<TablaPosiciones> findByTorneoId(@Param("idTorneo") Integer idTorneo);
}
