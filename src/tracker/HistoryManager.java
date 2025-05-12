package tracker;

import java.util.List;

public interface HistoryManager {
    void add(Task task);         // Добавление задачи в историю просмотров
    List<Task> getHistory();     // Получение списка задач из истории
    void remove(int id);
}
