package ru.oks.javaschool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.oks.javaschool.db.ConnectionForTest;
import ru.oks.javaschool.db.service.TransportService;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * Тестирование отправки информации о местоположении общественного транспорта в бд.
 */
public class TestPublicTransport {
    /**
     * Файл с информацией о местоположении автобусов.
     */
    private final static File BUSES = new File("src/test/resources/buses.xml");
    /**
     * Файл с информацией о местопложении троллейбусов.
     */
    private final static File TROLLEYBUSES = new File("src/test/resources/trolleybuses.xml");
    /**
     * Объект сервиса для работы с информацией о местоположении общественного транспорта.
     */
    private TransportService transportService;
    /**
     * Переменная, сообщающая о том, произошло ли подключение к базе данных или нет.
     */
    private boolean errorConnectingToDataBase;
    /**
     * Переменная класса для работы с десериализацией и бд.
     */
    public PublicTransport publicTransport = new PublicTransport(connectToTestDB());

    /**
     * Подключение к тестовой базе данных и создание TransportService.
     *
     * @return созданный TransportService.
     */
    public TransportService connectToTestDB() {
        try {
            ConnectionForTest connectionToDB = new ConnectionForTest();
            transportService = new TransportService(connectionToDB.getDataSource());
        } catch (SQLException throwables) {
            errorConnectingToDataBase = true;
        }
        return transportService;
    }

    /**
     * Тестирование отправки данных о местоположении транспорта в базу данных.
     */
    @Test
    public void testSendInfoAboutLocationTransportToDB() {
        if (errorConnectingToDataBase) {
            throw new RuntimeException("Не удалось подключиться к тестовой базе данных!");
        } else {
            Assertions.assertEquals(0, publicTransport.sendBusesAndTrolleybusesToDB(BUSES, TROLLEYBUSES));

            Assertions.assertEquals("26",
                    publicTransport.findTransportByTypeAndNumberAndTime("bus",
                            "26",
                            LocalDateTime.parse("2020-11-27T14:57:50")).getNumber());
            Assertions.assertEquals("20д",
                    publicTransport.findTransportByTypeAndNumberAndTime("trolleybus",
                            "20д",
                            LocalDateTime.parse("2020-11-27T13:57:40")).getNumber());
        }
    }
}
