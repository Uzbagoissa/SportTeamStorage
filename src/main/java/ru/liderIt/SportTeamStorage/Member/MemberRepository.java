package ru.liderIt.SportTeamStorage.Member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findMemberById(Long id);

    List<Member> findMembersByTeamId(Long teamId);

    List<Member> findMembersByTeamIdAndPosition(Long teamId, String position);
}
