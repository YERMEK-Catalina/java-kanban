package tracker;

import tracker.exceptions.ManagerSaveException;
import java.io.Writer;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private final File file;

    public FileBackedTaskManager(HistoryManager historyManager, File file) {
        super(historyManager);
        this.file = file;
    }

    private static String historyToString(HistoryManager manager) {
        List<Task> history = manager.getHistory();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < history.size(); i++) {
            builder.append(history.get(i).getId());
            if (i != history.size() - 1) {
                builder.append(",");

            }
        }
        return builder.toString();
    }

    protected void save() {
        StringBuilder content = new StringBuilder();

        content.append("id,type,name,status,description,epic\n");

        for (Task task : getTasks()) {
            content.append(toString(task)).append("\n");
        }

        for (Task epic : getEpics()) {
            content.append(toString(epic)).append("\n");
        }

        for (Task subtask : getSubtasks()) {
            content.append(toString(subtask)).append("\n");
        }

        content.append("\n");
        content.append(historyToString(getHistoryManager()));

        writeToFile(content.toString());


    }

    private String toString(Task task) {
        StringBuilder builder = new StringBuilder();
        builder.append(task.getId()).append(",");
        builder.append(getTaskType(task)).append(",");
        builder.append(task.getName()).append(",");
        builder.append(task.getStatus()).append(",");
        builder.append(task.getDescription()).append(",");

        if (task instanceof Subtask) {
            Subtask subtask = (Subtask) task;
            builder.append(subtask.getEpicId());
        } else {
            builder.append("");
        }

        return builder.toString();
    }

    private TaskType getTaskType(Task task) {
        if (task instanceof Epic) {
            return TaskType.EPIC;
        } else if (task instanceof Subtask) {
            return TaskType.SUBTASK;
        } else {
            return TaskType.TASK;
        }
    }

    private Task fromString(String value) {
        String[] fields = value.split(",");

        int id = Integer.parseInt(fields[0]);
        TaskType type = TaskType.valueOf(fields[1]);
        String name = fields[2];
        TaskStatus status = TaskStatus.valueOf(fields[3]);
        String description = fields[4];

        switch (type) {
            case TASK:
                Task task = new Task(name, description, status);
                task.setId(id);
                return task;
            case EPIC:
                Epic epic = new Epic(name, description);
                epic.setId(id);
                epic.setStatus(status);
                return epic;
            case SUBTASK:
                int epicId = Integer.parseInt(fields[5]);
                Subtask subtask = new Subtask(name, description, epicId, status);
                subtask.setId(id);
                return subtask;
            default:
                throw new IllegalArgumentException("Неизвестный тип задачи: " + type);
        }
    }

    private void writeToFile(String content) {
        try (Writer writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при сохранении файла: " + file.getName(), e);
        }

    }

    protected static FileBackedTaskManager loadFromFile(File file)    {
        FileBackedTaskManager manager = new FileBackedTaskManager(new InMemoryHistoryManager(), file);

        try {
            List<String> lines = Files.readAllLines(file.toPath());
            boolean isHistory = false;

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);

                if (line.isBlank()) {
                    isHistory = true;
                    continue;
                }

                if (!isHistory) {
                    Task task = manager.fromString(line);
                    int id = task.getId();

                    if (task instanceof Epic) {
                        manager.epics.put(id, (Epic) task);
                    } else if (task instanceof Subtask) {
                        manager.subtasks.put(id, (Subtask) task);
                    } else {
                        manager.tasks.put(id, task);
                    }

                    if (id >= manager.nextId) {
                        manager.nextId = id + 1;
                    }
                } else {
                    String[] historyIds = line.split(",");
                    for (String idString : historyIds) {
                        int id = Integer.parseInt(idString);
                        Task task = manager.tasks.get(id);
                        if (task == null) task = manager.epics.get(id);
                        if (task == null) task = manager.subtasks.get(id);
                        if (task != null) {
                            manager.historyManager.add(task);
                        }
                    }
                }
            }


        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при загрузке файла: " + file.getName(), e);
        }

        return manager;
    }


    @Override
    public int addNewTask(Task task) {
        int id = super.addNewTask(task);
        save();
        return id;
    }

    @Override
    public int addNewEpic(Epic epic) {
        int id = super.addNewEpic(epic);
        save();
        return id;
    }

    @Override
    public int addNewSubtask(Subtask subtask) {
        int id = super.addNewSubtask(subtask);
        save();
        return id;
    }

    @Override
    public Task updateTask(Task task) {
        Task updated = super.updateTask(task);
        save();
        return updated;
    }

    @Override
    public Epic updateEpic(Epic epic) {
        Epic updated = super.updateEpic(epic);
        save();
        return updated;
    }

    @Override
    public Subtask updateSubtask(Subtask subtask) {
        Subtask updated = super.updateSubtask(subtask);
        save();
        return updated;
    }

    @Override
    public Task removeTask(int id) {
        Task removed = super.removeTask(id);
        save();
        return removed;
    }

    @Override
    public Epic removeEpic(int id) {
        Epic removed = super.removeEpic(id);
        save();
        return removed;
    }

    @Override
    public Subtask removeSubtask(int id) {
        Subtask removed = super.removeSubtask(id);
        save();
        return removed;
    }

    @Override
    public void clearTasks() {
        super.clearTasks();
        save();
    }

    @Override
    public void clearEpics() {
        super.clearEpics();
        save();
    }

    @Override
    public void clearSubtasks() {
        super.clearSubtasks();
        save();
    }




}
