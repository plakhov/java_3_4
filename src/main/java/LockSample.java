import java.util.concurrent.TimeUnit;

public class LockSample {
    Object monitorOne = new Object();
    Object monitorTwo = new Object();

    public static void main(String[] args) {
        LockSample lockSample = new LockSample();
        Thread thread1 = new Thread(() -> lockSample.method1());
        Thread thread2 = new Thread(() -> lockSample.method2());
        thread1.start();
        thread2.start();
    }

    private void method2() {
        printMessage("Начало не сихноризированного блока");
        try {
            printMessage("ИБД");
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printMessage("Конец не сихноризированного блока");
        synchronized (monitorTwo) {
            printMessage("Начало сихноризированного блока");
            for (int i = 0; i < 3; i++) {
                printMessage("Пока");
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                } catch (InterruptedException e) {

                }
            }
            printMessage("Конец сихноризированного блока");
        }
    }


    private void method1() {
        printMessage("Начало не сихноризированного блока");
        try {
            printMessage("ИБД");
            Thread.sleep(TimeUnit.SECONDS.toMillis(3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printMessage("Конец не сихноризированного блока");
        synchronized (monitorOne) {
            printMessage("Начало сихноризированного блока");
            for (int i = 0; i < 3; i++) {
                printMessage("Привет");
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                } catch (InterruptedException e) {

                }
            }
            printMessage("Конец сихноризированного блока");
        }
    }

    private void printMessage(String message) {
        System.out.println(Thread.currentThread().getName()+ " : "+message);
    }
}
