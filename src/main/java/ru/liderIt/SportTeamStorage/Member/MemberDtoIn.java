package ru.liderIt.SportTeamStorage.Member;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberDtoIn {
    Long teamId;
    @NotNull
    @NotBlank(message = "Ошибка: lastName пустое или содержит только пробелы")
    String lastName;
    @NotNull
    @NotBlank(message = "Ошибка: firstName пустое или содержит только пробелы")
    String firstName;
    @NotNull
    @NotBlank(message = "Ошибка: fathersName пустое или содержит только пробелы")
    String fathersName;
    @NotNull
    @NotBlank(message = "Ошибка: bearthDate пустое или содержит только пробелы")
    String bearthDate;
    @NotNull
    @NotBlank(message = "Ошибка: position пустое или содержит только пробелы")
    String position;
}