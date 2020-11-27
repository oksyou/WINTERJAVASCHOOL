package ru.oks.javaschool;

import ru.oks.javaschool.db.connection.ConnectionToDB;
import ru.oks.javaschool.xml.converted.JacksonConverted;
import ru.oks.javaschool.db.entity.Transport;
import ru.oks.javaschool.xml.model.*;
import ru.oks.javaschool.db.repository.TransportRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс работы с данными о местоположении общественного транспорта.
 */
public class WorkXmlAndDB {
    /**
     * Конвертер.
     */
    private JacksonConverted jacksonConverter;
    /**
     * Транспортный репозиторий.
     */
    private TransportRepository transportRepository;

    /**
     * Конструктор.
     */
    public WorkXmlAndDB() {
        this.jacksonConverter =new JacksonConverted();
        connectionBD();
    }

    /**
     * Получение данных об автобусах из xml.
     *
     * @return данные об автобусах.
     * @throws IOException IOException
     */
    public Buses convertBuses() throws IOException {
        Path pathBus = Paths.get("src/main/resources", "buses.xml");
        String xmlBuses = new String(Files.readAllBytes(pathBus));
        return jacksonConverter.fromXml(xmlBuses, Buses.class);
    }

    /**
     * Получение данных о троллейбусах из xml.
     *
     * @return данные о троллейбусах.
     * @throws IOException IOException
     */
    public Trolleybuses convertTrolleybuses() throws IOException {
        Path pathTrolleybus = Paths.get("src/main/resources", "trolleybuses.xml");
        String xmlTrolleybus = new String(Files.readAllBytes(pathTrolleybus));
        return jacksonConverter.fromXml(xmlTrolleybus, Trolleybuses.class);
    }

    /**
     * Коннектимся к бд.
     */
    private void connectionBD(){
        ConnectionToDB connectionToDB = null;
        try {
            connectionToDB = new ConnectionToDB();
        } catch (IOException e) {
            System.out.println("Ошибка создания провайдера: " + e.getMessage());
        }

        transportRepository = new TransportRepository(connectionToDB.getDataSource());
        //КАЖДЫЙ РАЗ СОЗДАЕМ НОВУЮ ЧИСТУЮ ТАБЛИЦУ ДЛЯ ТЕСТОВ
        transportRepository.dropTable();
        transportRepository.initTable();
    }

    /**
     * Отправляем в бд данные об автобусах.
     *
     * @throws IOException IOException
     */
    public void sendBuses() throws IOException {
        for (Bus bus: convertBuses().getBuses()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            transportRepository.createNew(new Transport("bus",
                    bus.getNumber(),
                    LocalDateTime.parse(bus.getTime(), formatter),
                    bus.getLocation()));
        }
    }

    /**
     * Отправляем в бд данные о троллейбусах.
     *
     * @throws IOException IOException
     */
    public void sendTrolleybuses() throws IOException {
        for (Trolleybus trolleybus:convertTrolleybuses().getTrolleybuses()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            transportRepository.createNew(new Transport("trolleybus",
                    trolleybus.getNumber(),
                    LocalDateTime.parse(trolleybus.getTime(), formatter),
                    trolleybus.getLocation()));
        }
    }

    /**
     * Находим информацию о записи в бд с помощью типа, номера маршрута и времени.
     *
     * @param type тип.
     * @param number номер маршурта.
     * @param time момент времени.
     * @returnзапись о местоположении транспорта.
     */
    public Transport findTransportByTypeAndNumberAndTime(String type, String number, LocalDateTime time){
        return transportRepository.findByTypeAndNumberAndTime(type, number, time);
    }
}
