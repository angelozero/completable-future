package angelof.examples.completable_future;


import angelof.examples.helper.PrincipalService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureExample {

  /**
   * About Completable Future
   */

  public static void main(String[] args) {
    long startTimeSync = System.currentTimeMillis();

    CarDealer mazda = new CarDealer();
    CarDealer nissan = new CarDealer();
    CarDealer toyota = new CarDealer();
    CarDealer mitsubishi = new CarDealer();


    // Sync
    System.out.println(mazda.getCarPriceSync());
    System.out.println(nissan.getCarPriceSync());
    System.out.println(toyota.getCarPriceSync());
    System.out.println(mitsubishi.getCarPriceSync());
    long finaltTimeSync = System.currentTimeMillis();

    // Async
    Future<Integer> mazdaPrice = mazda.getCarPriceAsync();
    Future<Integer> nissanPrice = nissan.getCarPriceAsync();
    Future<Integer> toyotaPrice = toyota.getCarPriceAsync();
    Future<Integer> mitsubishiPrice = mitsubishi.getCarPriceAsync();

    System.out.println("Tempo de consulta serviço sincrono: " + ((finaltTimeSync - startTimeSync) / 1000) + " seg \n");
    PrincipalService.execute();

    long startTimeAsync = System.currentTimeMillis();
    long finaltTimeAsync = 0;
    try {
      System.out.println(mazdaPrice.get());
      System.out.println(nissanPrice.get());
      System.out.println(toyotaPrice.get());
      System.out.println(mitsubishiPrice.get());
      finaltTimeAsync = System.currentTimeMillis();

    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
    System.out.println("Tempo de consulta serviço assincrono: " + ((finaltTimeAsync - startTimeAsync) / 1000) + " seg");

  }
}
