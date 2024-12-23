package arlenyAres.FutCat_Api.controllers;

import arlenyAres.FutCat_Api.models.Torneo;
import arlenyAres.FutCat_Api.models.Usuario;
import arlenyAres.FutCat_Api.services.TorneoService;
import arlenyAres.FutCat_Api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/torneos")
public class TorneoController {

    @Autowired
    private TorneoService torneoService;
    @Autowired
    private UsuarioService usuarioService;

    // Listar torneos
    @GetMapping
    public List<Torneo> listarTorneos() {
        return torneoService.obtenerTodosLosTorneos();
    }

    // Obtener torneos por usuario
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<?> torneosPorUsuario(@PathVariable int idUsuario) {
        Optional<Usuario> opt = usuarioService.findById(idUsuario);
        if (opt.isPresent()) {
            Usuario usuario = opt.get();
            List<Torneo> torneos = torneoService.getTorneosByIdUser(usuario);
            return ResponseEntity.ok(torneos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Registrar torneo
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarTorneo(
            @RequestParam("nombreOrg") String nombreOrg,
            @RequestParam("tipoDocumento") String tipoDocumento,
            @RequestParam("numDocumento") String numDocumento,
            @RequestParam("telefono") String telefono,
            @RequestParam("nombreTorneo") String nombreTorneo,
            @RequestParam("direccion") String direccion,
            @RequestParam("tipoTorneo") String tipoTorneo,
            @RequestParam("cantidadEquipos") int cantidadEquipos,
            @RequestParam("montoPremio") String montoPremio,
            @RequestParam("idUsuario") int idUsuario
    ) {
        String estado = "Activo";
        Optional<Usuario> optionalUsuario = usuarioService.findById(idUsuario);
        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }
        Usuario usuario = optionalUsuario.get();

        torneoService.registrarTorneo(nombreOrg, tipoDocumento, numDocumento, telefono, nombreTorneo,
                direccion, tipoTorneo, cantidadEquipos, montoPremio, usuario, estado);

        return ResponseEntity.status(HttpStatus.CREATED).body("Torneo registrado");
    }

    // Eliminar torneo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTorneo(@PathVariable Integer id) {
        torneoService.eliminarTorneo(id);
        return ResponseEntity.noContent().build();
    }

    // Retornar torneos con fixture
    @GetMapping("/con-fixture")
    public List<Torneo> findTorneosConFixture() {
        return torneoService.obtenerTorneosConFixture();
    }
}
