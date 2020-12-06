package ru.oks.javaschool;

import ru.oks.javaschool.db.connection.ConnectionToDB;
import ru.oks.javaschool.db.entity.Transport;
import ru.oks.javaschool.db.service.TransportService;
import ru.oks.javaschool.file.FileToRead;
import ru.oks.javaschool.xml.converted.JacksonConverted;
import ru.oks.javaschool.xml.model.buses.Buses;
import ru.oks.javaschool.xml.model.trolleybuses.Trolleybuses;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Класс работы с данными о местоположении общественного транспорта.
 */
public class PublicTransport {
    /**
     * Конвертер.
     */
    private final JacksonConverted jacksonConverter;
    /**
     * Транспортный репозиторий.
     */
    private TransportService transportService;

    /**
     * Конструктор.
     */
    public PublicTransport() {
        this.jacksonConverter = new JacksonConverted();
        connectionDB();
    }

    /**
     * Конструктор c уже готовым TransportService.
     *
     * @param transportService TransportService.
     */
    public PublicTransport(TransportService transportService) {
        this.jacksonConverter = new JacksonConverted();
        this.transportService=transportService;
    }

    /**
     * Получение данных об автобусах из файла-xml.
     *
     * @param file файл.
     * @return данные об автобусах.
     */
    public Buses convertBuses(File file) {
        String xmlBuses = FileToRead.readingFromAFile(file);
        try {
            return jacksonConverter.fromXml(xmlBuses, Buses.class);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочесть файл "+file+"!");
        }
    }

    /**
     * Получение данных о троллейбусах из файла-xml.
     *
     * @param file файл.
     * @return данные о троллейбусах.
     */
    public Trolleybuses convertTrolleybuses(File file) {
        String xmlTrolleybus = FileToRead.readingFromAFile(file);
        try {
            return jacksonConverter.fromXml(xmlTrolleybus, Trolleybuses.class);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочесть файл "+file+"!");
        }
    }

    /**
     * Коннектимся к бд.
     */
    private void connectionDB() {
        try {
            ConnectionToDB connectionToDB = new ConnectionToDB();
            transportService = new TransportService(connectionToDB.getDataSource());
        } catch (SQLException throwables) {
            throw new RuntimeException("Не удалось подключиться к базе данных!");
        }
    }

    /**
     * Отправка данных, пришедших из файлов, в базу данных.
     *
     * @param fileBuses файл с данными о местоположении автобусов.
     * @param fileTrolleybuses файл с анными о местоположении троллейбусов.
     * @return 0, если данные отправлены; -1, если иначе
     */
    public int sendBusesAndTrolleybusesToDB(File fileBuses, File fileTrolleybuses) {
        return transportService.createNewFromListsBusAndTrolleybus(
                convertBuses(fileBuses).getBuses(),
                convertTrolleybuses(fileTrolleybuses).getTrolleybuses());

    }

    /**
     * Находим информацию о записи в бд с помощью типа, номера маршрута и времени.
     *
     * @param type   тип.
     * @param number номер маршурта.
     * @param time   момент времени.
     * @return запись о местоположении транспорта.
     */
    public Transport findTransportByTypeAndNumberAndTime(String type, String number, LocalDateTime time) {
        return transportService.findByTypeAndNumberAndTime(type, number, time);
    }
}
