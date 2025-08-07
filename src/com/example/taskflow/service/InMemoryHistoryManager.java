package com.example.taskflow.service;

import com.example.taskflow.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager<T extends Task> implements HistoryManager<T>{
    private final static int MAX_SIZE = 10;
    private final List<T> history = new ArrayList<>();
    Node<T> head;
    Node<T> tail;
    @Override
    public List<T> getHistory() {
        return history;
    }

    @Override
    public void add(T task) {
        if(task == null){
            return;
        }
        if(history.size() == MAX_SIZE){

            for (int i = 0; i < MAX_SIZE - 1; i++) {
                history.set(0, history.get(i + 1));
            }
            history.remove(history.get(9));
        }
        history.add(task);
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
}
