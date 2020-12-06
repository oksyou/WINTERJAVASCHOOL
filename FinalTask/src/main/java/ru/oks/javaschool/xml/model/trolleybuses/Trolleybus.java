package ru.oks.javaschool.xml.model.trolleybuses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ru.oks.javaschool.xml.model.Location;
import ru.oks.javaschool.xml.model.Transport;

/**
 * Класс троллейбуса.
 */
@JacksonXmlRootElement(localName = "trolleybus")
public class Trolleybus extends Transport {
    /**
     * Конструктор.
     */
    public Trolleybus() {
    }

    /**
     * Конструктор.
     *
     * @param number номер маршрута.
     * @param time момент времени.
     * @param location местоположение.
     */
    public Trolleybus(String number, String time, Location location) {
        super(number, time, location);
    }

    @Override
    public String toString() {
        return "Trolleybus{"+super.toString()+"}";
    }
}
