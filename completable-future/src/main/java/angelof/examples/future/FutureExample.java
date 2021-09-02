package angelof.examples.future;


import angelof.examples.helper.PrincipalService;

import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FutureExample {

  /**
   * About FUTURE
   */

  private static final ExecutorService service = Executors.newFixedThreadPool(1);


  public static void main(String[] args) {

    // With Lambda
    Future<Integer> apiService = service.submit(() -> {
      TimeUnit.SECONDS.sleep(2);
      return new Random().nextInt(100);
    });

    // Without Lambda
//    Future<Integer> apiService = service.submit(new Callable<Integer>() {
//      @Override
//      public Integer call() throws Exception {
//        TimeUnit.SECONDS.sleep(2);
//        return new Random().nextInt(100);
//      }
//    });

    Integer resultFromPrincipalService = PrincipalService.execute();

    /*
     * ASYNC
     */
    try {
      System.out.println("Resultado do serviço principal : " + resultFromPrincipalService);
      System.out.println("Resultado do serviço API       : " + apiService.get(3, TimeUnit.SECONDS));

      /*
       * SYNC
       */
//  try {
//    while (!apiService.isDone()) {
//      System.out.println("Resultado do serviço principal : " + resultFromPrincipalService);
//      System.out.println("Resultado do serviço API       : " + apiService.get());
//    }

    } catch (InterruptedException | ExecutionException | TimeoutException ex) {
      ex.printStackTrace();

    } finally {
      service.shutdown();
    }
  }
}
