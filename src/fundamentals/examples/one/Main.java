package fundamentals.examples.one;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private final static int LOOP_SIZE;

    static {
        LOOP_SIZE = 1000;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(LOOP_SIZE);

        StringBuilder builder = new StringBuilder();
        StringBuffer buffer = new StringBuffer();

        long timerBuffer = System.nanoTime();
        for(int i = 0 ; i < LOOP_SIZE ; i++){
            executorService.submit(new StringBufferThread(buffer));
        }

        long timerBuilder = System.nanoTime();
        for(int i = 0 ; i < LOOP_SIZE ; i++){
            executorService.submit(new StringBuilderThread(builder));
        }

        /** Checking how the classes handled multiple threads accesing it **/
        System.out.println(String.format("Buffer took %d milliseconds to append %d elements", timerBuffer = (System.nanoTime() - timerBuffer), StringBufferThread.counter));
        System.out.println(String.format("Builder took %d milliseconds to append %d elements", timerBuilder = (System.nanoTime() - timerBuilder), StringBuilderThread.counter));


        /** Checking how efficient the classes where at appending the text **/
        System.out.println(String.format("Buffer took on average %d milliseconds/append", (timerBuffer / StringBufferThread.counter)));
        System.out.println(String.format("Builder took on average %d milliseconds/append", (timerBuilder / StringBuilderThread.counter)));

        System.out.println(String.format("Buffer has repeated or invalid number stored? %s", hasRepeatedOrInvalidNumber(buffer.toString())));
        System.out.println(String.format("Builder has repeated or invalid number stored? %s", hasRepeatedOrInvalidNumber(builder.toString())));
    }

    private static boolean hasRepeatedOrInvalidNumber(String s){
        String[] numbers = s.split(";");
        Set<Integer> nums = new HashSet<>();

        try{
            for (String number : numbers)  {
                if(!nums.add(Integer.valueOf(number))) {
                    return true;
                }
            }
        }catch (Exception e){
            return true;
        }
        return false;
    }


}
