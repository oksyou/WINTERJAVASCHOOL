package ru.oks.javaschool.db.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс для форматирования.
 */
public class Formatter {
    /**
     * Преобразование строки в момент времени формата Timestamp.
     *
     * @param time момент времени в формате строки.
     * @return момент времени в формате Timestamp.
     */
    public static Timestamp StringToTimestamp(String time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return Timestamp.valueOf(LocalDateTime.parse(time, formatter));
    }
}
