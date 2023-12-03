package nl.multicode.example.openinghours;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Month;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import nl.multicode.example.openinghours.map.NormalDayOpeningHoursMapper;
import nl.multicode.example.openinghours.map.SpecialDayOpeningHoursMapper;
import nl.multicode.example.openinghours.repository.SpecialDaysRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OpeningTimeServiceTest {

    private final static ZoneId AMSTERDAM_ZONE_ID = ZoneId.of("Europe/Amsterdam");
    @Mock
    private NormalDayOpeningHoursMapper normalDayOpeningHoursMapper;
    @Mock
    private SpecialDayOpeningHoursMapper specialDayOpeningHoursMapper;
    @Mock
    private SpecialDaysRepository specialDaysRepository;

    @InjectMocks
    private OpeningTimeService service;

    private static OffsetDateTime getDateTime(int year, int month, int day, int hour, int minute, int second) {

        return OffsetDateTime.of(year, month, day, hour, minute, second, 0,
                AMSTERDAM_ZONE_ID.getRules().getOffset(OffsetDateTime.now().toLocalDateTime()));
    }

    @Test
    @DisplayName("Given a Monday, When getting opening hours, Then return opening at 9 AM and closing at 7 PM")
    void testOpeningHoursOnMonday() {

        final var dateTime = getDateTime(2023, Month.JANUARY.getValue(), 2, 12, 0, 0);
        final var hours = service.getOpeningHours(dateTime);
        assertThat(hours.getOpenTime()).isNotNull();
        assertThat(hours.getCloseTime()).isNotNull();
        assertThat(hours.getOpenTime().getHour()).isEqualTo(9);
        assertThat(hours.getCloseTime().getHour()).isEqualTo(19);
    }

    @Test
    @DisplayName("Given a Saturday, When getting opening hours, Then return opening at 9 AM and closing at 4 PM")
    void testOpeningHoursOnSaturday() {

        final var dateTime = getDateTime(2023, Month.JANUARY.getValue(), 7, 12, 0, 0);
        final var hours = service.getOpeningHours(dateTime);
        assertThat(hours.getOpenTime()).isNotNull();
        assertThat(hours.getCloseTime()).isNotNull();
        assertThat(hours.getOpenTime().getHour()).isEqualTo(9);
        assertThat(hours.getCloseTime().getHour()).isEqualTo(16);
    }

    @Test
    @DisplayName("Given a Sunday, When getting opening hours, Then return null indicating closed")
    void testClosedOnSunday() {

        final var dateTime = getDateTime(2023, Month.JANUARY.getValue(), 1, 12, 0, 0);
        final var hours = service.getOpeningHours(dateTime);
        assertThat(hours.getOpenTime()).isNull();
        assertThat(hours.getCloseTime()).isNull();
    }
}
