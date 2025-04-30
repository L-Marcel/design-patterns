package app.persons;

import java.util.Optional;

import app.tasks.Task;
import lombok.Getter;

@Getter
public abstract class Worker extends Person {
    protected Optional<Task> task;

    public Worker(String name) {
        super(name);
        this.task = Optional.empty();
    };

    protected void setTask(Task task) {
        if(task != null) this.task = Optional.of(task);
        else this.task = Optional.empty();
    };

    public void work() {
        this.task.ifPresent((Task task) -> {
            System.out.println(this.getName() + ":");
            task.execute();
        });
    };
};
