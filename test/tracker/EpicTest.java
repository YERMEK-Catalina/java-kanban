package tracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    private Epic epic;

    @BeforeEach
    void setUp() {
        epic = new Epic("Epic task", "Contains many subtasks");
        epic.setId(1);                         // Устанавливаем id, чтобы можно было сравнить
    }

    @Test
    void shouldNotAllowAddingItselfAsSubtask() {
        epic.addSubtaskId(1);                  // Пытаемся добавить сам себя
        assertFalse(epic.getSubtaskIds().contains(1),
                "Epic не должен содержать свой собственный id в списке подзадач");
    }
}
