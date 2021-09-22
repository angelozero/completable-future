package angelof.examples.completable_future;


import angelof.examples.service.CarDealerApi;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureExample2 {

    private static final String BRAND_AND_PRICE = "[Brand: %s - Price: %.2f U$] ";

    /**
     * About Completable Future - examples with Streams
     */

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        List<CarDealerApi> carDealerApiList = Arrays.asList(
                new CarDealerApi("mitsubishi"),
                new CarDealerApi("nissan"),
                new CarDealerApi("honda"),
                new CarDealerApi("toyota"),
                new CarDealerApi("mazda"));

        System.out.println(getPrices(carDealerApiList));
        long finaltTimeSync = System.currentTimeMillis();
        System.out.println("Tempo de consulta: " + ((finaltTimeSync - start) / 1000) + " seg \n");

        System.out.println(getPricesParallelStream(carDealerApiList));
        long finaltTimeParallelStream = System.currentTimeMillis();
        System.out.println("Tempo de consulta: " + ((finaltTimeParallelStream - finaltTimeSync) / 1000) + " seg \n");

        System.out.println(getPricesCompletableFeature(carDealerApiList));
        long finaltTimeCompletableFeature = System.currentTimeMillis();
        System.out.println("Tempo de consulta: " + ((finaltTimeCompletableFeature - finaltTimeParallelStream) / 1000) + " seg \n");

        System.out.println(getPricesCompletableFeatureAsync(carDealerApiList));
        long finaltTimeCompletableFeatureAsync = System.currentTimeMillis();
        System.out.println("Tempo de consulta: " + ((finaltTimeCompletableFeatureAsync - finaltTimeCompletableFeature) / 1000) + " seg \n");

    }

    private static List<String> getPrices(List<CarDealerApi> carDealerApiList) {
        System.out.println("\n--- sequencial stream ---");
        return carDealerApiList.stream()
                .map(carDealerApi -> String.format(BRAND_AND_PRICE, carDealerApi.getBrand(), carDealerApi.getCarPriceSync()))
                .collect(Collectors.toList());
    }

    private static List<String> getPricesParallelStream(List<CarDealerApi> carDealerApiList) {
        System.out.println("--- parallel stream ---");
        return carDealerApiList.parallelStream()
                .map(carDealerApi -> String.format(BRAND_AND_PRICE, carDealerApi.getBrand(), carDealerApi.getCarPriceSync()))
                .collect(Collectors.toList());
    }

    private static List<String> getPricesCompletableFeature(List<CarDealerApi> carDealerApiList) {
        System.out.println("--- completable feature ---");
        return carDealerApiList.stream()
                .map(carDealerApi -> CompletableFuture.supplyAsync(
                        () -> String.format(BRAND_AND_PRICE, carDealerApi.getBrand(), carDealerApi.getCarPriceSync())
                )).map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private static List<String> getPricesCompletableFeatureAsync(List<CarDealerApi> carDealerApiList) {
        System.out.println("--- completable feature async---");
        List<CompletableFuture<String>> carDealerListFuture = carDealerApiList.stream()
                .map(carDealerApi -> CompletableFuture.supplyAsync(
                        () -> String.format(BRAND_AND_PRICE, carDealerApi.getBrand(), carDealerApi.getCarPriceSync())
                )).collect(Collectors.toList());

        return carDealerListFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}
