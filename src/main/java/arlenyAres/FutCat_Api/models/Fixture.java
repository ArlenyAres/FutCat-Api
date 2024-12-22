package arlenyAres.FutCat_Api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Fixture")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Fixture")
    private Integer idFixture;

    @ManyToOne
    @JoinColumn(name = "ID_Torneo")
    private Torneo torneo;

    @Column(name = "Cant_Jornadas")
    private Integer cantJornadas;
}
