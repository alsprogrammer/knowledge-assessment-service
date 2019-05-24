package amanagment.services;

import amanagment.data.models.Assessment;
import amanagment.data.models.Option;
import amanagment.data.models.Task;

import java.util.List;
import java.util.Optional;

public interface IAssessmentService {
    String addTopic(String name, String parentTopicId);
    boolean removeTopic(String id);
    String createTask(String stemText, String stemImageBase64, String topicIdToAddTo);
    boolean removeTaskFromTopic(String taskId, String topicId);
    String createOption(String optionText, String optionImageBase64, String taskIdToAddTo);
    boolean removeOptionFromTask(String optionId, String taskId);
    Assessment generateAssessment(int taskNum, List<String> topicIds, String testeeId);
    List<Assessment> getAssessmentsByTesteeId(String testeeId);
    Optional<Assessment> getAssessmentById(String id);
}
