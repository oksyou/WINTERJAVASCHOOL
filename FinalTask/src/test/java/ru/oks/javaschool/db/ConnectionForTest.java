package ru.oks.javaschool.db;

import org.postgresql.ds.PGSimpleDataSource;

/**
 * Класс подключения к базе данных PostgreSQL для тестов.
 */
public class ConnectionForTest {
    /**
     * DataSource.
     */
    private PGSimpleDataSource dataSource;
    private final static String USER="root";
    private final static String PASSWORD="123";
    private final static String DATABASE_NAME="FinalTask";
    /**
     * Метод получения экземпляра DataSource'а.
     *
     * @return data source object
     */
    public PGSimpleDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new PGSimpleDataSource();
            dataSource.setUser(USER);
            dataSource.setPassword(PASSWORD);
            dataSource.setDatabaseName(DATABASE_NAME);
        }
        return dataSource;
    }
}
