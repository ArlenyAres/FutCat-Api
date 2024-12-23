package arlenyAres.FutCat_Api.services;

import arlenyAres.FutCat_Api.models.Encuentro;
import arlenyAres.FutCat_Api.repositories.EncuentroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EncuentroService {
    private final EncuentroRepository encuentroRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EncuentroService(JdbcTemplate jdbcTemplate, EncuentroRepository encuentroRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.encuentroRepository = encuentroRepository;
    }

    public List<Encuentro> obtenerEncuentrosPorJornada(Integer idJornada) {
        return encuentroRepository.findByJornadaId(idJornada);
    }

    public void actualizarGolesYEstado(int idEncuentro, int golesLocal, int golesVisita) {
        String sql = "CALL ActualizarGolesYEstado(?, ?, ?)";
        jdbcTemplate.update(sql, idEncuentro, golesLocal, golesVisita);
    }

    public void actualizarFecha(int idEncuentro, LocalDateTime fechaEncuentro) {
        String sql = "CALL ActualizarFecha(?, ?)";
        jdbcTemplate.update(sql, idEncuentro, fechaEncuentro);
    }
}
