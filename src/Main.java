import java.util.concurrent.*;

public class Main {
//  3. Написать программу, которая задание 2 выполнит в 4 потоках из пула.
//      Сравнить, быстрее ли параллельное выполнение.
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + " " + countTwo());
                System.out.println(Thread.currentThread().getName() + " " + (System.currentTimeMillis() - start));
            }
        };
        thread.start();

        ExecutorService es = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            es.execute(() -> {
                long s = System.currentTimeMillis();
                System.out.println(Thread.currentThread().getName() + " " + count());
                System.out.println(Thread.currentThread().getName() + " " + (System.currentTimeMillis() - s));
            });
        }
        es.shutdown();
    }


    //    2. Написать программу, которая 4 раза считает сумму от1 до 10000000 и выводит результат на консоль.
    private static long countTwo() {
        long result = 0;
        for (int i = 0; i < 4; i++)
            result = result + count();
        return result;
    }

    private static long count() {
        long result = 0;
        for (int i = 1; i <= 10000000; i++)
            result = result + i;
        return result;
    }
}