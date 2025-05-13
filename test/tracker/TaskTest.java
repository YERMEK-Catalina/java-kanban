package tracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testTaskEqualityById() {
        Task task1 = new Task("Task1", "Description1", TaskStatus.NEW);
        Task task2 = new Task("Task2", "Description2", TaskStatus.IN_PROGRESS);
        task1.setId(100);
        task2.setId(100);
        assertEquals(task1, task2);
    }

    @Test
    public void testEpicEqualityById() {
        Epic epic1 = new Epic("Epic1", "Epic description");
        Epic epic2 = new Epic("Epic2", "Another description");
        epic1.setId(200);
        epic2.setId(200);
        assertEquals(epic1, epic2);
    }

    @Test
    public void testSubtaskEqualityById() {
        Subtask subtask1 = new Subtask("Subtask1", "Description1", 10, TaskStatus.NEW);
        Subtask subtask2 = new Subtask("Subtask2", "Description2", 20, TaskStatus.DONE);
        subtask1.setId(300);
        subtask2.setId(300);
        assertEquals(subtask1, subtask2);
    }
}


