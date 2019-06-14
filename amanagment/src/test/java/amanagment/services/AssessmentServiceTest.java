package amanagment.services;

import amanagment.data.dblayer.IStorage;
import amanagment.data.dblayer.InMemoryStorage;
import amanagment.data.exceptions.EntityExistsException;
import amanagment.data.exceptions.EntityNotExistsException;
import amanagment.data.models.Image;
import amanagment.data.models.Task;
import amanagment.data.models.TaskElement;
import amanagment.data.models.Topic;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentServiceTest {

    @Test
    void addTopic() throws EntityExistsException, EntityNotExistsException {
        FakeStorage fakeStorage = new FakeStorage();
        AssessmentService assessmentService = new AssessmentService(fakeStorage);

        final String topicName = "New topic";

        assessmentService.addTopic(topicName, null);

        assertEquals(1, fakeStorage.getTopics().size());
        assertEquals(topicName, fakeStorage.getTopics().get(0).getName());
        assertNull(fakeStorage.getTopics().get(0).getParentTopicId());
    }

    @Test
    void addTopicWithParent() throws EntityExistsException, EntityNotExistsException {
        FakeStorage fakeStorage = new FakeStorage();
        AssessmentService assessmentService = new AssessmentService(fakeStorage);

        final String topicName = "New topic";
        final String topicName1 = "New topic1";

        String topicId = assessmentService.addTopic(topicName, null);
        String topic1Id = assessmentService.addTopic(topicName1, topicId);

        assertEquals(2, fakeStorage.getTopics().size());
        assertEquals(topicName, fakeStorage.topics.get(topicId).getName());
        assertNull(fakeStorage.topics.get(topicId).getParentTopicId());
        assertEquals(topicId, fakeStorage.topics.get(topic1Id).getParentTopicId());
    }

    @Test
    void addTopicWithExistingName() throws EntityExistsException, EntityNotExistsException {
        FakeStorage fakeStorage = new FakeStorage();
        AssessmentService assessmentService = new AssessmentService(fakeStorage);

        final String topicName = "New topic";

        assessmentService.addTopic(topicName, null);

        Assertions.assertThrows(EntityExistsException.class, () -> {
            assessmentService.addTopic(topicName, null);
        });
    }

    @Test
    void removeTopic() {
        FakeStorage fakeStorage = new FakeStorage();
        AssessmentService assessmentService = new AssessmentService(fakeStorage);

        Topic topicInStorage1 = Topic.builder().name("Existing topic").build();
        Topic topicInStorage2 = Topic.builder().name("Existing topic2").build();
        fakeStorage.topics.put(topicInStorage1.getId(), topicInStorage1);
        fakeStorage.topics.put(topicInStorage2.getId(), topicInStorage2);

        boolean result = assessmentService.removeTopic(topicInStorage1.getId());

        assertTrue(result);
        assertEquals(0, fakeStorage.getTopics().stream().filter(topic -> topic.getId().equals(topicInStorage1.getId())).count());
    }

    @Test
    void removeNonExistingTopic() {
        FakeStorage fakeStorage = new FakeStorage();
        AssessmentService assessmentService = new AssessmentService(fakeStorage);

        Topic topicInStorage1 = Topic.builder().name("Existing topic").build();
        Topic topicInStorage2 = Topic.builder().name("Existing topic2").build();
        fakeStorage.topics.put(topicInStorage1.getId(), topicInStorage1);
        fakeStorage.topics.put(topicInStorage2.getId(), topicInStorage2);

        boolean result = assessmentService.removeTopic(UUID.randomUUID().toString());

        assertFalse(result);
        assertEquals(2, fakeStorage.getTopics().size());
    }

    @Test
    void addTaskToTopicWithoutImage() throws EntityNotExistsException{
        FakeStorage fakeStorage = new FakeStorage();
        AssessmentService assessmentService = new AssessmentService(fakeStorage);

        String stemText = "Stem text";
        Image stemImage = null;
        String taskId;

        Topic topicInStorage = Topic.builder().name("Existing topic").build();
        fakeStorage.topics.put(topicInStorage.getId(), topicInStorage);

        taskId = assessmentService.createTask(stemText, stemImage, topicInStorage.getId());


        assertNotNull(taskId);
        assertEquals(1, topicInStorage.getTasks().size());
        assertEquals(stemText, topicInStorage.getTasks().stream().filter(t -> t.getId().equals(taskId)).findFirst().get().getStem().getText());
    }

    @Test
    void addTaskToTopicWithImage() throws EntityExistsException, EntityNotExistsException{
        FakeStorage fakeStorage = new FakeStorage();
        AssessmentService assessmentService = new AssessmentService(fakeStorage);

        String stemText = "Stem text";
        String stemImageText = "abracadabra image";
        String stemImageCaption = "New image";

        Topic topicInStorage = Topic.builder().name("Existing topic").build();
        fakeStorage.topics.put(topicInStorage.getId(), topicInStorage);

        Image image = Image.builder().caption(stemImageCaption).image(stemImageText).build();
        String taskId = assessmentService.createTask(stemText, image, topicInStorage.getId());


        assertNotNull(taskId);
        assertEquals(1, topicInStorage.getTasks().size());
        assertEquals(stemText, topicInStorage.getTasks().stream().filter(t -> t.getId().equals(taskId)).findFirst().get().getStem().getText());
    }

    /////////
    @Test
    void removeTaskFromTopic() throws EntityNotExistsException{
        FakeStorage fakeStorage = new FakeStorage();
        AssessmentService assessmentService = new AssessmentService(fakeStorage);

        String stemText = "Stem text";
        String stemImageText = "abracadabra image";
        String stemImageCaption = "New image";

        Topic topicInStorage = Topic.builder().name("Existing topic").build();
        fakeStorage.topics.put(topicInStorage.getId(), topicInStorage);

        Image image = Image.builder().caption(stemImageCaption).image(stemImageText).build();
        TaskElement taskElement = TaskElement.builder().text(stemText).imageId(image.getId()).build();
        Task task = Task.builder().stem(taskElement).build();
        topicInStorage.getTasks().add(task);

        fakeStorage.tasks.put(task.getId(), task);

        boolean result = assessmentService.removeTaskFromTopic(task.getId(), topicInStorage.getId());

        assertTrue(result);
        assertEquals(0, topicInStorage.getTasks().size());
    }

    @Test
    void removeNonExistingTaskFromTopic() throws EntityNotExistsException{
        FakeStorage fakeStorage = new FakeStorage();
        AssessmentService as = new AssessmentService(fakeStorage);

        String stemText = "Stem text";
        String stemImageText = "abracadabra image";
        String stemImageCaption = "New image";

        Topic topicInStorage = Topic.builder().name("Existing topic").build();
        fakeStorage.topics.put(topicInStorage.getId(), topicInStorage);

        Image image = Image.builder().caption(stemImageCaption).image(stemImageText).build();
        TaskElement taskElement = TaskElement.builder().text(stemText).imageId(image.getId()).build();
        Task task = Task.builder().stem(taskElement).build();
        fakeStorage.tasks.put(task.getId(), task);
        topicInStorage.addTask(task);

        TaskElement fakeTaskElement = TaskElement.builder().text(stemText).imageId(image.getId()).build();
        Task fakeTask = Task.builder().stem(fakeTaskElement).build();

        boolean result = as.removeTaskFromTopic(fakeTask.getId(), topicInStorage.getId());

        assertFalse(result);
        assertEquals(1, topicInStorage.getTasks().size());
    }
}