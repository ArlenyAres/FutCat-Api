package arlenyAres.FutCat_Api.controllers;

import arlenyAres.FutCat_Api.models.Equipo;
import arlenyAres.FutCat_Api.models.Jugador;
import arlenyAres.FutCat_Api.services.EquipoService;
import arlenyAres.FutCat_Api.services.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    @Autowired
    private JugadorService jugadorService;
    @Autowired
    private EquipoService equipoService;

    // Listar jugadores por equipo
    @GetMapping("/equipo/{idEquipo}")
    public List<Jugador> listarJugadoresPorEquipo(@PathVariable Integer idEquipo) {
        return jugadorService.obtenerJugadoresPorEquipo(idEquipo);
    }

    // Registrar un jugador
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarJugador(
            @RequestParam("nombre") String nombre,
            @RequestParam("apellido") String apellido,
            @RequestParam("tipoDocumento") String tipoDocumento,
            @RequestParam("numDocumento") String numDocumento,
            @RequestParam("numCamiseta") int numCamiseta,
            @RequestParam("posicion") String posicion,
            @RequestParam("idEquipo") Integer idEquipo
    ) {
        Equipo equipo = equipoService.obtenerEquipoPorId(idEquipo);
        if (equipo == null) {
            return ResponseEntity.badRequest().body("Equipo no encontrado");
        }
        // Validaciones
        if (jugadorService.verificarNumCamisetaDuplicado(numCamiseta, idEquipo)) {
            return ResponseEntity.badRequest().body("El número de camiseta ya está en uso para este equipo");
        }
        if (jugadorService.verificarNombreApellidoDuplicado(nombre, apellido, idEquipo)) {
            return ResponseEntity.badRequest().body("El nombre y apellido ya están en uso para este equipo");
        }
        // Verificar jugador en el torneo
        String resultado = jugadorService.verificarJugadorTorneo(idEquipo, numDocumento);
        if (!resultado.equals("OK")) {
            return ResponseEntity.badRequest().body(resultado);
        }

        jugadorService.registrarJugador(nombre, apellido, tipoDocumento, numDocumento, numCamiseta, posicion, equipo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Jugador registrado");
    }

    // Eliminar jugador
    @DeleteMapping("/{idJugador}")
    public ResponseEntity<?> eliminarJugador(@PathVariable Integer idJugador) {
        jugadorService.eliminarJugador(idJugador);
        return ResponseEntity.noContent().build();
    }

    // Actualizar jugador
    @PostMapping("/actualizar")
    public ResponseEntity<?> actualizarJugador(
            @RequestParam("idJugador") Integer idJugador,
            @RequestParam("nombre") String nuevoNombre,
            @RequestParam("apellido") String nuevoApellido,
            @RequestParam("numCamiseta") int nuevoNumCamiseta,
            @RequestParam("posicion") String nuevaPosicion,
            @RequestParam("idEquipo") Integer idEquipo
    ) {
        if (jugadorService.verificarNumCamisetaDuplicado(nuevoNumCamiseta, idEquipo)) {
            return ResponseEntity.badRequest().body("Número de camiseta en uso");
        }
        boolean ok = jugadorService.actualizarJugador(idJugador, nuevoNombre, nuevoApellido, nuevoNumCamiseta, nuevaPosicion);
        if (!ok) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Jugador actualizado");
    }
}
