package nl.multicode.example.openinghours.model;

import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class OpeningHours {

    private final OffsetDateTime openTime;
    private final OffsetDateTime closeTime;


}
