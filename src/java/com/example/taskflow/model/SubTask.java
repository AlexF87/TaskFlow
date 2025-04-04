package java.com.example.taskflow.model;

import java.util.Objects;

public class SubTask extends Task{
    private int idEpic;

    public SubTask(int id, String name, String description, TaskStatus status, int idEpic) {
        super(id, name, description, status);
        this.idEpic = idEpic;
    }

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SubTask subTask = (SubTask) o;
        return idEpic == subTask.idEpic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idEpic); // Автоматически использует 31 внутри
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", description='" + this.getDescription() + '\'' +
                "idEpic=" + idEpic +
                '}';
    }
}
