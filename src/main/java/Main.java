import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SimpleThread simpleThread = new SimpleThread();
        simpleThread.start();

        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        simpleThread.interrupt();
//        Thread thread = new Thread(new SimpleRunnable());
//        thread.start();
//        new Thread(() -> System.out.println("Запустили поток через анонимный класс")).start();
    }
}
