package ru.oks.javaschool;

import ru.oks.javaschool.annotations.MapKeyFail;

/**
 * Тестовый класс с аннотацией @MapKeyFail.
 */
@MapKeyFail
public class FirstClassForTest {
    int a;
    int b;

    public FirstClassForTest(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
