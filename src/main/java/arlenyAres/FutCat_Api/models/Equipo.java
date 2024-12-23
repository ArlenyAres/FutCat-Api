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


    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNomEquipo() {
        return nomEquipo;
    }

    public void setNomEquipo(String nomEquipo) {
        this.nomEquipo = nomEquipo;
    }

    public String getNomEquipoCorto() {
        return nomEquipoCorto;
    }

    public void setNomEquipoCorto(String nomEquipoCorto) {
        this.nomEquipoCorto = nomEquipoCorto;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Delegado getDelegado() {
        return delegado;
    }

    public void setDelegado(Delegado delegado) {
        this.delegado = delegado;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }
}
