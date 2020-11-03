package ru.oks.javaschool.actors;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Класс фильма, в котором снимался актер.
 */
@JacksonXmlRootElement(localName = "film")
public class Film {
    /**
     * Название.
     */
    @JacksonXmlProperty(localName = "title", isAttribute = true)
    private String title;
    /**
     * Роль
     */
    @JacksonXmlProperty(localName = "role", isAttribute = true)
    private String role;

    /**
     * Конструктор.
     *
     * @param title название
     * @param role роль
     */
    public Film(String title, String role) {
        this.title = title;
        this.role = role;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Film() {
    }

    public String getTitle() {
        return title;
    }

    public String getRole() {
        return role;
    }
}
