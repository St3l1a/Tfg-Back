package estrella.tfg.registro;

import estrella.tfg.config.EncryptionService;
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

    @Autowired
    private EncryptionService encryptionService;

    // ---------- MÉTODOS PRIVADOS DE CIFRADO ----------

    /**
     * Cifra la nota del DTO (si no es null) y la asigna al registro.
     */
    private void encryptNota(RegistroDto dto, Registro entity) {
        if (dto.getNota() != null && !dto.getNota().isEmpty()) {
            entity.setNota(encryptionService.encrypt(dto.getNota()));
        } else {
            // Si la nota es null o vacía, la dejamos como null (o vacía) en la entidad
            entity.setNota(dto.getNota());
        }
    }

    /**
     * Descifra la nota de la entidad y la devuelve como String.
     */
    private String decryptNota(String encryptedNota) {
        if (encryptedNota == null || encryptedNota.isEmpty()) {
            return encryptedNota;
        }
        try {
            return encryptionService.decrypt(encryptedNota);
        } catch (Exception e) {
            // Si falla el descifrado, devolvemos la nota cifrada o lanzamos excepción.
            // Podemos lanzar RuntimeException para evitar datos corruptos.
            throw new RuntimeException("Error al descifrar la nota", e);
        }
    }

    // ---------- MÉTODOS PÚBLICOS ----------

    @Override
    public RegistroDto get(Long id) {
        Registro registro = registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        return buildRegistroDto(registro);
    }

    @Override
    public RegistroDto save(Long id, RegistroDto data) {
        Registro registro;
        if (id == null) {
            registro = new Registro();
        } else {
            registro = registroRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        }

        // Copiar propiedades simples (idDia, idSentimiento, etc.) excepto la nota
        BeanUtils.copyProperties(data, registro, "id", "nota");

        // Cifrar y asignar la nota
        encryptNota(data, registro);

        // Guardar
        Registro saved = registroRepository.save(registro);

        // Construir DTO de respuesta con la nota descifrada
        return buildRegistroDto(saved);
    }

    @Override
    public void delete(Long id) throws Exception {
        if (!registroRepository.existsById(id)) {
            throw new Exception("Not exists");
        }
        registroRepository.deleteById(id);
    }

    @Override
    public List<RegistroDto> findAll() {
        List<Registro> registros = (List<Registro>) registroRepository.findAll();
        List<RegistroDto> result = new ArrayList<>();
        for (Registro r : registros) {
            result.add(buildRegistroDto(r));
        }
        return result;
    }

    @Override
    public RegistroDto findById(Long id) {
        Registro registro = registroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        return buildRegistroDto(registro);
    }

    @Override
    public List<RegistroDto> findByUserId(Long userId) {
        List<Registro> registros = registroRepository.findRegistroByUserId(userId);
        List<RegistroDto> result = new ArrayList<>();
        for (Registro r : registros) {
            result.add(buildRegistroDto(r));
        }
        return result;
    }

    // ---------- MÉTODO PRIVADO AUXILIAR PARA CONSTRUIR DTO ----------
    private RegistroDto buildRegistroDto(Registro registro) {
        RegistroDto dto = new RegistroDto();
        // Copiar campos simples (id, idDia, idSentimiento) pero NO la nota (la manejamos aparte)
        BeanUtils.copyProperties(registro, dto, "nota");

        // Descifrar la nota y asignarla al DTO
        dto.setNota(decryptNota(registro.getNota()));

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
                dto.setFecha(dia.getFecha());
            }
        }

        return dto;
    }
}