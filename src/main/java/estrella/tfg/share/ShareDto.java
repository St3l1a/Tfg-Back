package estrella.tfg.share;

import java.util.Date;

public class ShareDto {

    Long id;
    Long id_profesional;
    Long id_usuario;
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
