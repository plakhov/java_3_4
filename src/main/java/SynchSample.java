public class SynchSample {

    private volatile String nextString = "Привет";

    public static void main(String[] args) {
        SynchSample synchSample = new SynchSample();
        Thread thread1 = new Thread(() -> synchSample.method1());
        Thread thread2 = new Thread(() -> synchSample.method2());
        thread1.start();
        thread2.start();
    }

    private void method1() {
        try {
            synchronized (this) {
                for (int i = 0; i < 3; i++) {
                    while (!"Привет".equals(nextString)) {
                        wait();
                    }
                    printMessage("Привет");
                    nextString = "Пока";
                    notify();
                }
            }
        } catch (Exception e) {

        }
    }

    private void method2() {
        try {
            synchronized (this) {
                for (int i = 0; i < 3; i++) {
                    while (!"Пока".equals(nextString)) {
                        wait();
                    }
                    printMessage("Пока");
                    nextString = "Привет";
                    notifyAll();
                }
            }
        } catch (Exception e) {

        }
    }

    private void printMessage(String message) {
        System.out.println(Thread.currentThread().getName()+ " : "+message);
    }
}
