package ru.liderIt.SportTeamStorage.Team;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TeamService {
    TeamDtoOut saveTeam(TeamDtoIn teamDtoIn);
    List<TeamDtoOut> getAllTeams(List<String> sports, String rangeStart, String rangeEnd);
    TeamDtoOut updateTeam(long teamId, TeamDtoInUpdate teamDtoInUpdate);
    void removeTeam(long teamId);
}