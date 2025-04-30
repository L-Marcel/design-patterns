package app.persons;

import java.time.Duration;

import app.tasks.Task;
public class TemporaryWorker extends Worker {
    private Duration duration;

    public TemporaryWorker(String name, Duration duration) {
        super(name);
        this.setDuration(duration);
    };

    @Override
    public void setTask(Task task) {
        super.setTask(task);
    };

    public void setDuration(Duration duration) {
        if(duration != null && !duration.isNegative()) {
            this.duration = duration;
        } else {
            this.duration = Duration.ZERO;
        };
    };

    public Duration getDuration() {
        return this.duration;
    };

    private void decrementDuration() {
        Duration rest = this.duration.minusDays(1);
        this.setDuration(rest);
    };

    private void clearTaskIfWorkEndded() {
        if(duration.toDays() <= 0)
            this.setTask(null);
    };
    
    @Override
    public void work() {
        this.clearTaskIfWorkEndded();
        super.work();
        this.decrementDuration();
        this.clearTaskIfWorkEndded();
    };
};
