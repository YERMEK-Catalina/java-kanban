package tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    private List<Integer> subtaskIds = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
    }

    public List<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtaskId(int subTaskId) {
        if (this.id == subTaskId) {
            return;
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

        return getId() == epic.getId(); // Проверка только по id
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", subtaskIds=" + subtaskIds +
                '}';
    }
}
