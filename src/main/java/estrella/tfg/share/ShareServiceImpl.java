package estrella.tfg.share;

import jakarta.transaction.Transactional;
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

}
