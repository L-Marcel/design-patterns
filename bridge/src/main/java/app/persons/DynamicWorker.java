package app.persons;

import java.util.LinkedList;
import java.util.List;
import app.tasks.Task;
import lombok.Getter;

@Getter
public class DynamicWorker extends Worker {
    private List<Task> tasks; 
    
    public DynamicWorker(String name) {
        super(name);
        this.tasks = new LinkedList<Task>();
    };

    private void toNextTask() {
        this.task.ifPresentOrElse((Task task) -> {
            int index = 1 + this.tasks.indexOf(
                this.task.get()
            );

            index = index % this.tasks.size();
            Task nextTask = this.tasks.get(index);
            this.setTask(nextTask);
        }, () -> {
            Task nextTask = this.tasks.get(0);
            this.setTask(nextTask);
        });
    };

    @Override
    public void work() {
        this.toNextTask();
        super.work();
    };
};
