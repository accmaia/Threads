package fundamentals.examples.one;

public class Resource {

    private static StringBuilder sb;
    private static Integer counter;

    static{
        sb = new StringBuilder();
        counter = 0;
    }
    public static void execute() {
        try {
            sb.append(String.format("%d;", counter++));
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static StringBuilder getSb() {
        return sb;
    }

    public static void setSb(StringBuilder sb) {
        Resource.sb = sb;
    }

    public static Integer getCounter() {
        return counter;
    }

    public static void setCounter(Integer counter) {
        Resource.counter = counter;
    }
}
