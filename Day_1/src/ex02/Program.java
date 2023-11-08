package ex02;

import java.util.Scanner;

public class Program {
    static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; ++i) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    static int numCutter(int num) {
        int res = 0;
        while (num / 10 != 0) {
            res += num % 10;
            num /= 10;
        }
        res += num % 10;
        return res;
    }

    public static void main(String args[]) {
        int num = 0;
        int count = 0;
        Scanner in = new Scanner(System.in);
        while (num != 42) {
            num = in.nextInt();
            if (isPrime(numCutter(num)))
                ++count;
        }
        in.close();
        System.out.println("Count of coffee-request – " + count);
    }
}
