package com.knubisoft.base.arrays;

import java.util.Arrays;

public class ArraysTasksImpl implements ArraysTasks {

    @Override
    public int[] reverse(int[] array) {
        int j = array.length;
        int[] reverseArray = new int[array.length];

        // fill array since the end [--j]
        for (int value : array)
            reverseArray[--j] = value;

        return reverseArray;
    }

    @Override
    public int[] mergeArrays(int[] array1, int[] array2) {
        int i = 0; // 0 because we must fill our first array from 0 element
        int j = array1.length; //first array fill to this length then we fill our second array

        int[] sumLength = new int[array1.length + array2.length]; // count sum two arrays
        int[] concatArray = new int[sumLength.length]; // this array has sum of two arrays length and will be fill

        // fill concatArray from 0 element
        for (int value : array1)
            concatArray[i++] = value;

        // fill concatArray from array1.length element
        for (int value : array2)
            concatArray[j++] = value;

        return concatArray;
    }

    @Override
    public int[] findMax3InArray(int[] array) {
        // If the number of elements in the input array is less than 3, just return it.
        if (array.length < 3) {
            return array;
        }

        // sort by ascending order
        Arrays.sort(array);

        int count = 0;
        int[] maxValues = new int[3];

        // take last three elements
        for (int i = array.length - 3; i < array.length; i++) {
            maxValues[count++] = array[i];

        }

        // do reverse array
        int j = maxValues.length;
        int[] reverseArray = new int[maxValues.length];

        // fill array since the end [--j]
        for (int value : maxValues)
            reverseArray[--j] = value;

        return reverseArray;
    }

    @Override
    public int findLongestIncreasingContinuesSubsequence(int[] array) {

        if (array == null || array.length == 0) {
            return 0;
        }

        // count max column
        int maxColum = 1;

        //count all count
        int count = 1;


        for (int i = 1; i < array.length; i++) {
            // compare [i] element with [i-1] if yes, count++
            if (array[i] > array[i - 1]) {
                count++;
            }
            // else [i] < [i-1] count = 1 and find next continuous increasing subsequence
            else {
                count = 1;
            }

            //find max iteration
            maxColum = Math.max(count, maxColum);
        }

        return maxColum;
    }

    @Override
    public int sumOfAllUniqueElements(int[] array) {

        int sumUniqueNumbers = 0;
        int sumAllNumbers = 0;

        for (int i = 0; i < array.length; i++) {
            // sum all numbers in array
            sumAllNumbers += array[i];

            for (int j = i + 1; j < array.length; j++) {
                if (array[i] == array[j]) {
                    // sum only equal numbers
                    sumUniqueNumbers += array[j];
                    break;
                }
            }

        }

        return sumAllNumbers - sumUniqueNumbers;
    }

    @Override
    public int[] moveZeroes(int[] array) {
        int j = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) {     // element: 0, 7
                int temp = array[j]; // if element array[1] != 0 -> temp = array[0] (0)
                array[j] = array[i]; // array[0] = array[1]
                array[i] = temp;     // array[1] = 0
                j++;                 // j++
            }                        // element: 7, 0
        }

        return array;
    }

    @Override
    public int findFinalValue(int[] nums, int original) {

        int count = original;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == count) {
                    count = count * 2;

                }

            }

        }

        return count;
    }

    @Override
    public String longestCommonPrefix(String[] words) {
        StringBuilder builder = new StringBuilder("");

        // {}
        if (words.length == 0) {
            return "";
        }

        // {apple}
        if (words.length == 1) {
            builder.append(words[0]);
            return builder.toString(); // apple
        }

        Arrays.sort(words);

        int length = words[0].length(); // count length element[0] because in for we cannot use words.length

        // {apple, applause, application}
        for (int i = 0; i < length; i++) {
            // if the first char == first char in the next element, add his to builder;
            if (words[0].charAt(i) == words[words.length - 1].charAt(i)) {
                builder.append(words[0].charAt(i));
            } else {
                break;
            }

        }
        return builder.toString();
    }

    @Override
    public int missingNumber(int[] array) {
        Arrays.sort(array); //{ 0, 1, 3}

        int result = 0;

        for (int i = 0; i < array.length; i++) {
            result++; // count each entry into the for

            // if the first element - 1 does not equal the 0 element, return the result

            try {
                if (array[i + 1] - 1 != array[i]) {
                    return result;
                }

                if (array[0] != 0) {
                    return 0;
                }

            } catch (IndexOutOfBoundsException e) {
                System.err.println("IndexOutOfBoundsException");
            }

        }


        return result;
    }

    @Override
    public boolean containsDuplicate(int[] array) {

        // sort array {
        Arrays.sort(array); // if elements are repeated then [0 element] == [1 element]

        for (int i = 0; i < array.length; i++) {
            try {
                if (array[i] == array[i + 1]) {
                    return true;
                }
            } catch (Exception e) {
                return false;
            }

        }

        return false;
    }
}
