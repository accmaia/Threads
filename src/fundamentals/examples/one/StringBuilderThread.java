package fundamentals.examples.one;

public class StringBuilderThread implements Runnable{
    static int counter;
    private StringBuilder builder;

    static {
        counter = 0;
    }

    public StringBuilderThread(StringBuilder builder) {
        this.builder = builder;
    }

    @Override
    public void run() {
        builder.append(String.format("%d;", counter++));
    }


}
