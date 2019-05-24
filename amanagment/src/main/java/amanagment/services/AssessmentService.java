package amanagment.services;

import amanagment.data.dblayer.IStorage;
import amanagment.data.generators.IdGenerator;
import amanagment.data.models.*;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class AssessmentService implements IAssessmentService {
    private final IStorage storage;

    @Inject
    public AssessmentService(IStorage storage) {
        this.storage = storage;
    }

    @Override
    public String addTopic(String name, Optional<String> parentTopicId) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("The name of the topic have to be provided");

        Topic.TopicBuilder topicBuilder = Topic.builder().name(name);
        parentTopicId.ifPresent(topicBuilder::parentTopicId);
        Topic newlyCreatedTopic = topicBuilder.build();
        storage.putTopic(newlyCreatedTopic);

        return newlyCreatedTopic.getId();
    }

    @Override
    public boolean removeTopic(String id) {
        return storage.deleteTopic(id);
    }

    @Override
    public String createTask(String stemText, Optional<String> stemImageBase64, String topicIdToAddTo) {
        if (StringUtils.isBlank(stemText)) {
            throw new IllegalArgumentException("The name of the task have to be provided");
        }

        if (StringUtils.isBlank(topicIdToAddTo)) {
            throw new IllegalArgumentException("The id of the topic to connect the task to have to be provided");
        }

        TaskElement.TaskElementBuilder stem = TaskElement.builder().text(stemText);
        
        stemImageBase64.ifPresent(image -> {
            Optional<Image> existingImage = storage.getImageById(IdGenerator.generateId(image));
            if (existingImage.isPresent()) {
                stem.imageId(existingImage.get().getId());
            } else {
                Image newImage = Image.builder().image(stemImageBase64.get()).build();
                storage.putImage(newImage);
                stem.imageId(newImage.getId());
            }
        });

        Task newTask = Task.builder().stem(stem.build()).build();
        storage.putTask(newTask);

        return null;
    }

    @Override
    public boolean removeTaskFromTopic(String taskId, String topicId) {
        return false;
    }

    @Override
    public String createOption(String optionText, String optionImageBase64, String taskIdToAddTo) {
        return null;
    }

    @Override
    public boolean removeOptionFromTask(String optionId, String taskId) {
        return false;
    }

    @Override
    public Assessment generateAssessment(int taskNum, List<String> topicIds, String testeeId) {
        return null;
    }

    @Override
    public List<Assessment> getAssessmentsByTesteeId(String testeeId) {
        return null;
    }

    @Override
    public Optional<Assessment> getAssessmentById(String id) {
        return storage.getAssessmentById(id);
    }
}
