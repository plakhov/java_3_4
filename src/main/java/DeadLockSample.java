import java.util.concurrent.TimeUnit;

public class DeadLockSample {

    Object monitorOne = new Object();
    Object monitorTwo = new Object();

    public static void main(String[] args) {
        DeadLockSample deadLockSample = new DeadLockSample();
        Thread thread1 = new Thread(() -> deadLockSample.method1());
        Thread thread2 = new Thread(() -> deadLockSample.method2());
        thread1.start();
        thread2.start();
    }

    private void method2() {
        printMessage("Начало не сихноризированного блока");
        try {
            printMessage("ИБД");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printMessage("Конец не сихноризированного блока");
        synchronized (monitorTwo) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printMessage("Начало сихноризированного блока");
            synchronized (monitorOne) {
                printMessage("ИБД");
            }
            printMessage("Конец сихноризированного блока");
        }
    }


    private void method1() {
        printMessage("Начало не сихноризированного блока");
        try {
            printMessage("ИБД");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printMessage("Конец не сихноризированного блока");
        synchronized (monitorOne) {
            printMessage("Начало сихноризированного блока");
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (monitorTwo) {
                printMessage("ИБД");
            }
            printMessage("Конец сихноризированного блока");
        }
    }

    private void printMessage(String message) {
        System.out.println(Thread.currentThread().getName()+ " : "+message);
    }
}
