package arlenyAres.FutCat_Api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Jornada")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jornada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Jornada")
    private Integer idJornada;

    @ManyToOne
    @JoinColumn(name = "ID_Fixture")
    private Fixture fixture;

    @Column(name = "Num_Fecha")
    private Integer numFecha;

    @Column(name = "Cant_Encuentros")
    private Integer cantEncuentros;
}
