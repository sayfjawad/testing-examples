package nl.multicode.example.openinghours.map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Optional;
import nl.multicode.example.openinghours.repository.SpecialDaysRepository;
import nl.multicode.example.openinghours.util.OpeningHoursCreator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class SpecialDayOpeningHoursMapperTest {

    private final static ZoneId AMSTERDAM_ZONE_ID = ZoneId.of("Europe/Amsterdam");

    private final SpecialDaysRepository specialDaysRepository = Mockito.mock(SpecialDaysRepository.class);
    private final SpecialDayOpeningHoursMapper mapper = new SpecialDayOpeningHoursMapper(specialDaysRepository);

    private static OffsetDateTime getDateTime(int year, int month, int day, int hour, int minute, int second) {

        return OffsetDateTime.of(year, month, day, hour, minute, second, 0,
                AMSTERDAM_ZONE_ID.getRules().getOffset(OffsetDateTime.now().toLocalDateTime()));
    }

    @Test
    void testSpecialDayOpeningHours() {

        final var specialHours = OpeningHoursCreator.create(2024, 2, 14, 9, 0, 16, 0); // Define expected special hours
        final var specialDate = LocalDate.of(2024, 2, 14);
        when(specialDaysRepository.getSpecialDayHours(specialDate))
                .thenReturn(Optional.of(specialHours));

        final var dateTime = getDateTime(2024, 2, 14, 9, 0, 0);
        final var hours = mapper.map(dateTime);
        assertThat(hours).usingRecursiveComparison().isEqualTo(specialHours);
    }
}
