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
            Class.forName("org.postgresql.Driver");

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
                String wordDb = checkIsNullColumn(rs.getString("BeforeRoot") ) + checkIsNullColumn(rs.getString("Root")) + checkIsNullColumn(rs.getString("AfterRoot"));
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
        else return "";
    }

    void insertWord(String[] wordParts) {
        String sql = "INSERT INTO Dictionary (BeforeRoot,Root,AfterRoot) VALUES (";
        sql+="'";
        String temp = wordParts[0].replaceAll("\\s+","");
        sql+=temp;
        sql+="'";
        sql+=",";
        sql+="'";
        sql+=wordParts[1].replaceAll("\\s+","");
        sql+="'";
        sql+=",";
        sql+="'";
        sql+=wordParts[2].replaceAll("\\s+","");
        sql+="'";
        sql+=")";
        sql+=";";
        try {
            stmt = dbConnection.createStatement();
            stmt.executeUpdate(sql);
        }
        catch (SQLException exp3) {
            System.out.println("Error create connection. Error is " + exp3);
            return;
        }

    }
    String[] getRootWords(String word) {
        //String sql = "SELECT BeforeRoot,Root,AfterRoot FROM Dictionary ORDER BY BeforeRoot IS NULL DESC, AfterRoot IS NULL DESC;";
        String sql = "SELECT BeforeRoot,Root,AfterRoot FROM Dictionary;";
        String rootOfWord = null;
        String[] rootWord = new String[100]; // TODO: Fix constant size of array
        try {
            stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String wordDb = checkIsNullColumn(rs.getString("BeforeRoot")) + checkIsNullColumn(rs.getString("Root")) + checkIsNullColumn(rs.getString("AfterRoot"));
                if (wordDb.equals(word)) {
                    rootOfWord = rs.getString("Root");
                }
            }
            //String sqlRootWords = "SELECT BeforeRoot,Root,AfterRoot FROM Dictionary WHERE Root =" + "'" + rootOfWord + "'" + ';' ;
            String sqlRootWords = "SELECT BeforeRoot,Root,AfterRoot FROM Dictionary WHERE Root =" + "'" + rootOfWord + "'" + "ORDER BY BeforeRoot IS NULL DESC, AfterRoot IS NULL DESC;";
            stmt = dbConnection.createStatement();
            ResultSet rsRoot = stmt.executeQuery(sqlRootWords);
            int wordIndex = 0;
            String wordDb = null;
            while (rsRoot.next()) {
                if(!(checkIsNullColumn(rsRoot.getString("BeforeRoot")) + checkIsNullColumn(rsRoot.getString("Root")) + checkIsNullColumn(rsRoot.getString("AfterRoot"))).equals(word))
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
        return rootWord;
    }
}
