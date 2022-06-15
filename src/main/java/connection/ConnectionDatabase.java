package connection;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test";
    private static final String JDBC_USERNAME = "tux";
    private static final String JDBC_PASSWORD = "tux";
    private static final int MAX_CONNECTIONS = 5;

    private static Connection connection;


    public static DataSource getDataSource() {
        BasicDataSource pool = new BasicDataSource();
        pool.setUrl(ConnectionDatabase.JDBC_URL);
        pool.setUsername(ConnectionDatabase.JDBC_USERNAME);
        pool.setPassword(ConnectionDatabase.JDBC_PASSWORD);
        pool.setInitialSize(ConnectionDatabase.MAX_CONNECTIONS);
        return pool;
    }
    public static Connection getConnect() throws SQLException{
        return ConnectionDatabase.getDataSource().getConnection();
    }
}
