package angelof.examples.future;


import angelof.examples.service.PrincipalService;

import java.util.Random;
import java.util.concurrent.*;

public class FutureExample {

    /**
     * About FUTURE
     */
    private static final ExecutorService service = Executors.newFixedThreadPool(1);


    public static void main(String[] args) {
        try {
//           Without Lambda
//            Future<Integer> apiService = service.submit(new Callable<Integer>() {
//                @Override
//                public Integer call() throws Exception {
//                    TimeUnit.SECONDS.sleep(2);
//                    return new Random().nextInt(100);
//                }
//            });

            // With Lambda
            Future<Integer> apiService = service.submit(() -> {
                TimeUnit.SECONDS.sleep(3);
                return new Random().nextInt(100);
            });


            Integer resultFromPrincipalService0 = new PrincipalService().execute();
            Integer resultFromPrincipalService1 = new PrincipalService().execute();
            Integer resultFromPrincipalService2 = new PrincipalService().execute();
            Integer resultFromPrincipalService3 = new PrincipalService().execute();

            /*
             * ASYNC
             */
            System.out.println("\nResultado do serviço principal 0: " + resultFromPrincipalService0);
            System.out.println("Resultado do serviço principal 1: " + resultFromPrincipalService1);
            System.out.println("Resultado do serviço principal 2: " + resultFromPrincipalService2);
            System.out.println("Resultado do serviço principal 3: " + resultFromPrincipalService3);
            System.out.println("");
            // you can use like this but is gonna turn into a sync call ---> while (!apiService.isDone())...
            System.out.println("Valor do serviço API (future)   : " + apiService.get(2, TimeUnit.SECONDS));

        } catch (InterruptedException | ExecutionException | TimeoutException ex) {
            ex.printStackTrace();

        } finally {
            service.shutdown();
        }
        System.out.println("Fim do processamento");
    }
}
