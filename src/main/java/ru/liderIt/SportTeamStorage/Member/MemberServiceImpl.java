package ru.liderIt.SportTeamStorage.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.liderIt.SportTeamStorage.Team.Team;
import ru.liderIt.SportTeamStorage.Team.TeamDtoOut;
import ru.liderIt.SportTeamStorage.Team.TeamMapper;
import ru.liderIt.SportTeamStorage.exceptions.IncorrectParameterException;
import ru.liderIt.SportTeamStorage.exceptions.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository repository;

    @Override
    public MemberDtoOut saveMember(MemberDtoIn memberDtoIn) {
        return MemberMapper.toMemberDtoOut(repository.save(MemberMapper.toMember(memberDtoIn)));
    }

    @Override
    public List<MemberDtoOut> getAllMembersByTeamId(Long teamId, List<String> positions) {
        List<Member> members = new ArrayList<>();
        if (positions == null || positions.isEmpty()){
            members.addAll(repository.findMembersByTeamId(teamId));
        } else {
            for (String position : positions) {
                members.addAll(repository.findMembersByTeamIdAndPosition(teamId, position));
            }
        }
        return MemberMapper.toListMemberDtoOut(members).stream()
                .sorted(Comparator.comparing(MemberDtoOut::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberDtoOut> getAllMembersWithoutTeam(List<String> positions) {
        List<Member> members = new ArrayList<>();
        if (positions == null || positions.isEmpty()){
            members.addAll(repository.findMembersByTeamId(null));
        } else {
            for (String position : positions) {
                members.addAll(repository.findMembersByTeamIdAndPosition(null, position));
            }
        }
        return MemberMapper.toListMemberDtoOut(members).stream()
                .sorted(Comparator.comparing(MemberDtoOut::getId))
                .collect(Collectors.toList());
    }

    @Override
    public MemberDtoOut updateMember(long memberId, MemberDtoInUpdate memberDtoInUpdate) {
        memberValid(memberId);
        Member member = repository.getById(memberId);
        member.setTeamId(memberDtoInUpdate.getTeamId() == null ? member.getTeamId() : memberDtoInUpdate.getTeamId());
        member.setLastName(memberDtoInUpdate.getLastName() == null ? member.getLastName() : memberDtoInUpdate.getLastName());
        member.setFirstName(memberDtoInUpdate.getFirstName() == null ? member.getFirstName() : memberDtoInUpdate.getFirstName());
        member.setFathersName(memberDtoInUpdate.getFathersName() == null ? member.getFathersName() : memberDtoInUpdate.getFathersName());
        member.setBearthDate(memberDtoInUpdate.getBearthDate() == null ? member.getBearthDate() : MemberMapper.stringToLocalDate(memberDtoInUpdate.getBearthDate()));
        member.setPosition(memberDtoInUpdate.getPosition() == null ? member.getPosition() : memberDtoInUpdate.getPosition());
        member.setId(memberId);
        return MemberMapper.toMemberDtoOut(repository.save(member));
    }

    @Override
    public void removeMember(long memberId) {
        memberValid(memberId);
        repository.deleteById(memberId);
    }

    @Override
    public MemberDtoOut updateTeamOfMember(long memberId, Long newTeamId) {
        memberValid(memberId);
        Member member = repository.getById(memberId);
        member.setTeamId(newTeamId);
        return MemberMapper.toMemberDtoOut(repository.save(member));
    }

    private void memberValid(long memberId) {
        if (repository.findMemberById(memberId) == null) {
            log.error("Участник с {} не найден", memberId);
            throw new NotFoundException("Участник не найден");
        }
    }
}