package ru.oks.javaschool;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Класс потока-демона.
 */
public class MyDaemonThread implements Runnable {
    /**
     * Дериктория для очищения.
     */
    private final String d;
    /**
     * Количество секунд паузы.
     */
    private final int s;

    /**
     * Конструктор.
     *
     * @param d директория.
     * @param s количество секунд.
     */
    public MyDaemonThread(String d, int s) {
        this.d = d;
        this.s = s;
    }

    /**
     * Очищение директории d каждые s секунд.
     */
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                FileUtils.cleanDirectory(new File(d));
                Thread.sleep(s * 1000);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
