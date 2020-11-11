package connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Класс подключения к базе данных.
 */
public class ConnectionToDB {
    /**
     * Получение соединения с базой данных.
     *
     * @return подключение.
     */
    public static Connection getDBConnection() {
        Connection dbConnection = null;
        try(InputStream input = new FileInputStream("src/main/resources/application.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            try {
                Class.forName(prop.getProperty("db.driver"));
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
            try {
                dbConnection = DriverManager.getConnection(prop.getProperty("db.server"),
                        prop.getProperty("db.user"), prop.getProperty("db.password"));
                return dbConnection;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}
