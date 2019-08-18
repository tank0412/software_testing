import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {
    ResultSet  rs = null;
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

}