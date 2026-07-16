package estrella.tfg.usuario.model;

public class UsuarioDto {
    private Long id;

    private String correo;

    private String nombre;

    private String telefono;

    private boolean profesional;

    private String contrasena;

    private Integer edad;

    private Character sexo;

    private String pareja;

    private String familia;

    private String situacion_economica;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isProfesional() {
        return profesional;
    }

    public void setProfesional(boolean profesional) {
        this.profesional = profesional;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getPareja() {
        return pareja;
    }

    public void setPareja(String pareja) {
        this.pareja = pareja;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getSituacionEconomica() {
        return situacion_economica;
    }

    public void setSituacionEconomica(String situacion_economica) {
        this.situacion_economica = situacion_economica;
    }
}
