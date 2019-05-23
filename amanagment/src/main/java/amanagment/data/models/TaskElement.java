package amanagment.data.models;

import amanagment.data.generators.IdGenerator;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
abstract class TaskElement {
    private final String id = IdGenerator.generateId(this);
    @NonNull
    private String text;
    private String imageId;
}
