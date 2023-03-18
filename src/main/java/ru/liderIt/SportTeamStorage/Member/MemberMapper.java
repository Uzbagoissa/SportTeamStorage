package ru.liderIt.SportTeamStorage.Member;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MemberMapper {

    public static MemberDtoOut toMemberDtoOut(Member member) {
        return new MemberDtoOut(
                member.getId(),
                member.getTeamId(),
                member.getLastName(),
                member.getFirstName(),
                member.getFathersName(),
                localDateToString(member.getBearthDate()),
                member.getPosition()
        );
    }

    public static Member toMember(MemberDtoIn memberDtoIn) {
        Member member = new Member();
        member.setTeamId(memberDtoIn.getTeamId());
        member.setLastName(memberDtoIn.getLastName());
        member.setFirstName(memberDtoIn.getFirstName());
        member.setFathersName(memberDtoIn.getFathersName());
        member.setBearthDate(stringToLocalDate(memberDtoIn.getBearthDate()));
        member.setPosition(memberDtoIn.getPosition());
        return member;
    }

    public static List<MemberDtoOut> toListMemberDtoOut(Iterable<Member> members) {
        List<MemberDtoOut> result = new ArrayList<>();
        for (Member member : members) {
            result.add(toMemberDtoOut(member));
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