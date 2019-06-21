package amanagment.data.models;

import lombok.*;

@Data
@Builder
public class TaskElement implements IModel {
    private final String id = generateId();
    private String text;
    private String imageId;

    @Override
    public String getIdPrefix() {
        return "TE";
    }
}
