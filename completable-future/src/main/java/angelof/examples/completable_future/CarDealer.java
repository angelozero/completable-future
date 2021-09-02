package angelof.examples.completable_future;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CarDealer {

  private String brand;

  public CarDealer() {
  }

  public CarDealer(String brand) {
    this.brand = brand.toUpperCase(Locale.ROOT);
  }

  public String getBrand() {
    return brand;
  }

  public double getCarPriceSync() {
    return getPrice();
  }

  public Future<Integer> getCarPriceAsync() {
    CompletableFuture<Integer> futurePrice = new CompletableFuture<>();
    new Thread(() -> futurePrice.complete(getPrice())).start();

    return futurePrice;
  }

//  public Future<Integer> getCarPriceAsyncWithCatchException() {
//    CompletableFuture<Integer> futurePrice = new CompletableFuture<>();
//    new Thread(() -> {
//      try {
//        futurePrice.complete(getPrice());
//      } catch (Exception e) {
//        futurePrice.completeExceptionally(e);
//      }
//    }).start();
//    return futurePrice;
//  }

//  public Future<Integer> getCarPriceSupplyAsync() {
//    return CompletableFuture.supplyAsync(this::getPrice);
//  }

  private Integer getPrice() {
    delay();
//    System.out.println(1/0);
    return ThreadLocalRandom.current().nextInt(100) * 100;
  }

  private static void delay() {
    try {
      TimeUnit.SECONDS.sleep(new Random().nextInt(3));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
