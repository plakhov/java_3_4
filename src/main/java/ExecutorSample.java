import java.util.Random;
import java.util.concurrent.*;

public class ExecutorSample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService;
        executorService = Executors.newSingleThreadExecutor();
        Runnable runnable = () -> {
            System.out.println("1");
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(6));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Callable<String> stringCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                return "Возвращаемое значение";
            }
        };
        Future<String> future = executorService.submit(stringCallable);
//        while (!future.isDone()) {
//            Thread.sleep(500);
//            System.out.println("Ожидаю результат");
//        }
        try {
            System.out.println(future.get(1,TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

}
