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
    @NotNull
    @NotBlank(message = "Ошибка: name пустое или содержит только пробелы")
    String name;
    @NotNull
    @NotBlank(message = "Ошибка: sport пустое или содержит только пробелы")
    String sport;
    @NotNull
    @NotBlank(message = "Ошибка: foundationDate пустое или содержит только пробелы")
    String foundationDate;
}