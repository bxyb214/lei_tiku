package com.songzi.repository;

import com.songzi.domain.Examine;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Examine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExamineRepository extends JpaRepository<Examine, Long> {

    @Query(value = "select e.id from Examine e where e.userId = ?1")
    Long findIdByUserId(Long userId);
}