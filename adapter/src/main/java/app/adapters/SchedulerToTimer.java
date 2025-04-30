package app.adapters;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

import app.schedules.Scheduler;

public class SchedulerToTimer implements Scheduler {
    private static Timer timer;

    public SchedulerToTimer() {
        if(SchedulerToTimer.timer == null) 
            SchedulerToTimer.timer = new Timer();
    };

    @Override
    public void schedule(Runnable task, Duration delay) {
        TimerTask timeTask = new TimerTask() {
            @Override
            public void run() {
                task.run();
            };
        };
        
        SchedulerToTimer.timer.schedule(timeTask, delay.toMillis());
    };
};
