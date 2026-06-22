package estrella.tfg.usuario;

import estrella.tfg.usuario.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
}
