package ru.liderIt.SportTeamStorage.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liderIt.SportTeamStorage.Member.Member;
import ru.liderIt.SportTeamStorage.Member.MemberDtoOut;
import ru.liderIt.SportTeamStorage.Member.MemberRepository;
import ru.liderIt.SportTeamStorage.Member.MemberService;
import ru.liderIt.SportTeamStorage.exceptions.IncorrectParameterException;
import ru.liderIt.SportTeamStorage.exceptions.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;
    private final MemberService memberService;

    @Override
    public TeamDtoOut saveTeam(TeamDtoIn teamDtoIn) {
        return TeamMapper.toTeamDtoOut(repository.save(TeamMapper.toTeam(teamDtoIn)));
    }

    @Override
    public List<TeamDtoOut> getAllTeams(List<String> sports, String rangeStart, String rangeEnd) {
        List<Team> teams = new ArrayList<>();
        if (rangeStart == null && rangeEnd == null){
            if (sports == null || sports.isEmpty()){
                teams.addAll(repository.findAll());
            } else {
                for (String sport : sports) {
                    teams.addAll(repository.findTeamsBySport(sport));
                }
            }
        } else {
            LocalDate start = TeamMapper.stringToLocalDate(rangeStart);
            LocalDate end = TeamMapper.stringToLocalDate(rangeEnd);
            if (start.isAfter(end)) {
                log.error("Границы периода поиска указаны неверно. Начало должно быть раньше конца");
                throw new IncorrectParameterException("Границы периода поиска указаны неверно. Начало должно быть раньше конца");
            }
            if (start.isAfter(LocalDate.now())) {
                log.error("Границы периода поиска указаны неверно. Начало должно быть указано в прошлом");
                throw new IncorrectParameterException("Границы периода поиска указаны неверно. Начало должно быть указано в прошлом");
            }
            if (sports == null || sports.isEmpty()){
                teams.addAll(repository.findTeamsByRange(start, end));
            } else {
                for (String sport : sports) {
                    teams.addAll(repository.findTeamsByRangeBySport(start, end, sport));
                }
            }
        }
        return TeamMapper.toListTeamDtoOut(teams).stream()
                .sorted(Comparator.comparing(TeamDtoOut::getId))
                .collect(Collectors.toList());
    }

    @Override
    public TeamDtoOut updateTeam(long teamId, TeamDtoInUpdate teamDtoInUpdate) {
        teamValid(teamId);
        Team team = repository.getById(teamId);
        team.setName(teamDtoInUpdate.getName() == null ? team.getName() : teamDtoInUpdate.getName());
        team.setSport(teamDtoInUpdate.getSport() == null ? team.getSport() : teamDtoInUpdate.getSport());
        team.setFoundationDate(teamDtoInUpdate.getFoundationDate() == null ? team.getFoundationDate() : TeamMapper.stringToLocalDate(teamDtoInUpdate.getFoundationDate()));
        team.setId(teamId);
        return TeamMapper.toTeamDtoOut(repository.save(team));
    }

    @Override
    public void removeTeam(long teamId) {
        teamValid(teamId);
        List<MemberDtoOut> members = memberService.getAllMembersByTeamId(teamId, null);
        for (MemberDtoOut member : members) {
            memberService.updateTeamOfMember(member.getId(), null);
        }
        repository.deleteById(teamId);
    }

    private void teamValid(long teamId) {
        if (repository.findTeamById(teamId) == null) {
            log.error("Команда с {} не найдена", teamId);
            throw new NotFoundException("Команда не найдена");
        }
    }
}