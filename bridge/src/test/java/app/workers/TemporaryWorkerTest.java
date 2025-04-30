package app.workers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import app.BaseTest;
import app.items.Item;
import app.persons.TemporaryWorker;
import app.tasks.StorageTask;
import app.tasks.Task;

public class TemporaryWorkerTest extends BaseTest {
    @Test
    public void mustWorkTwoDays() {
        Item item = new Item("Livro");
        Task task = new StorageTask(item);

        TemporaryWorker worker = new TemporaryWorker(
            "Temporary", 
            Duration.ofDays(2)
        );
        worker.setTask(task);

        worker.work();
        assertEquals(Duration.ofDays(1), worker.getDuration());
        assertEquals(
            "Temporary:" + System.lineSeparator() +
            "Livro foi armazenado(a)!", 
            BaseTest.output.toString().trim()
        );

        BaseTest.output.reset();

        worker.work();
        assertEquals(Optional.empty(), worker.getTask());
        assertEquals(Duration.ZERO, worker.getDuration());
        assertEquals(
            "Temporary:" + System.lineSeparator() +
            "Livro foi armazenado(a)!", 
            BaseTest.output.toString().trim()
        );

        BaseTest.output.reset();

        worker.work();
        assertEquals(Optional.empty(), worker.getTask());
        assertEquals(Duration.ZERO, worker.getDuration());
        assertEquals("", BaseTest.output.toString().trim());
    };

    @Test
    public void mustNotWork() {
        Item item = new Item("Livro");
        Task task = new StorageTask(item);

        TemporaryWorker worker = new TemporaryWorker(
            "Temporary", 
            Duration.ZERO
        );
        worker.setTask(task);
        assertEquals(Duration.ZERO, worker.getDuration());

        worker.work();
        assertEquals(Optional.empty(), worker.getTask());
        assertEquals(Duration.ZERO, worker.getDuration());
        assertEquals("", BaseTest.output.toString().trim());

        worker.work();
        assertEquals(Optional.empty(), worker.getTask());
        assertEquals(Duration.ZERO, worker.getDuration());
        assertEquals("", BaseTest.output.toString().trim());
    };

    @Test
    public void mustConvertNullDurationToZeroDuration() {
        Item item = new Item("Livro");
        Task task = new StorageTask(item);

        TemporaryWorker worker = new TemporaryWorker(
            "Temporary", 
            null
        );
        worker.setTask(task);
        assertEquals(Duration.ZERO, worker.getDuration());

        worker.work();
        assertEquals(Optional.empty(), worker.getTask());
        assertEquals(Duration.ZERO, worker.getDuration());
        assertEquals("", BaseTest.output.toString().trim());

        worker.work();
        assertEquals(Optional.empty(), worker.getTask());
        assertEquals(Duration.ZERO, worker.getDuration());
        assertEquals("", BaseTest.output.toString().trim());
    };

    @Test
    public void mustConvertNegativeDurationToZeroDuration() {
        Item item = new Item("Livro");
        Task task = new StorageTask(item);

        TemporaryWorker worker = new TemporaryWorker(
            "Temporary", 
            Duration.ofDays(-1)
        );
        worker.setTask(task);

        worker.work();
        assertEquals(Optional.empty(), worker.getTask());
        assertEquals(Duration.ZERO, worker.getDuration());
        assertEquals("", BaseTest.output.toString().trim());

        worker.work();
        assertEquals(Optional.empty(), worker.getTask());
        assertEquals(Duration.ZERO, worker.getDuration());
        assertEquals("", BaseTest.output.toString().trim());
    };
};
