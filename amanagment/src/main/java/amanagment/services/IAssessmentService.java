package amanagment.services;

import amanagment.data.exceptions.EntityExistsException;
import amanagment.data.exceptions.EntityNotExistsException;
import amanagment.data.models.Assessment;
import amanagment.data.models.Image;

import java.util.List;
import java.util.Optional;

public interface IAssessmentService {
    String addTopic(String name, String parentTopicId) throws EntityExistsException, EntityNotExistsException;
    boolean removeTopic(String id);
    String createTask(String stemText, Image stemImageBase64, String topicIdToAddTo) throws EntityNotExistsException;
    boolean removeTaskFromTopic(String taskId, String topicId) throws EntityNotExistsException;
    String createOption(String optionText, String optionImageBase64, String taskIdToAddTo) throws EntityNotExistsException;
    boolean removeOptionFromTask(String optionId, String taskId) throws EntityNotExistsException;
    Assessment generateAssessment(int taskNum, List<String> topicIds, String testeeId) throws EntityNotExistsException;
    List<Assessment> getAssessmentsByTesteeId(String testeeId);
    Optional<Assessment> getAssessmentById(String id);
}
