package arlenyAres.FutCat_Api.services;

import arlenyAres.FutCat_Api.models.Torneo;
import arlenyAres.FutCat_Api.models.Usuario;
import arlenyAres.FutCat_Api.models.Organizador;
import arlenyAres.FutCat_Api.repositories.TorneoRepository;
import arlenyAres.FutCat_Api.repositories.OrganizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TorneoService {
    private final TorneoRepository torneoRepository;
    private final OrganizadorRepository organizadorRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TorneoService(TorneoRepository torneoRepository,
                         OrganizadorRepository organizadorRepository,
                         JdbcTemplate jdbcTemplate) {
        this.torneoRepository = torneoRepository;
        this.organizadorRepository = organizadorRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Torneo> obtenerTodosLosTorneos() {
        return torneoRepository.findAll();
    }

    public List<Torneo> getTorneosByIdUser(Usuario usuario) {
        return torneoRepository.findTournamentsByIdUser(usuario);
    }

    @Transactional
    public void registrarTorneo(String nombreOrganizador,
                                String tipoDocumento,
                                String numDocumento,
                                String telefono,
                                String nombreTorneo,
                                String direccion,
                                String tipoTorneo,
                                int cantidadEquipos,
                                String montoPremio,
                                Usuario usuario,
                                String estado) {
        // 1. Crear y guardar Organizador
        Organizador organizador = new Organizador();
        organizador.setNombreOrganizador(nombreOrganizador);
        organizador.setTipoDocumento(tipoDocumento);
        organizador.setNumDocumento(numDocumento);
        organizador.setTelefono(telefono);
        organizadorRepository.save(organizador);

        // 2. Crear y guardar Torneo
        Torneo torneo = new Torneo();
        torneo.setOrganizador(organizador);
        torneo.setNombreTorneo(nombreTorneo);
        torneo.setDireccion(direccion);
        torneo.setTipoTorneo(tipoTorneo);
        torneo.setCantEquipos(cantidadEquipos);
        torneo.setMontoPremio(montoPremio);
        torneo.setUsuario(usuario);
        torneo.setEstado(estado);

        torneoRepository.save(torneo);
    }

    @Transactional
    public void eliminarTorneo(Integer id) {
        // Llamando a tu procedimiento almacenado
        String procedureCall = "CALL EliminarTorneo(?)";
        jdbcTemplate.update(procedureCall, id);
    }

    public Torneo obtenerTorneoPorId(int idTorneo) {
        Optional<Torneo> optionalTorneo = torneoRepository.findById(idTorneo);
        if (optionalTorneo.isPresent()) {
            return optionalTorneo.get();
        } else {
            throw new RuntimeException("Torneo no encontrado con ID: " + idTorneo);
        }
    }

    public List<Torneo> obtenerTorneosConFixture() {
        return torneoRepository.findTorneosConFixture();
    }
}