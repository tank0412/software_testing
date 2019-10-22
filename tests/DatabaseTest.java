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
        Connection ourConnection = db.getConnection();
        try {
        Statement stmt  = ourConnection.createStatement();
        String sql = "INSERT INTO Dictionary (BeforeRoot,Root,AfterRoot) " +
                "VALUES (null, 'лес', 'ной');";
        stmt.executeUpdate(sql);
        testDBSelect();
        while (rs.next()) {
             String word = rs.getString("BeforeRoot") + rs.getString("Root") + rs.getString("AfterRoot");
             if(word == "лесной") {
                 System.out.println("Test passed");
                 break;
             }
            }
        }
        catch (SQLException exp) {
            fail("We have SQLException " + exp);
        }
    }
    //реализовать обновление сохраненного слова в БД, если пользователь сохранил слово с опечаткой

    @Test
    public void testDBUpdate() {
        Database db = new Database();
        Connection ourConnection = db.getConnection();
        try {
            Statement stmt  = ourConnection.createStatement();
            String sql = "UPDATE Dictionary SET AfterRoot ='ные' WHERE Root = 'лес' AND AfterRoot ='ной';";
            stmt.executeUpdate(sql);

            /*
            while (rs.next()) {
                String word = rs.getString("BeforeRoot") + rs.getString("Root") + rs.getString("AfterRoot");
                if(word == "лесной") {
                    System.out.println("Test passed");
                    break;
                }
            }
            */
            testDBSelect();
            while (rs.next()) {
                String word = db.checkIsNullColumn(rs.getString("BeforeRoot")) + db.checkIsNullColumn(rs.getString("Root")) + db.checkIsNullColumn(rs.getString("AfterRoot"));
                if(word.equals("лесные")) {
                    System.out.println("Test passed");
                    return;
                    //break;
                }
            }
            fail("Update  record failed;");
        }
        catch (SQLException exp) {
            fail("We have SQLException " + exp);
        }

    }
    //Реализовать вывод полученного списка слов из БД по одинаковому корню.

    @Test
    public void testDBSelectRootWords() {
        Database db = new Database();
        Connection ourConnection = db.getConnection();
        try {
            Statement stmt  = ourConnection.createStatement();
            String sqlRootWords = "SELECT BeforeRoot,Root,AfterRoot FROM Dictionary WHERE Root =" + "'" + "лес" + "'" + "ORDER BY BeforeRoot IS NULL DESC, AfterRoot IS NULL DESC;";
            ResultSet rootResults = stmt.executeQuery(sqlRootWords);
            rootResults.next();
                String word = db.checkIsNullColumn(rootResults.getString("BeforeRoot")) + db.checkIsNullColumn(rootResults.getString("Root")) + db.checkIsNullColumn(rootResults.getString("AfterRoot"));
                if(word == "лес") {
                    System.out.println("Test passed");
                }

        }
        catch (SQLException exp) {
            fail("We have SQLException " + exp);
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



}