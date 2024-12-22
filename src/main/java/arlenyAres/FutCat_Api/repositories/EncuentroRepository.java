package arlenyAres.FutCat_Api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import arlenyAres.FutCat_Api.models.Encuentro;
import java.util.List;

public interface EncuentroRepository extends JpaRepository<Encuentro, Integer> {

    @Query("SELECT e FROM Encuentro e WHERE e.idJornada.idJornada = :idJornada")
    List<Encuentro> findByJornadaId(Integer idJornada);

    /*
    // Ejemplo de update
    // @Modifying
    // @Query(value = "UPDATE Encuentro SET Goles_Local = :golesLocal, Goles_Visita = :golesVisita WHERE ID_Encuentro = :idEncuentro", nativeQuery = true)
    // void actualizarResultado(@Param("idEncuentro") Integer idEncuentro, @Param("golesLocal") Integer golesLocal, @Param("golesVisita") Integer golesVisita);
    */
}