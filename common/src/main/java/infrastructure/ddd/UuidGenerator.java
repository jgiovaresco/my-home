package infrastructure.ddd;

import ddd.IdGenerator;
import java.util.UUID;

public class UuidGenerator implements IdGenerator {

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
