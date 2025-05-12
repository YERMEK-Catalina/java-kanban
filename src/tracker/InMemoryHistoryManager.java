package tracker;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    private static class Node {
        Task task;
        Node next;
        Node previous;
        Node (Task task) {
            this.task = task;
        }
    }

    private Node head;
    private Node tail;
    private final Map<Integer, Node> nodeMap = new HashMap<>();

    private void linkLast(Task task) {
        Node newNode = new Node(task);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }

        nodeMap.put(task.getId(), newNode);
    }
    private void removeNode(Node node) {
        if (node == null) return;

        if (node == head) {
            head = node.next;
        }

        if (node == tail) {
            tail = node.previous;
        }

        if (node.previous != null) {
            node.previous.next = node.next;
        }

        if (node.next != null) {
            node.next.previous = node.previous;
        }

        node.next = null;
        node.previous = null;
    }


    public void remove(int id) {
        if (nodeMap.containsKey(id)) {
            removeNode(nodeMap.get(id));
            nodeMap.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        List<Task> history = new ArrayList<>();
        Node current = head;
        while (current != null) {
            history.add(current.task);
           current = current.next;
        }
        return history;
    }

    @Override
    public void add(Task task) {
        if (task == null) return;

        if (nodeMap.containsKey(task.getId())) {
            removeNode(nodeMap.get(task.getId()));
        }

        linkLast(task);
    }


}
