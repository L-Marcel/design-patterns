package app.adapters;

import java.time.Duration;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import app.schedules.Scheduler;

public class SchedulerToExecutor implements Scheduler {
    private static ScheduledThreadPoolExecutor scheduler;
    private static int poolSize = 2;

    public SchedulerToExecutor() {
        if(SchedulerToExecutor.scheduler == null) 
            SchedulerToExecutor.scheduler = 
                new ScheduledThreadPoolExecutor(
                    SchedulerToExecutor.poolSize
                );
    };

    private static void incrementPoolSize() {
        SchedulerToExecutor.poolSize++;
        SchedulerToExecutor.scheduler.setCorePoolSize(poolSize);
    };

    private static void decrementPoolSize() {
        SchedulerToExecutor.poolSize--;
        SchedulerToExecutor.scheduler.setCorePoolSize(poolSize);
    };

    @Override
    public void schedule(Runnable task, Duration delay) {
        SchedulerToExecutor.incrementPoolSize();
        SchedulerToExecutor.scheduler.schedule(
            () -> {
                task.run();
                SchedulerToExecutor.decrementPoolSize();
            }, 
            delay.toMillis(), 
            TimeUnit.MILLISECONDS
        );
    };
};
