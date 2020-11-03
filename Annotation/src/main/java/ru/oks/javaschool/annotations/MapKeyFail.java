package ru.oks.javaschool.annotations;

import java.lang.annotation.*;

/**
 * Аннотация для ключа словаря.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapKeyFail {
}
