package nl.multicode.example.openinghours.util;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import nl.multicode.example.openinghours.model.OpeningHours;

public class OpeningHoursCreator {

    public static OpeningHours create(final int year, final int month, final int dayOfMonth, final int openingHour, final int openingMinute,
            final int closingHour, final int closingMinute) {

        return OpeningHours.builder()
                .openTime(getDateTime(year, month, dayOfMonth, openingHour, openingMinute))
                .closeTime(getDateTime(year, month, dayOfMonth, closingHour, closingMinute))
                .build();
    }

    private static OffsetDateTime getDateTime(int year, int month, int day, int hour, int minute) {

        return OffsetDateTime.of(year, month, day, hour, minute, 0, 0,
                ZoneId.of("Europe/Amsterdam").getRules().getOffset(OffsetDateTime.now().toLocalDateTime()));
    }
}
