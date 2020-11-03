package ru.oks.javaschool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.oks.javaschool.actors.Actors;
import ru.oks.javaschool.films.Actor;
import ru.oks.javaschool.films.Film;
import ru.oks.javaschool.films.Films;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Тестирование сериализации и десериализации xml.
 */
public class JacksonConvertedTest {
    private Films films;

    private JacksonConverted jacksonConverter;

    @BeforeEach
    public void initialization(){
        Actor actor1=new Actor("Актер 1", "30", "Роль 1");
        Actor actor2=new Actor("Актер 2", "23", "Роль 2");
        Actor actor3=new Actor("Актер 3", "40", "Роль 3");
        Actor actor4=new Actor("Актер 4", "70", "Роль 4");
        Actor actor5=new Actor("Актер 2", "23", "Роль 5");
        Actor actor6=new Actor("Актер 3", "40", "Роль 6");

        Film film1=new Film("Фильм 1", "Описание 1", Arrays.asList(actor1,actor2,actor3));
        Film film2=new Film("Фильм 2", "Описание 2", Arrays.asList(actor4,actor5,actor6));

        films=new Films(Arrays.asList(film1, film2));

        jacksonConverter = new JacksonConverted();
    }

    /**
     * Сериализация из строки в xml файл.
     * @throws Exception исключение при сериализации
     */
    @Test
    public void testConvertfromXml() throws Exception {
        Path path = Paths.get("src/main/resources", "book_ex.xml");
        String xml = new String(Files.readAllBytes(path));
        Films convertingXml = jacksonConverter.fromXml(xml, Films.class);
        Assertions.assertEquals(films, convertingXml);
    }

    /**
     * Десериализация из xml файла в строку.
     * @throws IOException
     */
    @Test
    public void testConverttoXml() throws IOException {
        List<ru.oks.javaschool.actors.Actor> filmsOfActors=films.getFilmsOfActors();
        Actors actors=new Actors(filmsOfActors);
        //нужно убрать из исходного xml перенос каретки - иначе падает тест
        String xml=jacksonConverter.toXml(actors).replaceAll("[\r]+", "");

        Path path = Paths.get("src/main/resources", "book_ex_1.xml");
        String ojidayu = new String(Files.readAllBytes(path));

        //из обоих строк удаляем везде 2 и больше пробела - при создании он ставит пробелов меньше, чем в исходном xml
        xml=xml.replaceAll(" +", " ");
        ojidayu=ojidayu.replaceAll(" +", " ");

        Assertions.assertEquals(ojidayu, xml);
    }
}
