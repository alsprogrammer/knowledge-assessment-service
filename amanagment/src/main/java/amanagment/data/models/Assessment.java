package amanagment.data.models;

import amanagment.data.generators.IdGenerator;
import lombok.*;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
public class Assessment {
    private final String id = IdGenerator.generateId(this);
    private final Instant creationInstant = Clock.systemUTC().instant();
    @NonNull
    private String name;
    @NonNull
    private Testee testee;
    @Singular
    private final List<Task> tasks = new ArrayList<>();
}
