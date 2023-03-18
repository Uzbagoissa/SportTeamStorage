package ru.liderIt.SportTeamStorage.Team;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamDtoOut {
    Long id;
    String name;
    String sport;
    String foundationDate;
}