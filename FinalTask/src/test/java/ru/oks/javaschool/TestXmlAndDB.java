package ru.oks.javaschool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Тестирование.
 */
public class TestXmlAndDB {
    /**
     * Переменная для тестирования методов.
     */
    public WorkXmlAndDB workXmlAndDB = new WorkXmlAndDB();

    /**
     * Тестирование десериализации авобусов.
     *
     * @throws IOException IOException
     */
    @Test
    public void testDeserializationBus() throws IOException {
        Assertions.assertEquals("Bus{number='31', " +
                        "time='27-11-2020 14:58:02', " +
                        "location='Location{x=15.9, y=2.4}'}",
                workXmlAndDB.convertBuses().getBuses().get(2).toString());
    }

    /**
     * Тестирование десериализации троллейбусов.
     *
     * @throws IOException IOException
     */
    @Test
    public void testDeserializationTrolleybus() throws IOException {
        Assertions.assertEquals("Trolleybus{number='12', " +
                        "time='27-11-2020 13:57:38', " +
                        "location='Location{x=12.3, y=6.3}'}",
                workXmlAndDB.convertTrolleybuses().getTrolleybuses().get(1).toString());
    }

    /**
     * Тестирование отправки данных в базу данных.
     *
     * @throws IOException IOException
     */
    @Test
    public void testSendToDB() throws IOException {
        workXmlAndDB.sendBuses();
        Assertions.assertEquals("26",
                workXmlAndDB.findTransportByTypeAndNumberAndTime("bus",
                        "26",
                        LocalDateTime.parse("2020-11-27T14:57:50")).getNumber());
        workXmlAndDB.sendTrolleybuses();
        Assertions.assertEquals("20д",
                workXmlAndDB.findTransportByTypeAndNumberAndTime("trolleybus", "20д",
                        LocalDateTime.parse("2020-11-27T13:57:40")).getNumber());
    }
}
