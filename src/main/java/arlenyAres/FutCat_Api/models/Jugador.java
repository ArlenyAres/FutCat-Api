package arlenyAres.FutCat_Api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Jugador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Jugador")
    private Integer idJugador;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellidos")
    private String apellido;

    @Column(name = "Tipo_Documento")
    private String tipoDocumento;

    @Column(name = "Num_Documento")
    private String numDocumento;

    @Column(name = "Num_Camiseta")
    private int numCamiseta;

    @Column(name = "Posicion")
    private String posicion;

    @ManyToOne
    @JoinColumn(name = "ID_Equipo")
    private Equipo equipo;
}

