package amanagment.data.models;

import lombok.*;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Value
public class Assessment implements IModel {
    private final String id = generateId();
    private final Instant creationInstant = Clock.systemUTC().instant();
    @NonNull
    private String name;
    @NonNull
    private Testee testee;
    @Singular
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public String getIdPrefix() {
        return "AS";
    }
}
