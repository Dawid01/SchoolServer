package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<GroupClass,Long> {

    @Query("SELECT i FROM GroupClass i WHERE LOWER(i.externalID) = LOWER(:externalID)")
    public GroupClass findByexternalID(@Param("externalID") String externalID);
}
