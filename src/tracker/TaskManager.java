package tracker;

import java.util.List;

public interface TaskManager {

    int addNewTask(Task task);

    int addNewEpic(Epic epic);

    int addNewSubtask(Subtask subtask);

    Task getTask(int id);

    Epic getEpic(int id);

    Subtask getSubtask(int id);

    List<Task> getTasks();

    List<Task> getEpics();

    List<Task> getSubtasks();

    Task updateTask(Task task);

    Epic updateEpic(Epic epic);

    Subtask updateSubtask(Subtask subtask);

    Task removeTask(int id);

    Epic removeEpic(int id);

    Subtask removeSubtask(int id);

    void clearTasks();

    void clearEpics();

    void clearSubtasks();

    List<Task> getHistory();

    List<Subtask> getSubtasksByEpic(int epicId);
}
