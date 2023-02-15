package fundamentals.examples.one;

public class StringBufferThread implements Runnable{
    static int counter;
    private StringBuffer buffer;

    static {
        counter = 0;
    }

    public StringBufferThread(StringBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.append(String.format("%d;", counter++));
    }
}
