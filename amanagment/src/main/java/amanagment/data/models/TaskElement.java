package amanagment.data.models;

import amanagment.data.generators.IdGenerator;
import lombok.*;

@Data
@Builder
public class TaskElement implements IModel {
    private final String id = IdGenerator.generateId(this);
    private String text;
    private String imageId;

    @Override
    public String getIdPrefix() {
        return "TE";
    }
}
