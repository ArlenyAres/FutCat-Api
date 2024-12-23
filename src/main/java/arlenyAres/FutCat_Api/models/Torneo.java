package arlenyAres.FutCat_Api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "TORNEO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Torneo")
    private Integer idTorneo;

    @ManyToOne
    @JoinColumn(name = "ID_Organizador")
    private Organizador organizador;

    @Column(name = "Nombre_Torneo")
    private String nombreTorneo;

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Tipo_Torneo")
    private String tipoTorneo;

    @Column(name = "Cantidad_Equipos")
    private Integer cantEquipos;

    @Column(name = "Monto_Premio")
    private String montoPremio;

    @Column(name = "Goles_Walkover")
    private Integer golesWalkover;

    @Column(name = "Estado")
    private String estado;

    @Column(name = "Fecha_Inicio")
    private LocalDateTime fechaInicio;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;
}