package tracker;

import java.util.List;

public interface TaskManager {
    // Добавление задач
    int addNewTask(Task task);
    int addNewEpic(Epic epic);
    int addNewSubtask(Subtask subtask);

    // Получение задач по id (с записью в историю)
    Task getTask(int id);
    Epic getEpic(int id);
    Subtask getSubtask(int id);

    // Получение всех задач
    List<Task> getTasks();
    List<Task> getEpics();
    List<Task> getSubtasks();

    // Обновление задач
    Task updateTask(Task task);
    Epic updateEpic(Epic epic);
    Subtask updateSubtask(Subtask subtask);

    // Удаление задач по id
    Task removeTask(int id);
    Epic removeEpic(int id);
    Subtask removeSubtask(int id);

    // Очистка всех задач
    void clearTasks();
    void clearEpics();
    void clearSubtasks();

    // Получение истории просмотров
    List<Task> getHistory();

    // Дополнительно
    List<Subtask> getSubtasksByEpic(int epicId);
}
