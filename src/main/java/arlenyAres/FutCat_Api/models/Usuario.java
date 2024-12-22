package arlenyAres.FutCat_Api.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Integer idUsuario;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellidos")
    private String apellidos;

    @Column(name = "Correo")
    private String correo;

    @Column(name = "Username")
    private String username;

    @Column(name = "Contraseña") // OJO con los caracteres especiales
    private String contraseña;
}
