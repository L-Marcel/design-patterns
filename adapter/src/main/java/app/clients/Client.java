package app.clients;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

import app.errors.InvalidEventTime;
import app.events.Event;
import app.schedules.Scheduler;

public class Client {
    private Scheduler scheduler;

    public Client(Scheduler scheduler) {
        this.scheduler = scheduler;
    };

    public void schedule(Event event, Instant when) {
        Instant now = Instant.now();
        if(when.isBefore(now)) throw new InvalidEventTime();
        Duration duration = Duration.between(now, when);
        scheduler.schedule(event, duration);
    };
};
