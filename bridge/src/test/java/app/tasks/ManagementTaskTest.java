package app.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import app.BaseTest;
import app.items.Item;
import app.persons.DynamicWorker;
import app.persons.TemporaryWorker;
import app.persons.Workers;

public class ManagementTaskTest extends BaseTest {
    @Test
    public void mustFireTasklessWorkers() {
        Item item = new Item("Livro");
        Task storage = new StorageTask(item);

        Workers workers = new Workers();
        Task management = new ManagementTask(workers);

        DynamicWorker dynamic = new DynamicWorker("Dynamic");
        dynamic.getTasks().add(management);
    
        TemporaryWorker temporary = new TemporaryWorker(
            "Temporary",
            Duration.ofDays(1)
        );
        temporary.setTask(storage);

        workers.add(dynamic);
        workers.add(temporary);
        workers.work();

        assertEquals(
            "Dynamic:" + System.lineSeparator().repeat(2) +
            "Temporary:" + System.lineSeparator() +
            "Livro foi armazenado(a)!", 
            BaseTest.output.toString().trim()
        );

        BaseTest.output.reset();

        workers.work();

        assertEquals(
            "Dynamic:" + System.lineSeparator() +
            "Temporary foi demitido(a)!",
            BaseTest.output.toString().trim()
        );
    };
};
