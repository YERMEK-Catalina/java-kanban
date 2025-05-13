package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tracker.*;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryTaskManagerTest {

    private InMemoryTaskManager taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager(new InMemoryHistoryManager());
    }

    @Test
    void shouldAddAndFindTasksById() {
        // Добавляем обычную задачу
        Task task = new Task("Task name", "Task description", TaskStatus.NEW);
        int taskId = taskManager.addNewTask(task);
        Task savedTask = taskManager.getTask(taskId);
        assertNotNull(savedTask, "Обычная задача не найдена по id");
        assertEquals(taskId, savedTask.getId(), "Id задачи не совпадает");

        // Добавляем эпик
        Epic epic = new Epic("Epic name", "Epic description");
        int epicId = taskManager.addNewEpic(epic);
        Epic savedEpic = taskManager.getEpic(epicId);
        assertNotNull(savedEpic, "Эпик не найден по id");
        assertEquals(epicId, savedEpic.getId(), "Id эпика не совпадает");

        // Добавляем подзадачу, привязанную к эпику
        Subtask subtask = new Subtask("Subtask name", "Subtask description", epicId, TaskStatus.NEW);
        int subtaskId = taskManager.addNewSubtask(subtask);
        Subtask savedSubtask = taskManager.getSubtask(subtaskId);
        assertNotNull(savedSubtask, "Подзадача не найдена по id");
        assertEquals(subtaskId, savedSubtask.getId(), "Id подзадачи не совпадает");
        assertEquals(epicId, savedSubtask.getEpicId(), "Id эпика в подзадаче не совпадает");
    }
}
