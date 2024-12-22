package arlenyAres.FutCat_Api.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Encuentro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Encuentro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Encuentro")
    private Integer idEncuentro;

    @ManyToOne
    @JoinColumn(name = "ID_Jornada")
    private Jornada idJornada;

    @ManyToOne
    @JoinColumn(name = "ID_Equipo_Local")
    private Equipo idEquipoLocal;

    @ManyToOne
    @JoinColumn(name = "ID_Equipo_Visita")
    private Equipo idEquipoVisita;

    @Column(name = "Equipo_Local")
    private String equipoLocal;

    @Column(name = "Equipo_Visita")
    private String equipoVisita;

    @Column(name = "Goles_Local")
    private Integer golesLocal;

    @Column(name = "Goles_Visita")
    private Integer golesVisita;

    @Column(name = "Estado")
    private String estado;

    @Column(name = "Fecha_Encuentro")
    private LocalDateTime fechaEncuentro;
}