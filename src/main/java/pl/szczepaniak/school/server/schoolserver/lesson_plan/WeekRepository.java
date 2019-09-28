package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WeekRepository extends JpaRepository<Week,Long> {

    @Query("SELECT i FROM Week i WHERE LOWER(i.externalID) = LOWER(:externalID)")
    public Week findByexternalID(@Param("externalID") String externalID);
}