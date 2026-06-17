package estrella.tfg.usuario.model;

import jakarta.persistence.*;
import org.hibernate.mapping.PrimaryKey;
import org.springframework.context.annotation.Primary;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "correo", nullable = false)
    private String correo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "nombre", nullable = false)
    private String telefono;

    @Column(name = "profesional")
    private boolean profesional;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @Column(name = "edad")
    private Integer edad;

    @Column(name = "sexo")
    private Character sexo;

    @Column(name = "pareja")
    private String pareja;

    @Column(name = "familia")
    private String familia;

    @Column(name = "situacionEconomica")
    private String situacionEconomica;

}
