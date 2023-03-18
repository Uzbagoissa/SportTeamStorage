package ru.liderIt.SportTeamStorage.Team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTeamById(Long id);

    List<Team> findTeamsBySport(String sport);

    @Query(value = "SELECT t.* " +
            "FROM teams AS t " +
            "WHERE t.foundation_date > ?1 AND t.foundation_date < ?2 ", nativeQuery = true)
    List<Team> findTeamsByRange(LocalDate rangeStart, LocalDate rangeEnd);

    @Query(value = "SELECT t.* " +
            "FROM teams AS t " +
            "WHERE t.foundation_date > ?1 AND t.foundation_date < ?2 AND t.sport = ?3 ", nativeQuery = true)
    List<Team> findTeamsByRangeBySport(LocalDate rangeStart, LocalDate rangeEnd, String sport);
}