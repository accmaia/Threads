package fundamentals.examples.one;

public class SynchronizedResource {


    private static StringBuilder sb;
    private static Integer counter;

    static{
        sb = new StringBuilder();
        counter = 0;
    }
    public synchronized static void execute() {
        sb.append(String.format("%d;", counter++));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static StringBuilder getSb() {
        return sb;
    }

    public static void setSb(StringBuilder sb) {
        SynchronizedResource.sb = sb;
    }

    public static Integer getCounter() {
        return counter;
    }

    public static void setCounter(Integer counter) {
        SynchronizedResource.counter = counter;
    }
}
