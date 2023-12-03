package nl.multicode.example.openinghours;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import nl.multicode.example.openinghours.map.NormalDayOpeningHoursMapper;
import nl.multicode.example.openinghours.map.SpecialDayOpeningHoursMapper;
import nl.multicode.example.openinghours.model.OpeningHours;
import nl.multicode.example.openinghours.repository.SpecialDaysRepository;

@Builder
@RequiredArgsConstructor
public class OpeningTimeService {

    private static final ZoneId AMSTERDAM_ZONE_ID = ZoneId.of("Europe/Amsterdam");

    private final NormalDayOpeningHoursMapper normalDayOpeningHoursMapper;
    private final SpecialDayOpeningHoursMapper specialDayOpeningHoursMapper;
    private final SpecialDaysRepository specialDaysRepository;


    public OpeningHours getOpeningHours(OffsetDateTime dateTime) {

        if (isSpecialDate(dateTime)) {
            return specialDayOpeningHoursMapper.map(dateTime);
        }
        return normalDayOpeningHoursMapper.map(dateTime);
    }

    private boolean isSpecialDate(final OffsetDateTime dateTime) {

        return specialDaysRepository.getSpecialDayHours(dateTime.toLocalDate()).isPresent();
    }
}
