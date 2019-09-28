package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card,Long> {

    @Query("SELECT i FROM Card i WHERE LOWER(i.externalID) = LOWER(:externalID)")
    public Card findByexternalID(@Param("externalID") String externalID);
}