package fa.training.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {
    public static Connection getConnection() {
        try {
            //2 Load driver JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //3. DriverManager => Connection (host. port. username / password)
            String url = "jdbc:sqlserver://localhost:1433;databaseName=SMS;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "1";
            Connection conn = DriverManager.getConnection(url, username, password);

            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
