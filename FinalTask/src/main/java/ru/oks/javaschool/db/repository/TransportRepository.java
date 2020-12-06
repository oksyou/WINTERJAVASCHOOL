package ru.oks.javaschool.db.repository;

import ru.oks.javaschool.db.entity.Transport;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Репозиторий для работы с данными таблицы "transport".
 */
public interface TransportRepository {
    /**
     * Метод инициализации таблицы.
     */
    void initTable() throws SQLException;

    /**
     * Удаление таблицы.
     */
    void dropTable() throws SQLException;

    /**
     * Поиск всех записей о местоположении транспрта.
     *
     * @return список всех записей.
     */
    List<Transport> findAll();

    /**
     * Поиск записи по идентификатору.
     *
     * @param id идентификатор.
     * @return запись о местоположении транспорта.
     */
    Transport find(long id);

    /**
     * Нахождение записи по типу транспорта, номеру маршрута и моменту времени.
     *
     * @param type   тип.
     * @param number номер маршрута.
     * @param time   момент времени.
     * @return запись о местоположении транспорта
     */
    Transport findByTypeAndNumberAndTime(String type, String number, LocalDateTime time);

    /**
     * Метод создания новой записи о местоположении траспорта.
     *
     * @param transport транспорт.
     * @return 0, если создана; -1, если ошибка
     */
    int createNew(Transport transport);

    /**
     * Обновление записи о местоположении транспорта.
     *
     * @param id идентификатор.
     * @param transport транспорт.
     * @return 0, если запись обновлена успешно; -1 иначе
     */
    int update(long id, Transport transport);

    /**
     * Удаление записи о местоположении транспорта.
     *
     * @param id идентификатор.
     * @return 0, если запись удалена успешно; -1 иначе
     */
    int delete(int id);
}
