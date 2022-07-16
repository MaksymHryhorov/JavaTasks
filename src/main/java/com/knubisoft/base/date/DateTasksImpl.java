package com.knubisoft.base.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class DateTasksImpl implements DateTasks {

    @Override
    public String add1Day(String date) {

        LocalDate localDate = LocalDate.parse(date);
        LocalDate newLocal = localDate.plusDays(1);


        return String.valueOf(newLocal);
    }

    @Override
    public int getMonthFromDate(String date) {                        // Mon, 26 Nov 2018
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("EEE, d MMM yyyy", Locale.ENGLISH);
        LocalDate dateTime = LocalDate.parse(date, pattern);

        return dateTime.getMonthValue();
    }

    @Override
    public String findBiggestDate(String date1, String date2, String date3) {
        //2015-01-22 10:15:55
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        LocalDateTime dateTime1 = LocalDateTime.parse(date1, pattern); // "yyyy-MM-dd HH:mm:ss"
        LocalDateTime dateTime2 = LocalDateTime.parse(date2, pattern); // "yyyy-MM-dd HH:mm:ss"
        LocalDateTime dateTime3 = LocalDateTime.parse(date3, pattern); // "yyyy-MM-dd HH:mm:ss"

        if (dateTime1.isAfter(dateTime2) && dateTime1.isAfter(dateTime3)) {
            return date1;
        } else if (dateTime2.isAfter(dateTime1) && dateTime2.isAfter(dateTime3)) {
            return date2;
        } else if (dateTime3.isAfter(dateTime1) && dateTime3.isAfter(dateTime2)) {
            return date3;
        }

        return date3;
    }

    @Override
    public String getLastDayOfTheMonth(String date) {
        LocalDate localDate = LocalDate.parse(date);
        int year = localDate.getYear();
        int month = localDate.getMonthValue();

        YearMonth yearMonthObject = YearMonth.of(year, month);
        int daysInMonth = yearMonthObject.lengthOfMonth();

        LocalDate newDate = LocalDate.of(year, month, daysInMonth);

        return String.valueOf(newDate);
    }

    @Override
    public String sumTimes(String time1, String time2) {
        //07:00:00
        LocalTime localTime1 = LocalTime.parse(time1, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime localTime2 = LocalTime.parse(time2, DateTimeFormatter.ofPattern("HH:mm:ss"));

        LocalTime result = localTime1.plusHours(localTime2.getHour())
                .plusMinutes(localTime2.getMinute())
                .plusSeconds(localTime2.getSecond());

        String resultForZero = "0";

        if (result.getSecond() < 1) {
            return String.valueOf(result + ":" + resultForZero + 0);
        }

        return String.valueOf(result);
    }

    @Override
    public String getDateAfter2Weeks(String date) {
        LocalDate localDate = LocalDate.parse(date);
        LocalDate newDate = localDate.plusWeeks(2);

        return String.valueOf(newDate);
    }

    @Override
    public long getNumberOfDaysBetweenTwoDates(String date1, String date2) {
        LocalDate firstDate = LocalDate.parse(date1);
        LocalDate secondDate = LocalDate.parse(date2);

        return secondDate.toEpochDay() - firstDate.toEpochDay();
        //return (int)( (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24));
        //return ChronoUnit.DAYS.between(startDate,endDate);
    }

    @Override
    public String[] getTheNextAndPreviousFriday(String date) {
        String[] dates = new String[2];

        LocalDate localDate = LocalDate.parse(date);
        LocalDate previousF = localDate.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
        LocalDate nextF = localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

        dates[0] = String.valueOf(previousF);
        dates[1] = String.valueOf(nextF);

        return dates;
    }

    @Override
    public int getNumberOfMonthsRemainingInTheYear(String date) {
        LocalDate someDate = LocalDate.parse(date);

        int month = someDate.getMonthValue();
        int result = 0;

        for (int i = month; i < 12; i++) {
            result += 1;
        }

        return result;
    }
}
