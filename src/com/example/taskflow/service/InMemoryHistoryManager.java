package com.example.taskflow.service;

import com.example.taskflow.model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager<T extends Task> implements HistoryManager<T>{
    private final static int MAX_SIZE = 10;
    private final List<T> history = new ArrayList<>();
    private final Map<Integer, Node> nodeMap = new HashMap<>();
    Node<T> head;
    Node<T> tail;
    @Override
    public List<T> getHistory() {
        return getTasks();
    }

    @Override
    public void add(T task) {
        if(task == null){
            return;
        }
        var historyList = getHistory();
        Node<T> newNode = new Node(task,null, null);

        if(historyList.size() == MAX_SIZE) {
            removeNode(nodeMap.get(historyList.get(0)));
            nodeMap.remove(historyList.get(0));
            nodeMap.put(task.getId(), newNode);
            linkLast(newNode);
        } else if (nodeMap.containsKey(task.getId())) {
            removeNode(nodeMap.get(task.getId()));
            linkLast(nodeMap.get(task.getId()));
        } else {
            nodeMap.put(task.getId(), newNode);
            linkLast(newNode);
        }

    }

    @Override
    public void remove(int id) {

    }

    //Добавляет задачу в конец списка CustomLinkedList
    private void linkLast(Node<T> taskNode) {
       if(head == null) {
           head = taskNode;
           tail = taskNode;
       } else{
           var oldTail = tail;
           tail = taskNode;
           taskNode.prev = oldTail;
           oldTail.next = taskNode;
       }

    }

    //Собирает все задачи из CustomLinkedList в обычный ArrayList
    private List<T> getTasks() {
        if(head == null) {
            return new ArrayList<>();
        } else {
            List<T> allHistory = new ArrayList<>();
            Node<T> current = head;
            while(current != null){
                allHistory.add(current.data);
                current = current.next;
            }
            return allHistory;
        }
    }

    // принимает объект Node (узел связного списка) и вырезает его.
    private void removeNode(Node<T> node) {
        if(node == null) {
            return;
        }
        //Удаление в начале
        if(head == node) {
            head = node.next;
            head.prev = null;
            node.next = null;
        }
        //Удаление в конце
        if(tail == node) {
            tail = node.prev;
            tail.next = null;
            node.prev = null;
        } else {
            //Удаление в середине
            var prev = node.prev;
            var next = node.next;

            prev.next = next;
            next.prev = prev;
            node.next = null;
            node.prev = null;
        }


    }
}
