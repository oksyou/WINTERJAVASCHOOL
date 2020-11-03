package ru.oks.javaschool;

import ru.oks.javaschool.annotations.MapKeyFail;
import ru.oks.javaschool.annotations.MapValueFail;
import ru.oks.javaschool.exceptions.PutMapKeyFailException;
import ru.oks.javaschool.exceptions.PutMapValueFailException;

import java.util.HashMap;

/**
 * Переопределенный класс HashMap.
 *
 * @param <K> ключ
 * @param <V> значение
 */
public class MyHashMap<K, V> extends HashMap<K, V> {
    /**
     * Переопределение метода put.
     *
     * @param key ключ
     * @param value значение
     * @return значение
     */
    @Override
    public V put(K key, V value) {
        if (key.getClass().isAnnotationPresent(MapKeyFail.class)) {
            throw new PutMapKeyFailException();
        }
        if (value.getClass().isAnnotationPresent(MapValueFail.class)){
            throw new PutMapValueFailException();
        }
        return super.put(key, value);
    }
}
