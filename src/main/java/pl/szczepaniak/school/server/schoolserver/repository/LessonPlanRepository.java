package pl.szczepaniak.school.server.schoolserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.szczepaniak.school.server.schoolserver.model.LessonPlan;

public interface LessonPlanRepository extends JpaRepository<LessonPlan,Long> {

    @Query("SELECT lp FROM LessonPlan lp WHERE LOWER(lp.name) = LOWER(:name)")
    public LessonPlan findByName(@Param("name") String name);
}
