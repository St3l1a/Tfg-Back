package estrella.tfg.sentimiento;

import estrella.tfg.sentimiento.model.Sentimiento;
import estrella.tfg.sentimiento.model.SentimientoDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SentimientoServiceImpl implements SentimientoService {

    @Autowired
    SentimientoRepository sentimientoRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Sentimiento get(Long id) {

        return this.sentimientoRepository.findById(id).orElse(null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, SentimientoDto data) {

        Sentimiento sentimiento;

        if (id == null) {
            sentimiento = new Sentimiento();
        } else {
            sentimiento = this.get(id);
        }

        BeanUtils.copyProperties(data, sentimiento, "id");

        this.sentimientoRepository.save(sentimiento);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.sentimientoRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sentimiento> findAll() {

        return (List<Sentimiento>) this.sentimientoRepository.findAll();
    }
}
