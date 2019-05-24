package amanagment.data.models;

import amanagment.data.generators.IdGenerator;
import lombok.*;

import java.util.UUID;

@Data
@Builder
public class TaskElement {
    private final String id = IdGenerator.generateId(this);
    private String text;
    private String imageId;
}
