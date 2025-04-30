package app.persons;

import java.time.Duration;

import app.tasks.Task;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemporaryWorker extends Worker {
    private Duration duration;

    public TemporaryWorker(String name, Duration duration) {
        super(name);
        this.duration = duration;
    };

    @Override
    public void setTask(Task task) {
        super.setTask(task);
    };
    
    @Override
    public void work() {
        super.work();

        if(this.duration != null && this.duration.toDays() > 0) {
            this.duration = this.duration.minusDays(1);
        } else {
            this.setTask(null);
        };
    };
};
