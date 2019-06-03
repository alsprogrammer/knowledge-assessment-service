package amanagment.data.models;

import amanagment.data.generators.IdGenerator;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class Task implements IModel {
    private final String id = IdGenerator.generateId(this);
    @NonNull
    private TaskElement stem;
    @Singular
    private final Set<TaskElement> options = new HashSet<>();
    @Singular
    private final Set<String> answerIds = new HashSet<>();
    @Singular
    private final Set<String> distractorIds = new HashSet<>();

    public boolean addAnswer(String optionText) {
        boolean ret;

        TaskElement newAnswer = TaskElement.builder().text(optionText).build();
        ret = options.add(newAnswer);
        if (ret)
            answerIds.add(newAnswer.getId());

        return ret;
    }

    public boolean addDistractor(String optionText) {
        boolean ret;

        TaskElement newAnswer = TaskElement.builder().text(optionText).build();
        ret = options.add(newAnswer);
        if (ret)
            distractorIds.add(newAnswer.getId());

        return ret;
    }

    public boolean addAnswer(String optionText, Image image) {
        boolean ret;

        TaskElement newAnswer = TaskElement.builder().text(optionText).imageId(image.getId()).build();
        ret = options.add(newAnswer);
        if (ret)
            answerIds.add(newAnswer.getId());

        return ret;
    }

    public boolean addDistractor(String optionText, Image image) {
        boolean ret;

        TaskElement newAnswer = TaskElement.builder().text(optionText).imageId(image.getId()).build();
        ret = options.add(newAnswer);
        if (ret)
            distractorIds.add(newAnswer.getId());

        return ret;
    }

    public boolean removeOption(TaskElement optionToRemove){
        boolean ret;

        ret = options.remove(optionToRemove);
        if (ret) {
            answerIds.remove(optionToRemove.getId());
            distractorIds.remove(optionToRemove.getId());
        }

        return ret;
    }

    @Override
    public String getIdPrefix() {
        return "TK";
    }
}
