package estrella.tfg.registro.model;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "registro")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_dia")
    private Long idDia;

    @Column(name = "id_sentimiento")
    private Long idSentimiento;

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

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Long getIdDia() {
        return idDia;
    }

    public void setIdDia(Long idDia) {
        this.idDia = idDia;
    }
}
