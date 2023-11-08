package ex03;

import java.util.Scanner;

public class Program {
    static long numCutter(long num, int week, int counter) {
        long ex = 1;
        for (int i = 1; i < counter + 1 - week; ++i) {
            ex *= 10;
        }
        long res = (num / ex) % 10;
        return res;
    }

    public static void main(String args[]) {
        long res = 0;
        int min = 10;
        int counter = 0;
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        while (!str.equals("42") && counter < 18) {
            if (!str.equals("Week " + ++counter)) {
                System.err.println("Illegal Argument");
                System.exit(-1);
            } else {
                min = 10;
                for (int i = 0; i < 5; ++i) {
                    int temp = in.nextInt();
                    if (temp < min)
                        min = temp;
                }
                res += min;
                res *= 10;
                in.nextLine();
            }
            if (counter < 18)
                str = in.nextLine();
        }
        res /= 10;

        for (int i = 1; i <= counter; ++i) {
            System.out.print("Week " + i + " ");
            int stop = (int) numCutter(res, i, counter);
            for (int j = 0; j < stop; ++j) {
                System.out.print("=");
            }
            System.out.println(">");

        }
        in.close();
    }
}
