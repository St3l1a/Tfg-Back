package estrella.tfg.usuario;

import estrella.tfg.usuario.model.Usuario;
import estrella.tfg.usuario.model.UsuarioDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Usuario get(Long id) {

        return this.usuarioRepository.findById(id).orElse(null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, UsuarioDto data) {

        Usuario usuario;

        if (id == null) {
            usuario = new Usuario();
        } else {
            usuario = this.get(id);
        }

        BeanUtils.copyProperties(data, usuario, "id");

        this.usuarioRepository.save(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.usuarioRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Usuario> findAll() {

        return (List<Usuario>) this.usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {

        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    @Override
    public Long login(String correo, String password) {

        Usuario usuario = usuarioRepository.findByCorreo(correo).orElse(null);

        if (usuario == null) {
            throw ((new RuntimeException("Usuario no encontrado")));
        }

        if (!usuario.getContrasena().equals(password)) {
           throw ( new RuntimeException("Datos no encontrado"));
        }

        return usuario.getId();
    }
}
