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
        if (this.id == subTaskId) {
            return; // нельзя добавлять эпик как подзадачу к самому себе
        }
        subtaskIds.add(subTaskId);
    }

    public void removeSubtaskId(int subTaskId) {
        subtaskIds.remove(Integer.valueOf(subTaskId));
    }

    public void clearSubtaskIds() {
        subtask
