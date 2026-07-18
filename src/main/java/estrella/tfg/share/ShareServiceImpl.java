package estrella.tfg.share;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class ShareServiceImpl implements ShareService{
    @Autowired
    private ShareRepository shareRepository;

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
    public void save(Long id, ShareDto data) {

        Share Share;

        Share = new Share();

        BeanUtils.copyProperties(data, Share, "id");

        this.shareRepository.save(Share);
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
