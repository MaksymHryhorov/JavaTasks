package com.knubisoft.tasks.algorithm.fibonacci;

public class FibonacciImpl implements Fibonacci {
    @Override
    public int generateNFibonacciSequence(int n) {

        if (n >= 100) {
            throw new NumberFormatException();
        }

        //return firstRealization(n);
        return secondRealization(n);
    }

    // F0 = 0, F1 = 1; Fn = F0 + F1 (n amount)
    public int firstRealization(int n) {
        int f0 = 0;
        int f1 = 1;
        int result = n;

        for (int i = 1; i < n; i++) {
            result = f0 + f1;
            f0 = f1;
            f1 = result;
        }

        return result;
    }

    // F0 = 1, F1 = 1, Fn = F(n-1) + F(n-2)
    public int secondRealization(int n) {
        if (n <= 1) {
            return n;
        }

        int[] array = new int[n];
        array[0] = 1;
        array[1] = 1;

        for (int i = 2; i < array.length; i++) {
            array[i] = array[i - 1] + array[i - 2];
        }

        return array[array.length - 1]; //return last element
    }
}
