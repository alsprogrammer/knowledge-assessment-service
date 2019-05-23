package amanagment.data.dblayer;

import amanagment.data.models.*;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class InMemoryStorage implements IStorage {
    private final List<Assessment> assessments = new ArrayList<>();
    private final List<Image> images = new ArrayList<>();
    private final List<Option> options = new ArrayList<>();
    private final List<Stem> stems = new ArrayList<>();
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
    public Optional<Option> getOptionById(String id) {
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
    public Optional<Stem> getStemById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean putStem(Stem stem) {
        return false;
    }

    @Override
    public boolean deleteStem(String id) {
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
        return null;
    }

    @Override
    public boolean putTopic(Topic topic) {
        return false;
    }

    @Override
    public boolean deleteTopic(String id) {
        return false;
    }
}
