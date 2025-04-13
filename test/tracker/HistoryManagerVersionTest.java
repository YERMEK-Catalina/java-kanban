package tracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryManagerVersionTest {

    @Test
    void shouldKeepPreviousVersionOfTaskInHistory() {
        HistoryManager historyManager = new InMemoryHistoryManager();

        Task originalTask = new Task("Original Name", "Original Description", TaskStatus.NEW);
        originalTask.setId(1);

        historyManager.add(originalTask);  // добавляем в историю

        // Меняем задачу после добавления в историю
        originalTask.setName("Updated Name");
        originalTask.setDescription("Updated Description");
        originalTask.setStatus(TaskStatus.DONE);

        List<Task> history = historyManager.getHistory();
        Task taskFromHistory = history.get(0);

        // Проверяем, что данные в истории не изменились
        assertEquals("Original Name", taskFromHistory.getName());
        assertEquals("Original Description", taskFromHistory.getDescription());
        assertEquals(TaskStatus.NEW, taskFromHistory.getStatus());
    }
}
