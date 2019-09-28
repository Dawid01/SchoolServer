package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClassRoomRepository extends JpaRepository<ClassRoom,Long> {

    @Query("SELECT i FROM ClassRoom i WHERE LOWER(i.externalID) = LOWER(:externalID)")
    public ClassRoom findByexternalID(@Param("externalID") String externalID);

}