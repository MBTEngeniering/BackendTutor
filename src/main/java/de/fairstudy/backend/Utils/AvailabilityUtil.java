package de.fairstudy.backend.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AvailabilityUtil {

    public static LocalDateTime toDateTime(String tag, String monat, String jahr, String uhrzeit) {
        int day = Integer.parseInt(tag);
        int month = Integer.parseInt(monat);
        int year = Integer.parseInt(jahr);

        LocalDate date = LocalDate.of(year, month, day);
        LocalTime time = LocalTime.parse(uhrzeit); // erwartet Format "HH:mm"

        return LocalDateTime.of(date, time);
    }
}
