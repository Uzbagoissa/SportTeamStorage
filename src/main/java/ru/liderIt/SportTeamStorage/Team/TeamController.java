package ru.liderIt.SportTeamStorage.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/teams")
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamDtoOut saveTeam(@Valid @RequestBody TeamDtoIn teamDtoIn) {
        log.info("Команда добавлена");
        return teamService.saveTeam(teamDtoIn);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeamDtoOut> getAllTeams(@RequestParam(value = "sports", required = false) List<String> sports,
                                        @RequestParam(value = "rangeStart", required = false) String rangeStart,
                                        @RequestParam(value = "rangeEnd", required = false) String rangeEnd) {
        log.info("Команды найдены");
        return teamService.getAllTeams(sports, rangeStart, rangeEnd);
    }

    @PatchMapping("/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public TeamDtoOut updateTeam(@PathVariable long teamId,
                                 @RequestBody TeamDtoInUpdate teamDtoInUpdate) {
        log.info("Данные команды обновлены");
        return teamService.updateTeam(teamId, teamDtoInUpdate);
    }

    @DeleteMapping("/{teamId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTeam(@PathVariable long teamId) {
        log.info("Команда удалена");
        teamService.removeTeam(teamId);
    }
}
