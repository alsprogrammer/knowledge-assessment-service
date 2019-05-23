package amanagment.services;

import amanagment.data.models.Assessment;

import java.util.List;
import java.util.Optional;

public interface IAssessmentService {
    Optional<Assessment> getAssessmentById(String id);
    Assessment generateAssessment(List<String> topicIds);
}
