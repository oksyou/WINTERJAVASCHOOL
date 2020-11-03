package ru.oks.javaschool;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.oks.javaschool.exceptions.PutMapKeyFailException;
import ru.oks.javaschool.exceptions.PutMapValueFailException;

import java.util.Map;

/**
 * Класс тестирование работы с аннотированными данными словаря.
 */
public class AnnotationTest {
    /**
     * Тестовый объект ключа.
     */
    FirstClassForTest key;
    /**
     * Тестовый объект значения.
     */
    SecondClassForTest value;

    @BeforeEach
    public void initialize(){
        key=new FirstClassForTest(1, 2);
        value=new SecondClassForTest("1", "2");
    }

    /**
     * Тестирование работы с аннотированными данными словаря.
     */
    @Test
    public void testAnnotation(){
        Map<Object, Object> map=new MyHashMap<>();
        //убедиться, что все нормально, если подаются данные без аннотаций
        map.put(1, 1);
        Assertions.assertEquals(1, map.get(1));
        //убедиться, что все нормально, если подаются данные с другими аннотациями
        Assertions.assertDoesNotThrow(() ->map.put(value, key));

        //исключение для ключа
        Assertions.assertThrows(PutMapKeyFailException.class, () ->map.put(key, 1));
        //исключение для значения
        Assertions.assertThrows(PutMapValueFailException.class, () ->map.put(1,value));

    }

}
