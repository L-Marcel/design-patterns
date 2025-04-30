package app.clients;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import app.BaseTest;
import app.adapters.SchedulerToExecutor;
import app.adapters.SchedulerToTimer;
import app.events.NotificationEvent;

public class ClientTest extends BaseTest {
    @Test
    public void mustBeAbleToShedule() {
        //[TODO] Terminar os testes do cliente
        
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
    };
};
