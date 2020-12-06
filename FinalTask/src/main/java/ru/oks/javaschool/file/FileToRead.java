package ru.oks.javaschool.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс для считывания данных из файла.
 */
public class FileToRead {
    /**
     * Чтение информации из файла.
     *
     * @param file файл
     * @return строка
     */
    public static String readingFromAFile(File file) {
        String returnString;
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }
            returnString = stringBuilder.toString();
        }
        catch (IOException e){
            throw new RuntimeException("Не удалось прочесть файл!"+e);
        }
        return returnString;
    }
}
