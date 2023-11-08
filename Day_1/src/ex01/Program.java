package ex01;

import java.util.Scanner;

public class Program {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        if (num <= 1) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        } else {
            int count = 1;
            boolean res = true;
            for (int i = 2; i * i <= num; ++i, ++count) {
                if (num % i == 0) {
                    res = false;
                    break;
                }
            }
            System.out.println("" + res + " " + count);
        }
        in.close();
    }
}
