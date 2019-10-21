import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
    //Реализовать поддержку ввода слова пользователем в формате: предкоренная часть слова, корень и посткоренная часть
    @Test
    void testInputWordInAFormat() {
        String inputedWord= "предутренний\r\n" + "пред утрен ний\r\n" + "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        Main.main(null);
        System.setIn(System.in);
        System.setOut(System.out);

        String output = out.toString();
//
        if(output.contains("You have entered not Two words!")) {
            fail("Failed testInputWordInAFormat");
        }
        DatabaseTest dbt = new DatabaseTest();
        dbt.deleteWordFromDB("пред", "утрен", "ний");



    }
    //Реализовать выход из программы при вводе буквы q
    @Test
    void testExitAfterQ() {
        String inputedWord= "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        Main.main(null);
        System.setIn(System.in);
        System.setOut(System.out);

        String output = out.toString();

        if(!output.contains("Exiting...")) {
            fail("Failed testInputWordInAFormat");
        }

    }
}