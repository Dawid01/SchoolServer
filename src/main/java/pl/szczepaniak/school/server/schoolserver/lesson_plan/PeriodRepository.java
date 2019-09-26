package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PeriodRepository extends JpaRepository<Period,Long> {

    @Query("SELECT i FROM Period i WHERE LOWER(i.externalID) = LOWER(:externalID)")
    public Period findByexternalID(@Param("externalID") String externalID);
}
