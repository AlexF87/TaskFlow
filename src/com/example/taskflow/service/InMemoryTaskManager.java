package com.example.taskflow.service;

import com.example.taskflow.model.Epic;
import com.example.taskflow.model.SubTask;
import com.example.taskflow.model.Task;
import com.example.taskflow.model.TaskStatus;

import java.util.*;

public class InMemoryTaskManager implements Manager{
    //Хранилище задач
    Map<Integer, Task> tasks = new HashMap<>();

    //Id для всех задач
    private static int id = 0;
    //Task методы
    //Получение всех задач
    public List<Task> getTaskAll(){
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i) instanceof Epic || tasks.get(i) instanceof SubTask){
                continue;
            }
            else {
                taskList.add(tasks.get(i));
            }
        }
        return taskList;
    }

    //Удаление всех задач
    public void removeTask(){
        for (int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i) instanceof Epic || tasks.get(i) instanceof SubTask){
                continue;
            }
            else {
                tasks.remove(tasks.get(i));
            }
        }
        System.out.println("All task removed");
    }

    //Получение по идентификатору задачи
    public Task getTask(int id){
        return tasks.getOrDefault(id, null);
    }

    //Создание задачи
    public Task createTask(Task task){
        task.setId(++id);
        tasks.put(task.getId(),task);
        return task;
    }

    //Обновление задачи
    public Task updateTask(Task newTask){
        Task oldTask = tasks.get(newTask.getId());
        oldTask.setName(newTask.getName());
        oldTask.setDescription(newTask.getDescription());
        oldTask.setStatus(newTask.getStatus());
        return tasks.get(newTask.getId());
    }

    //Удаление задачи по id
    public void removeTask(int id){
        tasks.remove(id);
    }

    //Epic
    //Получение всех эпиков
    public List<Epic> getAllEpics() {
        List<Epic> epicList = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Epic epic) {
                epicList.add(epic);
            }
        }
        return epicList;
    }

    //Удаление всех эпиков
    public void removeEpic(){
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof Epic) {
                tasks.remove(i);
            }
        }
    }

    //Получение эпика по id
    public Epic getEpic(int id){
        return (Epic)tasks.getOrDefault(id, null);
    }

    //Создание эпика
    public Epic createEpic(Epic epic){
        epic.setId(++id);
        tasks.put(epic.getId(), epic);
        return epic;
    }

    //Обновление эпика
    public Epic updateEpic(Epic newEpic){
        Epic epic = (Epic)tasks.get(newEpic.getId());
        epic.setDescription(newEpic.getDescription());
        epic.setSubTasks(newEpic.getSubTasks());
        epic.setName(newEpic.getName());
        epic.setStatus(newEpic.getStatus());
        return epic;
    }

    //Удаление по id
    public void removeEpic(int id){
        tasks.remove(id);
    }

    //Получение списка подзадач определенного эпика
    public List<SubTask> getListSubTasksOfEpic(int id){
        Epic epic = (Epic)tasks.get(id);
        return epic.getSubTasks();
    }

    //SubTask
    //Получение списка всех SubTask
    public List<SubTask> getAllSubTasks() {
        List<SubTask> subTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof SubTask subTask) {
                subTasks.add(subTask);
            }
        }
        return subTasks;
    }

    //Удаление всех SubTask
    public void removeAllSubTasks(){
        Set<Integer> epicsId = new HashSet<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) instanceof SubTask subTask) {
                epicsId.add(subTask.getIdEpic());
                tasks.remove(i);
            }
        }
        for (Integer i : epicsId){
            changeStatusForEpic((Epic)tasks.get(i));
        }
    }

    //Получение по id SubTask
    public SubTask getSubTask(int id){
        return (SubTask)tasks.getOrDefault(id, null);
    }

    //Создание SubTask
    public SubTask createSubTask(SubTask subTask){
        subTask.setId(++id);
        tasks.put(subTask.getId(), subTask);
        Epic epic = (Epic)tasks.get(subTask.getIdEpic());
        epic.getSubTasks().add(subTask);
        changeStatusForEpic((Epic)tasks.get(subTask.getIdEpic()));
        return subTask;
    }

    //Обновление SubTask
    public SubTask updateSubTask(SubTask newSubTask){
        SubTask subTask = (SubTask)tasks.get(newSubTask.getId());
        subTask.setDescription(newSubTask.getDescription());
        subTask.setIdEpic(newSubTask.getIdEpic());
        subTask.setName(newSubTask.getName());
        subTask.setStatus(newSubTask.getStatus());
        changeStatusForEpic((Epic)tasks.get(subTask.getIdEpic()));
        return subTask;
    }

    //Удаление по идентификатору SubTask
    public void removeSubTask(int id){
        SubTask subTask = (SubTask)tasks.get(id);
        int epicId = subTask.getIdEpic();
        tasks.remove(id);
        changeStatusForEpic((Epic)tasks.get(epicId));
    }

    //Изменение статуса для эпика
    public void changeStatusForEpic(Epic epic) {
        List<SubTask> subTasks = epic.getSubTasks();

        //NEW
        if (epic.getSubTasks().isEmpty()) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }

        boolean statusNew = subTasks.stream()
                .allMatch(subTask -> subTask.getStatus() == TaskStatus.NEW);
        if (statusNew) {
            epic.setStatus(TaskStatus.NEW);
            return;
        }

        //DONE
        boolean statusDone = subTasks.stream()
                .allMatch(subtask -> subtask.getStatus() == TaskStatus.DONE);
        if (statusDone) {
            epic.setStatus(TaskStatus.DONE);
            return;
        }

        //IN_PROGRESS
        epic.setStatus(TaskStatus.IN_PROGRESS);
    }
}
