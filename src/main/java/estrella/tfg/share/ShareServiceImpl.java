package estrella.tfg.share;

import estrella.tfg.config.EncryptionService;
import estrella.tfg.usuario.UsuarioRepository;
import estrella.tfg.usuario.model.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class ShareServiceImpl implements ShareService{
    @Autowired
    private ShareRepository shareRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Autowired
    private EncryptionService encryptionService;

    @Override
    public List<Long> getUsersWhoSharedWith(Long id){
        List<Long> result = new ArrayList<>();
        result =  this.shareRepository.obtenerUsuariosCompartidos(id);
        return result;
    }

    @Override
    public List<Long> obtenerRelacionesDeUsuario(Long id){
        List<Long> result = new ArrayList<>();
        result =  this.shareRepository.obtenerRelacionesDeUsuario(id);
        return result;
    }

    @Override
    public void save(Long id, String correoProfesional) {
        String correoCifrado = encryptionService.encrypt(correoProfesional);
        Usuario usuario = usuarioRepository.findByCorreo(correoCifrado).orElse(null);
        if (usuario == null) {
            throw new RuntimeException("Profesional no encontrado" + correoProfesional);
        }
        Share newShare = new Share();

        newShare.setId_profesional(usuario.getId());
        newShare.setId_usuario(id);
        newShare.setFecha_autorizacion(new Date());

        this.shareRepository.save(newShare);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean eliminarCompartirDatos(Long idUsuario, Long idProfesional) {
        long existentes = shareRepository.countByUsuarioAndProfesional(idUsuario, idProfesional);
        if (existentes == 0) {
            return false;
        }
        shareRepository.deleteByUsuarioAndProfesional(idUsuario, idProfesional);
        return true;
    }
}
