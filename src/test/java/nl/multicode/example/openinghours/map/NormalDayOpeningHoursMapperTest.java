package nl.multicode.example.openinghours.map;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import nl.multicode.example.openinghours.model.OpeningHours;
import org.junit.jupiter.api.Test;

class NormalDayOpeningHoursMapperTest {

    private final NormalDayOpeningHoursMapper mapper = new NormalDayOpeningHoursMapper();

    @Test
    void testMondayOpeningHours_weekDay() {

        OffsetDateTime monday = OffsetDateTime.parse("2023-03-13T12:00:00+01:00"); // A Monday
        OpeningHours hours = mapper.map(monday);
        assertThat(hours.getOpenTime().getHour()).isEqualTo(9);
        assertThat(hours.getCloseTime().getHour()).isEqualTo(19);
    }

    @Test
    void testMondayOpeningHours_weekendDay_saterday() {

        OffsetDateTime monday = OffsetDateTime.parse("2023-03-18T12:00:00+01:00"); // A Monday
        OpeningHours hours = mapper.map(monday);
        assertThat(hours.getOpenTime().getHour()).isEqualTo(9);
        assertThat(hours.getCloseTime().getHour()).isEqualTo(16);
    }

    @Test
    void testMondayOpeningHours_weekendDay_sunday() {

        OffsetDateTime monday = OffsetDateTime.parse("2023-03-19T12:00:00+01:00"); // A Monday
        OpeningHours hours = mapper.map(monday);
        assertThat(hours.getOpenTime()).isNull();
        assertThat(hours.getCloseTime()).isNull();
    }
}
