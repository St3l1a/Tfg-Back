package estrella.tfg.registro.model;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.beans.ConstructorProperties;
import java.util.Date;

@Entity
@Table(name = "registro")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sentimiento_id")
    private Long idSentimiento;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "nota")
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
