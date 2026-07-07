package estrella.tfg.usuario;

import estrella.tfg.usuario.model.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByCorreoAndContrasena(String correo, String contrasena);


    Optional<Usuario> findByCorreo(String correo);
}
