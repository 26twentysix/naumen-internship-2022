
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class AlgoTests {
    @Test
    public void defaultTest() {
        final String inputFilePath = "src/main/resources/input0.txt";
        try {
            Assertions.assertEquals(9, Main.findServerRequests(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void anotherTest() {
        final String inputFilePath = "src/main/resources/input1.txt";
        try {
            Assertions.assertEquals(9, Main.findServerRequests(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void another2Test() {
        final String inputFilePath = "src/main/resources/input2.txt";
        try {
            Assertions.assertEquals(10, Main.findServerRequests(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void another3Test() {
        final String inputFilePath = "src/main/resources/input3.txt";
        try {
            Assertions.assertEquals(1, Main.findServerRequests(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void another4Test() {
        final String inputFilePath = "src/main/resources/input4.txt";
        try {
            Assertions.assertEquals(8, Main.findServerRequests(inputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
