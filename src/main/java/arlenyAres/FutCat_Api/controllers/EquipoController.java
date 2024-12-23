package arlenyAres.FutCat_Api.controllers;

import arlenyAres.FutCat_Api.models.Equipo;
import arlenyAres.FutCat_Api.models.Torneo;
import arlenyAres.FutCat_Api.services.EquipoService;
import arlenyAres.FutCat_Api.services.TorneoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private TorneoService torneoService;

    @Autowired
    private EquipoService equipoService;

    // Obtener equipos de un torneo
    @GetMapping("/torneo/{idTorneo}")
    public List<Equipo> obtenerEquiposPorTorneo(@PathVariable Integer idTorneo) {
        return equipoService.obtenerEquiposPorTorneo(idTorneo);
    }

    // Registrar equipo
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarEquipo(@RequestParam String nombreDelegado,
                                             @RequestParam String apellidosDelegado,
                                             @RequestParam String tipoDocumentoDelegado,
                                             @RequestParam String numDocumentoDelegado,
                                             @RequestParam String telefonoDelegado,
                                             @RequestParam String nombreEquipo,
                                             @RequestParam String nombreCortoEquipo,
                                             @RequestParam String ciudadEquipo,
                                             @RequestParam Integer idTorneo) {
        Torneo torneo = torneoService.obtenerTorneoPorId(idTorneo);
        if (torneo == null) {
            return ResponseEntity.badRequest().body("Torneo no encontrado con ID: " + idTorneo);
        }

        equipoService.registrarEquipo(nombreDelegado, apellidosDelegado, tipoDocumentoDelegado, numDocumentoDelegado,
                telefonoDelegado, nombreEquipo, nombreCortoEquipo, ciudadEquipo, torneo);

        return ResponseEntity.status(HttpStatus.CREATED).body("Equipo registrado correctamente");
    }

    // Mostrar todos los equipos (opcional)
    @GetMapping
    public List<Equipo> listarTodosLosEquipos() {
        return equipoService.obtenerTodosLosEquipos();
    }

    // Eliminar equipo
    @DeleteMapping("/{idEquipo}")
    public ResponseEntity<?> eliminarEquipo(@PathVariable Integer idEquipo) {
        equipoService.eliminarEquipo(idEquipo);
        return ResponseEntity.noContent().build();
    }
}
