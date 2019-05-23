package amanagment.data.generators;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class IdGenerator {
    public String generateId(Object entity) {
        return UUID.randomUUID().toString();
    }
}
