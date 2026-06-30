package estrella.tfg.tarea;

import estrella.tfg.tarea.model.Tarea;
import estrella.tfg.tarea.model.TareaDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TareaServiceImpl implements TareaService {

    @Autowired
    TareaRepository tareaRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Tarea get(Long id) {

        return tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, TareaDto data) {

        Tarea usuario;

        if (id == null) {
            usuario = new Tarea();
        } else {
            usuario = this.get(id);
        }

        BeanUtils.copyProperties(data, usuario, "id");

        this.tareaRepository.save(usuario);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.get(id) == null) {
            throw new Exception("Not exists");
        }

        this.tareaRepository.deleteById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tarea> findAll() {

        return (List<Tarea>) this.tareaRepository.findAll();
    }

    @Override
    public TareaDto findById(Long id){
        TareaDto result = new TareaDto();
        Tarea tarea =  tareaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        BeanUtils.copyProperties(tarea, result);

        return result;
    }
}
