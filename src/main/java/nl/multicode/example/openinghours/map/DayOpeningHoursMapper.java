package nl.multicode.example.openinghours.map;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import nl.multicode.example.openinghours.model.OpeningHours;

public interface DayOpeningHoursMapper {

    static final ZoneId AMSTERDAM_ZONE_ID = ZoneId.of("Europe/Amsterdam");

    OpeningHours map(OffsetDateTime offsetDateTime);

    default OffsetDateTime getOffsetDateTime(final OffsetDateTime dateTime, final LocalTime openingTime) {

        return dateTime.toLocalDate()
                .atTime(openingTime)
                .atZone(AMSTERDAM_ZONE_ID)
                .toOffsetDateTime();
    }
}
