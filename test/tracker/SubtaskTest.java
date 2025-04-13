package tracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SubtaskTest {

    @Test
    public void testSubtaskCannotBeItsOwnEpic() {
        Subtask subtask = new Subtask("Subtask", "Description", 999, TaskStatus.NEW);
        subtask.setId(123);        // Устанавливаем id подзадачи
        subtask.setEpicId(123);    // Пытаемся сделать её эпиком самой себе

                                   // Проверяем, что epicId не стал равен id
        assertNotEquals(subtask.getId(), subtask.getEpicId());
    }
}
