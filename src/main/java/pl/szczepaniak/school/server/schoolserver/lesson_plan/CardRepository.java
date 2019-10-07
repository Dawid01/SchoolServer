package pl.szczepaniak.school.server.schoolserver.lesson_plan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardRepository extends JpaRepository<Card,Long> {

    @Query("SELECT i FROM Card i WHERE LOWER(i.externalID) = LOWER(:externalID)")
    public Card findByexternalID(@Param("externalID") String externalID);

    @Query(value = "SELECT c FROM Card c WHERE c.className =:className and c.day =:day",
            countQuery = "SELECT count(c) FROM Card c WHERE c.className =:className and c.day =:day",
            nativeQuery = false)
    Page<Card> findCards(@Param("className") String className, @Param("day") String day, Pageable pageable);

    @Query(value = "SELECT c FROM Card c WHERE c.teacher =:teacher and c.day =:day",
            countQuery = "SELECT count(c) FROM Card c WHERE c.teacher =:teacher and c.day =:day",
            nativeQuery = false)
    Page<Card> findCardsForTeacher(@Param("teacher") String className, @Param("day") String day, Pageable pageable);

    @Query(value = "SELECT c FROM Card c WHERE c.room =:room and c.day =:day",
            countQuery = "SELECT count(c) FROM Card c WHERE c.room =:room and c.day =:day",
            nativeQuery = false)
    Page<Card> findCardsForRooms(@Param("room") String className, @Param("day") String day, Pageable pageable);
}