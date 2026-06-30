package estrella.tfg.tarea.model;

import jakarta.persistence.Column;

import java.util.Date;

public class TareaDto {
    private Long id;

    private Long idDia;

    private String nombre;

    private String descripcion;

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
