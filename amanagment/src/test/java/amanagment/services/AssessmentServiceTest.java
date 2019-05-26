package amanagment.services;

import amanagment.data.dblayer.IStorage;
import amanagment.data.dblayer.InMemoryStorage;
import amanagment.data.exceptions.EntityExistsException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentServiceTest {

    @Test
    void addTopic() {
        FakeStorage fakeStorage = new FakeStorage();
        AssessmentService as = new AssessmentService(fakeStorage);

        try {
            as.addTopic("New topic", Optional.empty());
        } catch (EntityExistsException e) {
            System.out.println(e);
        }

        assertEquals(1, fakeStorage.getTopics().size());
        assertEquals(fakeStorage.getTopics().get(0).getParentTopicId(), Optional.empty());
    }

    @Test
    void addTopicWithExistingName() {
        FakeStorage fakeStorage = new FakeStorage();
        AssessmentService as = new AssessmentService(fakeStorage);

        final String topicName = "New topic";

        try {
            as.addTopic(topicName, Optional.empty());
        } catch (EntityExistsException e) {
            System.out.println(e);
        }

        Assertions.assertThrows(EntityExistsException.class, () -> {
            as.addTopic(topicName, Optional.empty());
        });
    }
}