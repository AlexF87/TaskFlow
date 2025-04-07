package java.com.example.taskflow.service;

import java.com.example.taskflow.model.Epic;
import java.com.example.taskflow.model.SubTask;
import java.com.example.taskflow.model.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
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
}
