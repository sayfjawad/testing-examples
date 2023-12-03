package nl.multicode.example.openinghours.map;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import nl.multicode.example.openinghours.model.OpeningHours;

public class NormalDayOpeningHoursMapper implements DayOpeningHoursMapper {


    public OpeningHours map(OffsetDateTime dateTime) {

        final var dayOfWeek = dateTime.getDayOfWeek();

        LocalTime openingTime;
        LocalTime closingTime;

        switch (dayOfWeek) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                // Weekdays: Open from 9:00 AM to 7:00 PM
                openingTime = LocalTime.of(9, 0);
                closingTime = LocalTime.of(19, 0);
                return OpeningHours.builder()
                        .openTime(getOffsetDateTime(dateTime, openingTime))
                        .closeTime(getOffsetDateTime(dateTime, closingTime))
                        .build();
            case SATURDAY:
                // Saturdays: Open from 9:00 AM to 4:00 PM
                openingTime = LocalTime.of(9, 0);
                closingTime = LocalTime.of(16, 0);
                return OpeningHours.builder()
                        .openTime(getOffsetDateTime(dateTime, openingTime))
                        .closeTime(getOffsetDateTime(dateTime, closingTime))
                        .build();
            case SUNDAY:
                // Sundays: Closed
                return OpeningHours.builder().build();
            default:
                throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeek);
        }
    }
}

