package tracker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManagersTest {

    @Test
    public void testGetDefaultReturnsInitializedTaskManager() {
        TaskManager taskManager = Managers.getDefault();

                                              // Проверяем, что TaskManager не null
        assertNotNull(taskManager, "TaskManager должен быть проинициализирован");
    }

    @Test
    public void testGetDefaultHistoryReturnsInitializedHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();

                                               // Проверяем, что HistoryManager не null
        assertNotNull(historyManager, "HistoryManager должен быть проинициализирован");
    }
}
