package ex04;

import java.util.Scanner;

public class Program {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        if (line.equals("")) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        char[] char_arr = line.toCharArray();
        int[] numbers = new int[65535];
        for (int i = 0; i < line.length(); ++i) {
            numbers[char_arr[i]]++;
        }
        int[] occurrences = new int[10];
        char[] letters = new char[10];
        int index = 0;
        int max = 0;

        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 65535; ++j) {
                if (numbers[j] > 999) {
                    System.err.println("Illegal Argument");
                    System.exit(-1);
                }
                if (numbers[j] > max) {
                    max = numbers[j];
                    index = j;
                }
            }
            if (max > 0 && max < 1000) {
                occurrences[i] = max;
                letters[i] = (char) index;
                max = 0;
                numbers[index] = 0;
            }
        }

        max = occurrences[0];
        int[] hash = new int[10];
        for (int i = 0; i < 10; ++i) {
            hash[i] = occurrences[i] * 10 / max;
        }

        System.out.println();
        for (int i = 0; i < 10; ++i) {
            if (occurrences[i] == max) {
                System.out.print(occurrences[i] + "\t");
            }
        }
        System.out.println();
        for (int i = 10; i > 0; --i) {
            for (int j = 0; j < 10; ++j) {
                if (hash[j] >= i)
                    System.out.print("#\t");
                if (i - hash[j] == 1) {
                    if (occurrences[j] > 0)
                        System.out.print(occurrences[j] + "\t");
                }
            }
            System.out.println();
        }

        for (int i = 0; i < 10; ++i) {
            if (letters[i] != '\0')
                System.out.print(letters[i] + "\t");
        }
        System.out.println();
        in.close();
    }
}
