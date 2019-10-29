import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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
    //Реализовать поддержку ввода слова пользователем в формате: предкоренная часть слова, пробел, корень, пробел, посткоренная часть
    @Test
    void testInputWordInAFormat() {
        Input input = new Input();
        String wordParts[] = input.prepareWordToStoreInDB("пред утрен ний");
        wordParts = input.checkConcatAndWord("предутренний", wordParts);
        Database db = new Database();
        db.insertWord(wordParts);
        boolean checkIsExists = db.checkIsWordExists("предутренний");
        db.deleteWordFromDB("пред", "утрен", "ний");
        if(!checkIsExists) {
            fail("Failed testInputWordInAFormat");
        }
    }

    //Реализовать конкатенацию ввода пользователя в виде предкоренной части слова, корня и посткоренной части в одно слово
    //Реализовать сравнение конкатенированных слов со словом, которое ввел пользователь
    @Test
    void testWrongInputWordInAFormat() {
        Input input = new Input();
        String wordParts[] = input.prepareWordToStoreInDB("пред утрен няя");
        wordParts = input.checkConcatAndWord("предутренний", wordParts);

        if(wordParts != null) {
            fail("Failed testInputWordInAFormat");
        }
    }
    //Реализовать проверку введенных частей слова пользователем на соответствие шаблону (частьСлова Пробел частьСлова Пробел частьСлова)
    //Реализовать вывод сообщения об ошибке, если слово, введенное пользователем, не соответствует шаблону.
    @Test
    void testWrongInputWord() {
        String inputedWord= "предутренний\r\n" + "Y\r\n" + "пред утренний\r\n" + "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        Main.main(null);
        System.setIn(System.in);
        System.setOut(System.out);

        String output = out.toString();
        if(!output.contains("You have entered not three word parts!")) {
            fail("Failed testWrongInputWord");
        }

    }
    //Реализовать завершение работы программы при вводе буквы q
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

    // Реализовать вывод сообщения об ошибке, если конкатенированное слово, которое получено из ранее введенных не совпадает
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
        String inputedWord= "подделанный\r\n" + "Y\r\n" + "под дел анный\r\n" + "q";
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
        db.deleteWordFromDB("под", "дел", "анный");

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
    //Проверить ответ пользователя на запрос: корректным будет являться ответ “Y” (с учетом регистра) или “q”. (для выхода из программы)
    //Если ответ некорректен вывести сообщение об ошибке и повторить запрос, пока ответ не станет корректным
    @Test
    void checkUserAnswerOnRequest() {
        String inputedWord= "сделанная\r\n" + "F\r\n" + "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        Main.main(null);
        System.setIn(System.in);
        System.setOut(System.out);

        String output = out.toString();

        if(!output.contains("Incorrect answer. Try again")) {
            fail("Failed checkUserAnswerOnRequest");
        }
    }

    //Реализовать вывод полученного списка слов из БД по одинаковому корню.
    @Test
    public void testPublishRootWords() {
        Database db = new Database();
        String[] wordOne = new String[3];
        wordOne[0] = "на";
        wordOne[1] = "сып";
        wordOne[2] = "ать";
        db.insertWord(wordOne);
        String[] wordTwo = new String[3];
        wordTwo[0] = "от";
        wordTwo[1] = "сып";
        wordTwo[2] = "ал";
        db.insertWord(wordTwo);

        String inputedWord= "насыпать\r\n" + "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        Main.main(null);
        System.setIn(System.in);
        System.setOut(System.out);

        String output = out.toString();

        if(!output.contains("от-сып-ал") || !output.contains("на-сып-ать")  ) {
            fail("Failed testDBIsExistsAndConcat");
        }

        db.deleteWordFromDB(wordOne[0], wordOne[1],wordOne[2]);
        db.deleteWordFromDB(wordTwo[0], wordTwo[1],wordTwo[2]);


    }
}