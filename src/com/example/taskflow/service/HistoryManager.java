package com.example.taskflow.service;

import com.example.taskflow.model.Task;

import java.util.List;

public interface HistoryManager <T extends Task> {
    List<T> getHistory();
    void add(T task);
}
