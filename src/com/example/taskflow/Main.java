package com.example.taskflow;

import com.example.taskflow.model.Epic;
import com.example.taskflow.model.SubTask;
import com.example.taskflow.model.Task;
import com.example.taskflow.model.TaskStatus;
import com.example.taskflow.service.HistoryManager;
import com.example.taskflow.service.InMemoryHistoryManager;
import com.example.taskflow.service.InMemoryTaskManager;
import com.example.taskflow.service.Manager;
import java.util.ArrayList;
import java.util.Collections;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Epic epic1 = new Epic(0,"first", "first des", TaskStatus.NEW,new ArrayList<>());
        Epic epic2 = new Epic(0,"second", "second des", TaskStatus.NEW,new ArrayList<>());
        SubTask subTask1 = new SubTask(0, "AAAA","AAAfor epic 1", TaskStatus.NEW, 1);
        SubTask subTask2 = new SubTask(0, "AA2","AAAfor epic 1", TaskStatus.NEW, 1);
        SubTask subTask3 = new SubTask(0, "BBB","AAAfor epic 2", TaskStatus.NEW, 2);

        Manager manager = Managers.getDefault();

        manager.createEpic(epic1);
        manager.createEpic(epic2);
        manager.createSubTask(subTask1);
        manager.createSubTask(subTask2);
        manager.createSubTask(subTask3);

        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubTasks());

        subTask1.setStatus(TaskStatus.DONE);
        manager.updateSubTask(subTask1);
        System.out.println(manager.getAllEpics());
        manager.getTask(0);
        manager.getEpic(1);
        manager.getEpic(1);
        manager.getEpic(1);
        manager.getEpic(1);
        manager.getEpic(1);
        manager.getEpic(1);
        manager.getEpic(1);
        manager.getEpic(1);
        manager.getEpic(1);
        manager.getEpic(1);
        manager.getEpic(2);
        manager.getSubTask(3);

        InMemoryTaskManager manager1 = (InMemoryTaskManager) manager;
        System.out.println(manager1.historyManager.getHistory());
    }
}