package tracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class TaskImmutabilityTest {

    @Test
    void taskShouldNotChangeAfterAddingToManager() {
        // Создаем экземпляр менеджера задач с заглушкой для HistoryManager
        HistoryManager dummyHistoryManager = new HistoryManager() {
            @Override
            public void add(Task task) {}
            @Override
            public List<Task> getHistory() {
                return new ArrayList<>();
            }
        };

        // Создаем новый экземпляр менеджера задач
        InMemoryTaskManager taskManager = new InMemoryTaskManager(dummyHistoryManager);

        // Создаем задачу
        Task originalTask = new Task("Task Title", "Task Description", TaskStatus.NEW);

        // Сохраняем исходные данные задачи
        String originalName = originalTask.getName();
        String originalDescription = originalTask.getDescription();
        TaskStatus originalStatus = originalTask.getStatus();
        int originalId = originalTask.getId();

        // Добавляем задачу в менеджер
        int taskId = taskManager.addNewTask(originalTask);

        // Получаем задачу по ID, чтобы проверить её состояние
        Task retrievedTask = taskManager.getTask(taskId);

        // Проверяем, что данные задачи остались неизменными
        assertEquals(originalName, retrievedTask.getName(), "Name has changed");
        assertEquals(originalDescription, retrievedTask.getDescription(), "Description has changed");
        assertEquals(originalStatus, retrievedTask.getStatus(), "Status has changed");
        assertEquals(originalId, retrievedTask.getId(), "ID has changed");  // Проверка ID
    }
}
