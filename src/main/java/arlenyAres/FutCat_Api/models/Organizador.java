package arlenyAres.FutCat_Api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Organizador")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Organizador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Organizador")
    private Integer idOrganizador;

    @Column(name = "Nombre_Organizador")
    private String nombreOrganizador;

    @Column(name = "Tipo_Documento")
    private String tipoDocumento;

    @Column(name = "Num_Documento")
    private String numDocumento;

    @Column(name = "Telefono")
    private String telefono;
}