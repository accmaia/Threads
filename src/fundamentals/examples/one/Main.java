package fundamentals.examples.one;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private final static int LOOP_SIZE;
    private static int builderCounter;
    private static int bufferCounter;

    static {
        LOOP_SIZE = 1000;
        builderCounter = 0;
        bufferCounter = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService builderExecutorService = Executors.newFixedThreadPool(LOOP_SIZE);
        ExecutorService bufferExecutorService = Executors.newFixedThreadPool(LOOP_SIZE);

        StringBuilder builder = new StringBuilder();
        StringBuffer buffer = new StringBuffer();

        StringBuilder singleBuilder = new StringBuilder();
        StringBuffer singleBuffer = new StringBuffer();

        /** MULTI-THREAD BUILDER **/
        long timerBuilder = System.nanoTime();
        for(int i = 0 ; i < LOOP_SIZE ; i++){
            builderExecutorService.submit(new StringBuilderThread(builder));
        }

        builderExecutorService.shutdown();
        builderExecutorService.awaitTermination(10, TimeUnit.MINUTES);
        timerBuilder = (System.nanoTime() - timerBuilder);
        System.out.println("PROCESSED MULTI THREAD BUILDER");

        /** MULTI-THREAD BUFFER **/
        long timerBuffer = System.nanoTime();
        for(int i = 0 ; i < LOOP_SIZE ; i++){
            bufferExecutorService.submit(new StringBufferThread(buffer));
        }

        bufferExecutorService.shutdown();
        bufferExecutorService.awaitTermination(10, TimeUnit.MINUTES);
        timerBuffer = (System.nanoTime() - timerBuffer);
        System.out.println("PROCESSED MULTI THREAD BUFFER");


        /** SINGLE-THREAD BUILDER **/
        long singleTimerBuilder = System.nanoTime();
        for(int i = 0 ; i < LOOP_SIZE ; i++){
            singleBuilder.append(String.format("%d;", builderCounter++));
        }
        singleTimerBuilder = (System.nanoTime() - singleTimerBuilder);
        System.out.println("PROCESSED SINGLE THREAD BUILDER");

        /** SINGLE-THREAD BUFFER **/
        long singleTimerBuffer = System.nanoTime();
        for(int i = 0 ; i < LOOP_SIZE ; i++){
            singleBuffer.append(String.format("%d;", bufferCounter++));
        }
        singleTimerBuffer = (System.nanoTime() - singleTimerBuffer);
        System.out.println("PROCESSED SINGLE THREAD BUFFER");


        /** Checking how the classes handled multiple threads accessing it **/
        System.out.println(String.format("(MULTI THREAD) Builder took:  %d milliseconds to append %d elements", timerBuilder, StringBuilderThread.counter));
        System.out.println(String.format("(MULTI THREAD) Buffer took:   %d milliseconds to append %d elements", timerBuffer , StringBufferThread.counter));
        System.out.println(String.format("(SINGLE THREAD) Builder took: %d milliseconds to append %d elements", singleTimerBuilder, builderCounter));
        System.out.println(String.format("(SINGLE THREAD) Buffer took:  %d milliseconds to append %d elements", singleTimerBuffer , bufferCounter));

        /** Checking how efficient the classes where at appending the text **/
        System.out.println(String.format("(MULTI THREAD) Builder took on average:  %d milliseconds/append", (timerBuilder / StringBuilderThread.counter)));
        System.out.println(String.format("(MULTI THREAD) Buffer took on average:   %d milliseconds/append", (timerBuffer / StringBufferThread.counter)));
        System.out.println(String.format("(SINGLE THREAD) Builder took on average: %d milliseconds/append", (singleTimerBuilder / builderCounter)));
        System.out.println(String.format("(SINGLE THREAD) Buffer took on average:  %d milliseconds/append", (singleTimerBuffer / bufferCounter)));

        /** Checking how reliable the classes are at appending the text **/
        System.out.println(String.format("(MULTI THREAD) Builder has repeated or invalid number stored? %s", hasRepeatedOrInvalidNumber(builder.toString())));
        System.out.println(String.format("(MULTI THREAD) Buffer has repeated or invalid number stored? %s", hasRepeatedOrInvalidNumber(buffer.toString())));
        System.out.println(String.format("(SINGLE THREAD) Builder has repeated or invalid number stored? %s", hasRepeatedOrInvalidNumber(singleBuilder.toString())));
        System.out.println(String.format("(SINGLE THREAD) Buffer has repeated or invalid number stored? %s", hasRepeatedOrInvalidNumber(singleBuffer.toString())));
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
