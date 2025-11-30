package com.lucas.screenmatch2.repository;

import com.lucas.screenmatch2.model.Category;
import com.lucas.screenmatch2.model.Episode;
import com.lucas.screenmatch2.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    Optional<Series> findSeriesByTitleContainingIgnoreCase(String title);
    List<Series> findByActorsContainingIgnoreCaseAndRateGreaterThanEqual(String actorName, double rate);
    List<Series> findTop5ByOrderByRateDesc();
    List<Series> findByGenre(Category category);
//    List<Series> findByTotalSeasonsLessThanEqualAndRateGreaterThanEqual(int maxSeasons, double minRate);
    //JPQL
    @Query("SELECT s FROM Series s WHERE s.totalSeasons <= :maxSeasons AND s.rate >= :minRate")
    List<Series> findBySeasonsAndRate(int maxSeasons, double minRate);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE e.title ILIKE %:titleKeyword%")
    List<Episode> findEpisodeByTitleKeyword(String titleKeyword);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :series ORDER BY e.rate DESC LIMIT 5")
    List<Episode> top5Episodes(Series series);

    @Query("SELECT e FROM Series s JOIN s.episodes e WHERE s = :series AND YEAR(e.dateRelease) >= :startYear")
    List<Episode> findEpisodesFromAYear(Series series, int startYear);
}
