package angelof.examples.completable_future;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutureExample2 {

  private static final String BRAND_AND_PRICE = "[Brand: %s - Price: %.2f U$] ";

  /**
   * About Completable Future - an example without control about CarDealer Service
   */

  public static void main(String[] args) {

    long start = System.currentTimeMillis();

    List<CarDealer> carDealerList = Arrays.asList(
      new CarDealer("mitsubishi"),
      new CarDealer("nissan"),
      new CarDealer("honda"),
      new CarDealer("toyota"),
      new CarDealer("mazda"));

    System.out.println(getPrices(carDealerList));
    long finaltTimeSync = System.currentTimeMillis();
    System.out.println("Tempo de consulta serviço sincrono: " + ((finaltTimeSync - start) / 1000) + " seg \n");

    System.out.println(getPricesParallelStream(carDealerList));
    long finaltTimeParallelStream = System.currentTimeMillis();
    System.out.println("Tempo de consulta serviço sincrono: " + ((finaltTimeParallelStream - finaltTimeSync) / 1000) + " seg \n");


    System.out.println(getPricesCompletableFeature(carDealerList));
    long finaltTimeCompletableFeature = System.currentTimeMillis();
    System.out.println("Tempo de consulta serviço sincrono: " + ((finaltTimeCompletableFeature - finaltTimeParallelStream) / 1000) + " seg \n");

    System.out.println(getPricesCompletableFeatureAsync(carDealerList));
    long finaltTimeCompletableFeatureAsync = System.currentTimeMillis();
    System.out.println("Tempo de consulta serviço sincrono: " + ((finaltTimeCompletableFeatureAsync - finaltTimeCompletableFeature) / 1000) + " seg \n");

  }

  private static List<String> getPrices(List<CarDealer> carDealerList) {
    System.out.println("--- sequencial stream ---");
    return carDealerList.stream()
      .map(carDealer -> String.format(BRAND_AND_PRICE, carDealer.getBrand(), carDealer.getCarPriceSync()))
      .collect(Collectors.toList());
  }

  private static List<String> getPricesParallelStream(List<CarDealer> carDealerList) {
    System.out.println("--- parallel stream ---");
    return carDealerList.parallelStream()
      .map(carDealer -> String.format(BRAND_AND_PRICE, carDealer.getBrand(), carDealer.getCarPriceSync()))
      .collect(Collectors.toList());
  }

  private static List<String> getPricesCompletableFeature(List<CarDealer> carDealerList) {
    System.out.println("--- completable feature ---");
    return carDealerList.stream()
      .map(carDealer -> CompletableFuture.supplyAsync(
        () -> String.format(BRAND_AND_PRICE, carDealer.getBrand(), carDealer.getCarPriceSync())
      )).map(CompletableFuture::join)
      .collect(Collectors.toList());
  }

  private static List<String> getPricesCompletableFeatureAsync(List<CarDealer> carDealerList) {
    System.out.println("--- completable feature async---");
    List<CompletableFuture<String>> carDealerListFuture = carDealerList.stream()
      .map(carDealer -> CompletableFuture.supplyAsync(
        () -> String.format(BRAND_AND_PRICE, carDealer.getBrand(), carDealer.getCarPriceSync())
      )).collect(Collectors.toList());

    return carDealerListFuture.stream().map(CompletableFuture::join).collect(Collectors.toList());
  }

}
