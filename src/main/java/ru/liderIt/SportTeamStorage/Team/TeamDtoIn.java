package ru.liderIt.SportTeamStorage.Team;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamDtoIn {
    Long id;
    @NotNull
    @NotBlank(message = "Ошибка: name пустое или содержит только пробелы")
    String name;
    @NotNull
    @NotBlank(message = "Ошибка: sport пустое или содержит только пробелы")
    String sport;
    String foundationDate;
}