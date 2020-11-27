package ru.oks.javaschool.xml.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Класс списка троллейбуса.
 */
@JacksonXmlRootElement(localName = "trolleybuses")
public class Trolleybuses {
    /**
     * Список троллейбусов.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "trolleybus")
    private List<Trolleybus> trolleybuses;

    /**
     * Конструктор.
     */
    public Trolleybuses() {
    }

    /**
     * Конструктор.
     *
     * @param trolleybuses список троллейбусов.
     */
    public Trolleybuses(List<Trolleybus> trolleybuses) {
        this.trolleybuses = trolleybuses;
    }

    public List<Trolleybus> getTrolleybuses() {
        return trolleybuses;
    }
}
