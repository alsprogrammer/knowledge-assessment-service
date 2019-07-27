package amanagment.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
public class Testee implements IModel {
    private final String id = generateId();
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;

    @Override
    public String getIdPrefix() {
        return "TT";
    }
}
