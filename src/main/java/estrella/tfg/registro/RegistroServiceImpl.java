package estrella.tfg.registro;

import estrella.tfg.registro.model.Registro;
import estrella.tfg.registro.model.RegistroDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

        return registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
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

    @Override
    public RegistroDto findById(Long id){
        RegistroDto result = new RegistroDto();
        Registro registro =  registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        BeanUtils.copyProperties(registro, result);

        return result;
    }
    public List<RegistroDto> findByUserId(Long id){
        List<RegistroDto> result = new ArrayList<>();

        List<Registro> all = registroRepository.findRegistroByUserId(id);

        if(all.isEmpty()){
            throw new RuntimeException("Usuario no encontrado");

        }
        for(Registro r: all){

            RegistroDto dto = new RegistroDto();
            BeanUtils.copyProperties(r, dto);
            result.add(dto);

        }

        return result;    }
}
