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
    //Реализовать проверку введенных частей слова пользователем на соответствие шаблону (частьСлова Пробел частьСлова Пробел частьСлова)
    //Реализовать вывод сообщения об ошибке, если слово, введенное пользователем, не соответствует шаблону.
    @Test
    void testInputWordInAFormat() {
        String inputedWord= "предутренний\r\n" + "Y\r\n" + "пред утрен ний\r\n" + "q";
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
        Database db = new Database();
        db.deleteWordFromDB("пред", "утрен", "ний");



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

    //Если в слове есть цифры, латиница и спец. символы, вывести сообщение об ошибке.
    @Test
    void testInputWordWithFewSpecialSymbols() {
        String word = "test\r\n";
        testInputWordWithSpecialSymbols(word);
        word = "тест0419\r\n";
        testInputWordWithSpecialSymbols(word);
        word = "тест!\r\n";
        testInputWordWithSpecialSymbols(word);
    }

    void testInputWordWithSpecialSymbols(String word) {
        String inputedWord= word + "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        Main.main(null);
        System.setIn(System.in);
        System.setOut(System.out);

        String output = out.toString();

        if(!output.contains("Input has latin or digit or special symbol. Try again")) {
            fail("Failed testInputWordWithSpecialSymbols");
        }

    }

    //Реализовать сравнение конкатенированных слов со словом, которое ввел пользователь
    // Реализовать вывод сообщения об ошибке, если конкатенированное слово, которое получено из ранее введенных
    @Test
    void testWordsInput() {
        String inputedWord= "предутренний\r\n" + "Y\r\n" + "пред утрен няя\r\n" + "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        Main.main(null);
        System.setIn(System.in);
        System.setOut(System.out);

        String output = out.toString();

        if(!output.contains("First word which you entered and word from concated parts do not match")) {
            fail("Failed testWordsInput");
        }
    }

    //Ожидать ввода нового слова или q
    @Test
    void checkWaitForNewWordOrQ() {
        String inputedWord= "сделанный\r\n" + "Y\r\n" + "с дел анный\r\n" + "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        Main.main(null);
        System.setIn(System.in);
        System.setOut(System.out);

        String output = out.toString();

        int count = output.length() - output.replaceAll("Please write a word","").length();

        Database db = new Database();
        db.deleteWordFromDB("c", "дел", "анный");

        if(count <= 19) {
            fail("Failed checkWaitForNewWordOrQ");
        }
    }
    //Реализовать запрос у пользователя на сохранение слова в БД
    @Test
    void checkRequestForWordInput() {
        String inputedWord= "сделанная\r\n" + "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        Main.main(null);
        System.setIn(System.in);
        System.setOut(System.out);

        String output = out.toString();

        if(!output.contains("Should we save word in DB: press 'Y' for continue or 'q' for exit")) {
            fail("Failed checkRequestForWordInput");
        }
    }
}