package ru.liderIt.SportTeamStorage.Member;

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
@RequestMapping(path = "/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDtoOut saveMember(@Valid @RequestBody MemberDtoIn memberDtoIn) {
        log.info("Участник команды добавлен");
        return memberService.saveMember(memberDtoIn);
    }

    @GetMapping("/teams/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDtoOut> getAllMembersByTeamId(@PathVariable long teamId,
                                                    @RequestParam(value = "position", required = false) List<String> positions) {
        log.info("Участники команды найдены");
        return memberService.getAllMembersByTeamId(teamId, positions);
    }

    @GetMapping("/noTeams")
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDtoOut> getAllMembersWithoutTeam(@RequestParam(value = "position", required = false) List<String> positions) {
        log.info("Спортсмены без команды найдены");
        return memberService.getAllMembersWithoutTeam(positions);
    }

    @PatchMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDtoOut updateMember(@PathVariable long memberId,
                                     @RequestBody MemberDtoInUpdate memberDtoInUpdate) {
        log.info("Данные участника команды обновлены");
        return memberService.updateMember(memberId, memberDtoInUpdate);
    }

    @DeleteMapping("/{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeMember(@PathVariable long memberId) {
        log.info("Участник команды удален");
        memberService.removeMember(memberId);
    }

    @PatchMapping("/changeTeam/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDtoOut updateTeamOfMember(@PathVariable long memberId,
                                           @RequestParam(value = "newTeamId", required = false) Long newTeamId) {
        log.info("Спортсмен переведен в другую команду");
        return memberService.updateTeamOfMember(memberId, newTeamId);
    }
}
