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
    @JsonRpcMethod
    public Optional<Assessment> getAssessmentById(String id) {
        return service.getAssessmentById(id);
    }

    @Override
    @JsonRpcMethod
    public Assessment generateAssessment(List<String> topicIds) {
        return service.generateAssessment(topicIds);
    }
}
