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
    private final IStorage storage;

    @Inject
    public JRPCAssessmentService(IStorage storage) {
        this.storage = storage;
    }

    @Override
    @JsonRpcMethod
    public Optional<Assessment> getAssessmentById(String id) {
        return storage.getAssessmentById(id);
    }

    @Override
    @JsonRpcMethod
    public Assessment generateAssessment(List<String> topicIds) {
        return null;
    }
}
