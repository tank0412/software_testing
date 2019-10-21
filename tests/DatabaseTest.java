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
        String selectQuery = "SELECT * FROM Dictionary;";
        Database db = new Database();
        Connection ourConnection = db.getConnection();
        Statement stmt = null;
        try {
            stmt = ourConnection.createStatement();
            rs = stmt.executeQuery(selectQuery);
        }
        catch (SQLException exp) {
            fail("We have SQLException " + exp);
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
        String inputedWord= "предутренний\r\n" + "пред утрен ний\r\n" + "q";
        InputStream in = new ByteArrayInputStream(inputedWord.getBytes());
        System.setIn(in);
        Main.main(null);
        System.setIn(System.in);
        testDBSelect();
        try {
        while (rs.next()) {
            String word = rs.getString("BeforeRoot") + rs.getString("Root") + rs.getString("AfterRoot");
            if(word.equals("предутренний")) {
                System.out.println("Test passed");
                break;
            }
        }
    }
        catch (SQLException e) {
            fail("We have SQLException " + e);
    }
        deleteWordFromDB("пред", "утрен", "ний");

    }
    void deleteWordFromDB(String before, String root, String after) {
        Database db = new Database();
        Connection ourConnection = db.getConnection();
        try {
            Statement stmt = ourConnection.createStatement();
            String sql = "DELETE FROM Dictionary WHERE beforeroot =" +  "'" + before + "'" + " AND " + "Root=" + "'" + root + "'" + " AND" + " AfterRoot=" +  "'" + after + "'" + ";" ;
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            fail("We have SQLException " + e);
        }
    }



}