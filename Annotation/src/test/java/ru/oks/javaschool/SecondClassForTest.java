package ru.oks.javaschool;

import ru.oks.javaschool.annotations.MapValueFail;

/**
 * Тестовый класс с аннотацией @MapValueFail.
 */
@MapValueFail
public class SecondClassForTest {
    String a;
    String b;

    public SecondClassForTest(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }
}
