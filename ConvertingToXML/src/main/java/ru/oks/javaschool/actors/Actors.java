package ru.oks.javaschool.actors;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

/**
 * Класс списка актеров.
 */
@JacksonXmlRootElement(localName = "actors")
public class Actors {
    /**
     * Список актеров.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "actor")
    private List<Actor> actors;

    /**
     * Конструктор.
     *
     * @param actors список актеров
     */
    public Actors(List<Actor> actors) {
        this.actors = actors;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Actors() {
    }

    /**
     * Проверка на эквивалентность актеров.
     *
     * @param actor актер
     * @return эквивалентны или нет
     */
    public boolean isActor(Actor actor){
        boolean isA=false;
        for (Actor a:actors){
            if (a.getName().equals(actor.getName()) && a.getAge().equals(actor.getAge())) {
                isA=true;
                break;
            }
        }
        return isA;
    }

    public List<Actor> getActors() {
        return actors;
    }
}
