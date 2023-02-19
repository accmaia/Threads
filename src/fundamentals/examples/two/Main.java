package fundamentals.examples.two;

public class Main {
    private static MyThread thread;
    public static void main(String[] args) throws InterruptedException {
        thread = new MyThread("My thread");
        printThreadState();

        thread.start();
        printThreadState();

        Thread.sleep(1000);
        printThreadState();
        //while (thread.getState().equals(Thread.State.))

    }

    private static void printThreadState() {
        System.out.println(String.format("%s is in the state: %s", thread.getName(), thread.getState()));
    }
}
