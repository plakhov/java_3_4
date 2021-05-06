public class SimpleThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10 || isInterrupted(); i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println(i);
        }
    }
}
