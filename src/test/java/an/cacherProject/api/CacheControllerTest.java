package an.cacherProject.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CacheControllerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeTestClass
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void processInstruction() {
        System.out.print("dog");
        assertEquals("dog", outContent.toString());
    }
}