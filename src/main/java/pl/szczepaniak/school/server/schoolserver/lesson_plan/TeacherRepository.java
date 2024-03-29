package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    @Query("SELECT i FROM Teacher i WHERE LOWER(i.externalID) = LOWER(:externalID)")
    public Teacher findByexternalID(@Param("externalID") String externalID);
}
