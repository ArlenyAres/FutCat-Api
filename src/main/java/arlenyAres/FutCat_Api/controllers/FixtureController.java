package arlenyAres.FutCat_Api.controllers;

import arlenyAres.FutCat_Api.models.Encuentro;
import arlenyAres.FutCat_Api.models.Jornada;
import arlenyAres.FutCat_Api.models.TablaPosiciones;
import arlenyAres.FutCat_Api.models.Torneo;
import arlenyAres.FutCat_Api.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/fixture")
public class FixtureController {

    @Autowired
    private TorneoService torneoService;
    @Autowired
    private FixtureService fixtureService;
    @Autowired
    private JornadaService jornadaService;
    @Autowired
    private EncuentroService encuentroService;
    @Autowired
    private TablaPosicionesService tablaPosicionesService;

    // Obtener jornadas de un torneo
    @GetMapping("/jornadas")
    public ResponseEntity<?> obtenerJornadas(@RequestParam("idTorneo") Integer idTorneo) {
        List<Jornada> jornadas = jornadaService.obtenerJornadasPorTorneo(idTorneo);
        return ResponseEntity.ok(jornadas);
    }

    // Obtener tabla de posiciones
    @GetMapping("/tablaPosiciones")
    public ResponseEntity<?> obtenerTablaPosiciones(@RequestParam("idTorneo") Integer idTorneo) {
        List<TablaPosiciones> tabla = tablaPosicionesService.obtenerTablaPosiciones(idTorneo);
        return ResponseEntity.ok(tabla);
    }

    // Obtener encuentros de una jornada (ya lo tenías en /encuentros? idJornada=xxx)
    @GetMapping("/encuentros")
    public List<Encuentro> obtenerEncuentrosPorJornada(@RequestParam("idJornada") Integer idJornada) {
        return encuentroService.obtenerEncuentrosPorJornada(idJornada);
    }

    // Generar fixture de un torneo
    @PostMapping("/generar")
    public ResponseEntity<?> generarFixture(@RequestParam("idTorneo") Integer idTorneo) {
        Torneo torneo = torneoService.obtenerTorneoPorId(idTorneo);
        if (torneo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El torneo con ID " + idTorneo + " no existe.");
        }
        List<Integer> equipos = new ArrayList<>();
        fixtureService.recuperarEquipos(idTorneo, equipos);
        fixtureService.generarFixture(equipos, idTorneo);
        return ResponseEntity.ok("Fixture generado correctamente");
    }

    // Actualizar goles y estado
    @PostMapping("/actualizar-goles-y-estado")
    public ResponseEntity<?> actualizarGolesYEstado(@RequestBody Encuentro encuentro) {
        encuentroService.actualizarGolesYEstado(
                encuentro.getIdEncuentro(),
                encuentro.getGolesLocal(),
                encuentro.getGolesVisita()
        );
        return ResponseEntity.ok("Goles y estado actualizados");
    }

    // Actualizar fecha
    @PostMapping("/actualizar-fecha")
    public ResponseEntity<?> actualizarFecha(@RequestBody Encuentro encuentro) {
        encuentroService.actualizarFecha(encuentro.getIdEncuentro(), encuentro.getFechaEncuentro());
        return ResponseEntity.ok("Fecha del encuentro actualizada");
    }
}
