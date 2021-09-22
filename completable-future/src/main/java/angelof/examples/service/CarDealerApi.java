package angelof.examples.service;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CarDealerApi {

    private String brand;

    public CarDealerApi() {
    }

    public CarDealerApi(String brand) {
        this.brand = brand.toUpperCase(Locale.ROOT);
    }

    public String getBrand() {
        return brand;
    }

    public double getCarPriceSync() {
        return getPrice();
    }

    public Future<Integer> getCarPriceAsync() {
        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                completableFuture.complete(getPrice());
            }
        }).start();
        return completableFuture;
    }

    /*
     * LAMBDA
     */
//    public Future<Integer> getCarPriceAsync() {
//        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
//        new Thread(() -> completableFuture.complete(getPrice())).start();
//        return completableFuture;
//    }

    /*
     * EXCEPTION
     */
//    public Future<Integer> getCarPriceAsync() {
//        CompletableFuture<Integer> completableFuture = new CompletableFuture<>();
//        new Thread(() -> {
//            try {
//                completableFuture.complete(getPrice());
//
//            } catch (Exception e) {
//                completableFuture.completeExceptionally(e);
//            }
//        }).start();
//        return completableFuture;
//    }

    /*
     * SUPPLY ASYNC
     */
//    public Future<Integer> getCarPriceAsync() {
//        return CompletableFuture.supplyAsync(this::getPrice);
//    }

    private Integer getPrice() {
        delay();
        //System.out.println(1 / 0);
        return ThreadLocalRandom.current().nextInt(120) * 100;
    }

    private static void delay() {
        try {
            int timeWait = new Random().nextInt(4) + 1;
//            System.out.println("\nTempo de requisição ---> " + timeWait );
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
