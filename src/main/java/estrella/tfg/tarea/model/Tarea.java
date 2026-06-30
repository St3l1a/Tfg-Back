package estrella.tfg.tarea.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tarea")
public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_dia")
    private Long idDia;

    @Column(name = "nombre")
    private String nombre;


    @Column(name = "descripcion")
    private String descripcion;


    @Column(name = "completado")
    private Boolean completado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdDia() {
        return idDia;
    }

    public void setIdDia(Long idDia) {
        this.idDia = idDia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }
}
