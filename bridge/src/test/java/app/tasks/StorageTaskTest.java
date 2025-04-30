package app.tasks;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.BaseTest;
import app.items.Item;

public class StorageTaskTest extends BaseTest {
    @Test
    public void shouldStore() {
        Item item = new Item("Livro");
        Task task = new StorageTask(item);
        task.execute();

        assertEquals(
            "Livro foi armazenado(a)!", 
            this.output.toString().trim()
        );
    };
};