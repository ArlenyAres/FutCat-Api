package arlenyAres.FutCat_Api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import arlenyAres.FutCat_Api.models.Torneo;
import arlenyAres.FutCat_Api.models.Usuario;

@Repository
public interface TorneoRepository extends JpaRepository<Torneo, Integer> {

    @Query("SELECT t FROM Torneo t WHERE t.usuario = ?1")
    List<Torneo> findTournamentsByIdUser(Usuario usuario);

    @Query("SELECT t FROM Torneo t WHERE t.idTorneo IN (SELECT f.torneo.idTorneo FROM Fixture f)")
    List<Torneo> findTorneosConFixture();
}

// Esto filtra torneos que tengan al menos un Fixture