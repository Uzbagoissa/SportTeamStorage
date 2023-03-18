package ru.liderIt.SportTeamStorage.Team;

import java.util.List;

public interface TeamService {
    TeamDtoOut saveTeam(TeamDtoIn teamDtoIn);

    List<TeamDtoOut> getAllTeams(List<String> sports, String rangeStart, String rangeEnd);

    TeamDtoOut updateTeam(long teamId, TeamDtoInUpdate teamDtoInUpdate);

    void removeTeam(long teamId);
}