package amanagment.data.models;

import amanagment.data.generators.IdGenerator;
import lombok.Data;
import lombok.NonNull;

@Data
public class Testee {
    private final String id = IdGenerator.generateId(this);
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
}
