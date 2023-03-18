package ru.liderIt.SportTeamStorage.Member;


import java.util.List;

public interface MemberService {
    MemberDtoOut saveMember(MemberDtoIn memberDtoIn);

    List<MemberDtoOut> getAllMembersByTeamId(Long teamId, List<String> positions);

    List<MemberDtoOut> getAllMembersWithoutTeam(List<String> positions);

    MemberDtoOut updateMember(long memberId, MemberDtoInUpdate memberDtoInUpdate);

    void removeMember(long memberId);

    MemberDtoOut updateTeamOfMember(long memberId, Long newTeamId);
}