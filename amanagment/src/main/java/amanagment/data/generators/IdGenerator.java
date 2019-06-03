package amanagment.data.generators;

import amanagment.data.models.IModel;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class IdGenerator {
    private final String idTemplate = "%s%s";

    public String generateId(IModel model) {
        return String.format(idTemplate, model.getIdPrefix(), UUID.randomUUID().toString());
    }
}
