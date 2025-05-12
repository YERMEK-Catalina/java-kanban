package tracker;

public class Main {
        public static void main(String[] args) {
                // Получаем менеджера через утилитный класс
                TaskManager manager = Managers.getDefault();

                // Создание обычных задач
                Task task1 = new Task("Переезд", "Собрать вещи и переехать", TaskStatus.NEW);
                Task task2 = new Task("Покупка квартиры", "Найти подходящую квартиру", TaskStatus.NEW);
                int task1Id = manager.addNewTask(task1);
                int task2Id = manager.addNewTask(task2);

                // Создание эпика и подзадач
                Epic epic1 = new Epic("Организация праздника", "Подготовить праздник для друзей");
                int epic1Id = manager.addNewEpic(epic1);

                Subtask subtask1 = new Subtask("Заказать торт", "Выбрать и заказать торт", epic1Id, TaskStatus.NEW);
                Subtask subtask2 = new Subtask("Украшения", "Закупить украшения", epic1Id, TaskStatus.NEW);
                int subtask1Id = manager.addNewSubtask(subtask1);
                int subtask2Id = manager.addNewSubtask(subtask2);

                // Создание ещё одного эпика с одной подзадачей
                Epic epic2 = new Epic("Ремонт", "Ремонт квартиры");
                int epic2Id = manager.addNewEpic(epic2);
                Subtask subtask3 = new Subtask("Покраска стен", "Выбрать цвет и покрасить стены", epic2Id, TaskStatus.NEW);
                int subtask3Id = manager.addNewSubtask(subtask3);

                // Обращения к задачам для формирования истории
                System.out.println("Просмотр задач для формирования истории:");
                System.out.println(manager.getTask(task1Id));
                System.out.println(manager.getEpic(epic1Id));
                System.out.println(manager.getSubtask(subtask1Id));
                System.out.println(manager.getTask(task2Id));
                System.out.println(manager.getSubtask(subtask2Id));
                System.out.println(manager.getEpic(epic2Id));
                System.out.println(manager.getSubtask(subtask3Id));

                // Вывод всех задач и истории
                System.out.println("\n--- Все задачи ---");
                System.out.println("Задачи: " + manager.getTasks());
                System.out.println("Эпики: " + manager.getEpics());
                System.out.println("Подзадачи: " + manager.getSubtasks());
                System.out.println("История просмотров: " + manager.getHistory());

                // Демонстрация обновления статуса эпика после изменения подзадач
                subtask1.setStatus(TaskStatus.DONE);
                manager.updateSubtask(subtask1);
                subtask2.setStatus(TaskStatus.DONE);
                manager.updateSubtask(subtask2);
                System.out.println("\nЭпик 1 после обновления подзадач:");
                System.out.println(manager.getEpic(epic1Id));

                // Демонстрация удаления задач
                manager.removeTask(task1Id);
                manager.removeEpic(epic2Id);
                System.out.println("\nПосле удаления некоторых задач:");
                System.out.println("Задачи: " + manager.getTasks());
                System.out.println("Эпики: " + manager.getEpics());
                System.out.println("Подзадачи: " + manager.getSubtasks());
        }
}
