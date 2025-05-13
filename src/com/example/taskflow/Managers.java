package com.example.taskflow;

import com.example.taskflow.model.Task;
import com.example.taskflow.service.HistoryManager;
import com.example.taskflow.service.InMemoryHistoryManager;
import com.example.taskflow.service.InMemoryTaskManager;
import com.example.taskflow.service.Manager;

public class Managers {
    public static Manager getDefault(){
        return new InMemoryTaskManager();
    }

    public static  HistoryManager<Task> getDefaultHistory(){
        return new InMemoryHistoryManager<>();
    }
}
