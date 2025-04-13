package tracker;

import java.util.List;

public interface    HistoryManager {
    // Добавление задачи в историю просмотров
    void add(Task task);
    // Получение списка задач из истории
    List<Task> getHistory();
}
