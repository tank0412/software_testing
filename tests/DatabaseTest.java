import org.junit.jupiter.api.Test;
import org.junit.Ignore;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    ResultSet  rs = null;

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
    @Deprecated
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

    //Реализовать поддержку сохранения однокоренного слова в виде предкоренной части, корня и посткоренной части в БД, если оно там отсутствует
    @Test
    void testInputWordSaveToDB() {
        Database db = new Database();
        if(db.checkIsWordExists("предутренний")) {
                fail("World have already exists before test");
        }

        Input input = new Input();
        String wordParts[] = input.prepareWordToStoreInDB("предутренний", "пред утрен ний");
        db.insertWord(wordParts);
        boolean checkIsExists = db.checkIsWordExists("предутренний");
        db.deleteWordFromDB("пред", "утрен", "ний");
        if(!checkIsExists) {
            fail("Failed testInputWordInAFormat");
        }
    }
    //Реализовать запрос всей таблицы со словами из БД.
    @Test
    public void testDBSelect() {

        Database db = new Database();
        String[][] dictionary = db.selectAllWords();
        if(dictionary == null) {
            fail("Failed to testDBSelect ");
        }
    }
    //Проверить слово на его присутствие в БД
    //Реализовать конкатенацию строки из БД в одно слово
    @Test
    void testDBIsExistsAndConcat() {
        Database db = new Database();
        String inputedWord= "преддипломная\r\n" + "Y\r\n" + "пред диплом ная\r\n" + "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        Main.main(null);
        System.setIn(System.in);
        System.setOut(System.out);

        String output = out.toString();

        if(!output.contains("Should we save word in DB: press 'Y' for continue or 'q' for exit")) {
            fail("Failed testDBIsExistsAndConcat");
        }

        boolean isExists = db.checkIsWordExists("преддипломная");
        if(!isExists) {
            fail("Failed to check is word exists in DB");
        }

        db.deleteWordFromDB("пред", "диплом", "ная");
    }

    //Реализовать разделение данных из столбцов дефисом
    @Test
    public void testRootWordsOutputWithDash() {
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

        ResultSet rsRoot = db.getRootWords(wordOne[0] + wordOne[1] + wordOne[2]);
        String[] rootWords = db.returnPreparedRootWordsFromDB(rsRoot);
        String wordOneConcat =  wordOne[0] + "-" +  wordOne[1] + "-" +  wordOne[2];
        String wordTwoConcat =  "" + "-" +  wordTwo[1] + "-" +  wordTwo[2];


        db.deleteWordFromDB(wordOne[0], wordOne[1],wordOne[2]);
        db.deleteWordFromDB(wordTwo[0], wordTwo[1],wordTwo[2]);

        boolean firstWord = false;
        boolean secondWord = false;

        for(int i = 0; i < 100; ++i) {
            if(rootWords[i] == null) {
                break;
            }

            if((rootWords[i].equals(wordOneConcat) && !firstWord) ) {
                firstWord = true;
            }
            if((rootWords[i].equals(wordTwoConcat)) && !secondWord ) {
                secondWord = true;
            }

        }
        if( !firstWord ) {
            fail("Failed to test testRootWordsOutputWithDash");
        }
        if(!secondWord ) {
            fail("Failed to test testRootWordsOutputWithDash");
        }
    }

    //Реализовать сортировку полученных данных из БД (однокоренных слов) по количеству пустых столбцов (если в столбце null, значит у слова этой части нет)
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

        ResultSet rootWords = db.getRootWords(wordOne[0] + wordOne[1] + wordOne[2]);
        boolean firstWordFound = false;
        String wordDb = "";
        try {
            while (rootWords.next()) {
                wordDb = db.checkIsNullColumn(rootWords.getString("BeforeRoot")) + db.checkIsNullColumn(rootWords.getString("Root")) + db.checkIsNullColumn(rootWords.getString("AfterRoot"));
                if(wordDb.equals("сыпать") ) {
                    firstWordFound = true;
                    break;
                }
            }
            rootWords.next();
            wordDb = db.checkIsNullColumn(rootWords.getString("BeforeRoot")) + db.checkIsNullColumn(rootWords.getString("Root")) + db.checkIsNullColumn(rootWords.getString("AfterRoot"));
        }
        catch (SQLException exp3) {
            System.out.println("Error create connection. Error is " + exp3);
            return;
        }
        if(!wordDb.equals("отсыпал") || !firstWordFound  ) {
            fail("Fail in testDBOrderByNuLL2");
        }

        db.deleteWordFromDB(wordOne[0], wordOne[1],wordOne[2]);
        db.deleteWordFromDB(wordTwo[0], wordTwo[1],wordTwo[2]);
    }



}