import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class InputTest {
    Input myinput = new Input();

    //Проверить введенное слово на наличие цифр, латиницы и спец. Символов.
    @Test
    public void testInputIsMatchPattern() {
        assertEquals(true, myinput.checkUserInput("привет"), "It contains cyrillyc so it must pass");
        assertEquals(false, myinput.checkUserInput("010101"), "It contains digit so it must fail");
        assertEquals(false, myinput.checkUserInput("hello@"), "It contains special symbols so it must fail");
        //assertEquals(false, myinput.checkUserInput("test"), "It contains latin so it must fail"); Add temp latin support for null in input
    }
}