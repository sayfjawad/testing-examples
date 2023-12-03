package nl.multicode.example;


import java.time.OffsetDateTime;
import java.time.ZoneId;
import nl.multicode.example.openinghours.OpeningTimeService;
import nl.multicode.example.openinghours.map.NormalDayOpeningHoursMapper;
import nl.multicode.example.openinghours.map.SpecialDayOpeningHoursMapper;
import nl.multicode.example.openinghours.repository.SpecialDaysRepository;

public class Main {

    public static final ZoneId AMSTERDAM_ZONE_ID = ZoneId.of("Europe/Amsterdam");

    public static void main(String[] args) {

        final var normalDayMapper = new NormalDayOpeningHoursMapper();
        final var specialDaysRepository = new SpecialDaysRepository();
        final var specialDayMapper = new SpecialDayOpeningHoursMapper(specialDaysRepository);

        final var service = OpeningTimeService.builder()
                .normalDayOpeningHoursMapper(normalDayMapper)
                .specialDayOpeningHoursMapper(specialDayMapper)
                .specialDaysRepository(specialDaysRepository)
                .build();

        final var dateTime = OffsetDateTime.now(AMSTERDAM_ZONE_ID);

        final var openingHours = service.getOpeningHours(dateTime);

        if (openingHours.getOpenTime() == null || openingHours.getCloseTime() == null) {
            System.out.println("The barber shop is closed on " + dateTime.getDayOfWeek());
        } else {
            System.out.println("Opening Time on " + dateTime.getDayOfWeek() + ": " + openingHours.getOpenTime());
            System.out.println("Closing Time on " + dateTime.getDayOfWeek() + ": " + openingHours.getCloseTime());
        }
    }
}