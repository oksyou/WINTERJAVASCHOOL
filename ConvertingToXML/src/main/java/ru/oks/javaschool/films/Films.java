package ru.oks.javaschool.films;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import ru.oks.javaschool.actors.Actors;

import java.util.*;

/**
 * Класс списка фильмов.
 */
@JacksonXmlRootElement(localName = "films")
public class Films {
    /**
     * Список фильмов.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "film")
    private List<Film> films;

    /**
     * Конструктор.
     *
     * @param films список фильмов.
     */
    public Films(List<Film> films) {
        this.films = films;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Films(){}

    /**
     * Получение списка актеров с их фильмами.
     *
     * @return список актеров пакета actors.
     */
    public List<ru.oks.javaschool.actors.Actor> getFilmsOfActors(){
        List<ru.oks.javaschool.actors.Actor> actorList=new ArrayList<>();
        Actors filmOfActors=new Actors(actorList);
        for (Film film:films){
            for (Actor actor:film.getActors()){
                ru.oks.javaschool.actors.Actor temp=new ru.oks.javaschool.actors.Actor(actor.getName(),actor.getAge());
                //если не содержится, то добавляем
                if (!filmOfActors.isActor(temp)) {
                    actorList.add(new ru.oks.javaschool.actors.Actor(actor.getName(), actor.getAge(),
                            new ru.oks.javaschool.actors.Film(film.getTitle(), actor.getRole())));
                }
                else{
                    for (ru.oks.javaschool.actors.Actor a:actorList){
                        if (a.actorEquals(actor)) {
                            a.addFilm(
                                    new ru.oks.javaschool.actors.Film(film.getTitle(), actor.getRole()));
                            break;
                        }

                    }

                }
            }
        }
        return actorList;
    }

    public List<Film> getFilms() {
        return films;
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
        Films movies = (Films) o;
        return Objects.equals(films, movies.films);
    }
}
