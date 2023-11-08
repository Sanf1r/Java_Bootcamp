package edu.school21.numbers;

public class NumberWorker {
    static boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException("Number must be strictly greater than 1!");
        }
        for (int i = 2; i * i <= number; ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    static int digitsSum(int number) {
        int res = 0;
        while (number / 10 != 0) {
            res += number % 10;
            number /= 10;
        }
        res += number % 10;
        return res;
    }
}
