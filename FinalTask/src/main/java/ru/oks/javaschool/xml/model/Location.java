package ru.oks.javaschool.xml.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Класс местоположения транспорта.
 */
@JacksonXmlRootElement(localName = "location")
public class Location {
    /**
     * Расстояние по оси x от центра.
     */
    @JacksonXmlProperty(localName = "x", isAttribute = true)
    private double x;
    /**
     * Расстояние по оси y от центра.
     */
    @JacksonXmlProperty(localName = "y", isAttribute = true)
    private double y;

    /**
     * Конструктор.
     */
    public Location() {
    }

    /**
     * Конструктор.
     *
     * @param x расстояние по оси x.
     * @param y расстояние по оси y.
     */
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
