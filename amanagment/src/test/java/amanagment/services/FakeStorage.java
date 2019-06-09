package amanagment.services;

import amanagment.data.dblayer.IStorage;
import amanagment.data.models.*;

import java.util.*;
import java.util.stream.Collectors;

public class FakeStorage implements IStorage {
    private final Map<String, Assessment> assessments = new HashMap<>();
    private final Map<String, Image> images = new HashMap<>();
    private final Map<String, Task> tasks = new HashMap<>();
    private final Map<String, Topic> topics = new HashMap<>();

    @Override
    public Optional<Assessment> getAssessmentById(String id) {
        return Optional.ofNullable(assessments.get(id));
    }

    @Override
    public List<Assessment> findAssessmentsByName(String name) {
        return assessments.values().stream().filter(v -> v.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public boolean putAssessment(Assessment assessment) {
        return assessments.put(assessment.getId(), assessment) != null;
    }

    @Override
    public boolean deleteAssessment(String id) {
        return assessments.remove(id) != null;
    }

    @Override
    public Optional<Image> getImageById(String id) {
        return Optional.ofNullable(images.get(id));
    }

    @Override
    public List<Image> findImagesByCaption(String caption) {
        return images.values().stream().filter(i -> i.getCaption().equals(caption)).collect(Collectors.toList());
    }

    @Override
    public boolean putImage(Image image) {
        return images.put(image.getId(), image) != null;
    }

    @Override
    public boolean deleteImage(String id) {
        return images.remove(id) != null;
    }

    @Override
    public Optional<TaskElement> getOptionById(String id) {
        return tasks
                .values()
                .stream()
                .map(Task::getOptions)
                .flatMap(opts -> opts.stream())
                .filter(opt -> opt.getId().equals(id))
                .findAny();
    }

    @Override
    public boolean putOption(TaskElement option) {
        List<Task> tasksToUpdate = tasksContainOptionId(option.getId());

        for (Task curTask: tasksToUpdate) {
            Optional<TaskElement> optionToDelete = curTask.getOptions().stream().filter(opt -> opt.getId().equals(option.getId())).findAny();
            optionToDelete.ifPresent(opt -> {
                curTask.getOptions().remove(opt);
                curTask.getOptions().add(option);
            });
        }

        return tasksToUpdate.size() > 0;
    }

    private List<Task> tasksContainOptionId(String optionId) {
        return tasks
                .values()
                .stream()
                .filter(task -> task.getOptions()
                        .stream()
                        .map(TaskElement::getId)
                        .anyMatch(id -> id.equals(optionId)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteOption(String id) {
        return false;
    }

    @Override
    public Optional<Task> getTaskById(String id) {
        return Optional.ofNullable(tasks.get(id));
    }

    @Override
    public boolean putTask(Task task) {
        return false;
    }

    @Override
    public boolean deleteTask(String id) {
        return false;
    }

    @Override
    public Optional<Topic> getTopicById(String id) {
        return topics.values().stream().filter(t -> t.getId().equals(id)).findAny();
    }

    @Override
    public List<Topic> findTopicByName(String name) {
        return topics.values().parallelStream().filter(topic -> topic.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public boolean putTopic(Topic topic) {
        return topics.put(topic.getId(), topic) != null;
    }

    @Override
    public boolean deleteTopic(String id) {
        Optional<Topic> foundTopic = topics.values().stream().filter(t -> t.getId().equals(id)).findAny();
        foundTopic.ifPresent(topics::remove);
        return foundTopic.isPresent();
    }

    public List<Assessment> getAssessments() {
        return new ArrayList<>(assessments.values());
    }

    public List<Image> getImages() {
        return new ArrayList<>(images.values());
    }

    public List<TaskElement> getOptions() {
        return tasks.values().stream().map(Task::getOptions).flatMap(tasks -> tasks.stream()).collect(Collectors.toList());
    }

    public List<TaskElement> getStems() {
        return tasks.values().stream().map(Task::getStem).collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Topic> getTopics() {
        return new ArrayList<>(topics.values());
    }
}
