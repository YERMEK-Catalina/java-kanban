package tracker;

import java.util.Objects;
import java.util.ArrayList;

public class Epic extends Task {
    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
    }

    private ArrayList<Integer> subtaskIds = new ArrayList<>();

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void addSubtaskId(int subTaskId) {
        subtaskIds.add(subTaskId);
    }

    public void removeSubtaskId(int subTaskId) {
        subtaskIds.remove(subTaskId);
    }

    public void clearSubtaskIds() {
        subtaskIds.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subtaskIds, epic.subtaskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subtaskIds);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtaskIds=" + subtaskIds +
                '}';
    }
}
