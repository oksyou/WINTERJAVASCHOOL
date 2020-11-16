package ru.oks.javaschool;

import org.junit.Assert;
import org.junit.Test;

/**
 * Класс тестирования потока-демона.
 */
public class TestDaemonThread {
    /**
     * Тестирование потока-демона.
     *
     * @throws InterruptedException возможное исключение при Thread.sleep
     */
    @Test
    public void testDaemonThread() throws InterruptedException {
        String directory="D:\\example";
        int second=2;

        Thread thread=new Thread(new MyDaemonThread(directory, second));
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(5_000);
        Assert.assertTrue(thread.isAlive());
        thread.interrupt();
        //время для завершения потока
        Thread.sleep(100);
        Assert.assertFalse(thread.isAlive());
    }
}
