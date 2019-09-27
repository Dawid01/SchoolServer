package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DayRepository extends JpaRepository<Day,Long> {

    @Query("SELECT i FROM Day i WHERE LOWER(i.externalID) = LOWER(:externalID)")
    public Day findByexternalID(@Param("externalID") String externalID);
}
