package ru.oks.javaschool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.*;

/**
 * Класс нахождения максимума с помощью потоков и с помощью коллекций.
 */
public class FindMaxTest {
    @Test
    public void testFindMax() {
        //Количество потоков
        int p=4;
        //Размер списка для нахождения максимума.
        int n=10;
        //Максимум
        final int[] max = {0};
        //список для нахождения максимума
        List<Integer> list=new ArrayList<>(p);
        for (int i = 0; i < n; i++) {
            list.add(new Random().nextInt()%n);
        }
        int maxCollections= Collections.max(list);

        ExecutorService executorService = Executors.newFixedThreadPool(p);
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final int index = list.get(i);
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    max[0] =Math.max(max[0], index);
                }
            };
            Future<?> future = executorService.submit(task);
            futures.add(future);
        }
        for (Future<?> future : futures) {
            while (!future.isDone());
        }
        executorService.shutdown();
        Assertions.assertEquals(maxCollections, max[0]);
    }
}
