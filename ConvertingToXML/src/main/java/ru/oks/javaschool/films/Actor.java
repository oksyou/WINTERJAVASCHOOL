package ru.oks.javaschool.films;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Objects;

/**
 * Класс актера в фильме.
 */
@JacksonXmlRootElement(localName = "actor")
public class Actor {
    /**
     * Имя.
     */
    @JacksonXmlProperty(localName = "name", isAttribute = true)
    private String name;
    /**
     * Возраст.
     */
    @JacksonXmlProperty(localName = "age", isAttribute = true)
    private String age;
    /**
     * Роль.
     */
    @JacksonXmlProperty(localName = "role", isAttribute = true)
    private String role;

    /**
     * Конструктор.
     *
     * @param name имя
     * @param age возраст
     * @param role роль
     */
    public Actor(String name, String age, String role) {
        this.name = name;
        this.age = age;
        this.role = role;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Actor(){}

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getRole() {
        return role;
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
        Actor actor = (Actor) o;
        return Objects.equals(name, actor.name) && Objects.equals(age, actor.age) && Objects.equals(role, actor.role);
    }
}
