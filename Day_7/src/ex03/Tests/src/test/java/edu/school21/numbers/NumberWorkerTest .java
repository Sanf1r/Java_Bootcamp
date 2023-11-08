package edu.school21.numbers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

class NumberWorkerTest {

    private final NumberWorker wrk = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89,
            97 })
    void isPrimeForPrimes(int number) {
        assertTrue(wrk.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = { 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20, 21, 22, 24, 25, 26, 27, 28, 30 })
    void isPrimeForNotPrimes(int number) {
        assertFalse(wrk.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = { -4, -6, -8, -9, -10, -12, 0, 1 })
    void isPrimeForIncorrectNumbers(int number) {
        assertThrows(IllegalNumberException.class, () -> wrk.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void CorrectDigitsSum(int number, int reference) {
        assertEquals(wrk.digitsSum(number), reference);
    }
}