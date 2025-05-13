package com.example.taskflow.service;

import com.example.taskflow.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager<T extends Task> implements HistoryManager<T>{
    private final static int MAX_SIZE = 10;
    private final List<T> history = new ArrayList<>();
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
        }
        history.add(task);
    }
}
