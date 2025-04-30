package app.workers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import app.BaseTest;
import app.items.Item;
import app.persons.DynamicWorker;
import app.tasks.StorageTask;
import app.tasks.Task;

public class DynamicWorkerTest extends BaseTest {
    @Test
    public void mustChangeTasks() {
        Item book = new Item("Livro");
        Task storageBook = new StorageTask(book);

        Item box = new Item("Caixa");
        Task storageBox = new StorageTask(box);

        DynamicWorker worker = new DynamicWorker("Dynamic");
        worker.getTasks().add(storageBox);
        worker.getTasks().add(storageBook);

        worker.work();
        assertEquals(
            "Dynamic:" + System.lineSeparator() +
            "Caixa foi armazenado(a)!", 
            BaseTest.output.toString().trim()
        );

        BaseTest.output.reset();

        worker.work();
        assertEquals(
            "Dynamic:" + System.lineSeparator() +
            "Livro foi armazenado(a)!", 
            BaseTest.output.toString().trim()
        );

        BaseTest.output.reset();

        worker.work();
        assertEquals(
            "Dynamic:" + System.lineSeparator() +
            "Caixa foi armazenado(a)!", 
            BaseTest.output.toString().trim()
        );
    };
};
