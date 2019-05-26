package amanagment.services;

import amanagment.data.exceptions.EntityExistsException;
import amanagment.data.models.Assessment;

import java.util.List;
import java.util.Optional;

public interface IAssessmentService {
    String addTopic(String name, Optional<String> parentTopicId) throws EntityExistsException;
    boolean removeTopic(String id);
    String createTask(String stemText, Optional<String> stemImageBase64, String topicIdToAddTo);
    boolean removeTaskFromTopic(String taskId, String topicId);
    String createOption(String optionText, String optionImageBase64, String taskIdToAddTo);
    boolean removeOptionFromTask(String optionId, String taskId);
    Assessment generateAssessment(int taskNum, List<String> topicIds, String testeeId);
    List<Assessment> getAssessmentsByTesteeId(String testeeId);
    Optional<Assessment> getAssessmentById(String id);
}
