package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LessonRepository extends JpaRepository<Lesson,Long> {

    @Query("SELECT i FROM Lesson i WHERE LOWER(i.externalID) = LOWER(:externalID)")
    public Lesson findByexternalID(@Param("externalID") String externalID);
}