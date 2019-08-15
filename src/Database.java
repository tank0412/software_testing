import java.sql.Connection;

public class Database {
    private Connection dbConnection = null;
    public Connection getConnection() {
        return dbConnection;
    }
}
