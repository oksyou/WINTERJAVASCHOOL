package ru.oks.javaschool.xml.model.buses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Класс списка автобусов.
 */
@JacksonXmlRootElement(localName = "buses")
public class Buses {
    /**
     * Список автобусов.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "bus")
    private List<Bus> buses;

    /**
     * Конструктор.
     */
    public Buses() {
    }

    /**
     * Конструктор.
     *
     * @param buses список автобусов.
     */
    public Buses(List<Bus> buses) {
        this.buses = buses;
    }

    public List<Bus> getBuses() {
        return buses;
    }
}
