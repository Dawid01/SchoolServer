package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplacementRepository extends JpaRepository<Replacement,Long> {


    @Query(value = "SELECT r FROM Replacement r WHERE r.week =:week and r.day =:day and r.className =:className",
            countQuery = "SELECT count(r) FROM Replacement r WHERE r.week =:room and r.day =:day and r.className =:className",
            nativeQuery = false)
    Page<Replacement> findReplacementByClass(@Param("week") String week, @Param("day") String day, @Param("className") String className, Pageable pageable);

    @Query(value = "SELECT r FROM Replacement r WHERE r.week =:week and r.day =:day and r.teacher =:teacher",
            countQuery = "SELECT count(r) FROM Replacement r WHERE r.week =:room and r.day =:day and r.teacher =:teacher",
            nativeQuery = false)
    Page<Replacement> findReplacementByTeacher(@Param("week") String week, @Param("day") String day, @Param("teacher") String teacher, Pageable pageable);
}
