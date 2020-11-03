package ru.oks.javaschool.annotations;

import java.lang.annotation.*;

/**
 * Аннотация для значения словаря.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapValueFail {
}
