package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test";
    private static final String JDBC_USERNAME = "tux";
    private static final String JDBC_PASSWORD = "tux";

    private static Connection connection;

    public static Connection createConnection() throws SQLException {
        if( connection == null ) {
            return DriverManager.getConnection(
                    JDBC_URL,
                    JDBC_USERNAME,
                    JDBC_PASSWORD
            );
        }
        return connection;
    }
}
