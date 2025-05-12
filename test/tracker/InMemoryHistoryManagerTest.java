package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class InMemoryHistoryManagerTest {

    private HistoryManager historyManager;
    private Task task1;
    private Task task2;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
        task1 = new Task("Task 1", "Description 1", TaskStatus.NEW);
        task1.setId(1);

        task2 = new Task("Task 2", "Description 2", TaskStatus.NEW);
        task2.setId(2);
    }

    @Test
    void shouldAddTaskToHistory() {
        historyManager.add(task1);
        historyManager.add(task2);

        List<Task> history = historyManager.getHistory();

        assertEquals(2, history.size());
        assertEquals(task1, history.get(0));
        assertEquals(task2, history.get(1));
    }

    @Test
    void shouldNotDuplicateTaskInHistory() {
        historyManager.add(task1);
        historyManager.add(task2);
        historyManager.add(task1);

        List<Task> history = historyManager.getHistory();

        assertEquals(2, history.size());
        assertEquals(task2, history.get(0));
        assertEquals(task1, history.get(1));

    }

    @Test
    void shouldRemoveTaskFromHistoryById() {
        historyManager.add(task1);
        historyManager.add(task2);

        historyManager.remove(task1.getId());
        List<Task> history = historyManager.getHistory();

        assertEquals(1, history.size());
        assertEquals(task2, history.get(0));


    }
}
