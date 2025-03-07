package tracker;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Создаем обычные задачи
        Task task1 = new Task("Переезд", "Собрать вещи и переехать", TaskStatus.NEW);
        Task task2 = new Task("Покупка квартиры", "Найти подходящую квартиру", TaskStatus.NEW);
        manager.addTask(task1);
        manager.addTask(task2);

        // Создаем эпик и подзадачи
        Epic epic1 = new Epic("Организация праздника", "Подготовить праздник для друзей");
        manager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Заказать торт", "Выбрать и заказать торт", epic1.getId(), TaskStatus.NEW);
        Subtask subtask2 = new Subtask("Украшения", "Закупить украшения", epic1.getId(), TaskStatus.NEW);
        manager.addSubtask(subtask1);
        manager.addSubtask(subtask2);

        // Создаем еще один эпик с одной подзадачей
        Epic epic2 = new Epic("Ремонт", "Ремонт квартиры");
        manager.addEpic(epic2);
        Subtask subtask3 = new Subtask("Покраска стен", "Выбрать цвет и покрасить стены", epic2.getId(), TaskStatus.NEW);
        manager.addSubtask(subtask3);

        // Тестовый вывод
        System.out.println("Все задачи:");
        System.out.println(manager.getTasks());
        System.out.println("Все эпики:");
        System.out.println(manager.getEpics());
        System.out.println("Все подзадачи:");
        System.out.println(manager.getSubtasks());
        System.out.println("Подзадачи для эпика 1:");
        System.out.println(manager.getSubtasksByEpic(epic1.getId()));

        // Изменение статуса подзадачи для проверки обновления статуса эпика
        subtask1.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1);
        subtask2.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask2);
        System.out.println("Эпик 1 после обновления подзадач:");
        System.out.println(manager.getEpic(epic1.getId()));

        // Удаление задач
        manager.removeTask(task1.getId());
        manager.removeEpic(epic2.getId());

        System.out.println("После удаления:");
        System.out.println("Все задачи:");
        System.out.println(manager.getTasks());
        System.out.println("Все эпики:");
        System.out.println(manager.getEpics());
        System.out.println("Все подзадачи:");
        System.out.println(manager.getSubtasks());
    }
}