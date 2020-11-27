package ru.oks.javaschool.db.entity;

import ru.oks.javaschool.xml.model.Location;

import java.time.LocalDateTime;

/**
 * Класс сущности "transport" для отслеживания местоположения транспорта в момент времени.
 */
public class Transport {
    /**
     * Идентификатор записи.
     */
    private long id;
    /**
     * Тип транспорта.
     */
    private String type;
    /**
     * Номер маршрута.
     */
    private String number;
    /**
     * Момент времени.
     */
    private LocalDateTime time;
    /**
     * Местоположение.
     */
    private Location location;

    /**
     * Конструктор по умолчанию.
     */
    public Transport() {
    }

    /**
     * Конструктор.
     *
     * @param type тип транспорта.
     * @param number номер маршрута.
     * @param time момент времени.
     * @param location местоположение.
     */
    public Transport(String type, String number, LocalDateTime time, Location location) {
        this.type = type;
        this.number = number;
        this.time = time;
        this.location = location;
    }

    /**
     * Конструктор.
     *
     * @param id идентификатор.
     * @param type тип транспорта.
     * @param number номер маршрута.
     * @param time момент времени.
     * @param location местоположение.
     */
    public Transport(long id, String type, String number, LocalDateTime time, Location location) {
        this.id = id;
        this.type = type;
        this.number = number;
        this.time = time;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", time=" + time +
                ", location=" + location +
                '}';
    }
}