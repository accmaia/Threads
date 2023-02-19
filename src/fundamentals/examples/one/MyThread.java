package fundamentals.examples.one;

public class MyThread implements Runnable{
    private Boolean synch;

    private static int counter;

    static {
        counter = 1;
    }

    public MyThread(Boolean synch) {
        this.synch = synch;
    }

    @Override
    public void run() {
        if(synch)
            SynchronizedResource.execute();
        else
            Resource.execute();
    }
}
