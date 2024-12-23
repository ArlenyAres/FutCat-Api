package arlenyAres.FutCat_Api.services;

import arlenyAres.FutCat_Api.models.TablaPosiciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TablaPosicionesService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TablaPosicionesService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TablaPosiciones> obtenerTablaPosiciones(int idTorneo) {
        String sql = "CALL sp_TablaPosiciones(?)";
        List<TablaPosiciones> tablaPosiciones = (List<TablaPosiciones>) jdbcTemplate.query(
                sql,
                new Object[]{idTorneo},
                new BeanPropertyRowMapper<>(TablaPosiciones.class)
        );
        return tablaPosiciones;
    }
}