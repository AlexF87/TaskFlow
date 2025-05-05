package com.example.taskflow.service;

import com.example.taskflow.model.Epic;
import com.example.taskflow.model.SubTask;
import com.example.taskflow.model.Task;
import com.example.taskflow.model.TaskStatus;
import java.util.*;

public interface Manager {

    //Task методы
    //Получение всех задач
    List<Task> getTaskAll();

    //Удаление всех задач
    void removeTask();

    //Получение по идентификатору задачи
    Task getTask(int id);

    //Создание задачи
    Task createTask(Task task);

    //Обновление задачи
    Task updateTask(Task newTask);

    //Удаление задачи по id
    void removeTask(int id);

    //Epic
    //Получение всех эпиков
    List<Epic> getAllEpics();
    //Удаление всех эпиков
    void removeEpic();

    //Получение эпика по id
    Epic getEpic(int id);

    //Создание эпика
    Epic createEpic(Epic epic);

    //Обновление эпика
    Epic updateEpic(Epic newEpic);

    //Удаление по id
    void removeEpic(int id);

    //Получение списка подзадач определенного эпика
    List<SubTask> getListSubTasksOfEpic(int id);

    //SubTask
    //Получение списка всех SubTask
    List<SubTask> getAllSubTasks();

    //Удаление всех SubTask
    void removeAllSubTasks();

    //Получение по id SubTask
    SubTask getSubTask(int id);

    //Создание SubTask
    SubTask createSubTask(SubTask subTask);

    //Обновление SubTask
    SubTask updateSubTask(SubTask newSubTask);

    //Удаление по идентификатору SubTask
    void removeSubTask(int id);

    //Изменение статуса для эпика
    void changeStatusForEpic(Epic epic);
}
