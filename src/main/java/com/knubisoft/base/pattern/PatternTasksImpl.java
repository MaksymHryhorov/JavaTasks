package com.knubisoft.base.pattern;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTasksImpl implements PatternTasks {
    private static Pattern pattern;
    private static Matcher matcher;

    @Override
    public boolean haveSetOfCharacters(String text) {
        if (text == null || text.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        pattern = Pattern.compile("[a-zA-Z0-9]*");
        matcher = pattern.matcher(text);

        return matcher.matches();
    }

    @Override
    public String matchByCharacters(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }

        if (text.trim().length() == 0) {
            return "Not matched!";
        }

        pattern = Pattern.compile("[pq]*");
        matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return "Found a match!";
        }

        return "Not matched!";
    }

    @Override
    public String matchStartAndEnd(String text) {
        if (text == null) {
            throw new IllegalArgumentException();
        }

        pattern = Pattern.compile("\\Bg\\B");
        matcher = pattern.matcher(text);

        if (matcher.find()) {
            return "Found a match!";
        }

        return "Not matched!";
    }

    @Override
    public String matchIpAddress(String text) {
        if (text == null || text.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

/*        pattern = Pattern.compile("[^0]");
        matcher = pattern.matcher(text);

        String formatted = "";

        if (matcher.find()) {

            formatted = text.replaceAll("[0]", "");
            return formatted;
        }*/

        return text.replaceAll("(?<=^|\\.)0+(?!\\.|$)", "");
    }

    @Override
    public String matchVowels(String text) {
        if (text == null || text.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        return text.replaceAll("[aeiouAEIOU]", "");
    }

    @Override
    public boolean validatePIN(String text) {
        if (text == null || text.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        pattern = Pattern.compile("\\d{4}|\\d{6}|\\d{8}");
        matcher = pattern.matcher(text);

        if (!matcher.matches()) {
            return false;
        }

        return true;
    }

    @Override
    public String divideDigit(int digit) {
        BigDecimal bd = new BigDecimal(digit);

        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setGroupingSeparator('#');

        DecimalFormat formatter = new DecimalFormat("###,###.##", symbols);

        return formatter.format(bd.longValue());
    }

    @Override
    public String removeAllNonAlphanumericCharacters(String text) {

        return text.replaceAll("[^a-zA-Z]", "");
    }

    @Override
    public boolean validatePhoneNumber(String text) {
        if (text == null || text.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        pattern = Pattern.compile(".[0-9]{3}.[0-9]{7}" + // “(123)4567890", "1234567890", "123-456-7890", "(123)456-7890",
                "|[0-9]{10}" +
                "|[0-9]{3}.[0-9]{3}.[0-9]{4}" +
                "|.[0-9]{3}.[0-9]{3}.[0-9]{4}");
        matcher = pattern.matcher(text);

        if (matcher.matches()) {
            return true;
        }

        return false;
    }

    @Override
    public String getLastVowelsByConstraint(String text, int n) {
        if (text == null || n <= -1 || n == 0 || n > text.length()) {
            throw new RuntimeException();
        }

        // String reg = "[^aeiouAEIOU]" + "{" + n + "}";

        String formatted = text.replaceAll("[^aeiouAEIOU]", "");

        return formatted;
    }

    @Override
    public boolean isMathematicalExpression(String text) {
        if (text == null || text.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        pattern = Pattern.compile("[0-9]*" +
                "|[0-9]*\s.\s[0-9]*");
        matcher = pattern.matcher(text);

        if (!matcher.matches()) {
            return false;
        }

        return true;
    }

    @Override
    public String insertDash(String text) {
        if (text == null || text.trim().length() == 0) {
            throw new IllegalArgumentException();
        }

        // ? - one or absent
        // find all UpperCaseLetter; find lowerCase
        return text.replaceAll("(?<=[A-Z])(?=[a-z])", "-");
    }
}
