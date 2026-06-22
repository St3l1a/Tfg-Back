package estrella.tfg.registro.model;

import jakarta.persistence.*;

import java.util.Date;

public class RegistroDto {
    private Long id;

    private Long idSentimiento;

    private Date fecha;

    private String nota;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSentimiento() {
        return idSentimiento;
    }

    public void setIdSentimiento(Long idSentimiento) {
        this.idSentimiento = idSentimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
