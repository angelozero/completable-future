package angelof.examples.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class PrincipalService {
    private final Integer MIN_NUM = 1;
    private final Integer MAX_NUM = new Random().nextInt(1000) + 1;


    public Integer execute() {
        return recursionAction(0, IntStream.rangeClosed(MIN_NUM, MAX_NUM).boxed().collect(Collectors.toList()));
    }

    public Integer recursionAction(int pos, List<Integer> numbersList) {
        return pos < numbersList.size() ? numbersList.get(pos) + recursionAction(pos + 1, numbersList) : 0;
    }
}
