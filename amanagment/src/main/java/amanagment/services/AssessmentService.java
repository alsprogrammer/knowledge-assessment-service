package amanagment.services;

import amanagment.data.dblayer.IStorage;
import amanagment.data.models.Assessment;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class AssessmentService implements IAssessmentService {
    private final IStorage storage;

    @Inject
    public AssessmentService(IStorage storage) {
        this.storage = storage;
    }

    @Override
    public Optional<Assessment> getAssessmentById(String id) {
        return storage.getAssessmentById(id);
    }

    @Override
    public Assessment generateAssessment(List<String> topicIds) {
        return null;
    }
}
