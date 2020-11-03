package ru.oks.javaschool.films;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;
import java.util.Objects;

/**
 * Класс фильма.
 */
@JacksonXmlRootElement(localName = "film")
public class Film {
    /**
     * Название.
     */
    @JacksonXmlProperty(localName = "title")
    private String title;
    /**
     * Описание.
     */
    @JacksonXmlProperty(localName = "description")
    private String description;
    /**
     * Список актеров.
     */
    @JacksonXmlElementWrapper(localName = "actors")
    @JacksonXmlProperty(localName = "actor")
    private List<Actor> actors;

    /**
     * Конструктор.
     *
     * @param title название
     * @param description описание
     * @param actors список актеров
     */
    public Film(String title, String description, List<Actor> actors) {
        this.title = title;
        this.description = description;
        this.actors = actors;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Film() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Actor> getActors() {
        return actors;
    }

    /**
     * Эквивалентность по значению.
     *
     * @param o объект
     * @return эквивалентны или нет
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title) && Objects.equals(description, film.description) && Objects.equals(actors, film.actors);
    }
}
