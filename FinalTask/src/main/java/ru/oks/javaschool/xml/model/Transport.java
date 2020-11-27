package ru.oks.javaschool.xml.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Абстрактный класс общественного транспорта.
 */
public abstract class Transport {
    /**
     * Номер маршрута.
     */
    @JacksonXmlProperty(localName = "number", isAttribute = true)
    private String number;
    /**
     * Момент времени.
     */
    @JacksonXmlProperty(localName = "time")
    private String time;
    /**
     * Местоположение.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "location")
    private Location location;

    /**
     * Конструктор.
     */
    public Transport() {
    }

    /**
     * Конструктор.
     *
     * @param number номер маршрута.
     * @param time момент времени.
     * @param location местоположение.
     */
    public Transport(String number, String time, Location location) {
        this.number = number;
        this.time = time;
        this.location = location;
    }

    public String getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "number='" + number + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'';
    }
}
