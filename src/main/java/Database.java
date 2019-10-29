import java.sql.*;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class Database {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/dictionary";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "postgres";

    static Statement stmt = null;
    public Database() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
        }
        catch (ClassNotFoundException exp) {
            System.out.println("Error create connection. Error is " + exp);
        }
        catch (SQLException exp2) {
            System.out.println("Error create connection. Error is " + exp2);
        }
    }
    private Connection dbConnection = null;
    public Connection getConnection() {
        return dbConnection;
    }

    public boolean checkIsWordExists(String word) {
        try {
            stmt = dbConnection.createStatement();
            String sql = "SELECT BeforeRoot,Root,AfterRoot FROM Dictionary";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //String wordDb = rs.getString("BeforeRoot") + rs.getString("Root") + rs.getString("AfterRoot");
                String wordDb = concatRecordFromDBToWord(rs);
                if(wordDb.equals(word)) {
                    System.out.println("Find record in DB");
                    //break;
                    return true;
                }
            }
        }
        catch (SQLException exp2) {
            System.out.println("Error create connection. Error is " + exp2);
        }
        return false;
    }
    String checkIsNullColumn(String column) {
        if(column == null ) return "";
        if(!column.equals("null")) return column;
        else return null;
    }

    void insertWord(String[] wordParts) {
        String sql = "INSERT INTO Dictionary (BeforeRoot,Root,AfterRoot) VALUES (" + checkIsNullBeforeInsert(wordParts[0].replaceAll("\\s+",""))+ "," +
                checkIsNullBeforeInsert(wordParts[1].replaceAll("\\s+","")) + "," + checkIsNullBeforeInsert(wordParts[2].replaceAll("\\s+","")) + ");";
        try {
            stmt = dbConnection.createStatement();
            stmt.executeUpdate(sql);
        }
        catch (SQLException exp3) {
            System.out.println("Error create connection. Error is " + exp3);
            return;
        }

    }
    String checkIsNullBeforeInsert(String wordPart) {
        if(wordPart.equals("null")) return null;
        else {
            return "'" + wordPart + "'";
        }
    }
    String checkIsNullBeforeDelete(String wordPart) {
        if(wordPart.equals("null")) return "is" + "null";
        else {
            return "=" + "'" + wordPart + "'";
        }
    }
    ResultSet getRootWords(String word) {
        //String sql = "SELECT BeforeRoot,Root,AfterRoot FROM Dictionary ORDER BY BeforeRoot IS NULL DESC, AfterRoot IS NULL DESC;";
        String sql = "SELECT BeforeRoot,Root,AfterRoot FROM Dictionary;";
        String rootOfWord = null;
        ResultSet rsRoot = null;
        try {
            stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String wordDb = concatRecordFromDBToWord(rs);
                if (wordDb.equals(word)) {
                    rootOfWord = rs.getString("Root");
                }
            }
            //String sqlRootWords = "SELECT BeforeRoot,Root,AfterRoot FROM Dictionary WHERE Root =" + "'" + rootOfWord + "'" + ';' ;
            String sqlRootWords = "SELECT BeforeRoot,Root,AfterRoot FROM Dictionary WHERE Root =" + "'" + rootOfWord + "'" + "ORDER BY BeforeRoot IS NULL DESC, AfterRoot IS NULL DESC;";
            stmt = dbConnection.createStatement();
            rsRoot = stmt.executeQuery(sqlRootWords);

        }
        catch (SQLException exp3) {
            System.out.println("Error create connection. Error is " + exp3);
            return null;
        }

        return rsRoot;
    }

    String[] returnPreparedRootWordsFromDB(ResultSet rsRoot) {
        String[] rootWord = new String[100]; // TODO: Fix constant size of array
        int wordIndex = 0;
        String wordDb;
        try {
            while (rsRoot.next()) {
                wordDb = checkIsNullColumn(rsRoot.getString("BeforeRoot")) + '-' + checkIsNullColumn(rsRoot.getString("Root")) + '-' + checkIsNullColumn(rsRoot.getString("AfterRoot"));
                rootWord[wordIndex] = wordDb;
                wordIndex++;
            }
        }
        catch (SQLException exp3) {
            System.out.println("Error create connection. Error is " + exp3);
            return null;
        }
        Set<String> temp = new LinkedHashSet<String>( Arrays.asList( rootWord ) ); // to make array of unique words
        rootWord = temp.toArray( new String[temp.size()] );
        return  rootWord;
    }

    void deleteWordFromDB(String before, String root, String after) {
        Connection ourConnection = getConnection();
        try {
            Statement stmt = ourConnection.createStatement();
            String sql = "DELETE FROM Dictionary WHERE beforeroot " +  checkIsNullBeforeDelete(before) + " AND " + "Root" + checkIsNullBeforeDelete(root) + " AND" + " AfterRoot" +  checkIsNullBeforeDelete(after) + ";" ;
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            System.out.println("We have SQLException " + e);
        }
    }

    public String[][] selectAllWords() {
        String selectQuery = "SELECT * FROM Dictionary;";
        Database db = new Database();
        Connection ourConnection = db.getConnection();
        Statement stmt;
        ResultSet rs;
        String[][] dictionary = null;
        try {
            stmt = ourConnection.createStatement();
            rs = stmt.executeQuery(selectQuery);
            dictionary = new String[100][3];
        int i = 0;
        while (rs.next()) {
            dictionary[i][0] = checkIsNullColumn(rs.getString("BeforeRoot"));
            dictionary[i][1] = checkIsNullColumn(rs.getString("Root"));
            dictionary[i][2] = checkIsNullColumn(rs.getString("AfterRoot"));
            i++;
                 }
        }
        catch (SQLException exp) {
            System.out.println("We have SQLException " + exp);
        }
        return  dictionary;
    }
    public void updateWord(String[] oldWord, String[] newWord) {
        Database db = new Database();
        Connection ourConnection = db.getConnection();
        try {
            Statement stmt  = ourConnection.createStatement();
            String sql = "UPDATE Dictionary SET beforeroot ='"+ newWord[0] + "' , root='" + newWord[1] + "' , afterroot='" + newWord[2] + "' WHERE beforeroot ='" + oldWord[0] + "' AND Root ='" + oldWord[1] + "' AND AfterRoot ='" + oldWord[2] + "';";
            stmt.executeUpdate(sql);
        }
        catch (SQLException exp) {
            System.out.println("We have SQLException " + exp);
        }
    }
    public String concatRecordFromDBToWord(ResultSet rs) {
        String wordDb = "";
        try {
            wordDb = checkIsNullColumn(rs.getString("BeforeRoot")) + checkIsNullColumn(rs.getString("Root")) + checkIsNullColumn(rs.getString("AfterRoot"));
        }
        catch (SQLException exp) {
            System.out.println("We have SQLException " + exp);
        }
        return wordDb;
    }
}
