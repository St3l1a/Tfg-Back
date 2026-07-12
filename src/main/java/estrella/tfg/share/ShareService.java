package estrella.tfg.share;

import java.util.List;

public interface ShareService {
    List<Long> getUsersWhoSharedWith(Long id);
}
