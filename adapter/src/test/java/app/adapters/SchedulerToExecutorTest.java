package app.adapters;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import app.BaseTest;
import app.events.NotificationEvent;
import app.schedules.Scheduler;

public class SchedulerToExecutorTest extends BaseTest {
    @Test
    public void mustSchedule() {
        Scheduler scheduler = new SchedulerToExecutor();

        NotificationEvent firstEvent = Mockito.spy(new NotificationEvent(
            "Teste 1", 
            "Um teste qualquer!"
        ));

        NotificationEvent secondEvent = Mockito.spy(new NotificationEvent(
            "Teste 2", 
            "Um teste qualquer!"
        ));
        
        Duration delay = Duration.ofSeconds(2);
        scheduler.schedule(firstEvent, delay);
        scheduler.schedule(secondEvent, delay.plusSeconds(2));

        Awaitility
            .await()
            .atLeast(delay.minusSeconds(2))
            .atMost(delay.plusSeconds(2))
            .untilAsserted(
                () -> {
                    Mockito.verify(firstEvent).run();
                    assertEquals(
                        "Teste 1 -> Um teste qualquer!",
                        BaseTest.output.toString().trim()
                    );
                    BaseTest.output.reset();
                }
            );
        
        Awaitility
            .await()
            .atLeast(delay)
            .atMost(delay.plusSeconds(4))
            .untilAsserted(
                () -> {
                    Mockito.verify(secondEvent).run();
                    assertEquals(
                        "Teste 2 -> Um teste qualquer!",
                        BaseTest.output.toString().trim()
                    );
                }
            );
    };
};
