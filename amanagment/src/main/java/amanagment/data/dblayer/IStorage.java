package amanagment.data.dblayer;

import amanagment.data.models.*;

import java.util.List;
import java.util.Optional;

public interface IStorage {
    Optional<Assessment> getAssessmentById(String id);
    List<Assessment> findAssessmentsByName(String name);
    boolean putAssessment(Assessment assessment);
    boolean deleteAssessment(String id);

    Optional<Image> getImageById(String id);
    List<Image> findImagesByCaption(String caption);
    boolean putImage(Image image);
    boolean deleteImage(String id);

    Optional<TaskElement> getOptionById(String id);
    boolean putOption(Image image);
    boolean deleteOption(String id);

    Optional<Task> getTaskById(String id);
    boolean putTask(Task task);
    boolean deleteTask(String id);

    Optional<Topic> getTopicById(String id);
    List<Topic> findTopicByName(String name);
    boolean putTopic(Topic topic);
    boolean deleteTopic(String id);
}
