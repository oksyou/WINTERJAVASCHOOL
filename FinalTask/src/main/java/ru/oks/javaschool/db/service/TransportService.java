package ru.oks.javaschool.db.service;

import org.postgresql.ds.PGSimpleDataSource;
import ru.oks.javaschool.db.entity.Transport;
import ru.oks.javaschool.db.repository.TransportRepository;
import ru.oks.javaschool.xml.model.Location;
import ru.oks.javaschool.xml.model.buses.Bus;
import ru.oks.javaschool.xml.model.trolleybuses.Trolleybus;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с информацией о местоположении общественного транспорта.
 */
public class TransportService implements TransportRepository {
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
    public TransportService(PGSimpleDataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Метод инициализации таблицы.
     */
    @Override
    public void initTable() throws SQLException {
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
            throw new SQLException("Таблица не создалась:(");
        }
    }

    /**
     * Удаление таблицы.
     */
    @Override
    public void dropTable() throws SQLException {
        String dropTableSQL = "DROP TABLE IF EXISTS "+TABLE_NAME;

        try (Connection dbConnection = dataSource.getConnection();
             Statement statement = dbConnection.createStatement()) {
            statement.execute(dropTableSQL);
        } catch (SQLException e) {
            throw new SQLException("Таблица не удалилась:(");
        }
    }

    /**
     * Поиск всех записей о местоположении транспрта.
     *
     * @return список всех записей.
     */
    @Override
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
     *
     * @param id идентификатор.
     * @return запись о местоположении транспорта.
     */
    @Override
    public Transport find(long id) {
        String getSTransport=
                "SELECT id, type, number, time, x, y " +
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
    @Override
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
     * @param transport транспорт.
     * @return 0, если создана; -1, если ошибка
     */
    public int createNew(Transport transport) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + "(type, number, time, x, y) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, transport.getType());
            statement.setString(2, transport.getNumber());
            statement.setTimestamp(3, Timestamp.valueOf(transport.getTime()));
            statement.setDouble(4, transport.getLocation().getX());
            statement.setDouble(5, transport.getLocation().getY());
            statement.execute();
            return 0;
        } catch (SQLException throwables) {
            return -1;
        }
    }

    /**
     * Создание в базе всех автобусов и троллейбусов, переданных в параметрах.
     *
     * @param buses автобусы.
     * @param trolleybuses троллейбусы.
     * @return 0, если созданы; -1, если не созданы
     */
    public int createNewFromListsBusAndTrolleybus(List<Bus> buses, List<Trolleybus> trolleybuses){
        String sqlQuery = "INSERT INTO " + TABLE_NAME + "(type, number, time, x, y) VALUES (?, ?, ?, ?, ?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            for(Bus bus:buses){
                statement.setString(1, "bus");
                statement.setString(2, bus.getNumber());
                statement.setTimestamp(3, Formatter.StringToTimestamp(bus.getTime()));
                statement.setDouble(4, bus.getLocation().getX());
                statement.setDouble(5, bus.getLocation().getY());
                statement.execute();
            }
            for(Trolleybus trolleybus:trolleybuses){
                statement.setString(1, "trolleybus");
                statement.setString(2, trolleybus.getNumber());
                statement.setTimestamp(3, Formatter.StringToTimestamp(trolleybus.getTime()));
                statement.setDouble(4, trolleybus.getLocation().getX());
                statement.setDouble(5, trolleybus.getLocation().getY());
                statement.execute();
            }
            return 0;
        } catch (SQLException throwables) {
            return -1;
        }
    }

    /**
     * Обновление записи о местоположении транспорта.
     *
     * @param id идентификатор.
     * @param transport транспорт.
     * @return 0, если запись обновлена успешно; -1 иначе
     */
    @Override
    public int update(long id, Transport transport) {
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
            return 0;
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Удаление записи о местоположении транспорта.
     *
     * @param id идентификатор.
     * @return  0, если запись удалена успешно; -1 иначе
     */
    @Override
    public int delete(int id) {
        String deleteStudent =
                "DELETE FROM "+TABLE_NAME+" WHERE id="+id;
        try (Connection dbConnection = dataSource.getConnection();
             Statement statement = dbConnection.createStatement();
             ResultSet rs=statement.executeQuery(deleteStudent)) {
            return 0;
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            return -1;
        }
    }

}
