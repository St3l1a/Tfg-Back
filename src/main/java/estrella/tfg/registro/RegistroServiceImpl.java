package estrella.tfg.registro;

import estrella.tfg.dia.DiaRepository;
import estrella.tfg.dia.model.Dia;
import estrella.tfg.registro.model.Registro;
import estrella.tfg.registro.model.RegistroDto;
import estrella.tfg.sentimiento.SentimientoRepository;
import estrella.tfg.sentimiento.model.Sentimiento;
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
    private RegistroRepository registroRepository;

    @Autowired
    private SentimientoRepository sentimientoRepository;

    @Autowired
    private DiaRepository diaRepository;

    /**
     * Obtiene un registro por su ID y lo convierte a DTO con fecha y valor.
     */
    @Override
    public RegistroDto get(Long id) {
        Registro registro = registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        return buildRegistroDto(registro);
    }

    /**
     * Guarda (crea o actualiza) un registro y devuelve el DTO completo.
     */
    @Override
    public RegistroDto save(Long id, RegistroDto data) {
        Registro registro;
        if (id == null) {
            registro = new Registro();
        } else {
            // Si existe, lo obtenemos como entidad (no como DTO)
            registro = registroRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        }

        // Copiar propiedades (idDia, idSentimiento, nota)
        BeanUtils.copyProperties(data, registro, "id");

        // Guardar
        Registro saved = this.registroRepository.save(registro);

        // Construir DTO de respuesta
        return buildRegistroDto(saved);
    }

    /**
     * Elimina un registro por su ID.
     */
    @Override
    public void delete(Long id) throws Exception {
        if (!registroRepository.existsById(id)) {
            throw new Exception("Not exists");
        }
        this.registroRepository.deleteById(id);
    }

    /**
     * Obtiene todos los registros como entidades (sin DTO).
     * Si quieres devolver DTOs, puedes cambiar la firma.
     */
    @Override
    public List<RegistroDto> findAll() {
        List<Registro> registros = (List<Registro>) this.registroRepository.findAll();
        List<RegistroDto> result = new ArrayList<>();
        for (Registro r : registros) {
            result.add(buildRegistroDto(r));
        }
        return result;
    }

    /**
     * Busca un registro por ID y devuelve su DTO.
     * (similar a get, pero con nombre más descriptivo)
     */
    @Override
    public RegistroDto findById(Long id) {
        Registro registro = registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        return buildRegistroDto(registro);
    }

    /**
     * Obtiene todos los registros de un usuario (por su ID) y los devuelve como DTOs.
     */
    @Override
    public List<RegistroDto> findByUserId(Long userId) {
        List<Registro> registros = registroRepository.findRegistroByUserId(userId);
        List<RegistroDto> result = new ArrayList<>();
        for (Registro r : registros) {
            result.add(buildRegistroDto(r));
        }
        return result;
    }

    // ---------- MÉTODO PRIVADO AUXILIAR ----------
    private RegistroDto buildRegistroDto(Registro registro) {
        RegistroDto dto = new RegistroDto();
        // Copiar campos simples (id, idDia, idSentimiento, nota)
        BeanUtils.copyProperties(registro, dto);

        // Obtener valor del sentimiento
        Long valor = 0L;
        if (registro.getIdSentimiento() != null) {
            Sentimiento sentimiento = sentimientoRepository.findById(registro.getIdSentimiento()).orElse(null);
            if (sentimiento != null && sentimiento.getValor() != null) {
                valor = sentimiento.getValor();
            }
        }
        dto.setValor(valor);

        // Obtener fecha del día asociado
        if (registro.getIdDia() != null) {
            Dia dia = diaRepository.findById(registro.getIdDia()).orElse(null);
            if (dia != null && dia.getFecha() != null) {
                dto.setFecha(dia.getFecha()); // LocalDate
            }
        }

        return dto;
    }
}