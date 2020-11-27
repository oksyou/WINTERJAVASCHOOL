package ru.oks.javaschool.db.repository;

import ru.oks.javaschool.db.entity.Transport;
import ru.oks.javaschool.xml.model.Location;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Репозиторий для сущности "transport".
 */
public class TransportRepository {
    /**
     * Имя таблицы.
     */
    private static final String TABLE_NAME = "transport";
    /**
     * DataSource.
     */
    private PGSimpleDataSource dataSource;

    /**
     * Конструктор.
     *
     * @param dataSource DataSource.
     */
    public TransportRepository(PGSimpleDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Метод инициализации таблицы.
     */
    public void initTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(id SERIAL NOT NULL PRIMARY KEY," +
                "type varchar NOT NULL," +
                "number varchar NOT NULL,"+
                "time timestamp without time zone," +
                "x double precision," +
                "y double precision" +
                ")";

        try (Connection dbConnection = dataSource.getConnection();
             Statement statement = dbConnection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Удаление таблицы.
     */
    public void dropTable() {
        String dropTableSQL = "DROP TABLE IF EXISTS "+TABLE_NAME;

        try (Connection dbConnection = dataSource.getConnection();
             Statement statement = dbConnection.createStatement()) {
            statement.execute(dropTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * МПоиск всех записей о местоположении транспрта.
     *
     * @return список всех записей.
     */
    public List<Transport> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME);
            List<Transport> taskList = new ArrayList<>();
            while (resultSet.next()) {
                taskList.add(new Transport(resultSet.getLong("id"),
                        resultSet.getString("type"),
                        resultSet.getString("number"),
                        resultSet.getTimestamp("time").toLocalDateTime(),
                        new Location(resultSet.getDouble("x"), resultSet.getDouble("y"))));
            }
            return taskList;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Поиск записи по идентификатору.
     * @param id идентификатор.
     * @return запись о местоположении транспорта.
     */
    public Transport find(long id) {
        String getSTransport=
                "SELECT type, number, time, x, y " +
                        "FROM "+TABLE_NAME+" WHERE id="+id;

        Transport transport=null;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getSTransport);
            while ( resultSet.next() ) {
                transport=new Transport(resultSet.getLong("id"),
                        resultSet.getString("type"),
                        resultSet.getString("number"),
                        resultSet.getTimestamp("time").toLocalDateTime(),
                        new Location(resultSet.getDouble("x"), resultSet.getDouble("y")));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return transport;
    }

    /**
     * Нахождение записи по типу транспорта, номеру маршрута и моменту времени.
     *
     * @param type тип.
     * @param number номер маршрута.
     * @param time момент времени.
     * @return запись о местоположении транспорта
     */
    public Transport findByTypeAndNumberAndTime(String type, String number, LocalDateTime time) {
        String getSTransport=
                "SELECT id, type, number, time, x, y " +
                        "FROM "+TABLE_NAME+
                        " WHERE type=\'"+type+"\' " +
                        "and number=\'"+number+"\' " +
                        "and time=\'"+Timestamp.valueOf(time)+"\'";

        Transport transport=null;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getSTransport);
            while ( resultSet.next() ) {
                transport=new Transport(resultSet.getLong("id"),
                        resultSet.getString("type"),
                        resultSet.getString("number"),
                        resultSet.getTimestamp("time").toLocalDateTime(),
                        new Location(resultSet.getDouble("x"), resultSet.getDouble("y")));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return transport;
    }

    /**
     * Метод создания новой записи о местоположении траспорта.
     *
     * @param transport транспорт
     */
    public void createNew(Transport transport) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + "(type, number, time, x, y) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, transport.getType());
            statement.setString(2, transport.getNumber());
            statement.setTimestamp(3, Timestamp.valueOf(transport.getTime()));
            statement.setDouble(4, transport.getLocation().getX());
            statement.setDouble(5, transport.getLocation().getY());
            statement.execute();
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Обновление записи о местоположении транспорта.
     *
     * @param id идентификатор.
     * @param transport транспорт.
     */
    public void update(long id, Transport transport) {
        String setStudent =
                "UPDATE "+TABLE_NAME+" SET type=?, number=?, time=?, x=?, y=? " +
                        "WHERE id=?";
        try (Connection dbConnection = dataSource.getConnection();
             PreparedStatement st = dbConnection.prepareStatement(setStudent)) {
            st.setString(1, transport.getType());
            st.setString(2, transport.getNumber());
            st.setTimestamp(3, Timestamp.valueOf(transport.getTime()));
            st.setDouble(4, transport.getLocation().getX());
            st.setDouble(5, transport.getLocation().getY());
            st.setLong(6, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    /**
     * Удаление записи о местоположении транспорта.
     *
     * @param id идентификатор.
     */
    public void delete(int id) {
        String deleteStudent =
                "DELETE FROM "+TABLE_NAME+" WHERE id="+id;
        try (Connection dbConnection = dataSource.getConnection();
             Statement statement = dbConnection.createStatement();
             ResultSet rs=statement.executeQuery(deleteStudent)) {
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }
}
