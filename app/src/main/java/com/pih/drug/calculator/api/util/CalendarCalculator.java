package com.pih.drug.calculator.api.util;

import java.util.Calendar;

public class CalendarCalculator {
    public static final int MINIMUM_DAYS = 30;
    public static final int DIVIDER_MULTIPLIER = 2;

    public static Calendar calculateNextAppointmentDate(Calendar calendar, int numberOfDrugs) {
        int divider = numberOfDrugs / MINIMUM_DAYS;
        int daysToNextAppointment = numberOfDrugs - (divider * DIVIDER_MULTIPLIER);
        calendar.add(Calendar.DAY_OF_MONTH,daysToNextAppointment);
        return calendar;
    }
}
