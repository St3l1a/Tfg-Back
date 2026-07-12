package estrella.tfg.share;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "compartir_datos")
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "id_profesional")
    Long id_profesional;

    @Column(name = "id_usuario")
    Long id_usuario;

    @Column(name = "fecha_autorizacion")
    Date fecha_autorizacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_profesional() {
        return id_profesional;
    }

    public void setId_profesional(Long id_profesional) {
        this.id_profesional = id_profesional;
    }

    public Long getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Date getFecha_autorizacion() {
        return fecha_autorizacion;
    }

    public void setFecha_autorizacion(Date fecha_autorizacion) {
        this.fecha_autorizacion = fecha_autorizacion;
    }
}
