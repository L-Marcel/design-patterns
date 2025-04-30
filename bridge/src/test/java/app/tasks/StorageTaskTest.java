package app.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import app.BaseTest;
import app.items.Item;

public class StorageTaskTest extends BaseTest {
    @Test
    public void mustStoreAnItem() {
        Item item = new Item("Livro");
        Task task = new StorageTask(item);
        task.execute();

        assertEquals(
            "Livro foi armazenado(a)!", 
            BaseTest.output.toString().trim()
        );
    };
};