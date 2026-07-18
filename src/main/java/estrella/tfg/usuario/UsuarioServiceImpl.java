package estrella.tfg.usuario;

import estrella.tfg.config.EncryptionService;
import estrella.tfg.usuario.model.Usuario;
import estrella.tfg.usuario.model.UsuarioDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EncryptionService encryptionService;

    private void encryptAndCopy(UsuarioDto dto, Usuario entity) {
        // Copiamos todo excepto los campos que vamos a cifrar manualmente
        BeanUtils.copyProperties(dto, entity, "id", "contrasena", "correo", "nombre", "telefono", "pareja", "familia", "situacionEconomica");

        // Ciframos los campos sensibles (todos String)
        if (dto.getNombre() != null) entity.setNombre(encryptionService.encrypt(dto.getNombre()));
        if (dto.getCorreo() != null) entity.setCorreo(encryptionService.encrypt(dto.getCorreo()));
        if (dto.getTelefono() != null) entity.setTelefono(encryptionService.encrypt(dto.getTelefono()));
        if (dto.getContrasena() != null) entity.setContrasena(encryptionService.encrypt(dto.getContrasena()));
        if (dto.getPareja() != null) entity.setPareja(encryptionService.encrypt(dto.getPareja()));
        if (dto.getFamilia() != null) entity.setFamilia(encryptionService.encrypt(dto.getFamilia()));
        if (dto.getSituacionEconomica() != null) entity.setSituacionEconomica(encryptionService.encrypt(dto.getSituacionEconomica()));
    }

    private Usuario decryptEntity(Usuario entity) {
        if (entity == null) return null;

        // Creamos una nueva instancia (NO gestionada)
        Usuario decrypted = new Usuario();

        // Copiamos todas las propiedades excepto las que vamos a descifrar manualmente
        BeanUtils.copyProperties(entity, decrypted,
                "nombre", "correo", "telefono", "contrasena", "pareja", "familia", "situacionEconomica");

        // Ahora desciframos los campos sensibles sobre la copia
        if (entity.getNombre() != null) {
            decrypted.setNombre(encryptionService.decrypt(entity.getNombre()));
        }
        if (entity.getCorreo() != null) {
            decrypted.setCorreo(encryptionService.decrypt(entity.getCorreo()));
        }
        if (entity.getTelefono() != null) {
            decrypted.setTelefono(encryptionService.decrypt(entity.getTelefono()));
        }
        // No desciframos la contraseña (se mantiene cifrada)
        if (entity.getPareja() != null) {
            decrypted.setPareja(encryptionService.decrypt(entity.getPareja()));
        }
        if (entity.getFamilia() != null) {
            decrypted.setFamilia(encryptionService.decrypt(entity.getFamilia()));
        }
        if (entity.getSituacionEconomica() != null) {
            decrypted.setSituacionEconomica(encryptionService.decrypt(entity.getSituacionEconomica()));
        }

        // Aseguramos que el ID se copie (BeanUtils ya lo copia si no está excluido)
        // Pero como no lo excluimos, se copia automáticamente.

        return decrypted;
    }

    @Override
    public Usuario get(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return decryptEntity(usuario);
    }

    @Override
    public void save(Long id, UsuarioDto data) {
        Usuario usuario;
        if (id == null) {
            usuario = new Usuario();
        } else {
            usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        }
        encryptAndCopy(data, usuario);
        usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (!usuarioRepository.existsById(id)) {
            throw new Exception("El usuario no existe");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        return usuarios.stream().map(this::decryptEntity).collect(Collectors.toList());
    }

    @Override
    public Usuario findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return decryptEntity(usuario);
    }

    @Override
    public Long login(String correo, String password) {
        String correoCifrado = encryptionService.encrypt(correo);
        Usuario usuario = usuarioRepository.findByCorreo(correoCifrado).orElse(null);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
        String passwordCifrada = encryptionService.encrypt(password);
        if (!usuario.getContrasena().equals(passwordCifrada)) {
            throw new RuntimeException("Credenciales incorrectas");
        }
        return usuario.getId();
    }
}