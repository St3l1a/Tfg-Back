package estrella.tfg.dia;

import estrella.tfg.dia.model.Dia;
import estrella.tfg.dia.model.DiaDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DiaServiceImpl implements DiaService {

    @Autowired
    DiaRepository diaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Dia get(Long id) {

        return diaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dia no encontrado"));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public DiaDto save(Long id, DiaDto data) {

        Dia dia;

        if (id == null) {
            dia = new Dia();
        } else {
            dia = this.get(id);
        }

        BeanUtils.copyProperties(data, dia, "id");

        this.diaRepository.save(dia);

        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.diaRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dia> findAll() {

        return (List<Dia>) this.diaRepository.findAll();
    }

    @Override
    public DiaDto findById(Long id){
        DiaDto result = new DiaDto();
        Dia dia =  diaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        BeanUtils.copyProperties(dia, result);

        return result;
    }
}
