import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static ConnectionFactory factory = new ConnectionFactory();

    public static final String URL = "jdbc:mysql://remotemysql.com:3306/afjoA7w6GW?useLegacyDatetimeCode=false&serverTimezone=GMT%2B5";
    public static final String USER = "afjoA7w6GW";
    public static final String PASSWORD = "5Lk80EwrBL";
    public static final String TIMEZONE = "Asia";

    private ConnectionFactory() {
        try {

            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static Connection getConnection(){
        return factory.createConnection();
    }
}
