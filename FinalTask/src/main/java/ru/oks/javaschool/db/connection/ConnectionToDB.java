package ru.oks.javaschool.db.connection;

import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Класс подключения к базе данных.
 */
public class ConnectionToDB {
    /**
     * DataSource.
     */
    private PGSimpleDataSource dataSource;

    /**
     * Параметры конфигурации из файла application.properties.
     */
    private Map<String, String> properties = new HashMap<>();

    /**
     * Консутркутор.
     *
     * @throws IOException ошибка инициализации
     */
    public ConnectionToDB() throws IOException {
        loadProperties();
    }

    /**
     * Метод загрузки настроек конфигурации в память.
     *
     * @throws IOException ошибка получения настроек
     */
    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                this.properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            System.out.println("Error occurred during loading properties");
            throw e;
        }
    }

    /**
     * Метод получения экземпляра DataSource'а.
     *
     * @return data source object
     */
    public PGSimpleDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new PGSimpleDataSource();
            //dataSource.setServerName(properties.get("ru.oks.javaschool.db.server"));
            dataSource.setUser(properties.get("db.user"));
            dataSource.setPassword(properties.get("db.password"));
            dataSource.setDatabaseName(properties.get("db.name"));
        }

        return dataSource;
    }
}
