package angelof.examples.helper;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrincipalService {
  private static final Integer MIN_NUM = 1;
  private static final Integer MAX_NUM = new Random().nextInt(100);

  private PrincipalService() {
  }

  public static Integer execute() {
    return recursionAction(0, IntStream.rangeClosed(MIN_NUM, MAX_NUM).boxed().collect(Collectors.toList()));
  }

  public static Integer recursionAction(int pos, List<Integer> numbersList) {
    return pos < numbersList.size() ? numbersList.get(pos) + recursionAction(pos + 1, numbersList) : 0;
  }
}
