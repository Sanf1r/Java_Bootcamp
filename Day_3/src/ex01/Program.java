package ex01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Program {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Wrong arg count!");
            System.exit(-1);
        }
        List<String> result = new ArrayList<>();
        List<String> one = new ArrayList<>();
        List<String> two = new ArrayList<>();
        readFile(result, one, args, 1);
        readFile(result, two, args, 2);
        result.sort(Comparator.naturalOrder());

        Integer[] vectOne = new Integer[result.size()];
        Arrays.fill(vectOne, 0);
        Integer[] vectTwo = new Integer[result.size()];
        Arrays.fill(vectTwo, 0);
        String[] resAsArr = result.toArray(new String[result.size()]);

        for (int i = 0; i < resAsArr.length; ++i) {
            vectOne[i] = Collections.frequency(one, resAsArr[i]);
        }

        for (int i = 0; i < resAsArr.length; ++i) {
            vectTwo[i] = Collections.frequency(two, resAsArr[i]);
        }

        try {
            FileWriter fileWriter = new FileWriter("./ex01/dictionary.txt");
            for (int i = 0; i < resAsArr.length; ++i) {
                if (i == resAsArr.length - 1) {
                    fileWriter.write(resAsArr[i]);
                } else {
                    fileWriter.write(resAsArr[i] + ", ");
                }
            }
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.printf("Similarity = %.2f%n", calculation(result, vectOne, vectTwo));

    }

    private static void readFile(List<String> result, List<String> words, String[] args, int num) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(args[num - 1]));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(" ");
                for (int i = 0; i < split.length; ++i) {
                    if (!result.contains(split[i])) {
                        result.add(split[i]);
                    }
                    words.add(split[i]);
                }
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Something wrong with file!");
            System.exit(-1);
        }
    }

    private static Double calculation(List<String> result, Integer[] vectOne, Integer[] vectTwo) throws IOException {
        Integer numerator = 0;
        for (int i = 0; i < result.size(); ++i) {
            numerator += vectOne[i] * vectTwo[i];
        }

        Double left = 0.;
        Double right = 0.;
        for (int i = 0; i < result.size(); ++i) {
            left += vectOne[i] * vectOne[i];
            right += vectTwo[i] * vectTwo[i];
        }
        return numerator / (Math.sqrt(left) * Math.sqrt(right));
    }
}
