package com.example.taskflow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task{
    private List<SubTask> subTasks = new ArrayList<>();

    public Epic(int id, String name, String description, TaskStatus status, List<SubTask> subTasks) {
        super(id, name, description, status);
        this.subTasks = subTasks;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subTasks, epic.subTasks);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (subTasks != null ? subTasks.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + this.getId() + '\'' +
                "status" + this.getStatus() +'\'' +
                ", name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                "subTasks=" + subTasks +
                '}';
    }
}
