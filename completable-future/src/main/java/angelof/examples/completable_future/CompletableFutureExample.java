package angelof.examples.completable_future;


import angelof.examples.service.CarDealerApi;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CompletableFutureExample {

    /**
     * Completable Future
     */

    public static void main(String[] args) {

        CarDealerApi mazda = new CarDealerApi();
        CarDealerApi nissan = new CarDealerApi();
        CarDealerApi toyota = new CarDealerApi();
        CarDealerApi mitsubishi = new CarDealerApi();

        /*
         * SYNC
         */
        long startTimeSync = System.currentTimeMillis();
        System.out.println("Mazda      U$: " + mazda.getCarPriceSync());
        System.out.println("Nissan     U$: " + nissan.getCarPriceSync());
        System.out.println("Toyota     U$: " + toyota.getCarPriceSync());
        System.out.println("Mitsubishi U$: " + mitsubishi.getCarPriceSync());
        long finaltTimeSync = System.currentTimeMillis();

        System.out.println("Tempo de consulta serviço sincrono: " + ((finaltTimeSync - startTimeSync) / 1000) + " seg \n");

        /*
         * ASYNC
         */
        Future<Integer> mazdaPrice = mazda.getCarPriceAsync();
        Future<Integer> nissanPrice = nissan.getCarPriceAsync();
        Future<Integer> toyotaPrice = toyota.getCarPriceAsync();
        Future<Integer> mitsubishiPrice = mitsubishi.getCarPriceAsync();

        long startTimeAsync = System.currentTimeMillis();

        try {
            System.out.println("Mazda      U$: " + mazdaPrice.get());
            System.out.println("Nissan     U$: " + nissanPrice.get());
            System.out.println("Toyota     U$: " + toyotaPrice.get());
            System.out.println("Mitsubishi U$: " + mitsubishiPrice.get());

            System.out.println("Tempo de consulta serviço assincrono: " + ((System.currentTimeMillis() - startTimeAsync) / 1000) + " seg");

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
