package amanagment.jsonrpc.services;

import amanagment.data.dblayer.IStorage;
import amanagment.data.models.Assessment;
import amanagment.services.IAssessmentService;
import com.github.arteam.simplejsonrpc.core.annotation.JsonRpcMethod;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class JRPCAssessmentService implements IAssessmentService {
    private final IAssessmentService service;

    @Inject
    public JRPCAssessmentService(IAssessmentService service) {
        this.service = service;
    }

    @Override
    public String addTopic(String name, Optional<String> parentTopicId) {
        return null;
    }

    @Override
    public boolean removeTopic(String id) {
        return false;
    }

    @Override
    public String createTask(String stemText, Optional<String> stemImageBase64, Optional<String> topicIdToAddTo) {
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
    @JsonRpcMethod
    public Optional<Assessment> getAssessmentById(String id) {
        return service.getAssessmentById(id);
    }
}
