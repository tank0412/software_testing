import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {
    Input myinput = new Input();
    @Test
    public void testInputOfStringTestWord() {
        Scanner scanner = new Scanner("test");
        String result = myinput.requestWord("Write word 'test' to test function", scanner);
        assertEquals("test", result, "It is not a 'test' word. Test fail");
    }
    @Test
    public void testInputIsMatchPatter() {
        assertEquals(true, myinput.checkUserInput("привет"), "It contains cyrillyc so it must pass");
        assertEquals(false, myinput.checkUserInput("010101"), "It contains digit so it must fail");
        //assertEquals(false, myinput.checkUserInput("test"), "It contains latin so it must fail"); Add temp latin support for null in input
    }
}