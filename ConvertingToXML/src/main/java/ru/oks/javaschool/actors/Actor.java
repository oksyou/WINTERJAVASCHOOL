package ru.oks.javaschool.actors;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.*;

/**
 * Класс актера.
 */
@JacksonXmlRootElement(localName = "actor")
public class Actor {
    /**
     * Имя актера.
     */
    @JacksonXmlProperty(localName = "name")
    private String name;
    /**
     * Возраст актера.
     */
    @JacksonXmlProperty(localName = "age")
    private String age;
    /**
     * Фильмы, в которых снимался актер.
     */
    @JacksonXmlElementWrapper(localName = "films")
    @JacksonXmlProperty(localName = "film")
    private Set<Film> films;

    /**
     * Конструктор.
     *
     * @param name имя
     * @param age возраст
     * @param film фильм
     */
    public Actor(String name, String age, Film film) {
        this.name = name;
        this.age = age;
        this.films = new LinkedHashSet<>();
        this.films.add(film);
    }

    /**
     * Конструктор.
     *
     * @param name имя
     * @param age возраст
     */
    public Actor(String name, String age) {
        this.name = name;
        this.age = age;
        this.films=new LinkedHashSet<>();
    }

    /**
     * Конструктор по умолчанию.
     */
    public Actor() {
    }

    /**
     * Добавление фильма.
     * @param film фильм
     */
    public void addFilm(Film film){
        this.films.add(film);
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public Set<Film> getFilms() {
        return films;
    }

    /**
     * Переопределенный equals для сравнения по значению.
     *
     * @param o объект.
     * @return равны или нет по значению
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return Objects.equals(name, actor.name) && Objects.equals(age, actor.age) && Objects.equals(films, actor.films);
    }

    /**
     * Сравнение эквивалентности с актером пакета films
     * @param o
     * @return
     */
    public boolean actorEquals(ru.oks.javaschool.films.Actor o) {
        return Objects.equals(name, o.getName()) && Objects.equals(age, o.getAge());
    }
}
