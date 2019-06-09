package amanagment.services;

import amanagment.data.dblayer.IStorage;
import amanagment.data.exceptions.EntityExistsException;
import amanagment.data.exceptions.EntityNotExistsException;
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
    public String addTopic(String name, String parentTopicId) throws EntityExistsException, EntityNotExistsException {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("The name of the topic have to be provided");

        if (storage.findTopicByName(name).size() > 0)
            throw new EntityExistsException("Topic already exists");

        Topic.TopicBuilder newlyCreatedTopicBuilder = Topic.builder().name(name);
        if (parentTopicId != null) {
            Optional<Topic> topic = storage.getTopicById(parentTopicId);
            if (topic.isPresent()) {
                newlyCreatedTopicBuilder.parentTopicId(parentTopicId);
            } else {
                throw new EntityNotExistsException("Parent topic you want the new topic to add to, doesn't exist");
            }
        }
        Topic newlyCreatedTopic = newlyCreatedTopicBuilder.build();

        storage.putTopic(newlyCreatedTopic);

        return newlyCreatedTopic.getId();
    }

    @Override
    public boolean removeTopic(String id) {
        return storage.deleteTopic(id);
    }

    @Override
    public String createTask(String stemText, Image stemImage, String topicIdToAddTo) throws EntityNotExistsException {
        if (StringUtils.isBlank(stemText)) {
            throw new IllegalArgumentException("The name of the task have to be provided");
        }

        if (StringUtils.isBlank(topicIdToAddTo)) {
            throw new IllegalArgumentException("The id of the topic to connect the task to have to be provided");
        }

        TaskElement.TaskElementBuilder stem = TaskElement.builder().text(stemText);

        assignImageToStem(stem, stemImage);

        Task newTask = Task.builder().stem(stem.build()).build();

        assignTaskToTopic(newTask, topicIdToAddTo);

        storage.putTask(newTask);

        return newTask.getId();
    }

    private void assignImageToStem(TaskElement.TaskElementBuilder stemBuilder, Image stemImage) {
        if (stemBuilder == null)
            return;

        if (stemImage == null)
            return;

        Optional<Image> existingImage = storage.getImageById(stemImage.getId());
        if (existingImage.isPresent()) {
            stemBuilder.imageId(existingImage.get().getId());
        } else {
            storage.putImage(stemImage);
            stemBuilder.imageId(stemImage.getId());
        }
    }

    private void assignTaskToTopic(Task task, String topicIdToAddTo) throws EntityNotExistsException {
        Optional<Topic> topic = storage.getTopicById(topicIdToAddTo);

        if (topic.isPresent()) {
            Topic foundTopic = topic.get();
            foundTopic.getTasks().add(task);
            storage.putTopic(foundTopic);
        } else {
            throw new EntityNotExistsException("The topic to add task to is not found");
        }
    }

    @Override
    public boolean removeTaskFromTopic(String taskId, String topicId) throws EntityNotExistsException {
        boolean ret = false;

        Optional<Topic> topic = storage.getTopicById(topicId);
        topic.orElseThrow(() -> new EntityNotExistsException(String.format("Topic %s doesn't exist",topicId)));

        Topic t = topic.get();
        Optional<Task> taskToDelete = t.getTasks().stream().filter(tsk -> tsk.getId().equals(taskId)).findAny();
        taskToDelete.ifPresent(task -> t.getTasks().remove(task));
        storage.putTopic(t);
        ret = true;

        return ret;
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
