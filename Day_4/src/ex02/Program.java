package ex02;

import java.util.Arrays;
import java.util.Random;

public class Program {
    public static void main(String[] args) {

        if (args.length == 2 && args[0].startsWith("--arraySize=") &&
                args[1].startsWith("--threadsCount")) {
            try {
                int result = 0;
                Integer size = Integer.parseInt(args[0].substring(12));
                Integer count = Integer.parseInt(args[1].substring(15));
                if (count > 0 && count <= size && size <= 2000000) {
                    Random random = new Random();
                    Integer[] randArr = new Integer[size];
                    Arrays.setAll(randArr, i -> random.nextInt(1000 - 0) + 0);

                    System.out.println("Sum: " + Arrays.stream(randArr)
                            .mapToInt(Integer::intValue)
                            .sum());
                    Multi[] thArr = new Multi[count];

                    float partSize = randArr.length / (float) count;

                    for (int i = 0; i < thArr.length; ++i) {
                        thArr[i] = new Multi(i + 1, randArr,
                                (int) Math.ceil(i * partSize),
                                (int) Math.ceil(i * partSize + partSize));
                    }

                    for (Multi thread : thArr) {
                        thread.start();
                    }

                    for (Multi thread : thArr) {
                        thread.join();
                    }

                    for (Multi thread : thArr) {
                        result += thread.getResult();
                        System.out.println(thread);
                    }

                    System.out.println("Sum by threads: " + result);
                } else {
                    System.err.println("Wrong input data!");
                    System.exit(-1);
                }
            } catch (Exception e) {
                System.err.println("Wrong input data!");
                System.exit(-1);
            }
        } else {
            System.err.println("Wrong input data!");
            System.exit(-1);
        }
    }
}
