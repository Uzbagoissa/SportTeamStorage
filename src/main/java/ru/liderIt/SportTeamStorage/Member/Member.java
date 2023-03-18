package ru.liderIt.SportTeamStorage.Member;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "members", schema = "public")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "team_id")
    Long teamId;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "fathers_name", nullable = false)
    String fathersName;

    @Column(name = "bearth_date", nullable = false)
    LocalDate bearthDate;

    @Column(name = "position", nullable = false)
    String position;
}
