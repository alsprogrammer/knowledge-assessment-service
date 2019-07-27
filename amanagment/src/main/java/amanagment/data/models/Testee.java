package amanagment.data.models;

import amanagment.data.generators.IdGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
public class Testee implements IModel {
    private final String id = IdGenerator.generateId(this);
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    @Override
    public String getIdPrefix() {
        return "TT";
    }
}
