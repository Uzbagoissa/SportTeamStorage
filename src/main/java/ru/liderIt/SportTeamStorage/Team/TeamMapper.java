package ru.liderIt.SportTeamStorage.Team;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TeamMapper {

    public static TeamDtoOut toTeamDtoOut(Team team) {
        return new TeamDtoOut(
                team.getId(),
                team.getName(),
                team.getSport(),
                localDateToString(team.getFoundationDate())
        );
    }

    public static Team toTeam(TeamDtoIn teamDtoIn) {
        Team team = new Team();
        team.setName(teamDtoIn.getName());
        team.setSport(teamDtoIn.getSport());
        team.setFoundationDate(stringToLocalDate(teamDtoIn.getFoundationDate()));
        return team;
    }

    public static Team toTeam(TeamDtoInUpdate teamDtoInUpdate) {
        Team team = new Team();
        team.setId(teamDtoInUpdate.getId());
        team.setName(teamDtoInUpdate.getName());
        team.setSport(teamDtoInUpdate.getSport());
        team.setFoundationDate(stringToLocalDate(teamDtoInUpdate.getFoundationDate()));
        return team;
    }

    public static List<TeamDtoOut> toListTeamDtoOut(Iterable<Team> teams) {
        List<TeamDtoOut> result = new ArrayList<>();
        for (Team team : teams) {
            result.add(toTeamDtoOut(team));
        }
        return result;
    }

    public static LocalDate stringToLocalDate(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(timestamp, formatter);
        return localDate;
    }

    public static String localDateToString(LocalDate timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String localDate = timestamp.format(formatter);
        return localDate;
    }

}