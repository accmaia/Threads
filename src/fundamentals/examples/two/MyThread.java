package fundamentals.examples.two;

public class MyThread extends Thread{
    private Runnable run;

    public MyThread(String name) {
        super(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        setName(name);
    }
}
