package app.persons;

import java.time.Duration;
import java.util.Optional;

import app.tasks.Task;
import lombok.Getter;
import lombok.Setter;

public class TemporaryWorker extends Worker {
    private Optional<Duration> duration;

    public TemporaryWorker(String name, Duration duration) {
        super(name);
        this.duration = Optional.of(duration);
    };

    @Override
    public void setTask(Task task) {
        super.setTask(task);
    };

    public void setDuration(Duration duration) {
        if(duration != null) {
            this.duration = Optional.of(duration);
        } else {
            this.duration = Optional.empty();
        };
    };

    public Optional<Duration> getDuration() {
        return this.duration;
    };

    private void decrementDuration() {
        this.duration = this.duration.map((Duration duration) -> {
            return duration.minusDays(1);
        });
    };

    private void clearTaskIfWorkEndded() {
        this.duration.ifPresent((Duration duration) -> {
            if(duration.toDays() < 1) {
                this.setTask(null);
            };
        });
    };
    
    @Override
    public void work() {
        super.work();
        this.decrementDuration();
        this.clearTaskIfWorkEndded();
    };
};
