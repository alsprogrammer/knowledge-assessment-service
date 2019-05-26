package amanagment.services;

import amanagment.data.dblayer.IStorage;
import amanagment.data.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FakeStorage implements IStorage {
    private final List<Assessment> assessments = new ArrayList<>();
    private final List<Image> images = new ArrayList<>();
    private final List<TaskElement> options = new ArrayList<>();
    private final List<TaskElement> stems = new ArrayList<>();
    private final List<Task> tasks = new ArrayList<>();
    private final List<Topic> topics = new ArrayList<>();

    @Override
    public Optional<Assessment> getAssessmentById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Assessment> findAssessmentsByName(String name) {
        return null;
    }

    @Override
    public boolean putAssessment(Assessment assessment) {
        return false;
    }

    @Override
    public boolean deleteAssessment(String id) {
        return false;
    }

    @Override
    public Optional<Image> getImageById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Image> findImagesByCaption(String caption) {
        return null;
    }

    @Override
    public boolean putImage(Image image) {
        return false;
    }

    @Override
    public boolean deleteImage(String id) {
        return false;
    }

    @Override
    public Optional<TaskElement> getOptionById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean putOption(Image image) {
        return false;
    }

    @Override
    public boolean deleteOption(String id) {
        return false;
    }

    @Override
    public Optional<Task> getTaskById(String id) {
        return Optional.empty();
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
        return Optional.empty();
    }

    @Override
    public List<Topic> findTopicByName(String name) {
        return topics.parallelStream().filter(topic -> topic.getName().equals(name)).collect(Collectors.toList());
    }

    @Override
    public boolean putTopic(Topic topic) {
        return topics.add(topic);
    }

    @Override
    public boolean deleteTopic(String id) {
        return false;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public List<Image> getImages() {
        return images;
    }

    public List<TaskElement> getOptions() {
        return options;
    }

    public List<TaskElement> getStems() {
        return stems;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public List<Topic> getTopics() {
        return topics;
    }
}
