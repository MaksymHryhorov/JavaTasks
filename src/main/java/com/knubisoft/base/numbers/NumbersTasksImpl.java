package com.knubisoft.base.numbers;

import java.math.BigInteger;

public class NumbersTasksImpl implements NumbersTasks {

    @Override
    public int swapTwoNumbersWithoutUsingTemporaryVariable(int firstNumber, int secondNumber) {
        int change = secondNumber;
        secondNumber = firstNumber;
        firstNumber = change;

        return Integer.parseInt(firstNumber + "" + secondNumber);


    }

    @Override
    public boolean isUglyInt(int number) {

        return false;
    }

    @Override
    public int addDigits(int number) {
        //return number - 9 * ((number - 1) / 9);
        return number > 9 ? (number % 9 == 0 ? 9 : number % 9) : number;

    }

    @Override
    public boolean isHarshadNumber(int number) {
        if (number == 0) {
            return false;
        }
        String value = Integer.toString(number);

        int sum = 0;

        for (int i = 0; i < value.length(); i++) {
            sum += value.charAt(i) - '0';
        }

        return number % sum == 0;

/*
        if (number == 0) {
            return false;
        }

        int y = 0;
        int x = number;
        int sum = 0;

        while (x > 0) {
            y = x % 10;
            sum = sum + y;
            x = x / 10;
        }


        if (number % sum == 0)
            return true;*/
    }

    @Override
    public boolean isPrime(int number) {
        if (number < 2 || number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        for (int i = 5; i <= Math.sqrt(number) + 1; i = i + 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isArmstrongNumber(int number) {

        String value = Integer.toString(number);

        double res = 0;

        for (int i = 0; i < value.length(); i++) {
            res += Math.pow(value.charAt(i) - '0', value.length());

        }

        return (int) res == number;
    }

    @Override
    public BigInteger factorial(int number) {

        BigInteger result = BigInteger.valueOf(1);

        for (int i = number; i > 0; i--) {
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }

    @Override
    public boolean palindrome(int number) {
        String str = String.valueOf(number);
        String num = "";

        // reverse numbers
        for (int i = str.length() - 1; i >= 0; i--) {
            num = num + str.charAt(i);
        }

        if (!num.equals(str)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isAutomorphic(int number) {
/*        int exp = number * number;

        while (number > 0) {

            if (number % 10 != exp % 10) {
                return false;
            }
            number = number / 10;
            exp = exp / 10;
        }*/

        int exp = number*number;

        String str_num = Integer.toString(number);
        String square = Integer.toString(exp);

        if(square.endsWith(str_num))
            return true;

        return false;

    }
}
