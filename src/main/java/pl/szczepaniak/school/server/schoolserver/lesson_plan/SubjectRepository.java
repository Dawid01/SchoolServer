package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<Subject,Long> {

    @Query("SELECT i FROM Subject i WHERE LOWER(i.externalID) = LOWER(:externalID)")
    public Subject findByexternalID(@Param("externalID") String externalID);
}

