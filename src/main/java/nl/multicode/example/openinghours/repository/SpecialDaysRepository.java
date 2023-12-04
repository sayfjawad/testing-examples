package nl.multicode.example.openinghours.repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import nl.multicode.example.openinghours.model.OpeningHours;
import nl.multicode.example.openinghours.util.OpeningHoursCreator;

public class SpecialDaysRepository {

    private final Map<LocalDate, OpeningHours> specialDays = new HashMap<>();


    public SpecialDaysRepository() {

        // Add special days to repository
        // New years
        addSpecialDay(2024, 12, 31, 9, 0, 16, 0);
        // Valentine
        addSpecialDay(2024, 2, 14, 9, 0, 16, 0);
        // Easter
        addSpecialDay(2024, 3, 31, 9, 0, 16, 0);
        // Kings day
        addSpecialDay(2024, 4, 27, 9, 0, 16, 0);
        // Second Xmas 1st day (closed)
        addSpecialDay(LocalDate.of(2024, 12, 25), OpeningHours.builder().build());
        // Second Xmas 2nd day
        addSpecialDay(2024, 12, 26, 10, 0, 16, 0);
    }

    public Optional<OpeningHours> getSpecialDayHours(LocalDate date) {

        return Optional.ofNullable(specialDays.get(date));
    }

    public void addSpecialDay(final int year, final int month, final int dayOfMonth, final int openingHour, final int openingMinute,
            final int closingHour, final int closingMinute) {

        addSpecialDay(LocalDate.of(year, month, dayOfMonth),
                OpeningHoursCreator.create(year, month, dayOfMonth, openingHour, openingMinute, closingHour, closingMinute));
    }

    public void addSpecialDay(LocalDate date, OpeningHours hours) {

        specialDays.put(date, hours);
    }
}
