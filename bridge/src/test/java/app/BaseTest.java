package app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {
    static protected ByteArrayOutputStream output;

    @BeforeAll
    public static void setup() {
        BaseTest.output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    };

    @AfterEach
    public void cleanup() {
        BaseTest.output.reset();
    };
};
