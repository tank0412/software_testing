import org.junit.jupiter.api.Test;
import org.junit.Ignore;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    ResultSet  rs = null;

    //Реализовать запрос всей таблицы со словами из БД.
    @Test
    public void testDBSelect() {

        Database db = new Database();
        String[][] dictionary = db.selectAllWords();
        if(dictionary == null) {
            fail("Failed to testDBSelect ");
        }
    }
    //Реализовать поддержку сохранения однокоренного слова виде предкоренной части, корня и посткоренной части в БД.

    @Test
    public void testDBInsert() {

        Database db = new Database();
        String[] wordparts = new String[3];
        wordparts[0] = "пред";
        wordparts[1] = "диплом";
        wordparts[2] = "ная";
        db.insertWord(wordparts);

        if(!db.checkIsWordExists(wordparts[0] + wordparts[1] + wordparts[2])) {
            fail("Failed to test testDBInsert");
        }

        db.deleteWordFromDB(wordparts[0], wordparts[1], wordparts[2]);
    }
    //реализовать обновление сохраненного слова в БД, если пользователь сохранил слово с опечаткой

    @Test
    public void testDBUpdate() {

        Database db = new Database();
        String[] wordparts = new String[3];
        wordparts[0] = "пред";
        wordparts[1] = "диплом";
        wordparts[2] = "ная";
        db.insertWord(wordparts);

        String[] newWord = new String[3];
        newWord[0] = "пред";
        newWord[1] = "диплом";
        newWord[2] = "ный";

        db.updateWord(wordparts, newWord);

        boolean isUpdatedWordExists = db.checkIsWordExists(newWord[0] + newWord[1] + newWord[2]);

        db.deleteWordFromDB(newWord[0] , newWord[1] , newWord[2]);

        if(isUpdatedWordExists) {
            System.out.println("Test Passed");
        }
        else {
            fail("Failed to test testDBUpdate");
        }
    }
    //Реализовать вывод полученного списка слов из БД по одинаковому корню.

    @Test
    public void testDBSelectRootWords() {
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

        String[] rootWords = db.getRootWords(wordOne[0] + wordOne[1] + wordOne[2]);
        int wordsFoundCount = 0;
        String wordOneConcat =  wordOne[0] + "-" +  wordOne[1] + "-" +  wordOne[2];
        String wordTwoConcat =  wordTwo[0] + "-" +  wordTwo[1] + "-" +  wordTwo[2];
        for(int i = 0; i < 100; ++i) {
            if(rootWords[i] == null) {
                break;
            }
            if(rootWords[i].equals(wordOneConcat) || rootWords[i].equals(wordTwoConcat) ) {
                wordsFoundCount++;
            }

        }

        db.deleteWordFromDB(wordOne[0], wordOne[1],wordOne[2]);
        db.deleteWordFromDB(wordTwo[0], wordTwo[1],wordTwo[2]);


        if(wordsFoundCount != 2) {
            fail("Failed to test testDBSelectRootWords");
        }

    }

    //Реализовать поддержку сохранения однокоренного слова в виде предкоренной части, корня и посткоренной части в БД, если оно там отсутствует
    @Test
    void testInputWordSaveToDB() {
        Database db = new Database();
        String inputedWord= "предутренний\r\n" + "пред утрен ний\r\n" + "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        Main.main(null);
        System.setIn(System.in);

        if(!db.checkIsWordExists("предутренний")) {
            fail("Failed to test testInputWordSaveToDB");
        }

        db.deleteWordFromDB("пред", "утрен", "ний");

    }
    //Реализовать конкатенацию строки из БД в одно слово
    //Проверить слово на его присутствие в БД
    @Test
    void testDBIsExistsAndConcat() {
        Database db = new Database();
        String[] wordparts = new String[3];
        wordparts[0] = "пред";
        wordparts[1] = "диплом";
        wordparts[2] = "ная";
        db.insertWord(wordparts);

        boolean isExists = db.checkIsWordExists("преддипломная");
        if(!isExists) {
            fail("Failed to check is word exists in DB");
        }

        db.deleteWordFromDB(wordparts[0], wordparts[1], wordparts[2]);
    }

    //Реализовать сортировку полученных данных из БД по количеству пустых столбцов (если в столбце null, значит у слова этой части нет)
    //Реализовать разделение данных из столбцов дефисом 
    @Test
    public void testDBOrderByNuLL() {
        Database db = new Database();
        String[] wordOne = new String[3];
        wordOne[0] = "от";
        wordOne[1] = "сып";
        wordOne[2] = "ал";
        db.insertWord(wordOne);
        String[] wordTwo = new String[3];
        wordTwo[0] = "null";
        wordTwo[1] = "сып";
        wordTwo[2] = "ать";
        db.insertWord(wordTwo);

        String[] rootWords = db.getRootWords(wordOne[0] + wordOne[1] + wordOne[2]);
        String wordOneConcat =  wordOne[0] + "-" +  wordOne[1] + "-" +  wordOne[2];
        String wordTwoConcat =  "" + "-" +  wordTwo[1] + "-" +  wordTwo[2];


        db.deleteWordFromDB(wordOne[0], wordOne[1],wordOne[2]);
        db.deleteWordFromDB(wordTwo[0], wordTwo[1],wordTwo[2]);


        boolean firstWordFound = false;

        for(int i = 0; i < 100; ++i) {
            if(rootWords[i] == null) {
                break;
            }

            if(rootWords[i].equals(wordOneConcat) ) {
                firstWordFound = true;
            }


            if((!rootWords[i].equals(wordTwoConcat)) && !firstWordFound  ) {
                fail("Failed to test testDBOrderByNuLL");
            }

        }

    }



}