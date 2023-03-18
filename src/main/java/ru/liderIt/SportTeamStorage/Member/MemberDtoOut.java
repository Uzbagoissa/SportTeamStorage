package ru.liderIt.SportTeamStorage.Member;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberDtoOut {
    Long id;
    Long teamId;
    String lastName;
    String firstName;
    String fathersName;
    String bearthDate;
    String position;
}
