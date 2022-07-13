package com.knubisoft.base.bool;

import java.util.Arrays;

public class BoolTasksImpl implements BoolTasks {

    @Override
    public Boolean isTrueAutobox(boolean value) {
        if (value) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean isFalseAutobox(boolean value) {
        if (!value) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isTrueUnbox(Boolean value) {
        if (value) {
            return true;
        }

        return false;
    }

    @Override
    public Boolean isFalseUnbox(boolean value) {
        if (!value) {
            return false;
        }

        return true;
    }

    @Override
    public boolean andFunction(int digit, String string) {
        try {

            if (digit == Integer.parseInt(string)) {
                return true;
            }

        } catch (NumberFormatException e) {
            System.err.println("Invalid Number Format");
        }

        return false;
    }

    @Override
    public boolean orFunction(int digit, String string) {
        if (string == null || string.contains(" ")) {
            return false;
        }

        try {
            if (digit == Integer.parseInt(string)) {
                return true;
            }

        } catch (NumberFormatException e) {
            System.err.println("Number Format is wrong");
        }

        return false;
    }

    @Override
    public boolean isSameSizeArray(Object[] firstArray, Object... secondArray) {
        try {
            String[] stringArray = Arrays.copyOf(firstArray, firstArray.length, String[].class);
            String[] stringArray2 = Arrays.copyOf(secondArray, secondArray.length, String[].class);

            if (stringArray.length == stringArray2.length) {
                return true;
            }

        } catch (NullPointerException e) {
            System.err.println("FirstArray or secondArray is null");
        }

        return false;
    }

    @Override
    public boolean isSameCharactersCount(String username, String name, int maxLength) {
        try {
            if (username.isEmpty() || name.isEmpty() || maxLength == 0) {
                return false;
            }

            char[] usernameArray = username.toCharArray();
            char[] nameArray = name.toCharArray();

            // If you need to count same symbol in 2 arrays
            /*System.out.println(Arrays.toString(usernameArray));
            System.out.println(Arrays.toString(nameArray));
            int count = 0;

            for (int firstValue : usernameArray) {
                for (int secondValue : nameArray) {
                    if (firstValue == secondValue) {
                        count++;
                    }
                }
            }*/

            try {
                if (usernameArray.length < maxLength || nameArray.length < maxLength) {
                    return true;
                } else {
                    System.err.println("Length less then characters");
                    return false;
                }
            } catch (IndexOutOfBoundsException e) {

            }

        } catch (NullPointerException e) {
            System.err.println("username or user is null");
        }

        return false;
    }
}
