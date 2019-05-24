package amanagment.data.models;

import amanagment.data.generators.IdGenerator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class Task {
    private final String id = IdGenerator.generateId(this);
    @NonNull
    private TaskElement stem;
    @Singular
    private final Set<TaskElement> options = new HashSet<>();
    @Singular
    private final Set<String> answerIds = new HashSet<>();
    @Singular
    private final Set<String> distractorIds = new HashSet<>();
}
