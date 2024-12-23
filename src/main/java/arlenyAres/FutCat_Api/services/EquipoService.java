package arlenyAres.FutCat_Api.services;

import arlenyAres.FutCat_Api.models.Equipo;
import arlenyAres.FutCat_Api.models.Torneo;
import arlenyAres.FutCat_Api.models.Delegado;
import arlenyAres.FutCat_Api.repositories.DelegadoRepository;
import arlenyAres.FutCat_Api.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EquipoService {
    private final EquipoRepository equipoRepository;
    private final DelegadoRepository delegadoRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EquipoService(EquipoRepository equipoRepository, DelegadoRepository delegadoRepository, JdbcTemplate jdbcTemplate) {
        this.equipoRepository = equipoRepository;
        this.delegadoRepository = delegadoRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Equipo> obtenerTodosLosEquipos() {
        return (List<Equipo>) equipoRepository.findAll();
    }

    @Transactional
    public void registrarEquipo(String nombreDelegado, String apellidosDelegado, String tipoDocumento,
                                String numDocumento, String telefono, String nomEquipo, String nomEquipoCorto,
                                String ciudad, Torneo torneo) {
        // Crear Delegado
        Delegado delegado = new Delegado();
        delegado.setNombreDelegado(nombreDelegado);
        delegado.setApellidosDelegado(apellidosDelegado);
        delegado.setTipoDocumento(tipoDocumento);
        delegado.setNumDocumento(numDocumento);
        delegado.setTelefono(telefono);
        delegadoRepository.save(delegado);

        // Crear Equipo
        Equipo equipo = new Equipo();
        equipo.setDelegado(delegado);
        equipo.setNomEquipo(nomEquipo);
        equipo.setNomEquipoCorto(nomEquipoCorto);
        equipo.setCiudad(ciudad);
        equipo.setTorneo(torneo);
        equipoRepository.save(equipo);
    }

    @Transactional
    public void eliminarEquipo(Integer idEquipo) {
        String procedureCall = "CALL EliminarEquipo(?)";
        jdbcTemplate.update(procedureCall, idEquipo);
    }

    public List<Equipo> obtenerEquiposPorTorneo(Integer idTorneo) {
        return (List<Equipo>) equipoRepository.findByTorneoId(idTorneo);
    }

    public Equipo obtenerEquipoPorId(int idEquipo) {
        return equipoRepository.findById(idEquipo).orElse(null);
    }
}