package app.schedules;

import java.time.Duration;

public interface Scheduler {
    public void schedule(Runnable task, Duration delay);
};
