package tracker;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtaskId(int subTaskId) {
        if (getId() == subTaskId) {
            return; // нельзя добавлять эпик как подзадачу к самому себе
        }
        subtaskIds.add(subTaskId);
    }

    public void removeSubtaskId(int subTaskId) {
        subtaskIds.remove(Integer.valueOf(subTaskId));
    }

    public void clearSubtaskIds() {
        subtaskIds.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epic epic = (Epic) o;
        return getId() == epic.getId(); // сравнение только по id
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                ", subtaskIds=" + subtaskIds +
                '}';
    }
}
