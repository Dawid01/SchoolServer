package pl.szczepaniak.school.server.schoolserver.lesson_plan;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassRepository extends JpaRepository<Class,Long> {

    @Query("SELECT i FROM Class i WHERE LOWER(i.externalID) = LOWER(:externalID)")
    public Class findByexternalID(@Param("externalID") String externalID);
}
