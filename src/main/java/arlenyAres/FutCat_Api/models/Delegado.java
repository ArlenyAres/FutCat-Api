package arlenyAres.FutCat_Api.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Delegado")
public class Delegado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Delegado")
    private Integer idDelegado;

    @Column(name = "Nombre")
    private String nombreDelegado;

    @Column(name = "Apellidos")
    private String apellidosDelegado;

    @Column(name = "Tipo_Documento")
    private String tipoDocumento;

    @Column(name = "Num_Documento")
    private String numDocumento;

    @Column(name = "Telefono")
    private String telefono;


}