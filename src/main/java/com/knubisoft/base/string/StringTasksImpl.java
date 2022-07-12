package com.knubisoft.base.string;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class StringTasksImpl implements StringTasks {

    @Override
    public String reverseString(String original) {
        // First Method
        /* StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(original);
        stringBuilder.reverse();

        return stringBuilder.toString();
        return new StringBuilder(original).reverse().toString(); */

        if (original == null) {
            throw new IllegalArgumentException();
        }
        // Second method
        String result = "";
        // change our string to char array (A - 41 etc.)
        char[] arrayString = original.toCharArray();

        // Why arrayString.length - 1? because array count start with 0.
        // While char array >= 0; arrayString[41] -> [41] write in result -> []
        // break;
        for (int i = arrayString.length - 1; i >= 0; i--) {
            result = result + arrayString[i];
        }

        System.out.println(result);
        return result;
    }

    @Override
    public String insertStringInMiddle(String original, String toInsert) {
        if (original == null || toInsert == null || original.isEmpty() || toInsert.isEmpty()) {
            throw new IllegalArgumentException();
        }

        toInsert = toInsert.replaceAll("\\r\\n", "\n");

        int centerLine = original.length() / 2;
        // {Hello; World}  He (0-2)       + World    + (2-5) llo  = HeWorldllo
        String result = original.substring(0, centerLine) + toInsert + original.substring(centerLine);

        return result;
    }

    @Override
    public String insertSymbolInString(String original, char toInsert, int position) {
        if (original == null || original.isEmpty()) {
            throw new IllegalArgumentException();
        }

        if (position > original.length() || position < 0) {
            throw new IllegalArgumentException();
        }

        String result = original.substring(0, position) + toInsert + original.substring(position);

        return result;
    }

    @Override
    public String appendToString(StringBuilder original, String toAppend) {
        if (toAppend == null || original == null || toAppend.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (original.toString().isEmpty()) {
            throw new NoSuchElementException();
        }

        return original + toAppend;
    }

    @Override
    public boolean isPalindrome(String palindrome) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(palindrome);

        stringBuilder.reverse();

        if (palindrome.equals(stringBuilder.toString())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean hasLowerCase(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String test = str.toLowerCase();

        if (test.equals(str)) {
            return true;
        }

        return false;
    }

    @Override
    public String uniqueCharacters(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder();
        String formatted = str.toLowerCase();


        // The indexOf() method returns the position of the first(last) occurrence of specified character(s) in a string.
        for (int i = 0; i < formatted.length(); i++) {
            //                              [0] -> b   != [2] -> b
            //                              [1] -> o   == [1] -> o
            //                              [0] -> b   != [2] -> b
            if (formatted.indexOf(formatted.charAt(i)) == formatted.lastIndexOf(formatted.charAt(i))) {
                stringBuilder.append(formatted.charAt(i));

            }

        }

        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }

    @Override
    public String removeAllCharacters(String str, char charToRemove) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return str.replaceAll(String.valueOf(charToRemove), "");
    }

    @Override
    public String toCamelCase(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String[] formattedString = str.split("[\\W_]");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < formattedString.length; i++) {
            String word = formattedString[i];

            // condition ? trueStatement : falseStatement

            if (i == 0) {
                word = word.isEmpty() ? word : word.toLowerCase();
            } else {
                word = word.isEmpty() ? word : Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
            }

            result.append(word);

        }

        return result.toString();
    }

    @Override
    public String getCountRepeatableString(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder stringBuilder = new StringBuilder();

        char[] chars = str.toCharArray();
        int count = 1;

        // Find duplicate elements in an array (Brute force algorithm)
        for (int i = 0; i < chars.length; i++) {
            count = 1;

            // replace each char to 1
            stringBuilder.append(count);

            // Code simply goes through all the possible values that could be held
            // and checks to see whether it has found it or not.
            // If it doesn't, it moves on to the next possible combination until it does.
            for (int j = i + 1; j < chars.length; j++) {
                // checking if count >= 9 -> count = 0, why >=9? because down below we do count++.
                // Imagine if count = 9, then we do count++ and answer is 10, but we need 1
                if (count >= 9) {
                    count = 0;
                }

                if (chars[i] == chars[j]) {
                    count++;
                    stringBuilder.append(count);
                }

            }

        }

        return stringBuilder.toString();
    }

    @Override
    public String sortStringCharactersAsc(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        char[] array = str.toCharArray();
        Arrays.sort(array);

        return String.copyValueOf(array);
    }
}
