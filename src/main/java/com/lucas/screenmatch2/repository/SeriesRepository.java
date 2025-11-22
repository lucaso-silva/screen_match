package com.lucas.screenmatch2.repository;

import com.lucas.screenmatch2.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Series, Long> {
}
