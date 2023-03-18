package ru.liderIt.SportTeamStorage.Team;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamDtoInUpdate {
    Long id;
    String name;
    String sport;
    String foundationDate;
}