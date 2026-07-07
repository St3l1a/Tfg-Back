package estrella.tfg.usuario;

import estrella.tfg.usuario.model.Usuario;
import estrella.tfg.usuario.model.UsuarioDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UsuarioService {

    Usuario get(Long id);


    /**
     * Método para crear o actualizar un {@link Usuario}
     *
     * @param id PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, UsuarioDto dto);

    /**
     * Método para crear o actualizar un {@link Usuario}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /**
     * Recupera un listado de autores {@link Usuario}
     *
     * @return {@link List} de {@link Usuario}
     */
    List<Usuario> findAll();

    Usuario findById(Long id);

    Long login(String correo, String password);
}

