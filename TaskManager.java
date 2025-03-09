    package tracker;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;

    public class TaskManager {
        private HashMap<Integer, Task> tasks = new HashMap<>();
        private HashMap<Integer, Epic> epics = new HashMap<>();
        private HashMap<Integer, Subtask> subtasks = new HashMap<>();
        private int nextId = 1;

        public Task addTask(Task task) {
            task.setId(nextId++);
            tasks.put(task.getId(), task);
            return task;
        }

        public Epic addEpic(Epic epic) {
            epic.setId(nextId++);
            epics.put(epic.getId(), epic);
            return epic;
        }

        public Subtask addSubtask(Subtask subtask) {
            subtask.setId(nextId++);
            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            if (epic != null) {
                epic.addSubtaskId(subtask.getId());
                updateEpicStatus(epic.getId());
            }
            return subtask;
        }

        public Task getTask(int id) {
            return tasks.get(id);
        }

        public Epic getEpic(int id) {
            return epics.get(id);
        }

        public Subtask getSubtask(int id) {
            return subtasks.get(id);
        }

        public ArrayList<Task> getTasks() {
            return new ArrayList<>(tasks.values());
        }

        public ArrayList<Epic> getEpics() {
            return new ArrayList<>(epics.values());
        }

        public ArrayList<Subtask> getSubtasks() {
            return new ArrayList<>(subtasks.values());
        }

        public Task removeTask(int id) {
            return tasks.remove(id);
        }

        public Epic removeEpic(int id) {
            Epic epic = epics.get(id);
            if (epic != null) {
                List<Integer> subtaskIds = epic.getSubtaskIds();
                for (int subtaskId : subtaskIds) {
                    subtasks.remove(subtaskId);
                }
                epics.remove(id);
            }
            return epic;
        }

        public Subtask removeSubtask(int id) {
            Subtask subtask = subtasks.get(id);
            if (subtask != null) {
                Epic epic = epics.get(subtask.getEpicId());
                if (epic != null) {
                    epic.removeSubtaskId(id);
                }
                subtasks.remove(id);
            }
            return subtask;
        }

        public Task updateTask(Task task) {
            if (tasks.containsKey(task.getId())) {
                tasks.put(task.getId(), task);
                return task;
            }
            return null;

        }

        public Epic updateEpic(Epic epic) {
            if (epics.containsKey(epic.getId())) {
                epics.put(epic.getId(), epic);
                updateEpicStatus(epic.getId());
                return epic;
            }
            return null;
        }

        public Subtask updateSubtask(Subtask subtask) {

            if (!subtasks.containsKey(subtask.getId())) {
                return null;
            }

            subtasks.put(subtask.getId(), subtask);
            Epic epic = epics.get(subtask.getEpicId());
            if (epic == null) {
                return subtask;
            }
            updateEpicStatus(epic.getId());
            return subtask;
        }

        private void updateEpicStatus(int epicId) {
            Epic epic = epics.get(epicId);
            if (epic == null) {
                return;
            }

            List<Integer> subtaskIds = epic.getSubtaskIds();
            if (subtaskIds.isEmpty()) {
                epic.setStatus(TaskStatus.NEW);
                return;
            }

            boolean allNew = true;
            boolean allDone = true;

            for (int subtaskId : subtaskIds) {
                Subtask subtask = subtasks.get(subtaskId);
                TaskStatus status = subtask.getStatus();

                if (status != TaskStatus.NEW) {
                    allNew = false;
                }
                if (status != TaskStatus.DONE) {
                    allDone = false;
                }
            }

            if (allDone) {
                epic.setStatus(TaskStatus.DONE);
            } else if (allNew) {
                epic.setStatus(TaskStatus.NEW);
            } else {
                epic.setStatus(TaskStatus.IN_PROGRESS);
            }
        }

        public void clearTasks () {
            tasks.clear();
        }

        public void clearEpics () {
            epics.clear();
            subtasks.clear();
        }

        public void clearSubtasks () {
            for (Epic epic : epics.values()) {
                epic.clearSubtaskIds();
                updateEpicStatus(epic.getId());
            }
            subtasks.clear();
        }

        public List<Subtask> getSubtasksByEpic(int epicId) {
            Epic epic = epics.get(epicId);
            List<Subtask> subtaskList = new ArrayList<>();
            if (epic != null) {
                for (Integer subtaskId : epic.getSubtaskIds()) {
                    Subtask subtask = subtasks.get(subtaskId);
                    if (subtask != null) {
                        subtaskList.add(subtask);
                    }
                }
            }
            return subtaskList;
        }

    }

