package arlenyAres.FutCat_Api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import arlenyAres.FutCat_Api.models.Equipo;
@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    @Query("SELECT e FROM Equipo e WHERE e.torneo.idTorneo = :idTorneo")
    List<Equipo> findByTorneoId(@Param("idTorneo") Integer idTorneo);
}