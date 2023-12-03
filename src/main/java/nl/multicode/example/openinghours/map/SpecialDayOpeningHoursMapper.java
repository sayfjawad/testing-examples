package nl.multicode.example.openinghours.map;

import java.time.OffsetDateTime;
import nl.multicode.example.openinghours.model.OpeningHours;
import nl.multicode.example.openinghours.repository.SpecialDaysRepository;

public class SpecialDayOpeningHoursMapper implements DayOpeningHoursMapper {

    private final SpecialDaysRepository specialDaysRepository;

    public SpecialDayOpeningHoursMapper(SpecialDaysRepository specialDaysRepository) {

        this.specialDaysRepository = specialDaysRepository;
    }

    @Override
    public OpeningHours map(OffsetDateTime dateTime) {

        return specialDaysRepository.getSpecialDayHours(dateTime.toLocalDate())
                .orElse(OpeningHours.builder().build());
    }
}
