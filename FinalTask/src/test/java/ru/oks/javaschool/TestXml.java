package ru.oks.javaschool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * Тестирование десериализации xml-файла.
 */
public class TestXml {
    /**
     * Файл с информацией о местоположении автобусов.
     */
    private final static File BUSES = new File("src/test/resources/buses.xml");
    /**
     * Файл с информацией о местопложении троллейбусов.
     */
    private final static File TROLLEYBUSES = new File("src/test/resources/trolleybuses.xml");
    /**
     * Переменная класса для работы с десериализацией данных о местоположении транспорта.
     */
    public PublicTransport publicTransport = new PublicTransport();

    /**
     * Тестирование десериализации информации о местоположении автобусов.
     */
    @Test
    public void testDeserializingBusLocationInfo() {

        Assertions.assertEquals("Bus{number='31', " +
                        "time='27-11-2020 14:58:02', " +
                        "location='Location{x=15.9, y=2.4}'}",
                publicTransport.convertBuses(BUSES).getBuses().get(2).toString());
    }

    /**
     * Тестирование десериализации информации о местоположении троллейбусов.
     */
    @Test
    public void testDeserializingTrolleybusLocationInfo() {

        Assertions.assertEquals("Trolleybus{number='12', " +
                        "time='27-11-2020 13:57:38', " +
                        "location='Location{x=12.3, y=6.3}'}",
                publicTransport.convertTrolleybuses(TROLLEYBUSES).getTrolleybuses().get(1).toString());
    }
}
