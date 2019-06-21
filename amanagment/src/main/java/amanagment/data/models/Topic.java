package amanagment.data.models;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class Topic implements IModel {
    private final String id = generateId();
    private String parentTopicId;
    @NonNull
    private String name;
    @Singular
    private final Set<Task> tasks = new HashSet<>();

    public boolean addTask(Task task) {
        return tasks.add(task);
    }

    @Override
    public String getIdPrefix() {
        return "TC";
    }
}
