package estrella.tfg.registro;

import estrella.tfg.registro.model.Registro;
import estrella.tfg.registro.model.RegistroDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RegistroServiceImpl implements RegistroService {

    @Autowired
    RegistroRepository registroRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Registro get(Long id) {

        return this.registroRepository.findById(id).orElse(null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, RegistroDto data) {

        Registro usuario;

        if (id == null) {
            usuario = new Registro();
        } else {
            usuario = this.get(id);
        }

        BeanUtils.copyProperties(data, usuario, "id");

        this.registroRepository.save(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.registroRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Registro> findAll() {

        return (List<Registro>) this.registroRepository.findAll();
    }
}
