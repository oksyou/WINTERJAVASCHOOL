package ru.oks.javaschool.xml.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Класс автобуса.
 */
@JacksonXmlRootElement(localName = "bus")
public class Bus extends Transport{
    /**
     * Конструктор.
     */
    public Bus() { super();
    }

    /**
     * Конструктор.
     *
     * @param number номер маршрута.
     * @param time момент времени.
     * @param location местоположение.
     */
    public Bus(String number, String time, Location location) {
        super(number, time, location);
    }

    @Override
    public String toString() {
        return "Bus{"+super.toString()+"}";
    }
}
