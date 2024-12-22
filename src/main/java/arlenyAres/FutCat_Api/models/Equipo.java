package arlenyAres.FutCat_Api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Equipo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Equipo")
    private Integer idEquipo;

    @Column(name = "Nombre_Equipo")
    private String nomEquipo;

    @Column(name = "Nombre_corto")
    private String nomEquipoCorto;

    @Column(name = "Ciudad")
    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "ID_Delegado")
    private Delegado delegado;

    @ManyToOne
    @JoinColumn(name = "ID_Torneo")
    private Torneo torneo;
}
