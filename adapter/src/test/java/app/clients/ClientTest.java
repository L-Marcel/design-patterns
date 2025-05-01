package app.clients;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import java.time.Duration;
import java.time.Instant;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import app.BaseTest;
import app.adapters.SchedulerToExecutor;
import app.adapters.SchedulerToTimer;
import app.errors.InvalidEventTime;
import app.events.NotificationEvent;

public class ClientTest extends BaseTest {
    @Test
    public void mustBeAbleToShedule() {
        Client a = new Client(new SchedulerToTimer());
        Client b = new Client(new SchedulerToTimer());
        Client c = new Client(new SchedulerToExecutor());
        Client d = new Client(new SchedulerToExecutor());

        NotificationEvent firstEvent = Mockito.spy(new NotificationEvent(
            "Teste 1", 
            "Um teste qualquer!"
        ));

        NotificationEvent secondEvent = Mockito.spy(new NotificationEvent(
            "Teste 2", 
            "Um teste qualquer!"
        ));

        Instant now = Instant.now();
        assertThrows(
            InvalidEventTime.class,
            () -> {
                a.schedule(firstEvent, now.plusSeconds(-1));
            }
        );
        
        a.schedule(firstEvent, now.plusSeconds(2));
        b.schedule(firstEvent, now.plusSeconds(2));
        c.schedule(firstEvent, now.plusSeconds(3));
        d.schedule(firstEvent, now.plusSeconds(3));
        a.schedule(secondEvent, now.plusSeconds(3));
        b.schedule(secondEvent, now.plusSeconds(3));
        c.schedule(secondEvent, now.plusSeconds(4));
        d.schedule(secondEvent, now.plusSeconds(4));

        Duration duration = Duration.between(now, now.plusSeconds(2));
        Awaitility
            .await()
            .atLeast(duration.minusSeconds(1))
            .atMost(duration.plusSeconds(5))
            .untilAsserted(
                () -> {
                    Mockito.verify(firstEvent, times(4)).run();
                    Mockito.verify(secondEvent, times(4)).run();
                }
            );
    };
};
