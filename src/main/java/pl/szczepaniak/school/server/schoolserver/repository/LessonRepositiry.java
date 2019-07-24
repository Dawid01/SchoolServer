package pl.szczepaniak.school.server.schoolserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szczepaniak.school.server.schoolserver.model.Lesson;

public interface LessonRepositiry extends JpaRepository<Lesson,Long> {

    @Query(value = "SELECT l FROM Lesson l WHERE l.lessonPlan.id =:lessonPlanID",
            countQuery = "SELECT count(l) FROM Lesson l WHERE l.lessonPlan.id =:lessonPlanID",
            nativeQuery = false)
    Page<Lesson> findLessonPlanLessons(@Param("lessonPlanID") long lessonPlanID, Pageable pageable);
}
