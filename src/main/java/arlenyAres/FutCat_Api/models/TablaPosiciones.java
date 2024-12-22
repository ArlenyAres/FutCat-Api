package arlenyAres.FutCat_Api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TablaPosiciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreEquipo;
    private int pJugados;     // Partidos Jugados
    private int puntos;
    private int pGanados;      // Partidos ganados
    private int pPerdidos;      // Partidos perdidos
    private int pEmpatados;      // Partidos empatados
    private int golesAFavor;     // Goles a favor
    private int golesEnContra;     // Goles en contra
    private int diferenciaGoles;     // Diferencia de goles

    @ManyToOne
    @JoinColumn(name = "ID_Torneo")
    private Torneo torneo;

}