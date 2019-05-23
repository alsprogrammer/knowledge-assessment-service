package amanagment.data.models;

import amanagment.data.generators.IdGenerator;
import lombok.*;

@Value
@Builder(toBuilder = true)
public class Image {
    private final String id = IdGenerator.generateId(this);
    @NonNull
    private String caption;
    @NonNull
    private String image;
}
