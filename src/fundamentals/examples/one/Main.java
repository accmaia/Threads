package fundamentals.examples.one;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Integer THREAD_POOL_SIZE;
    private static final Integer LOOP_SIZE;

    static {
        THREAD_POOL_SIZE = 8;
        LOOP_SIZE = 100;
    }

    public static void main(String[] args) throws InterruptedException {
        long timer = loop(false);
        long synchronizedTimer = loop(true);

        System.out.println(String.format("Total time without synchronization: %d seconds", TimeUnit.SECONDS.convert(timer, TimeUnit.NANOSECONDS)));
        System.out.println(String.format("Total time with synchronization: %d seconds", TimeUnit.SECONDS.convert(synchronizedTimer, TimeUnit.NANOSECONDS)));

        System.out.println(String.format("Synchronization is %dx slower than without synchronization", synchronizedTimer / timer));

        System.out.println(String.format("It's reliable without synchronization: %s", isValid(SynchronizedResource.getSb().toString())));
        System.out.println(String.format("It's reliable with synchronization: %s", isValid(Resource.getSb().toString())));
    }

    private static long loop(boolean sync) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        long timer = System.nanoTime();
        for(int i = 0 ; i < LOOP_SIZE ; i++){
            executor.submit(new MyThread(sync));
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        timer = System.nanoTime() - timer;
        return timer;
    }

    private static boolean isValid(String s){
        int nextInt = -1;

        try {
            for (String iter : s.split(";")) {
                Integer num = Integer.parseInt(iter);
                if (num != nextInt + 1){
                    throw new Exception();
                }
                nextInt = num;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
